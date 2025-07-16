package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.InfoPaziente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;

public class AddInfoPazienteController {

    @FXML
    private ComboBox<String> fumoComboBox;
    @FXML
    private ComboBox<String> alcolComboBox;
    @FXML
    private ComboBox<String> obesitaComboBox;
    @FXML
    private ComboBox<String> ipertensioneComboBox;
    @FXML
    private ComboBox<String> colesteroloComboBox;
    @FXML
    private ComboBox<String> stupefacentiComboBox;
    @FXML
    private ComboBox<String> attivitaComboBox;
    @FXML
    private ComboBox<String> dietaComboBox;
    @FXML
    private TextArea descrizioneArea;
    @FXML
    private Button annullaButton;
    @FXML
    private Button inviaModificheButton;
    @FXML
    private Button aggiungiButton;
    @FXML
    private Button modificaButton;

    private DataService ds;
    private int idPaziente;

    @FXML
    public void initialize() throws Exception {
        ds = new DataService();
        idPaziente = NavigatorView.getUtenteSelezionato().getId();

        InfoPaziente info = ds.getInfoPazienteByID(idPaziente);

        if (info == null) {
            // Nessuna info presente, mostra solo il pulsante "aggiungi"
            aggiungiButton.setVisible(true);
            aggiungiButton.setManaged(true);

            modificaButton.setVisible(false);
            modificaButton.setManaged(false);

            inviaModificheButton.setVisible(false);
            inviaModificheButton.setManaged(false);

            annullaButton.setVisible(false);
            annullaButton.setManaged(false);
            campiEditabili(true);

            clearFields();
        } else {
            // Info presenti, mostra solo il pulsante "modifica"
            aggiungiButton.setVisible(false);
            aggiungiButton.setManaged(false);

            modificaButton.setVisible(true);
            modificaButton.setManaged(true);

            inviaModificheButton.setVisible(false);
            inviaModificheButton.setManaged(false);

            annullaButton.setVisible(false);
            annullaButton.setManaged(false);
            campiEditabili(false);

            riempiCampi();
        }
        setCampi();  // Carica sempre le opzioni per i comboBox
    }

    public void annulla() throws Exception {
        // Torna allo stato iniziale con dati caricati
        initialize();
    }

