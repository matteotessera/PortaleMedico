package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.Rilevazione;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

public class VediRilevazioneController {

    private Rilevazione rilevazione;
    @FXML
    private Button modificaButton;
    @FXML
    private TextField valoreField;
    @FXML
    private ComboBox<String> relazioneField;
    @FXML
    private ComboBox<String> pastoField;
    @FXML
    private Button annullaButton;
    @FXML
    private Button confermaButton;
    @FXML
    private Spinner oraField;
    @FXML
    private Spinner minutiField;
    @FXML
    private DatePicker dateField;

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

        dateField.setEditable(false);
        dateField.setMouseTransparent(true);
        dateField.setFocusTraversable(false);

        oraField.setEditable(false);
        minutiField.setEditable(false);


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
        dateField.setValue(rilevazione.getData().toLocalDate());
        oraField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutiField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        oraField.getValueFactory().setValue(rilevazione.getData().toLocalTime().getHour());
        minutiField.getValueFactory().setValue(rilevazione.getData().toLocalTime().getMinute());

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
        dateField.setEditable(true);
        dateField.setMouseTransparent(false);
        dateField.setFocusTraversable(true);
        oraField.setEditable(true);
        minutiField.setEditable(true);

        /*String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        relazioneField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");
        pastoField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");
        dateField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");
        oraField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");
        minutiField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; ");*/
    }

    public void annullaModifica(){
        initialize();

    }

    public boolean checkCampi() {
        LocalDate data = dateField.getValue();
        int ora = (int) oraField.getValue();
        int minuti = (int) minutiField.getValue();
        String valore = valoreField.getText();

        if (data.isAfter(LocalDate.now())) {
            mostraAlert("Errore", "La data non può essere nel futuro", Alert.AlertType.ERROR);
            return false;
        }

        if (ora < 0 || ora > 23) {
            mostraAlert("Errore", "L'ora ha un valore errato (deve essere tra 0 e 23)", Alert.AlertType.ERROR);
            return false;
        }
        if (minuti < 0 || minuti > 59) {
            mostraAlert("Errore", "I minuti hanno un valore errato (deve essere tra 0 e 59)", Alert.AlertType.ERROR);
            return false;
        }

        if (!valore.matches("\\d+")) {
            mostraAlert("Errore", "Il valore non può contenere caratteri", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    public void InviaModifica() throws Exception {
        DataService ds = new DataService();

        if(checkCampi()) {
            LocalDate data = dateField.getValue();
            LocalTime orario = LocalTime.of((Integer) oraField.getValue(), (Integer) minutiField.getValue());
            LocalDateTime nuovaData = LocalDateTime.of(data, orario);

            ds.updateRilevazione(rilevazione.getId(), Double.parseDouble(valoreField.getText()), relazioneField.getValue(), rilevazione.getIdPaziente(),
                    nuovaData, pastoField.getValue());

            mostraAlert("Successo", "Rilevazione modificata correttamente!", Alert.AlertType.INFORMATION);

            rilevazione = ds.getRilevazioneById(rilevazione.getId());
            NavigatorView.setRilevaizoneSelezionata(rilevazione);
            initialize();
        }
    }

    public void mostraAlert(String titolo, String contenuto, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);  // Puoi mettere un header se vuoi
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}
