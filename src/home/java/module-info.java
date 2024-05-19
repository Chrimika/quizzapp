module com.example.quizzapp {
    requires javafx.controls;
    requires javafx.fxml;

    requires org.controlsfx.controls;
    requires org.kordamp.bootstrapfx.core;

    opens com.example.quizzapp to javafx.fxml;
    exports com.example.quizzapp;
}