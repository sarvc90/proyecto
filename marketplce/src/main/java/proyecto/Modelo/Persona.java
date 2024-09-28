package proyecto.Modelo;

public class Persona {
    private String id;
    private String nombre;
    private String apellido;
    private String cedula;
    private String direccion;
    private String contraseña;

    // Constructor vacío
    public Persona() {
    }

    // Constructor con parámetros
    public Persona(String id, String nombre, String apellido, String cedula, String direccion, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cedula = cedula;
        this.direccion = direccion;
        this.contraseña = contraseña;
    }

    // Métodos getters y setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    // Métodos vacíos
    public void metodo1() {
        // Implementación pendiente
    }

    public void metodo2() {
        // Implementación pendiente
    }

    public void metodo3() {
        // Implementación pendiente
    }
}
