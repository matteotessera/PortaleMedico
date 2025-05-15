module com.dashapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;

    opens com.dashapp.controller to javafx.fxml;
    opens com.dashapp.model to javafx.fxml;
    opens com.dashapp.view to javafx.fxml;
    opens com.dashapp.config to javafx.fxml;

    exports com.dashapp;
}