package proyecto.Modelo;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Producto {
    private String id;
    private String nombre;
    private String descripcion;
    private String fechaPublicacion;

    // Constructor
// Constructor
public Producto(String id, String nombre, String descripcion, String fechaPublicacion) {
    this.id = id;
    this.nombre = nombre;
    this.descripcion = descripcion;
    this.fechaPublicacion = fechaPublicacion;
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

    public String getFechaPublicacion() {
        return fechaPublicacion;
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

    public void setFechaPublicacion(String fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }

    // Método toString
    @Override
    public String toString() {
        return id + "," + nombre + "," + descripcion + "," + fechaPublicacion;
    }

}
