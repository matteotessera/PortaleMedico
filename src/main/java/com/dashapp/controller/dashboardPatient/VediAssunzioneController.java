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
    public TextArea farmacoAssuntoBox;

    @FXML
    public TextArea terapiaIdBox;

    @FXML
    public DatePicker dataField;

    @FXML
    public Spinner oraField;

    @FXML
    public Spinner minutiField;

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

        dataField.setDisable(true);
        oraField.setDisable(true);
        minutiField.setDisable(true);


        terapiaIdBox.setStyle( "-fx-background-color: white;" + "-fx-control-inner-background: transparent;" + "-fx-border-color: transparent;" + "-fx-font-size: 16px;" + "-fx-text-fill: black;" + "-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;" + "-fx-highlight-fill: transparent;" + "-fx-highlight-text-fill: black;" + "-fx-background-insets: 0;" );
        farmacoAssuntoBox.setStyle( "-fx-background-color: white;" + "-fx-control-inner-background: transparent;" + "-fx-border-color: transparent;" + "-fx-font-size: 16px;" + "-fx-text-fill: black;" + "-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;" + "-fx-highlight-fill: transparent;" + "-fx-highlight-text-fill: black;" + "-fx-background-insets: 0;" );
        quantitaField.setStyle( "-fx-background-color: transparent;" + "-fx-control-inner-background: transparent;" + "-fx-border-color: transparent;" + "-fx-font-size: 16px;" + "-fx-text-fill: black;" + "-fx-focus-color: transparent;" + "-fx-faint-focus-color: transparent;" );

        dataField.setStyle("-fx-background-color: transparent; -fx-font-size: 16px; ");
        oraField.setStyle( "-fx-background-color: transparent;  -fx-font-size: 16px;");
        minutiField.setStyle("-fx-background-color: transparent; -fx-font-size: 16px;");

        caricaDati();
    }

    public void caricaDati() throws Exception {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        oraField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutiField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        DataService ds = new DataService();
        AssociazioneFarmaco ass = ds.getAssociazioneFarmacoById(assunzione.getIdAssociazioneFarmaco());
        Terapia terapia = ds.getTerapiaById(ass.getIdTerapia());
        Farmaco farmaco = ds.getFarmacoById(ass.getIdFarmaco());

        String textTerapia =
                "Terapia: " + terapia.getId() + "\n" +
                        "Data: " + terapia.getDataInizio() + " - " + terapia.getDataFine() + "\n" +
                        "Descrizione: " + terapia.getNote();

        terapiaIdBox.setText(textTerapia);

        String textFarmaco =
                "Nome: " +farmaco.getNome()+ "\n" +
                        "Descrizione: " + farmaco.getDescrizione();

        farmacoAssuntoBox.setText(textFarmaco);
        quantitaField.setText(String.valueOf(ass.getDose()) + "mg");
        dataField.setValue(assunzione.getData().toLocalDate());
        oraField.getValueFactory().setValue(assunzione.getData().toLocalTime().getHour());
        minutiField.getValueFactory().setValue(assunzione.getData().toLocalTime().getMinute());
    }

    @FXML
    private void modificaAssunzione(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);
        modificaButton.setVisible(false);

      //  terapiaField.setEditable(true);
    //    farmacoField.setEditable(true);
      //  quantitaField.setEditable(true);

        dataField.setDisable(false);
        oraField.setDisable(false);
        minutiField.setDisable(false);


        String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        //terapiaField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        //farmacoField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        //quantitaField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        dataField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");
        oraField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");
        minutiField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold;");

    }

    public void annullaModifica() throws Exception {
        initialize();

    }

    public void inviaModifica() throws Exception {
        DataService ds = new DataService();

        LocalDate data = dataField.getValue();
        String orarioString = oraField.getValue()+":"+minutiField.getValue();

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

        if (nuovaData.equals(assunzione.getData())) {
            annullaModifica();
            return;
        }

        ds.updateAssunzione(assunzione.getId(), nuovaData, assunzione.getStato());

        assunzione = ds.getAssunzioneById(assunzione.getId());


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
