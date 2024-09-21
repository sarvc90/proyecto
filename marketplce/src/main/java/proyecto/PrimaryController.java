package proyecto;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class PrimaryController {
    @FXML
    private ImageView imageView;
    @FXML
    private ImageView userImageView;
    
    public void initialize() {
        initImageView();
        initUserImageView();
    }

    @FXML
    public void initImageView() {
        Image image = new Image(getClass().getResource("imagenes/carrito.png").toExternalForm());
        imageView.setImage(image);
    }
    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondary");
    }

    @FXML
    public void initUserImageView() {
        Image image = new Image(getClass().getResource("imagenes/usuario.png").toExternalForm());
        userImageView.setImage(image);
    }
}
