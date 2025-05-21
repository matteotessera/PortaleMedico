package com.dashapp.controller.dashboardMedico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddPazientiController implements OverlayPaneAware {

    private AnchorPane overlayPane;

    @FXML
    private TextField nomePazienteField;
    @FXML
    private TextField cognomePazienteField;
    @FXML
    private DatePicker dataPazienteField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField codiceFiscaleField;
    @FXML
    private TextField viaField;
    @FXML
    private TextField comuneField;
    @FXML
    private TextField provinciaField;
    @FXML
    private TextField nazioneField;


    @FXML
    private Button registraPazienteButton;

    @FXML
    private void exit(javafx.event.ActionEvent event) {
        // Nasconde il nodo genitore (la finestra)
        if(overlayPane!= null) {
            overlayPane.setVisible(false);
            overlayPane.getChildren().clear();
            overlayPane.setPrefWidth(0);
            overlayPane.setPrefHeight(0);
        }
    }


    public void setOverlayPane(AnchorPane pane){
        this.overlayPane = pane;
    }

    @FXML
    private void registraPaziente(){

    }

}
