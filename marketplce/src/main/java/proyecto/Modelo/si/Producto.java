package proyecto.Modelo.si;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class Producto implements Serializable {
    private String id;
    private String nombre;
    private String codigo;
    private String imagen;
    private String categoria;
    private double precio;
    private String estado;
    private LocalDateTime fechaPublicacion;
    private List<Comentario> comentarios;
    private List<Usuario> meGusta;
    private Usuario vendedor;

    public Producto(String id, String nombre, String codigo, String imagen, String categoria, double precio, Usuario vendedor) {
        this.id = id;
        this.nombre = nombre;
        this.codigo = codigo;
        this.imagen = imagen;
        this.categoria = categoria;
        this.precio = precio;
        this.estado = "publicado";
        this.fechaPublicacion = LocalDateTime.now();
        this.comentarios = new ArrayList<>();
        this.meGusta = new ArrayList<>();
        this.vendedor = vendedor;
    }

    public void agregarComentario(Comentario comentario) {
        comentarios.add(comentario);
    }

    public void agregarMeGusta(Usuario usuario) {
        if (!meGusta.contains(usuario)) {
            meGusta.add(usuario);
        }
    }

    // Getters y setters
    public Usuario getVendedor() { return vendedor; }
    public List<Usuario> getMeGusta() { return meGusta; }
    public String getNombre() { return nombre; }
}