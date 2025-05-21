package com.dashapp.controller.dashboardMedico;


import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;

public class AddFarmaciController implements OverlayPaneAware {

    @FXML
    private TextField nomeFarmacoField;
    @FXML
    private TextField descrizioneFarmacoField;
    @FXML
    private Button registraFarmacoButton;

    private AnchorPane overlayPane;

    @FXML
    private void initialize() {

    }

    @FXML
    private void registraFarmaco() {
        String nome = nomeFarmacoField.getText();
        String descrizione = descrizioneFarmacoField.getText();

        //AGGIUNGERE logica che salva dati nel database
    }

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

}
