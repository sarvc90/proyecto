package proyecto.Modelo.Excepciones;

class CalificacionInvalidaException extends Exception {
    public CalificacionInvalidaException(String mensaje) {
        super(mensaje);
    }
}