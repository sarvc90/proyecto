package proyecto.Modelo.si;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class SistemaMarketplace {
    public List<Usuario> usuarios;
    public Administrador administrador;
    private ExecutorService executorService;
    static final String ARCHIVO_SERIALIZADO = "marketplace_data.xml";

    public SistemaMarketplace() {
        this.usuarios = new ArrayList<>();
        this.administrador = new Administrador("admin1", "Admin", "Principal", "1234567890", "Dirección Admin",
                "adminpass");
        this.executorService = Executors.newFixedThreadPool(5);
    }

    public void iniciarSesion(String cedula, String contrasena) {
        // Implementar lógica de inicio de sesión
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    public Administrador getAdministrador() {
        return administrador;
    }

    public void setAdministrador(Administrador administrador) {
        this.administrador = administrador;
    }

    public void registrarAccion(String tipoUsuario, String accion, String interfaz) {
        LocalDateTime ahora = LocalDateTime.now();
        String logEntry = String.format("[%s] Usuario: %s, Acción: %s, Interfaz: %s%n",
                ahora, tipoUsuario, accion, interfaz);
        // Aquí se implementaría la lógica para guardar en un archivo de log
        System.out.println(logEntry);
    }

    public void guardarDatos() {
        executorService.submit(() -> {
            try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARCHIVO_SERIALIZADO))) {
                oos.writeObject(this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void publicarProducto(Usuario vendedor, Producto producto)
            throws UsuarioNoAutorizadoException, ProductoYaPublicadoException {
        if (!usuarios.contains(vendedor)) {
            throw new UsuarioNoAutorizadoException("El usuario no está autorizado para publicar productos");
        }
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

    public void cargarDatos() {
        executorService.submit(() -> {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARCHIVO_SERIALIZADO))) {
                SistemaMarketplace sistema = (SistemaMarketplace) ois.readObject();
                this.usuarios = sistema.usuarios;
                this.administrador = sistema.administrador;
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        });
    }

    public void exportarEstadisticas(String rutaArchivo) {
        String estadisticas = administrador.generarEstadisticas(usuarios);
        try (PrintWriter writer = new PrintWriter(new FileWriter(rutaArchivo))) {
            writer.println("Reporte de Estadísticas del Marketplace");
            writer.println("Fecha: " + LocalDateTime.now());
            writer.println("Reporte realizado por: " + administrador.getNombre());
            writer.println("\nInformación del reporte:");
            writer.println(estadisticas);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario registrarUsuario(String id, String nombre, String apellido, String cedula, String direccion,
            String contrasena) throws UsuarioYaExisteException {
        if (usuarios.stream().anyMatch(u -> u.getCedula().equals(cedula))) {
            throw new UsuarioYaExisteException("Ya existe un usuario con esta cédula");
        }
        Usuario nuevoUsuario = new Usuario(id, nombre, apellido, cedula, direccion, contrasena);
        usuarios.add(nuevoUsuario);
        return nuevoUsuario;
    }

    // Métodos adicionales para la gestión del sistema
}
