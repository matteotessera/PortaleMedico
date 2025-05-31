package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.AddController;
import com.dashapp.model.Rilevazione;
import com.dashapp.model.RilevazioneGlicemia;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.chrono.ChronoLocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class AddRilevazioneGlicemiaController extends AddController {


    @FXML
    private ComboBox<RilevazioneGlicemia.TipoPasto> pastoBox;
    @FXML
    private ComboBox<RilevazioneGlicemia.TipoRilevazione> quandoBox;
    @FXML
    private TextField valoreField;
    @FXML
    private TextField orarioField;
    @FXML
    private DatePicker dataField;

    private DataService ds;


    public void initialize() {

        pastoBox.setItems(FXCollections.observableArrayList(RilevazioneGlicemia.TipoPasto.values()));
        quandoBox.setItems(FXCollections.observableArrayList(RilevazioneGlicemia.TipoRilevazione.values()));
        ds = new DataService();

    }



    //AGGGIUSTARE, va cambiato il servizio per il db, valore deve essere double, deve ammettere data e ora, POST da errore 500,
    @FXML
    private void aggiungiRilevazione() throws Exception {
        // crea nuovo tipo RilevazioneGlicemia
        RilevazioneGlicemia.TipoPasto tipoPasto = this.pastoBox.getValue();
        RilevazioneGlicemia.TipoRilevazione tipoRilevazione = this.quandoBox.getValue();

        Double valore;
        int valoreInt = 10;
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

        Rilevazione rilevazione = new Rilevazione("10", dataField.getValue().atTime(orario), valoreField.getText(), tipoPasto.toString(), String.valueOf(BoxDashboardControllerPatient.u.getId()));
        System.out.println("valoreInt: " + valoreInt);
        System.out.println("tipo: " + tipoPasto.toString());
        System.out.println("id utentwe: " + BoxDashboardControllerPatient.u.getId());
        ds.addRilevazionePaziente(valoreInt, tipoPasto.toString(), BoxDashboardControllerPatient.u.getId());

    }


}
