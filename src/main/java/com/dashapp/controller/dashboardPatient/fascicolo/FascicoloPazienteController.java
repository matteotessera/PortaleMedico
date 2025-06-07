package com.dashapp.controller.dashboardPatient.fascicolo;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class FascicoloPazienteController {

    @FXML
    private Label Hpatologie;

    @FXML
    private Label Hsintomi;

    @FXML
    private Label Hterapie;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private void initialize() throws IOException {
        showPatologie();
    }

    @FXML
    void showPatologie() throws IOException {
        Hpatologie.setStyle("-fx-font-weight: bold; -fx-background-color: #ffd448; -fx-border-color: #987505; -fx-border-width: 1;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/Fascicolo/TabPatologie.fxml"));
        Parent showPatologieContent = loader.load();
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showPatologieContent);
    }

    @FXML
    void showSintomi() {

    }

    @FXML
    void showTerapie() {

    }

}
