module proyecto {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires java.logging;
    opens proyecto to javafx.fxml;
    exports proyecto;
}
