package proyecto.Modelo;


public abstract class Persona {
    protected String nombres;
    protected String apellidos;
    protected String cedula;
    protected String contraseña;

    public Persona(String nombres, String email, String cedula, String contraseña) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.cedula = cedula;
        this.contraseña = contraseña;
    }
    public Persona() {
        this.nombres = "";
        this.apellidos = "";
        this.cedula = "";
        this.contraseña = "";
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
    public String getContraseña(){
        return contraseña;
    }
}