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

    private Terapia terapia;

    @FXML
    public Button annullaButton;
    @FXML
    public Button confermaButton;

    @FXML
    public DatePicker dataInizio;
    @FXML
    public DatePicker dataFine;
    @FXML
    public VBox listContainer;
    @FXML
    public TextField nome;



    @FXML
    public void initialize() throws Exception {
        terapia = NavigatorView.getTerapiaSelezionata();


        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setVisible(false);
        confermaButton.setManaged(false);

        dataInizio.setEditable(false);
        dataInizio.setMouseTransparent(true);
        dataInizio.setFocusTraversable(false);

        dataFine.setEditable(false);
        dataFine.setMouseTransparent(true);
        dataFine.setFocusTraversable(false);






        dataFine.setStyle( "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        dataInizio.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");

        caricaDati();
    }

    public void caricaDati() throws Exception {
        DataService ds = new DataService();
        List<AssociazioneFarmaco> ass = List.of(ds.getAssociazioniFarmaciByTerapia(terapia.getId()));

        Tabelle tab = new Tabelle();
        tab.tabellaFarmaciTerapia(ass, "vedi", Color.web("#2BD18D"), listContainer);

        //nome.setText(farmaci.getFirst().getNome());
        //nrAssunzioni.setText(String.valueOf(ass.getFirst().getNumeroAssunzioni()));
        //dose.setText(String.valueOf(ass.getFirst().getDose()));

        dataInizio.setValue(terapia.getDataInizio());
        dataFine.setValue(terapia.getDataFine());

    }




    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);  // Puoi mettere un header se vuoi
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}
