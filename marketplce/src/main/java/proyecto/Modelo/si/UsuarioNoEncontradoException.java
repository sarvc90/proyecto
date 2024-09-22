package proyecto.Modelo.si;

// Excepciones personalizadas
class UsuarioNoEncontradoException extends Exception {
    public UsuarioNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}