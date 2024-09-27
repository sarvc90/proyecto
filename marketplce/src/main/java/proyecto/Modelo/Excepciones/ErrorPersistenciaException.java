package proyecto.Modelo.Excepciones;

public class ErrorPersistenciaException extends Exception {
    public ErrorPersistenciaException(String message) {
        super(message);
    }
}