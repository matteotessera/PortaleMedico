package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.Tabelle.Tabelle;
import com.dashapp.model.AssociazioneFarmaco;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Sintomo;
import com.dashapp.model.Terapia;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class VediTerapiaController {

    @FXML
    public TextField farmacoField;
    @FXML
    public TextField assunzioniField;
    @FXML
    public TextField doseField;
    @FXML
    private TextField dataInizio;
    @FXML
    private TextField dataFine;

    private Terapia t;
    private AssociazioneFarmaco [] af;
    private int idTerapia;


    @FXML
    public void initialize() throws Exception {
        idTerapia = NavigatorView.getTerapiaSelezionata().getId();

        caricaDati();
    }

    public void caricaDati() throws Exception {
        disabilitaCampi();

        DataService ds = new DataService();
        t = ds.getTerapiaById(idTerapia);
        af = ds.getAssociazioniFarmaciByTerapia(idTerapia);

        farmacoField.setText(ds.getFarmacoById(af[0].getIdFarmaco()).getNome());
        assunzioniField.setText(Integer.toString(af[0].getNumeroAssunzioni()));
        doseField.setText(Integer.toString(af[0].getDose()));
        dataInizio.setText(t.getDataInizio().toString());
        dataFine.setText(t.getDataFine().toString());
    }

    public void disabilitaCampi(){
        farmacoField.setEditable(false);
        assunzioniField.setEditable(false);
        doseField.setEditable(false);
        dataInizio.setEditable(false);
        dataFine.setEditable(false);
    }


}
