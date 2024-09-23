package proyecto.Modelo.si;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServidorMarketplace {
    private static final int PUERTO = 5000;
    private SistemaMarketplace sistema;
    private static final String ARCHIVO_LOG = "marketplace_log.txt";
    private ExecutorService executorService;

    public ServidorMarketplace() {
        this.sistema = new SistemaMarketplace();
        this.executorService = Executors.newSingleThreadExecutor();
    }

    public void iniciar() {
        try (ServerSocket serverSocket = new ServerSocket(PUERTO)) {
            System.out.println("Servidor iniciado en el puerto " + PUERTO);
            while (true) {
                Socket clienteSocket = serverSocket.accept();
                new Thread(() -> manejarCliente(clienteSocket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    boolean autenticarUsuario(String cedula, String contrasena) {
        // Implementar lógica de autenticación
        return false;
    }

    String generarReporteVendedores(LocalDateTime fecha) {
        // Implementar lógica para generar el reporte de vendedores que han publicado en
        // la fecha dada
        return "Reporte de vendedores para la fecha " + fecha;
    }

    public void registrarAccion(String tipoUsuario, String accion, String interfaz) {
        LocalDateTime ahora = LocalDateTime.now();
        String logEntry = String.format("[%s] Usuario: %s, Acción: %s, Interfaz: %s%n",
                ahora, tipoUsuario, accion, interfaz);
        try (PrintWriter writer = new PrintWriter(new FileWriter(ARCHIVO_LOG, true))) {
            writer.println(logEntry);
        } catch (IOException e) {
            System.err.println("Error al escribir en el archivo de log: " + e.getMessage());
        }
    }

    public void guardarDatos() {
        executorService.submit(() -> {
            try {
                try (ObjectOutputStream oos = new ObjectOutputStream(
                        new FileOutputStream(sistema.ARCHIVO_SERIALIZADO))) {
                    oos.writeObject(sistema);
                }
            } catch (IOException e) {
                try {
                    throw new ErrorPersistenciaException("Error al guardar los datos: " + e.getMessage());
                } catch (ErrorPersistenciaException ex) {
                    // Handle the exception here
                    System.err.println("Error al guardar los datos: " + ex.getMessage());
                }
            }
        });
    }

    public void cargarDatos() {
        executorService.submit(() -> {
            try {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(sistema.ARCHIVO_SERIALIZADO))) {
                    SistemaMarketplace sistema = (SistemaMarketplace) ois.readObject();
                    this.sistema.setUsuarios(sistema.getUsuarios());
                    this.sistema.setAdministrador(sistema.getAdministrador());
                }
            } catch (IOException | ClassNotFoundException e) {
                try {
                    throw new ErrorPersistenciaException("Error al cargar los datos: " + e.getMessage());
                } catch (ErrorPersistenciaException ex) {
                    // Handle the exception here
                    System.err.println("Error al cargar los datos: " + ex.getMessage());
                }
            }
        });
    }

    public Usuario registrarUsuario(String id, String nombre, String apellido, String cedula, String direccion,
            String contrasena) throws UsuarioYaExisteException {
        if (sistema.getUsuarios().stream().anyMatch(u -> u.getCedula().equals(cedula))) {
            throw new UsuarioYaExisteException("Ya existe un usuario con esta cédula");
        }
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, cedula, direccion, contrasena);
        sistema.getUsuarios().add(nuevoUsuario);
        return nuevoUsuario;
    }

    public void publicarProducto(Usuario vendedor, Producto producto)
            throws ProductoYaPublicadoException {
        if (vendedor.getProductos().contains(producto)) {
            throw new ProductoYaPublicadoException("Este producto ya ha sido publicado");
        }
        vendedor.publicarProducto(producto);
    }

    public void comentarProducto(Usuario comentador, Producto producto, String comentario)
            throws UsuarioNoAutorizadoException, ComentarioInvalidoException {
        if (!comentador.getContactos().contains(producto.getVendedor())) {
            throw new UsuarioNoAutorizadoException(
                    "No puedes comentar productos de vendedores que no son tus contactos");
        }
        if (comentario == null || comentario.trim().isEmpty()) {
            throw new ComentarioInvalidoException("El comentario no puede estar vacío");
        }
        comentador.comentarProducto(producto, comentario);
    }

    public void calificarVendedor(Usuario calificador, Usuario vendedor, int calificacion)
            throws UsuarioNoAutorizadoException, CalificacionInvalidaException {
        if (!calificador.getContactos().contains(vendedor)) {
            throw new UsuarioNoAutorizadoException("No puedes calificar a un vendedor que no es tu contacto");
        }
        if (calificacion < 1 || calificacion > 5) {
            throw new CalificacionInvalidaException("La calificación debe estar entre 1 y 5");
        }
        calificador.calificarVendedor(vendedor, calificacion);
    }

    private void manejarCliente(Socket clienteSocket) {
        try (ObjectInputStream ois = new ObjectInputStream(clienteSocket.getInputStream());
                ObjectOutputStream oos = new ObjectOutputStream(clienteSocket.getOutputStream())) {

            String solicitud = (String) ois.readObject();
            switch (solicitud) {
                case "AUTENTICAR":
                    String cedula = (String) ois.readObject();
                    String contrasena = (String) ois.readObject();
                    boolean autenticado = autenticarUsuario(cedula, contrasena);
                    oos.writeObject(autenticado);
                    break;
                case "GUARDAR_DATOS":
                    guardarDatos();
                    oos.writeObject("Datos guardados exitosamente");
                    break;
                case "CARGAR_DATOS":
                    cargarDatos();
                    oos.writeObject("Datos cargados exitosamente");
                    break;
                case "REPORTE_VENDEDORES":
                    LocalDateTime fecha = (LocalDateTime) ois.readObject();
                    String reporte = generarReporteVendedores(fecha);
                    oos.writeObject(reporte);
                    break;
                case "REGISTRAR_USUARIO":
                    try {
                        String id = (String) ois.readObject();
                        String nombre = (String) ois.readObject();
                        String apellido = (String) ois.readObject();
                        String newCedula = (String) ois.readObject();
                        String direccion = (String) ois.readObject();
                        String newContrasena = (String) ois.readObject();
                        Usuario nuevoUsuario = sistema.registrarUsuario(id, nombre, apellido, newCedula, direccion,
                                newContrasena);
                        oos.writeObject("Usuario registrado exitosamente");
                        oos.writeObject(nuevoUsuario);
                    } catch (UsuarioYaExisteException e) {
                        oos.writeObject("Error: " + e.getMessage());
                    }
                    break;
                case "PUBLICAR_PRODUCTO":
                    try {
                        Usuario vendedor = (Usuario) ois.readObject();
                        Producto producto = (Producto) ois.readObject();
                        sistema.publicarProducto(vendedor, producto);
                        oos.writeObject("Producto publicado exitosamente");
                    } catch (ProductoYaPublicadoException e) {
                        oos.writeObject("Error: " + e.getMessage());
                    }
                    break;
                case "COMENTAR_PRODUCTO":
                    try {
                        Usuario comentador = (Usuario) ois.readObject();
                        Producto producto = (Producto) ois.readObject();
                        String comentario = (String) ois.readObject();
                        sistema.comentarProducto(comentador, producto, comentario);
                        oos.writeObject("Comentario agregado exitosamente");
                    } catch (UsuarioNoAutorizadoException | ComentarioInvalidoException e) {
                        oos.writeObject("Error: " + e.getMessage());
                    }
                    break;
                case "CALIFICAR_VENDEDOR":
                    try {
                        Usuario calificador = (Usuario) ois.readObject();
                        Usuario vendedor = (Usuario) ois.readObject();
                        int calificacion = (int) ois.readObject();
                        sistema.calificarVendedor(calificador, vendedor, calificacion);
                        oos.writeObject("Calificación registrada exitosamente");
                    } catch (UsuarioNoAutorizadoException | CalificacionInvalidaException e) {
                        oos.writeObject("Error: " + e.getMessage());
                    }
                    break;
                default:
                    oos.writeObject("Solicitud no reconocida");
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new ServidorMarketplace().iniciar();
    }
}

/*
 * 
 * 
 * // Clase para manejar la geolocalización
 * class ServicioGeolocalizacion {
 * public static String obtenerUbicacion(String direccion) {
 * // Simulación de llamada a API de geolocalización
 * // En una implementación real, aquí se haría una llamada HTTP a un servicio
 * de geolocalización
 * return "Latitud: 40.7128, Longitud: -74.0060"; // Coordenadas de ejemplo
 * }
 * }
 * 
 * // Actualización de la clase Usuario para incluir geolocalización
 * class Usuario extends Persona {
 * // ... (otros atributos y métodos)
 * private String coordenadas;
 * 
 * public Usuario(String id, String nombre, String apellido, String cedula,
 * String direccion, String contrasena) {
 * // ... (inicialización existente)
 * this.coordenadas = ServicioGeolocalizacion.obtenerUbicacion(direccion);
 * }
 * 
 * public String getCoordenadas() {
 * return coordenadas;
 * }
 * 
 * // ... (otros métodos)
 * }
 */
