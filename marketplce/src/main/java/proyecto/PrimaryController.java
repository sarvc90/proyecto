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
import proyecto.Modelo.si.Utilidades;

import java.io.IOException;
import java.net.Socket;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;

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
    private static final String SERVER_ADDRESS = "localhost"; // Cambiar si es necesario
    private static final int SERVER_PORT = 5000; // Puerto del ServidorMarketplace

    @FXML
    public void initialize() {
        updateTexts();
        initImageView();
        initUserImageView();

        loginButton.setOnAction(e -> {
            String username = usernameField.getText();
            String password = passwordField.getText();
            if (authenticate(username, password)) {
                System.out.println("Inicio de sesión exitoso");
                // Redirigir a la vista de administrador o vendedor
            } else {
                System.out.println("Error en el inicio de sesión");
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
                System.err.println("Error al abrir la ventana de registro: " + ex.getMessage());
            }
        });

        changeLangButton.setOnAction(e -> {
            // Aquí puedes implementar el cambio de idioma si es necesario
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
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
             ObjectInputStream ois = new ObjectInputStream(socket.getInputStream())) {
            
            oos.writeObject("AUTENTICAR");
            oos.writeObject(username);
            oos.writeObject(password);
            
            return (boolean) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.err.println("Error en autenticación: " + e.getMessage());
            return false;
        }
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

