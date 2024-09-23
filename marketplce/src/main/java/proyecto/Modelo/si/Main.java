package proyecto.Modelo.si;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        ServidorMarketplace servidor = new ServidorMarketplace();
        servidor.iniciar();

        // Pruebas
        System.out.println("Probando autenticación...");
        boolean autenticado = servidor.autenticarUsuario("12345678", "contrasena");
        System.out.println("Autenticado: " + autenticado);

        System.out.println("Probando registro de usuario...");
        try {
            Usuario nuevoUsuario = servidor.registrarUsuario("12345679", "nuevo_usuario", "apellido", "cedula", "direccion", "contrasena"); 
            System.out.println("Registrado: " + nuevoUsuario);
        } catch (UsuarioYaExisteException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Probando publicación de producto...");
        try {
            Usuario vendedor = new Usuario("12345678", "nombre", "apellido", "cedula", "direccion", "contrasena");
            Producto producto = new Producto("ID", "Producto 1", "codigo", "imagen", "categoria", 10.0, vendedor);
            servidor.publicarProducto(vendedor, producto);
            System.out.println("Producto publicado");
        } catch (ProductoYaPublicadoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Probando comentario de producto...");
        try {
            Usuario comprador = new Usuario("12345679", "nombre", "apellido", "cedula","direccion", "contrasena");
            Producto producto = new Producto("ID", "Producto 1", "codigo", "imagen", "categoria", 10.0, null);
            servidor.comentarProducto(comprador, producto, "Comentario");
            System.out.println("Comentario agregado");
        } catch (UsuarioNoAutorizadoException | ComentarioInvalidoException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Probando calificación de vendedor...");
        try {
            Usuario comprador = new Usuario("12345679", "nombre", "apellido", "cedula", "direccion", "contrasena");
            Usuario vendedor = new Usuario("12345678", "nombre", "apellido", "cedula","direccion", "contrasena");
            servidor.calificarVendedor(comprador, vendedor, 4);
            System.out.println("Calificación registrada");
        } catch (UsuarioNoAutorizadoException | CalificacionInvalidaException e) {
            System.out.println("Error: " + e.getMessage());
        }

        System.out.println("Probando reporte de vendedores...");
        String reporte = servidor.generarReporteVendedores(LocalDateTime.now());
        System.out.println("Reporte: " + reporte);

        System.out.println("Probando guardar datos...");
        servidor.guardarDatos();
        System.out.println("Datos guardados");

        System.out.println("Probando cargar datos...");
        servidor.cargarDatos();
        System.out.println("Datos cargados");
    }
}