    public void inviaDati() {
        try {
            JsonObject obj = costruisciJsonDaCampi();

            String noteJson = obj.toString();

            ds.addInfoPaziente(idPaziente, noteJson);

            initialize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void inviaModifiche() {
        try {
            JsonObject obj = costruisciJsonDaCampi();

            String noteJson = obj.toString();

            ds.updateInfoPaziente(ds.getInfoPazienteByID(idPaziente).getId(), idPaziente, noteJson);

            initialize();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void modifica() {
        modificaButton.setVisible(false);
        modificaButton.setManaged(false);

        annullaButton.setVisible(true);
        annullaButton.setManaged(true);

        inviaModificheButton.setVisible(true);
        inviaModificheButton.setManaged(true);
        campiEditabili(true);

    }

    private JsonObject costruisciJsonDaCampi() {
        JsonObject obj = new JsonObject();
        obj.addProperty("fumo", safeGetValue(fumoComboBox));
        obj.addProperty("alcol", safeGetValue(alcolComboBox));
        obj.addProperty("obesita", safeGetValue(obesitaComboBox));
        obj.addProperty("ipertensione", safeGetValue(ipertensioneComboBox));
        obj.addProperty("colesterolo", safeGetValue(colesteroloComboBox));
        obj.addProperty("stupefacenti", safeGetValue(stupefacentiComboBox));
        obj.addProperty("attivita", safeGetValue(attivitaComboBox));
        obj.addProperty("dieta", safeGetValue(dietaComboBox));
        obj.addProperty("descrizione", descrizioneArea.getText() != null ? descrizioneArea.getText() : "");
        return obj;
    }

    private String safeGetValue(ComboBox<String> comboBox) {
        return comboBox.getValue() != null ? comboBox.getValue() : "";
    }

    public void riempiCampi() throws Exception {
        InfoPaziente info = ds.getInfoPazienteByID(idPaziente);

        setCampi();

        if (info != null && info.getNote() != null && !info.getNote().isJsonNull()) {
            caricaInfoDaJson(info.getNote());
        } else {
            clearFields();
        }

    }

    public void caricaInfoDaJson(JsonElement note) {
        if (note == null || note.isJsonNull()) {
            clearFields();
            return;
        }

        Gson gson = new Gson();

        // Converto la stringa JSON in JsonObject
        String noteString = note.getAsString();
        JsonObject obj = gson.fromJson(noteString, JsonObject.class);

        fumoComboBox.setValue(obj.has("fumo") ? obj.get("fumo").getAsString() : null);
        alcolComboBox.setValue(obj.has("alcol") ? obj.get("alcol").getAsString() : null);
        obesitaComboBox.setValue(obj.has("obesita") ? obj.get("obesita").getAsString() : null);
        ipertensioneComboBox.setValue(obj.has("ipertensione") ? obj.get("ipertensione").getAsString() : null);
        colesteroloComboBox.setValue(obj.has("colesterolo") ? obj.get("colesterolo").getAsString() : null);
        stupefacentiComboBox.setValue(obj.has("stupefacenti") ? obj.get("stupefacenti").getAsString() : null);
        attivitaComboBox.setValue(obj.has("attivita") ? obj.get("attivita").getAsString() : null);
        dietaComboBox.setValue(obj.has("dieta") ? obj.get("dieta").getAsString() : null);
        descrizioneArea.setText(obj.has("descrizione") ? obj.get("descrizione").getAsString() : "");
    }

    public void setCampi() {
        fumoComboBox.getItems().setAll("Fumatore", "Ex-fumatore", "Non fumatore");
        alcolComboBox.getItems().setAll("Consumo abituale", "Consumo occasionale", "Non consuma");
        obesitaComboBox.getItems().setAll("Obeso", "Sovrappeso", "Normopeso");
        ipertensioneComboBox.getItems().setAll("Ipertensione diagnosticata", "Normoteso");
        colesteroloComboBox.getItems().setAll("Sì", "No");
        stupefacentiComboBox.getItems().setAll("Attuale", "Pregressa", "Nessuna");
        attivitaComboBox.getItems().setAll("Sedentario", "Moderata", "Regolare");
        dietaComboBox.getItems().setAll(
                "Dieta bilanciata",
                "Dieta povera di carboidrati",
                "Dieta a basso contenuto di zuccheri",
                "Dieta ricca di fibre",
                "Dieta controllata con conteggio carboidrati",
                "Nessuna dieta specifica"
        );

        fumoComboBox.setPromptText("Seleziona fumo");
        alcolComboBox.setPromptText("Seleziona consumo alcol");
        obesitaComboBox.setPromptText("Seleziona obesità");
        ipertensioneComboBox.setPromptText("Seleziona ipertensione");
        colesteroloComboBox.setPromptText("Seleziona colesterolo");
        stupefacentiComboBox.setPromptText("Seleziona dipendenza stupefacenti");
        attivitaComboBox.setPromptText("Seleziona attività fisica");
        dietaComboBox.setPromptText("Seleziona dieta");
    }

    private void campiEditabili(boolean val) {
        fumoComboBox.setEditable(val);
        alcolComboBox.setEditable(val);
        obesitaComboBox.setEditable(val);
        ipertensioneComboBox.setEditable(val);
        colesteroloComboBox.setEditable(val);
        stupefacentiComboBox.setEditable(val);
        attivitaComboBox.setEditable(val);
        dietaComboBox.setEditable(val);
        descrizioneArea.setEditable(val);
    }


    private void clearFields() {
        fumoComboBox.setValue(null);
        alcolComboBox.setValue(null);
        obesitaComboBox.setValue(null);
        ipertensioneComboBox.setValue(null);
        colesteroloComboBox.setValue(null);
        stupefacentiComboBox.setValue(null);
        attivitaComboBox.setValue(null);
        dietaComboBox.setValue(null);
        descrizioneArea.clear();
    }

}
