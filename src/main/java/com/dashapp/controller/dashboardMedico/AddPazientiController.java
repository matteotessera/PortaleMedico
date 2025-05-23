package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.AddController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddPazientiController extends AddController {

    private Pane overlayPane;

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
    private void registraPaziente(){

    }

}
