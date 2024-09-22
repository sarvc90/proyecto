package proyecto.Modelo.si;

class UsuarioYaExisteException extends Exception {
    public UsuarioYaExisteException(String mensaje) {
        super(mensaje);
    }
}
