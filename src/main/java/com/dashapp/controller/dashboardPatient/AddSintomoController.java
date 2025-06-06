package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.model.AddController;
//  com.dashapp.model.AssunzioneFarmaco;           da togliere commento
import com.dashapp.model.Sintomo;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class AddSintomoController extends AddController {




    @FXML
    private TextField descrizioneField;

    @FXML
    private ComboBox<String> SintomiBox;

    private DataService ds;

    @FXML
    private void initialize(){

        SintomiBox.getItems().addAll(sintomiDiabeteTipo2);

        // Aggiunge un listener per la selezione
        SintomiBox.valueProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                descrizioneField.setText(newValue); // Esempio: riempire automaticamente il campo descrizione
            }
        });

    }

    @FXML
    private void registraSintomo() throws Exception {
        ds = new DataService();


        String descrizione = descrizioneField.getText();

        ds.addSintomoPaziente(descrizione, BoxDashboardControllerPatient.u.getId());

    }





    List<String> sintomiDiabeteTipo2 = Arrays.asList(
            "Aumento della sete",
            "Minzione frequente",
            "Fame eccessiva",
            "Perdita di peso inspiegabile",
            "Visione offuscata",
            "Affaticamento",
            "Lenta guarigione delle ferite",
            "Infezioni frequenti",
            "Formicolio o intorpidimento a mani e piedi",
            "Pelle secca o prurito",
            "Irritabilità",
            "Alito fruttato"
    );







}
