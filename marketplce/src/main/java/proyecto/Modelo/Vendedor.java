package proyecto.Modelo;

import java.util.List;

public class Vendedor extends Persona {
    private List<Producto> publicaciones;
    private List<Vendedor> redDeContactos;
    private Muro muro;

    // Constructor vacío
    public Vendedor() {
        super();
    }

    // Constructor con parámetros
    public Vendedor(String id, String nombre, String apellido, String cedula, String direccion, String contraseña, List<Producto> publicaciones, List<Vendedor> redDeContactos, Muro muro) {
        super(id, nombre, apellido, cedula, direccion, contraseña);
        this.publicaciones = publicaciones;
        this.redDeContactos = redDeContactos;
        this.muro = muro;
    }

    // Métodos getters y setters
    public List<Producto> getPublicaciones() {
        return publicaciones;
    }

    public void setPublicaciones(List<Producto> publicaciones) {
        this.publicaciones = publicaciones;
    }

    public List<Vendedor> getRedDeContactos() {
        return redDeContactos;
    }

    public void setRedDeContactos(List<Vendedor> redDeContactos) {
        this.redDeContactos = redDeContactos;
    }



    public void crudProducto() {
        // Implementación pendiente
    }
}
