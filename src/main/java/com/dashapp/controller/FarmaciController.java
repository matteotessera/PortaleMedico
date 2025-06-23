package com.dashapp.controller;

import com.dashapp.model.Farmaco;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;

public class FarmaciController {
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

    private Farmaco f;


    public void initialize(){
        f = NavigatorView.getFarmacoSelezionato();
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
        descrizioneTextArea.setText(f.getDescrizione());
        titoloTextField.setText(f.getNome());
    }

    public void modificaFarmaco(){
        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);

        modificaButton.setVisible(false);

        descrizioneTextArea.setEditable(true);
        titoloTextField.setEditable(true);

        String bordoViola = "-fx-border-color: #cb6ce6; -fx-border-width: 1;";
        descrizioneTextArea.setStyle(bordoViola + " -fx-font-size: 16px;");
        titoloTextField.setStyle(bordoViola + "-fx-background-color: transparent; -fx-font-weight: bold; -fx-font-size: 26px;");
    }

    public void annullaModifica(){
        initialize();
    }

    public void InviaModifica() throws Exception {
        DataService ds = new DataService();
        ds.updateFarmaco(f.getId(), titoloTextField.getText(), descrizioneTextArea.getText());
        f = ds.getFarmacoById(f.getId());
        NavigatorView.setFarmacoSelezionato(f);

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
