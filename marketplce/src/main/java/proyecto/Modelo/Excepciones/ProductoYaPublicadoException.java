package proyecto.Modelo.Excepciones;

class ProductoYaPublicadoException extends Exception {
    public ProductoYaPublicadoException(String mensaje) {
        super(mensaje);
    }
}