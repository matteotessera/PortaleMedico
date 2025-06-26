package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.*;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class VediAssunzioneController {

    private Assunzione assunzione;
    @FXML
    public Button modificaButton;
    @FXML
    public Button annullaButton;
    @FXML
    public Button confermaButton;
    @FXML
    public DatePicker dataArea;
    @FXML
    public TextField orarioField;
    @FXML
    public TextArea terapiaField;
    @FXML
    public TextArea farmacoField;
    @FXML
    public TextField quantitaField;

    @FXML
    public void initialize() throws Exception {
        assunzione = NavigatorView.getAssunzioneSelezionata();

        modificaButton.setVisible(true);
        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setVisible(false);
        confermaButton.setManaged(false);

        terapiaField.setEditable(false);
        farmacoField.setEditable(false);
        quantitaField.setEditable(false);
        dataArea.setEditable(false);
        orarioField.setEditable(false);

        terapiaField.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        farmacoField.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        quantitaField.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");

        dataArea.setStyle( "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        orarioField.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");

        caricaDati();
    }

    public void caricaDati() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        DataService ds = new DataService();
        AssociazioneFarmaco ass = ds.getAssociazioneFarmacoById(assunzione.getIdAssociazioneFarmaco());
        Terapia terapia = ds.getTerapiaById(ass.getIdTerapia());
        Farmaco farmaco = ds.getFarmacoById(ass.getIdFarmaco());

        terapiaField.setText(terapia.toString());
        farmacoField.setText(farmaco.getNome() + ": \n" + farmaco.getDescrizione());
        quantitaField.setText(String.valueOf(ass.getDose()) + "mg");
        dataArea.setValue(assunzione.getData().toLocalDate());
        orarioField.setText(assunzione.getData().toLocalTime().toString());



    }


    @FXML
    private void modificaSintomo(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);
        modificaButton.setVisible(false);

      //  terapiaField.setEditable(true);
    //    farmacoField.setEditable(true);
      //  quantitaField.setEditable(true);

        dataArea.setEditable(true);
        orarioField.setEditable(true);

        String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        //terapiaField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        //farmacoField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        //quantitaField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        dataArea.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        orarioField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
    }

    public void annullaModifica() throws Exception {
        initialize();

    }

    public void InviaModifica() throws Exception {
        DataService ds = new DataService();

        LocalDate data = dataArea.getValue();
        String orarioString = orarioField.getText();

        // Validazione base
        if (data == null || orarioString == null || orarioString.isBlank()) {
            mostraAlert("Errore", "Data o ora non validi.");
            return;
        }

        LocalTime orario;
        try {
            orario = LocalTime.parse(orarioString); // Assumendo formato HH:mm[:ss]
        } catch (DateTimeParseException e) {
            mostraAlert("Formato ora non valido", "Usa il formato HH:mm o HH:mm:ss");
            return;
        }

        LocalDateTime nuovaData = LocalDateTime.of(data, orario);

        ds.updateAssunzione(assunzione.getId(), nuovaData, assunzione.getStato());

        //assunzione = ds.getAssunzioneById(assunzione.getId());


        NavigatorView.setAssunzioneSelezionata(assunzione);

        mostraAlert("Successo", "Assunzione modificata correttamente!");
        initialize();
    }

    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);  // Puoi mettere un header se vuoi
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}
