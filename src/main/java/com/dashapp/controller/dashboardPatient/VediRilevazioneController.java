package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.Rilevazione;
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

public class VediRilevazioneController {

    private Rilevazione rilevazione;
    @FXML
    public Button modificaButton;
    @FXML
    public TextArea valoreField;
    @FXML
    public ComboBox<String> relazioneField;
    @FXML
    public ComboBox<String> pastoField;
    @FXML
    public Button annullaButton;
    @FXML
    public Button confermaButton;
    @FXML
    public DatePicker dataArea;
    @FXML
    public TextField orarioField;

    @FXML
    public void initialize(){
        rilevazione = NavigatorView.getRilevazioneSelezionata();

        modificaButton.setVisible(true);
        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setVisible(false);
        confermaButton.setManaged(false);
        valoreField.setEditable(false);
        pastoField.setEditable(false);
        relazioneField.setEditable(false);
        dataArea.setEditable(false);
        orarioField.setEditable(false);

        valoreField.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        relazioneField.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        pastoField.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        dataArea.setStyle( "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        orarioField.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");

        pastoField.setItems(FXCollections.observableArrayList(
                Arrays.stream(Rilevazione.TipoPasto.values())
                        .map(e -> e.name().toLowerCase())
                        .toList()
        ));

        relazioneField.setItems(FXCollections.observableArrayList(
                Arrays.stream(Rilevazione.TipoRilevazione.values())
                        .map(e -> e.name().toLowerCase())
                        .toList()
        ));

        caricaDati();
    }

    public void caricaDati(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        valoreField.setText(rilevazione.getValore());
        relazioneField.setValue(rilevazione.getTipo());
        pastoField.setValue(rilevazione.getPasto());
        dataArea.setValue(rilevazione.getData().toLocalDate());
        orarioField.setText(rilevazione.getData().toLocalTime().toString());

        System.out.println(relazioneField.getValue().toString());

    }


    @FXML
    private void modificaSintomo(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);

        modificaButton.setVisible(false);

        valoreField.setEditable(true);
        relazioneField.setEditable(true);
        pastoField.setEditable(true);
        dataArea.setEditable(true);
        orarioField.setEditable(true);

        String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        valoreField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        relazioneField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        pastoField.setStyle(bordoBlu + " -fx-font-size: 16px;");
        dataArea.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        orarioField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
    }

    public void annullaModifica(){
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

        ds.updateRilevazione(rilevazione.getId(), Double.parseDouble(valoreField.getText()), relazioneField.getValue(), rilevazione.getIdPaziente(),
                nuovaData, pastoField.getValue());

        //rilevazione = ds.getRilevazioneById(rilevazione.getId());


        NavigatorView.setRilevaizoneSelezionata(rilevazione);

        mostraAlert("Successo", "Farmaco modificato correttamente!");
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
