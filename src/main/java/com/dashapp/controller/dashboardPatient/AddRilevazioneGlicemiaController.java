package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.AddController;
import com.dashapp.model.Rilevazione;

import com.dashapp.services.DataService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;

public class AddRilevazioneGlicemiaController extends AddController {

    private DashboardPatientController parentController;
    @FXML
    private ComboBox<Rilevazione.TipoPasto> pastoBox;
    @FXML
    private ComboBox<Rilevazione.TipoRilevazione> quandoBox;
    @FXML
    private TextField valoreField;
    @FXML
    private TextField orarioField;
    @FXML
    private DatePicker dataField;

    private DataService ds;


    public void initialize() {

        pastoBox.setItems(FXCollections.observableArrayList(Rilevazione.TipoPasto.values()));
        quandoBox.setItems(FXCollections.observableArrayList(Rilevazione.TipoRilevazione.values()));
        ds = new DataService();

    }



    //AGGGIUSTARE, va cambiato il servizio per il db, valore deve essere double, deve ammettere data e ora, POST da errore 500,
    @FXML
    private void aggiungiRilevazione() throws Exception {
        // crea nuovo tipo RilevazioneGlicemia
        Rilevazione.TipoPasto tipoPasto = this.pastoBox.getValue();
        Rilevazione.TipoRilevazione tipoRilevazione = this.quandoBox.getValue();

        Double valore;

        try {
            valore = Double.parseDouble(this.valoreField.getText());

        } catch (NumberFormatException e) {
            System.err.println("Errore: valore glicemia non valido");
            return; // oppure mostra un alert
        }

        LocalTime orario;
        try {
            orario = LocalTime.parse(this.orarioField.getText()); // formato: "HH:mm" o "HH:mm:ss"
        } catch (DateTimeParseException e) {
            System.err.println("Errore: orario non valido");
            return; // oppure mostra un alert per l'utente
        }


        ds.addRilevazionePaziente(valore, tipoRilevazione.toString().toLowerCase(), BoxDashboardControllerPatient.u.getId(), tipoPasto.toString().toLowerCase());
        parentController.FlagRilevazioniLabel.setText("Oggi hai eseguito " + (parentController.countRilevazioni+1) + " rilevazion" + (parentController.countRilevazioni == 1 ? "e" : "i"));

        inviaAvvisoAlMedico(valore, tipoRilevazione);


    }

    public void setParentController(DashboardPatientController controller) {
        this.parentController = controller;
    }

    private void inviaAvvisoAlMedico(Double valore, Rilevazione.TipoRilevazione quando){
        if(quando.toString().equals("PRE")){
            if(valore < 80){

            }
            if(valore > 130){

            }
        }
        else if(quando.toString().equals("POST")){
            if(valore > 180){

            }
        }

        return;
    }


}
