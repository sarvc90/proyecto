package proyecto.Modelo.si;

import java.io.Serializable;
import java.time.LocalDateTime;

class Comentario implements Serializable {
    private Usuario usuario;
    private String texto;
    private LocalDateTime fecha;

    public Comentario(Usuario usuario, String texto) {
        this.usuario = usuario;
        this.texto = texto;
        this.fecha = LocalDateTime.now();
    }

    // Getters
}