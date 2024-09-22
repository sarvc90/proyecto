package proyecto.Modelo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Vendedor implements Serializable {
    private String nombres;
    private String apellidos;
    private String cedula;
    private String direccion;
    private List<String> productos;
    private List<String> redDeContactos;

    public Vendedor(String nombres, String apellidos, String cedula, String direccion, List<String> productos, List<String> redDeContactos) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.direccion = direccion;
        this.productos = new ArrayList<>(productos); // Create a new list to avoid referencing the same list
        this.redDeContactos = new ArrayList<>(redDeContactos); // Create a new list to avoid referencing the same list
    }

    public Vendedor() {
        // No-op constructor for XML serialization
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public String getCedula() {
        return cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public List<String> getProductos() {
        return productos;
    }

    public List<String> getRedDeContactos() {
        return redDeContactos;
    }

    @Override
    public String toString() {
        return "Vendedor{" +
                "nombres='" + nombres + '\'' +
                ", apellidos='" + apellidos + '\'' +
                ", cedula='" + cedula + '\'' +
                ", direccion='" + direccion + '\'' +
                ", productos=" + productos +
                ", redDeContactos=" + redDeContactos +
                '}';
    }
}
