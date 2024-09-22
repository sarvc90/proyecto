module proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    requires javafx.graphics;
    opens proyecto to javafx.fxml;
    exports proyecto;
}
