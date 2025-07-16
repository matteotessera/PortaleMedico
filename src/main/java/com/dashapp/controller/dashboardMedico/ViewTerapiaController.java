package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.AssociazioneFarmaco;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Terapia;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;

import javax.annotation.processing.Generated;
import java.time.LocalDate;
import java.util.List;

public class ViewTerapiaController {
    @FXML
    public VBox VboxTerapia;

    @FXML
    private ComboBox<Terapia> sceltaTerapia;

    @FXML
    private Button modificaButton;

    @FXML
    private ComboBox<String> sceltaFarmaco;

    @FXML
    private TextField nAssunzioniField;

    @FXML
    private TextField doseField;

    @FXML
    private DatePicker dataInizioPicker;

    @FXML
    private DatePicker dataFinePicker;

    @FXML
    private TextArea note;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Label statusLabel;

    private DataService ds;

    private int idPaziente;
    private int idTerapia;

    private List<Terapia> terapieUtente;

    @FXML
    public void initialize() throws Exception {
        this.ds = new DataService();

        idPaziente = NavigatorView.getUtenteSelezionato().getId();

        VboxTerapia.setVisible(false);

        popolaCampiIniziali();

        disabilitaInput();

    }

    public void popolaCampiIniziali() throws Exception {
        terapieUtente = List.of(ds.getTerapiePaziente(idPaziente));

        sceltaTerapia.getItems().addAll(terapieUtente);

        Farmaco [] farmaci = ds.getFarmaci();

        for(Farmaco f : farmaci){
            sceltaFarmaco.getItems().add(f.getNome());
        }

        sceltaFarmaco.setEditable(false);
    }

    public void disabilitaInput(){
        modificaButton.setVisible(true);

        nAssunzioniField.setEditable(false);
        doseField.setEditable(false);
        note.setEditable(false);
        sceltaFarmaco.setEditable(false);
        dataInizioPicker.setDisable(true);
        dataFinePicker.setDisable(true);

        annullaButton.setVisible(false);
        confermaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setManaged(false);
    }

    public void abilitaInput(){
        modificaButton.setVisible(false);

        nAssunzioniField.setEditable(true);
        doseField.setEditable(true);
        note.setEditable(true);
        sceltaFarmaco.setEditable(true);
        dataInizioPicker.setDisable(false);
        dataFinePicker.setDisable(false);

        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        annullaButton.setManaged(true);
        confermaButton.setManaged(true);
    }

    public void modifica(){
        abilitaInput();
    }

    public void sceltaTerapia() throws Exception {
        Terapia terapiaSelezionata = sceltaTerapia.getValue();
        if (terapiaSelezionata != null) {
            caricaDatiTerapia(terapiaSelezionata.getId());
            this.idTerapia=terapiaSelezionata.getId();
        }
    }

    public void caricaDatiTerapia(int idTerapia) throws Exception {
        VboxTerapia.setVisible(true);
        Terapia t = ds.getTerapiaById(idTerapia);
        AssociazioneFarmaco [] af = ds.getAssociazioniFarmaciByTerapia(idTerapia);

        nAssunzioniField.setText(Integer.toString(af[0].getNumeroAssunzioni()));
        doseField.setText(Integer.toString(af[0].getDose()));
        note.setText(t.getNote());
        sceltaFarmaco.setValue(ds.getFarmacoById(af[0].getIdFarmaco()).getNome());
        dataInizioPicker.setValue(t.getDataInizio());
        dataFinePicker.setValue(t.getDataFine());
    }

    public boolean checkCampiAssunzione() {
        LocalDate dataInizio = dataInizioPicker.getValue();
        LocalDate dataFine = dataFinePicker.getValue();

        String farmacoSelezionato = (String) sceltaFarmaco.getValue();
        String nAssunzioniText = nAssunzioniField.getText();
        String doseText = doseField.getText();
        String noteText = note.getText();

        if (farmacoSelezionato == null) {
            mostraAlert("Errore", "Devi selezionare un farmaco.", Alert.AlertType.ERROR);
            return false;
        }

        if (nAssunzioniText == null || nAssunzioniText.trim().isEmpty()) {
            mostraAlert("Errore", "Devi inserire il numero di assunzioni.", Alert.AlertType.ERROR);
            return false;
        }

        if (!nAssunzioniText.matches("\\d+")) {
            mostraAlert("Errore", "Il numero di assunzioni deve essere un numero intero.", Alert.AlertType.ERROR);
            return false;
        }

        if (doseText == null || doseText.trim().isEmpty()) {
            mostraAlert("Errore", "Devi inserire la dose.", Alert.AlertType.ERROR);
            return false;
        }

        if (!doseText.matches("\\d+")) {
            mostraAlert("Errore", "La dose deve essere un numero intero.", Alert.AlertType.ERROR);
            return false;
        }

        if (dataInizio == null) {
            mostraAlert("Errore", "Devi selezionare una data di inizio.", Alert.AlertType.ERROR);
            return false;
        }

        if (dataFine == null) {
            mostraAlert("Errore", "Devi selezionare una data di fine.", Alert.AlertType.ERROR);
            return false;
        }

        if (dataInizio.isAfter(dataFine)) {
            mostraAlert("Errore", "La data di inizio non può essere successiva alla data di fine.", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }

    public void annulla() throws Exception {
        initialize();
    }

    public void inviaModifiche() throws Exception {
        if(checkCampiAssunzione()){

            int dose = Integer.parseInt(doseField.getText());
            int nAssunzioni = Integer.parseInt(nAssunzioniField.getText());
            int idFarmaco = ds.getFarmacoByNome(sceltaFarmaco.getSelectionModel().getSelectedItem().toString()).getId();
            LocalDate dataInizio = dataInizioPicker.getValue();
            LocalDate dataFine = dataFinePicker.getValue();
            String noteTA = note.getText();

            ds.updateTerapia(idTerapia, dataInizio, dataFine, noteTA, idPaziente, idFarmaco, nAssunzioni, dose);
            mostraAlert("Successo", "Terapia modificata correttamente", Alert.AlertType.INFORMATION);

            caricaDatiTerapia(idTerapia);
            disabilitaInput();
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