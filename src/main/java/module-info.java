module com.dashapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires org.kordamp.bootstrapfx.core;
    requires java.sql;
    requires com.google.gson;
    requires java.net.http;
    requires java.desktop;
    requires org.apache.pdfbox;

    opens com.dashapp.controller to javafx.fxml;
    opens com.dashapp.model to javafx.fxml, com.google.gson;  // <-- qui apri anche a Gson
    opens com.dashapp.view to javafx.fxml;
    //opens com.dashapp.config to javafx.fxml;

    exports com.dashapp;
    opens com.dashapp.controller.dashboardMedico to javafx.fxml;
    opens com.dashapp.controller.dashboardPatient to javafx.fxml;
    opens com.dashapp.controller.dashboardPatient.fascicolo to javafx.fxml;
    opens com.dashapp.controller.dashboardAdmin to javafx.fxml;

}