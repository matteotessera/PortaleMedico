package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.Sintomo;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VediSintomoController {

    private Sintomo sintomo;
    @FXML
    private Button modificaButton;
    @FXML
    private TextArea descrizioneTextArea;
    @FXML
    private Button annullaButton;
    @FXML
    private Button confermaButton;
    @FXML
    private DatePicker dataField;
    @FXML
    private Spinner oraField;
    @FXML
    private Spinner minutiField;

    @FXML
    public void initialize(){
        sintomo = NavigatorView.getSintomoSelezionato();

        modificaButton.setVisible(true);
        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setVisible(false);
        confermaButton.setManaged(false);
        descrizioneTextArea.setEditable(false);

        dataField.setEditable(false);
        dataField.setMouseTransparent(true);
        dataField.setFocusTraversable(false);

        oraField.setEditable(false);
        minutiField.setEditable(false);

        //textField.setEditable(false);

        /*descrizioneTextArea.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        dataField.setStyle( "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        textField.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");*/

        caricaDati();
    }

    public void caricaDati(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        descrizioneTextArea.setText(sintomo.getDescrizione());
        dataField.setValue(sintomo.getData().toLocalDate());

        oraField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 0));
        minutiField.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0));

        oraField.getValueFactory().setValue(sintomo.getData().toLocalTime().getHour());
        minutiField.getValueFactory().setValue(sintomo.getData().toLocalTime().getMinute());

    }


    @FXML
    private void modificaSintomo(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);

        modificaButton.setVisible(false);

        descrizioneTextArea.setEditable(true);
        dataField.setEditable(true);
        dataField.setMouseTransparent(false);
        dataField.setFocusTraversable(true);
        oraField.setEditable(true);
        minutiField.setEditable(true);

        /*String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        descrizioneTextArea.setStyle(bordoBlu + " -fx-font-size: 16px;");
        dataField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        textField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");*/
    }

    public void annullaModifica(){
        initialize();
    }

    public boolean checkCampi() {
        LocalDate data = dataField.getValue();
        int ora = (int) oraField.getValue();
        int minuti = (int) minutiField.getValue();

        if (data == null) {
            mostraAlert("Errore", "Devi inserire una data.", Alert.AlertType.ERROR);
            return false;
        }
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

        return true;
    }


    public void InviaModifica() throws Exception {
        DataService ds = new DataService();

        if(checkCampi()) {
            LocalDate data = dataField.getValue();
            LocalTime orario = LocalTime.of((Integer) oraField.getValue(), (Integer) minutiField.getValue());
            LocalDateTime nuovaData = LocalDateTime.of(data, orario);

            ds.updateSintomo(sintomo.getId(), descrizioneTextArea.getText(), sintomo.getIdPaziente(), nuovaData);
            mostraAlert("Successo", "Sintomo modificato correttamente!", Alert.AlertType.INFORMATION);

            sintomo = ds.getSintomoById(sintomo.getId());
            NavigatorView.setSintomoSelezionato(sintomo);
            initialize();
        }
    }

    public void mostraAlert(String titolo, String contenuto, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}
