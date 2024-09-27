package proyecto.Modelo.Excepciones;

class ComentarioInvalidoException extends Exception {
    public ComentarioInvalidoException(String mensaje) {
        super(mensaje);
    }
}