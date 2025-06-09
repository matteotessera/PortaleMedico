package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.AddController;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.util.HashMap;
import java.util.Map;

public class AddTerapiaController extends AddController {

    @FXML
    private ComboBox sceltaPaziente;
    @FXML
    private ComboBox sceltaFarmaco;
    @FXML
    private TextField nAssunzioniField;
    @FXML
    private TextField doseField;
    @FXML
    private ComboBox sceltaIndicazioni;
    @FXML
    private DatePicker dataInizioPicker;
    @FXML
    private DatePicker dataFinePicker;
    @FXML
    private TextArea note;
    @FXML

    private DataService ds;

    private  Map<String, Integer> pazienteMap;

    public void initialize() throws Exception {
        ds = new DataService();
        popolaComboBox();
    }

    public void popolaComboBox() throws Exception {
        pazienteMap = new HashMap<>();

        String email = NavigatorView.getAuthenticatedUser();
        Utente [] utenti = ds.getPazientiByMedico(ds.getUtenteByEmail(email).getId());
        for (Utente u : utenti) {
            String nomeCompleto = u.getNome() + " " + u.getCognome();
            sceltaPaziente.getItems().add(nomeCompleto);
            pazienteMap.put(nomeCompleto, u.getId());
        }
        Farmaco [] farmaci = ds.getFarmaci();

        for(Farmaco f : farmaci){
            sceltaFarmaco.getItems().add(f.getNome());
        }

        String[] indicazioniAssunzione = {
                "Dopo i pasti",
                "Prima dei pasti",
                "Lontano dai pasti",
                "Durante i pasti",
                "A stomaco vuoto",
                "Prima di coricarsi",
                "Al mattino",
                "Alla sera",
                "Ogni 12 ore",
                "Ogni 8 ore",
                "Al bisogno",
                "Una volta al giorno",
                "Due volte al giorno",
                "Tre volte al giorno",
                "Ogni settimana",
                "Ogni mese",
                "Subito dopo colazione",
                "Subito dopo cena",
                "In caso di dolore",
                "In caso di febbre",
                "Non interrompere senza consulto medico"
        };

        sceltaIndicazioni.getItems().addAll(indicazioniAssunzione);
    }

    public void assegnaTerapia() throws Exception {
        if(checkCampiValidi()){
            String selectedNomeCompleto = sceltaPaziente.getSelectionModel().getSelectedItem().toString();

            int idPaziente = pazienteMap.get(selectedNomeCompleto);
            int dose = Integer.parseInt(doseField.getText());
            int idFarmaco = ds.getFarmacoByNome(sceltaFarmaco.getSelectionModel().getSelectedItem().toString()).getId();
            ds.addTerapia(dataInizioPicker.getValue(), dataFinePicker.getValue(), note.getText(), idPaziente, idFarmaco, dose, nAssunzioniField.getText());
        }

    }

    private boolean checkCampiValidi() {
        String messaggioErrore = "";

        if (sceltaPaziente.getValue() == null) {
            messaggioErrore += "- Seleziona un paziente\n";
        }
        if (sceltaFarmaco.getValue() == null) {
            messaggioErrore += "- Seleziona un farmaco\n";
        }
        if (nAssunzioniField.getText() == null || nAssunzioniField.getText().trim().isEmpty()) {
            messaggioErrore += "- Inserisci il numero di assunzioni\n";
        }
        if (doseField.getText() == null || doseField.getText().trim().isEmpty()) {
            messaggioErrore += "- Inserisci la dose\n";
        }
        if (sceltaIndicazioni.getValue() == null) {
            messaggioErrore += "- Seleziona le indicazioni\n";
        }
        if (dataInizioPicker.getValue() == null) {
            messaggioErrore += "- Seleziona una data di inizio\n";
        }
        if (dataFinePicker.getValue() == null) {
            messaggioErrore += "- Seleziona una data di fine\n";
        }
        if (note.getText() == null || note.getText().trim().isEmpty()) {
            messaggioErrore += "- Inserisci una descrizione\n";
        }

        if (!messaggioErrore.isEmpty()) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Campi mancanti");
            alert.setHeaderText("Devi compilare tutti i campi richiesti:");
            alert.setContentText(messaggioErrore);
            alert.showAndWait();
            return false;
        }

        return true;
    }

}
