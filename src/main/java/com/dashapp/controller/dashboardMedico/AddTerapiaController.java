package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.AddController;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.collections.ListChangeListener;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import org.controlsfx.control.CheckComboBox;

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
    private CheckComboBox sceltaIndicazioni;
    @FXML
    private DatePicker dataInizioPicker;
    @FXML
    private DatePicker dataFinePicker;
    @FXML
    private TextArea note;
    @FXML
    public Label statusLabel;

    private DataService ds;

    private  Map<String, Integer> pazienteMap;

    public void initialize() throws Exception {
        ds = new DataService();
        sceltaIndicazioni.getStyleClass().add("check-combo-box");

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

        // Mette le indicazioni suggerite dalla box nella textArea
        sceltaIndicazioni.getCheckModel().getCheckedItems().addListener((ListChangeListener<String>) change -> {
            StringBuilder selectedItems = new StringBuilder();
            for (Object item : sceltaIndicazioni.getCheckModel().getCheckedItems()) {
                selectedItems.append(item).append(", ");
            }
            if (selectedItems.length() > 0) {
                // Rimuove l'ultima virgola e spazio finale
                selectedItems.setLength(selectedItems.length() - 2);
            }
            // Aggiorna la TextArea
            note.setText(selectedItems.toString());

            // Nasconde il testo visibile nella CheckComboBox
            sceltaIndicazioni.setTitle("");
        });

    }



    public void assegnaTerapia() throws Exception {
        if(checkCampiValidi()){
            String selectedNomeCompleto = sceltaPaziente.getSelectionModel().getSelectedItem().toString();

            int idPaziente = pazienteMap.get(selectedNomeCompleto);
            int dose = Integer.parseInt(doseField.getText());
            int nAssunzioni = Integer.parseInt(nAssunzioniField.getText());
            int idFarmaco = ds.getFarmacoByNome(sceltaFarmaco.getSelectionModel().getSelectedItem().toString()).getId();
            ds.addTerapia(dataInizioPicker.getValue(), dataFinePicker.getValue(), note.getText(), idPaziente, idFarmaco, nAssunzioni, dose);
            showMessage("Terapia assegnata con successo!");
            clearForm();
        }

    }

    private boolean checkCampiValidi() {

        if (sceltaPaziente.getValue() == null) {
            mostraAlert("Errore", "Seleziona un paziente", Alert.AlertType.ERROR);
            return false;
        }
        if (sceltaFarmaco.getValue() == null) {
            mostraAlert("Errore", "Seleziona un farmaco", Alert.AlertType.ERROR);
            return false;
        }
        if (nAssunzioniField.getText() == null || nAssunzioniField.getText().trim().isEmpty()) {
            mostraAlert("Errore", "Inserisci il numero di assunzioni", Alert.AlertType.ERROR);
            return false;
        }
        if (doseField.getText() == null || doseField.getText().trim().isEmpty()) {
            mostraAlert("Errore", "Inserisci la dose", Alert.AlertType.ERROR);
            return false;
        }
        if (dataInizioPicker.getValue() == null) {
            mostraAlert("Errore", "Seleziona una data di inizio", Alert.AlertType.ERROR);
            return false;
        }
        if (dataFinePicker.getValue() == null) {
            mostraAlert("Errore", "Seleziona una data di fine", Alert.AlertType.ERROR);
            return false;
        }
        if (note.getText() == null || note.getText().trim().isEmpty()) {
            mostraAlert("Errore", "Note non inserite", Alert.AlertType.ERROR);

            return false;
        }
        if (dataInizioPicker.getValue() != null && dataFinePicker.getValue() != null) {
            if (dataFinePicker.getValue().isBefore(dataInizioPicker.getValue())) {
                mostraAlert("Errore", "La data di fine deve essere successiva alla data di inizio", Alert.AlertType.ERROR);
                return false;
            }
        }
        return true;
    }

    private void clearForm() {
        sceltaPaziente.getSelectionModel().clearSelection();
        sceltaFarmaco.getSelectionModel().clearSelection();
        sceltaIndicazioni.getCheckModel().clearChecks();
        nAssunzioniField.clear();
        doseField.clear();
        dataInizioPicker.setValue(null);
        dataFinePicker.setValue(null);
        note.clear();
    }

    private void showMessage(String message){
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: green;");
        statusLabel.setVisible(true);
    }

    public void mostraAlert(String titolo, String contenuto, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }


}
