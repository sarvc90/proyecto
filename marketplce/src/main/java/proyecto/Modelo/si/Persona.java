package proyecto.Modelo.si;

import java.io.Serializable;

// Clase base para Usuario y Administrador
abstract class Persona implements Serializable {
    protected String id;
    protected String nombre;
    protected String apellido;
    protected String cedula;
    protected String direccion;
    protected String contrasena;

    // Constructor, getters y setters
}