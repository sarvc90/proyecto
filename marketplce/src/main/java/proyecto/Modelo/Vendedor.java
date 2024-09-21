package proyecto.Modelo;
import java.util.List;

public class Vendedor extends Persona {
    private String direccion;
    private List<Producto> productos;
    private List<Vendedor> redDeContactos;

    // Constructor
    public Vendedor(String nombres, String apellidos, String cedula, String contraseña,String direccion, List<Producto> productos, List<Vendedor> redDeContactos) {
        super(nombres, apellidos, cedula, contraseña);
        this.direccion = direccion;
        this.productos = productos;
        this.redDeContactos = redDeContactos;
    }

    // Getters
    public String getDireccion() {
        return direccion;
    }

    public List<Producto> getProductos() {
        return productos;
    }

    public List<Vendedor> getRedDeContactos() {
        return redDeContactos;
    }

    // Setters
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setProductos(List<Producto> productos) {
        this.productos = productos;
    }

    public void setRedDeContactos(List<Vendedor> redDeContactos) {
        this.redDeContactos = redDeContactos;
    }
    @Override
    public String toString() {
        return "Vendedor{" +
                "nombres='" + getNombres() + '\'' +
                ", apellidos='" + getApellidos() + '\'' +
                ", cedula='" + getCedula() + '\'' +
                ", direccion='" + direccion + '\'' +
                ", productos=" + productos +
                ", redDeContactos=" + redDeContactos +
                '}';
    }

}