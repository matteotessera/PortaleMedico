package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.model.AddController;
//  com.dashapp.model.AssunzioneFarmaco;           da togliere commento
import com.dashapp.model.Sintomo;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Date;

public class AddSintomoController extends AddController {



    @FXML
    private DatePicker dataField;
    @FXML
    private TextField oraField;
    @FXML
    private TextField descrizioneField;



    @FXML
    private void registraSintomo(){

        LocalDate data = dataField.getValue();

        LocalTime ora;
        try {
            ora = LocalTime.parse(this.oraField.getText()); // formato: "HH:mm" o "HH:mm:ss"
        } catch (DateTimeParseException e) {
            System.err.println("Errore: orario non valido");
            return; // oppure mostra un alert per l'utente
        }

        String descrizione = descrizioneField.getText();

        //DA SISTEMARE
        //Sintomo sintomo = new Sintomo("1", date_time, descrizione, "1");
        //System.out.println(sintomo.getData());
    }





}
