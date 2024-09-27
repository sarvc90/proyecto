package proyecto.Modelo.Excepciones;

class UsuarioYaExisteException extends Exception {
    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}