package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.AddController;
import com.dashapp.model.RilevazioneGlicemia;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
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


    public void initialize() {

        pastoBox.setItems(FXCollections.observableArrayList(RilevazioneGlicemia.TipoPasto.values()));
        quandoBox.setItems(FXCollections.observableArrayList(RilevazioneGlicemia.TipoRilevazione.values()));

    }

    @FXML
    private void registraRilevazione() {
        // crea nuovo tipo RilevazioneGlicemia
        RilevazioneGlicemia.TipoPasto tipoPasto = this.pastoBox.getValue();
        RilevazioneGlicemia.TipoRilevazione tipoRilevazione = this.quandoBox.getValue();

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

        RilevazioneGlicemia Rilevazione = new  RilevazioneGlicemia("1", LocalDate.now(), orario, valore, tipoRilevazione, tipoPasto, "1");
        System.out.println(Rilevazione.getOra());      //FUNZIONA!!
    }


}
