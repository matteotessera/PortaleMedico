module com.dashapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.google.gson;


    opens com.dashapp.controller to javafx.fxml;
    opens com.dashapp.model to javafx.fxml;
    opens com.dashapp.view to javafx.fxml;
    opens com.dashapp.config to javafx.fxml;

    exports com.dashapp;
    opens com.dashapp.controller.dashboardMedico to javafx.fxml;
    opens com.dashapp.controller.dashboardPatient to javafx.fxml;
}