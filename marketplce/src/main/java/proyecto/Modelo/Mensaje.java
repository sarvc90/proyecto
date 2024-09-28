package proyecto.Modelo;

import java.time.LocalDateTime;

public class Mensaje {
    private String contenido;
    private Vendedor emisor;
    private LocalDateTime horaDeEnvio;

    // Constructor
    public Mensaje(String contenido, Vendedor emisor) {
        this.contenido = contenido;
        this.emisor = emisor;
        this.horaDeEnvio = LocalDateTime.now(); // Asignamos la hora de envío actual
    }

    // Getters y setters

    // Método toString()
    @Override
    public String toString() {
        return "Mensaje{" +
                "contenido='" + contenido + '\'' +
                ", emisor=" + emisor +
                ", horaDeEnvio=" + horaDeEnvio +
                '}';
    }
}