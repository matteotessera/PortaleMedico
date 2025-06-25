package com.dashapp.controller.dashboardPatient;

import com.dashapp.model.Sintomo;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

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
    public TextField titoloTextField;

    @FXML
    public void initialize(){
        sintomo = NavigatorView.getSintomoSelezionato();

        modificaButton.setVisible(true);
        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setVisible(false);
        confermaButton.setManaged(false);
        descrizioneTextArea.setEditable(false);
        titoloTextField.setEditable(false);

        descrizioneTextArea.setStyle( "-fx-background-color: transparent; -fx-font-size: 16px;");
        titoloTextField.setStyle( "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");

        caricaDati();
    }

    public void caricaDati(){
        descrizioneTextArea.setText(sintomo.getDescrizione());
        titoloTextField.setText(sintomo.getDescrizione());
    }


    @FXML
    private void modificaSintomo(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);

        modificaButton.setVisible(false);

        descrizioneTextArea.setEditable(true);
        titoloTextField.setEditable(true);

        String bordoBlu = "-fx-border-color: #0078ff; -fx-border-width: 1;";
        descrizioneTextArea.setStyle(bordoBlu + " -fx-font-size: 16px;");
        titoloTextField.setStyle(bordoBlu + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
    }

    public void annullaModifica(){
        initialize();
    }

    public void InviaModifica() throws Exception {
        DataService ds = new DataService();
        //ds.updateSintomo
        //sintomo = ds.getSintomoById(f.getId());
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
