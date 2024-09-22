package proyecto;

import java.util.logging.Level;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto.Modelo.Utilidades;
import proyecto.Modelo.Vendedor;

public class RegistroVendedorController {
    @FXML
    private TextField nombresField;
    @FXML
    private TextField apellidosField;
    @FXML
    private TextField cedulaField;
    @FXML
    private TextField direccionField;
    @FXML
    private TextField productosField;
    @FXML
    private TextField redDeContactosField;

    private Utilidades utilidades = Utilidades.getInstance();
    private List<Vendedor> listaVendedores;

    public RegistroVendedorController() {
        listaVendedores = new ArrayList<>();
    }

    @FXML
    public void registrarVendedor() {
        try {
            String nombres = nombresField.getText();
            String apellidos = apellidosField.getText();
            String cedula = cedulaField.getText();
            String direccion = direccionField.getText();
            List<String> productos = productosField.getText().isEmpty() ? Collections.emptyList() : List.of(productosField.getText().split(","));
            List<String> redDeContactos = redDeContactosField.getText().isEmpty() ? Collections.emptyList() : List.of(redDeContactosField.getText().split(","));

            if (!existeVendedor(nombres)) {
                Vendedor vendedor = new Vendedor(nombres, apellidos, cedula, direccion, productos, redDeContactos);
                listaVendedores.add(vendedor);
                utilidades.guardarVendedorEnArchivo(vendedor);
                utilidades.escribirLog("Vendedor registrado: " + vendedor, Level.INFO);

                // Cerrar la ventana de registro
                Stage stage = (Stage) nombresField.getScene().getWindow();
                stage.close();
            } else {
                utilidades.escribirLog("Ya existe un vendedor con el nombre: " + nombres, Level.WARNING);
            }
        } catch (Exception e) {
            utilidades.escribirLog("Error al registrar vendedor: " + e.getMessage(), Level.SEVERE);
        }
    }

    private boolean existeVendedor(String nombres) {
        for (Vendedor vendedor : listaVendedores) {
            if (vendedor.getNombres().equals(nombres)) {
                return true;
            }
        }
        return false;
    }
}