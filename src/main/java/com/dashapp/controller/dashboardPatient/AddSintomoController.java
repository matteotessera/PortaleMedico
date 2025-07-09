package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.model.AddController;
//  com.dashapp.model.AssunzioneFarmaco;           da togliere commento
import com.dashapp.model.Sintomo;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;

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
    private TextArea descrizioneField;

    @FXML
    private ComboBox<String> SintomiBox;

    @FXML
    private Label erroreLabel;

    private DataService ds;

    @FXML
    private void initialize(){
        Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-SemiBold.ttf"), 12);

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

        if (descrizione == null || descrizione.trim().isEmpty()) {
            erroreLabel.setText("Inserire una descrizione");
            erroreLabel.setStyle("-fx-text-fill: red;");
            return;
        }


        ds.addSintomoPaziente(descrizione, BoxDashboardControllerPatient.u.getId());
            erroreLabel.setText("Oggetto inviato con successo");
            erroreLabel.setStyle("-fx-text-fill: green");
    }





    List<String> sintomiDiabeteTipo2 = Arrays.asList(
            "Spossatezza",
            "Nausea",
            "Mal di Testa",
            "Aumento della sete",
            "Minzione frequente",
            "Fame eccessiva",
            "Perdita di peso inspiegabile",
            "Visione offuscata",
            "Lenta guarigione delle ferite",
            "Infezioni frequenti",
            "Formicolio o intorpidimento a mani e piedi",
            "Pelle secca o prurito",
            "Irritabilità",
            "Alito fruttato"
    );







}
