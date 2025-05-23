package com.dashapp.controller.dashboardMedico;


import com.dashapp.model.AddController;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddFarmaciController extends AddController {

    @FXML
    private TextField nomeFarmacoField;
    @FXML
    private TextField descrizioneFarmacoField;
    @FXML
    private Button registraFarmacoButton;


    @FXML
    private void initialize() {

    }

    @FXML
    private void registraFarmaco() {
        String nome = nomeFarmacoField.getText();
        String descrizione = descrizioneFarmacoField.getText();

        //AGGIUNGERE logica che salva dati nel database
    }

}
