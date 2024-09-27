package proyecto.Modelo.Excepciones;

class UsuarioNoAutorizadoException extends Exception {
    public UsuarioNoAutorizadoException(String mensaje) {
        super(mensaje);
    }
}