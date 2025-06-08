package com.dashapp.controller.dashboardPatient.fascicolo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.io.IOException;

public class FascicoloPazienteController {

    @FXML
    private Label Hpatologie;

    @FXML
    private Label Hsintomi;

    @FXML
    private Label Hterapie;

    @FXML
    private StackPane contentPane;

    private String activeStyle = "-fx-font-weight: bold; -fx-background-color: #F8FFBC; -fx-border-width: 1; -fx-font-size: 20px;";

    @FXML
    private void initialize() throws IOException {

        Hsintomi.setStyle("-fx-font-weight: bold; -fx-border-color: #0C0E02; -fx-border-width: 1; ");
        Hterapie.setStyle("-fx-font-weight: bold;  -fx-border-color: #0C0E02; -fx-border-width: 1;");
        Hpatologie.setStyle(activeStyle);
        showPatologie();
    }

    @FXML
    void showPatologie() throws IOException {
        resetLabelStyles();
        Hpatologie.setStyle(activeStyle + "-fx-background-color: #ad343e33;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/Fascicolo/TabPatologie.fxml"));
        Parent showPatologieContent = loader.load();
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showPatologieContent);
    }

    @FXML
    void showSintomi() throws IOException {
        resetLabelStyles();
        Hsintomi.setStyle(activeStyle + "-fx-background-color: #9caf3933;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/Fascicolo/TabSintomi.fxml"));
        Parent showSintomiContent = loader.load();
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showSintomiContent);
    }

    @FXML
    void showTerapie() throws IOException {
        resetLabelStyles();
        Hterapie.setStyle(activeStyle + "-fx-background-color: #f2af2933;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/Fascicolo/TabTerapie.fxml"));
        Parent showTerapieContent = loader.load();

        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showTerapieContent);


    }

    private void resetLabelStyles() {
        Hpatologie.setStyle("");
        Hsintomi.setStyle("");
        Hterapie.setStyle("");
    }

}
