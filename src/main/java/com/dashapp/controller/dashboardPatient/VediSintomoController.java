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
    public Button modificaButton;
    @FXML
    public TextArea descrizioneTextArea;
    @FXML
    public Button annullaButton;
    @FXML
    public Button confermaButton;
    @FXML
    public DatePicker dataArea;
    @FXML
    public TextField textField;

    @FXML
    public void initialize(){
        sintomo = NavigatorView.getSintomoSelezionato();

        modificaButton.setVisible(true);
        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setVisible(false);
        confermaButton.setManaged(false);
        descrizioneTextArea.setEditable(false);

        dataArea.setEditable(false);
        dataArea.setMouseTransparent(true);
        dataArea.setFocusTraversable(false);

        textField.setEditable(false);

        descrizioneTextArea.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        dataArea.setStyle( "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        textField.setStyle("-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");

        caricaDati();
    }

    public void caricaDati(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        descrizioneTextArea.setText(sintomo.getDescrizione());
        dataArea.setValue(sintomo.getData().toLocalDate());
        textField.setText(sintomo.getData().toLocalTime().toString());

    }


    @FXML
    private void modificaSintomo(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);

        modificaButton.setVisible(false);

        descrizioneTextArea.setEditable(true);
        dataArea.setEditable(true);
        dataArea.setMouseTransparent(false);
        dataArea.setFocusTraversable(true);
        textField.setEditable(true);

        String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        descrizioneTextArea.setStyle(bordoBlu + " -fx-font-size: 16px;");
        dataArea.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
        textField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
    }

    public void annullaModifica(){
        initialize();

    }

    public void InviaModifica() throws Exception {
        DataService ds = new DataService();
        LocalDate data = dataArea.getValue();
        String orarioString = textField.getText();

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

        ds.updateSintomo(sintomo.getId(), descrizioneTextArea.getText(), sintomo.getIdPaziente(), nuovaData );

        //sintomo = ds.getSintomoById(sintomo.getId());


        NavigatorView.setSintomoSelezionato(sintomo);

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
