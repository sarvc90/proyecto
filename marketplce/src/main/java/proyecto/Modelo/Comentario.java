package proyecto.Modelo;

import java.time.LocalDateTime;

public class Comentario {
    private Vendedor autor;
    private LocalDateTime fechaPublicacion;
    private String texto;

    // Constructor
    public Comentario(Vendedor autor, LocalDateTime fechaPublicacion, String texto) {
        this.autor = autor;
        this.fechaPublicacion = fechaPublicacion;
        this.texto = texto;
    }

    // Método toString
    @Override
    public String toString() {
        return "Comentario{" +
                "autor=" + autor +
                ", fechaPublicacion=" + fechaPublicacion +
                ", contenido=" + texto +
                '}';
    }
}