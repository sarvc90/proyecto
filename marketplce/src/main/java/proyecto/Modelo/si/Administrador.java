package proyecto.Modelo.si;

import java.util.Comparator;
import java.util.List;

class Administrador extends Persona {
    public Administrador(String id, String nombre, String apellido, String cedula, String direccion, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.contrasena = contrasena;
    }

    public Usuario crearUsuario(String id, String nombre, String apellido, String cedula, String direccion, String contrasena) {
        return new Usuario(id, nombre, apellido, cedula, direccion, contrasena);
    }

    public void eliminarUsuario(Usuario usuario, List<Usuario> listaUsuarios) {
        listaUsuarios.remove(usuario);
    }
    public String getNombre() { 
        return nombre; 
    }
    public String generarEstadisticas(List<Usuario> usuarios) {
        StringBuilder estadisticas = new StringBuilder();
        estadisticas.append("Estadísticas del sistema:\n");
        
        // Cantidad total de usuarios
        estadisticas.append("Total de usuarios: ").append(usuarios.size()).append("\n");
        
        // Total de productos publicados
        int totalProductos = usuarios.stream()
                .mapToInt(u -> u.getProductos().size())
                .sum();
        estadisticas.append("Total de productos publicados: ").append(totalProductos).append("\n");
        
        // Usuario con más contactos
        Usuario usuarioMasContactos = usuarios.stream()
                .max(Comparator.comparingInt(u -> u.getContactos().size()))
                .orElse(null);
        if (usuarioMasContactos != null) {
            estadisticas.append("Usuario con más contactos: ")
                    .append(usuarioMasContactos.getNombre())
                    .append(" (").append(usuarioMasContactos.getContactos().size()).append(" contactos)\n");
        }
        
        // Producto con más "Me gusta"
        Producto productoMasGustado = usuarios.stream()
                .flatMap(u -> u.getProductos().stream())
                .max(Comparator.comparingInt(p -> p.getMeGusta().size()))
                .orElse(null);
        if (productoMasGustado != null) {
            estadisticas.append("Producto más gustado: ")
                    .append(productoMasGustado.getNombre())
                    .append(" (").append(productoMasGustado.getMeGusta().size()).append(" Me gusta)\n");
        }
        
        return estadisticas.toString();
    }
}






