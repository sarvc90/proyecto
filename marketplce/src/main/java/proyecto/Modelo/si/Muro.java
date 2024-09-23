package proyecto.Modelo.si;

import java.util.ArrayList;
import java.util.List;

public class Muro {
    private List<Producto> publicaciones;

    public Muro() {
        this.publicaciones = new ArrayList<>();
    }


    public void agregarPublicacion(Producto producto) {
        publicaciones.add(producto);
    }
    // Otros métodos de la clase Muro
}