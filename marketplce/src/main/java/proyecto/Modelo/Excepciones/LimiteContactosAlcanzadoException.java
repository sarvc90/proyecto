package proyecto.Modelo.Excepciones;

class LimiteContactosAlcanzadoException extends Exception {
    public LimiteContactosAlcanzadoException(String mensaje) {
        super(mensaje);
    }
}