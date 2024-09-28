package proyecto.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private LocalDateTime fechaPublicacion;
    private File imagen;
    private int precio;
    private int meGustas;
    private List<Comentario> comentarios; 


    // Constructor
    public Producto(String id, String nombre, String descripcion, String fechaPublicacion, File imagen, int precio, int meGustas) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.fechaPublicacion = LocalDateTime.parse(fechaPublicacion);
        this.imagen = imagen;
        this.precio = precio;
        this.meGustas = meGustas;
        this.comentarios = new ArrayList<>();
    }

    // Métodos get
    public String getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public LocalDateTime getFechaPublicacion() {
        return fechaPublicacion;
    }

    public File getImagen() {
        return imagen;
    }

    public void setImagen(File imagen) {
        this.imagen = imagen;
    }

    // Métodos set
    public void setId(String id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void agregarComentario(){
        //Lógica para agregar comentario
    }

    public void darMeGusto(){

    }

    public void comprar(){
        
    }

    // Método toString
    @Override
    public String toString() {
        return id + "," + nombre + "," + descripcion + "," + fechaPublicacion;
    }

}
