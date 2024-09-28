package proyecto.Modelo;

import java.util.ArrayList;
import java.util.List;

public class Chat {
    private List<Mensaje> mensajes;
    private Vendedor vendedor1;
    private Vendedor vendedor2;

    // Constructor
    public Chat(Vendedor vendedor1, Vendedor vendedor2) {
        this.vendedor1 = vendedor1;
        this.vendedor2 = vendedor2;
        this.mensajes = new ArrayList<>();
    }

    // Método CRUD para mensajes
    public void crudMensajes() {
    }
}