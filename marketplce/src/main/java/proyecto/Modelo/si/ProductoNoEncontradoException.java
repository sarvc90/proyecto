package proyecto.Modelo.si;

class ProductoNoEncontradoException extends Exception {
    public ProductoNoEncontradoException(String mensaje) {
        super(mensaje);
    }
}