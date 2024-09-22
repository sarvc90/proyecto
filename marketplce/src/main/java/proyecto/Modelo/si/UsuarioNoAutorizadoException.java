package proyecto.Modelo.si;

class UsuarioNoAutorizadoException extends Exception {
    public UsuarioNoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}
