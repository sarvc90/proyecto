package proyecto;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import proyecto.Modelo.Utilidades;

import java.io.IOException;
import java.util.Locale;
import java.util.logging.Level;

public class PrimaryController {
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView userImageView;
    @FXML
    private Label userLabel;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private Button loginButton;
    @FXML
    private Button registerButton;
    @FXML
    private Button changeLangButton;

    private Utilidades utilidades = Utilidades.getInstance();

    @FXML
    public void initialize() {
        updateTexts();
        initImageView();
        initUserImageView();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticate(username, password)) {
                utilidades.escribirLog(utilidades.getMensaje("login.exito"), Level.INFO);
                // Redirigir a la vista de administrador o vendedor
            } else {
                utilidades.escribirLog(utilidades.getMensaje("login.error"), Level.WARNING);
                // Mostrar mensaje de error
            }
        });

        registerButton.setOnAction(e -> {
            try {
                FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("registro_vendedor.fxml"));
                Parent root = fxmlLoader.load();
                Stage stage = new Stage();
                stage.setTitle("Registrar Vendedor");
                stage.setScene(new Scene(root));
                stage.show();
            } catch (IOException ex) {
                utilidades.escribirLog("Error al abrir la ventana de registro: " + ex.getMessage(), Level.SEVERE);
            }
        });

        changeLangButton.setOnAction(e -> {
            if (utilidades.getLocale().getLanguage().equals("es")) {
                utilidades.setLocale(new Locale("en", "US"));
            } else {
                utilidades.setLocale(new Locale("es", "ES"));
            }
            updateTexts();
        });
    }
    
    @FXML
    public void initUserImageView() {
        Image image = new Image(getClass().getResource("imagenes/usuario.png").toExternalForm());
        userImageView.setImage(image);
    }
    @FXML
    public void initImageView() {
        Image image = new Image(getClass().getResource("imagenes/carrito.png").toExternalForm());
        imageView.setImage(image);
    }
    private boolean authenticate(String username, String password) {
        // Lógica de autenticación
        return "admin".equals(username) && "admin".equals(password);
    }


    private void updateTexts() {
        usernameField.setPromptText(utilidades.getMensaje("login.usuario"));
        passwordField.setPromptText(utilidades.getMensaje("login.contrasena"));
        usernameField.setStyle("-fx-text-fill: #827b7b;");
        passwordField.setStyle("-fx-text-fill: #827b7b;");
        userLabel.setText(utilidades.getMensaje("login.usuario"));
        loginButton.setText(utilidades.getMensaje("login.boton"));
        registerButton.setText(utilidades.getMensaje("login.registrarse"));
        changeLangButton.setText(utilidades.getMensaje("login.cambiarIdioma"));
    }
}