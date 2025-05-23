package com.dashapp.controller.dashboardMedico;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddTerapiaController implements OverlayPaneAware {

    private Pane overlayPane;

    @FXML
    private DatePicker inizioField;
    @FXML
    private DatePicker fineField;
    @FXML
    private TextField nomeFarmacoField;
    @FXML
    private TextField nrAssunzioniField;
    @FXML
    private TextField doseGiornalieraField;
    @FXML
    private TextField indicazioniFarmacoField;
    @FXML
    private TextField noteTerapiaField;
    @FXML
    private Button registraTerapiaButton;

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

    public void setOverlayPane(Pane pane){
        this.overlayPane = pane;
    }

    @FXML
    private void registraTerapia(){

    }








}
