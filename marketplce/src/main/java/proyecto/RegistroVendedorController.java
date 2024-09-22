package proyecto;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import proyecto.Modelo.Utilidades;
import proyecto.Modelo.Vendedor;
import java.util.logging.Level;

import java.util.Collections;
import java.util.List;

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

    @FXML
    public void registrarVendedor() {
        String nombres = nombresField.getText();
        String apellidos = apellidosField.getText();
        String cedula = cedulaField.getText();
        String direccion = direccionField.getText();
        List<String> productos = productosField.getText().isEmpty() ? Collections.emptyList() : List.of(productosField.getText().split(","));
        List<String> redDeContactos = redDeContactosField.getText().isEmpty() ? Collections.emptyList() : List.of(redDeContactosField.getText().split(","));

        Vendedor vendedor = new Vendedor(nombres, apellidos, cedula, direccion, productos, redDeContactos);
        utilidades.guardarVendedorEnArchivo(vendedor);
        utilidades.escribirLog("Vendedor registrado: " + vendedor, Level.INFO);

        // Cerrar la ventana de registro
        Stage stage = (Stage) nombresField.getScene().getWindow();
        stage.close();
    }
}
