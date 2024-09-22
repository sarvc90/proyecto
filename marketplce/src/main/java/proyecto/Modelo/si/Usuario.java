package proyecto.Modelo.si;

import java.util.ArrayList;
import java.util.List;


class Usuario extends Persona {
    private List<Usuario> contactos;
    private List<Producto> productos;
    private Muro muro;
    private int reputacion;

    public Usuario(String id, String nombre, String apellido, String cedula, String direccion, String contrasena) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.contrasena = contrasena;
        this.contactos = new ArrayList<>();
        this.productos = new ArrayList<>();
        this.muro = new Muro();
        this.reputacion = 0;
    }

    public boolean login(String cedula, String contrasena) {
        return this.cedula.equals(cedula) && this.contrasena.equals(contrasena);
    }

    public void agregarContacto(Usuario usuario) {
        if (contactos.size() < 10 && !contactos.contains(usuario)) {
            contactos.add(usuario);
            usuario.getContactos().add(this);
        } else {
            throw new RuntimeException("No se puede agregar más contactos o el usuario ya es un contacto");
        }
    }

    public void eliminarContacto(Usuario usuario) {
        contactos.remove(usuario);
        usuario.getContactos().remove(this);
    }

    public void publicarProducto(Producto producto) {
        productos.add(producto);
        muro.agregarPublicacion(producto);
        for (Usuario contacto : contactos) {
            contacto.getMuro().agregarPublicacion(producto);
        }
    }

    public void comentarProducto(Producto producto, String comentario) {
        if (contactos.contains(producto.getVendedor())) {
            Comentario nuevoComentario = new Comentario(this, comentario);
            producto.agregarComentario(nuevoComentario);
        } else {
            throw new RuntimeException("No puedes comentar productos de vendedores que no son tus contactos");
        }
    }

    public void darMeGusta(Producto producto) {
        if (contactos.contains(producto.getVendedor())) {
            producto.agregarMeGusta(this);
        } else {
            throw new RuntimeException("No puedes dar Me Gusta a productos de vendedores que no son tus contactos");
        }
    }

    public void enviarMensaje(Usuario destinatario, String mensaje) {
        if (contactos.contains(destinatario)) {
            // Aquí se implementaría la lógica para enviar el mensaje al servidor
            System.out.println("Mensaje enviado a " + destinatario.getNombre() + ": " + mensaje);
        } else {
            throw new RuntimeException("No puedes enviar mensajes a usuarios que no son tus contactos");
        }
    }

    public void calificarVendedor(Usuario vendedor, int calificacion) {
        if (contactos.contains(vendedor) && calificacion >= 1 && calificacion <= 5) {
            vendedor.actualizarReputacion(calificacion);
        } else {
            throw new RuntimeException("No puedes calificar a este vendedor o la calificación es inválida");
        }
    }

    private void actualizarReputacion(int calificacion) {
        this.reputacion = (this.reputacion + calificacion) / 2;
    }
    
    // Getters y setters
    public List<Usuario> getContactos() { 
        return contactos; 
    }
    public Muro getMuro() { 
        return muro; 
    }
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public String getApellido() { return apellido; }
    public String getCedula() { return cedula; }
    public String getDireccion() { return direccion; }
    public String getContrasena() { return contrasena; }
    public List<Producto> getProductos() { 
        return productos; 
    }

    public void setId(String id) { this.id = id; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public void setCedula(String cedula) { this.cedula = cedula; }
    public void setDireccion(String direccion) { this.direccion = direccion; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }
    public void setContactos(List<Usuario> contactos) { this.contactos = contactos; }
    public void setProductos(List<Producto> productos) { this.productos = productos; }
    public void setMuro(Muro muro) { this.muro = muro; }
    public void setReputacion(int reputacion) { this.reputacion = reputacion; }
}