package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.BoxDashboardController;
import com.dashapp.model.AddController;
import com.dashapp.model.Rilevazione;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;
import java.util.Locale;

public class AddRilevazioneGlicemiaController extends AddController {

    private DashboardPatientController parentController;
    @FXML
    private ComboBox<Rilevazione.TipoPasto> pastoBox;
    @FXML
    private ComboBox<Rilevazione.TipoRilevazione> quandoBox;
    @FXML
    private TextField valoreField;
    @FXML
    private TextField orarioField;
    @FXML
    private TextField dataField;
    @FXML
    private Label erroreLabel;

    private DataService ds;




    public void initialize() {

        pastoBox.setItems(FXCollections.observableArrayList(Rilevazione.TipoPasto.values()));
        quandoBox.setItems(FXCollections.observableArrayList(Rilevazione.TipoRilevazione.values()));
        ds = new DataService();

        dataField.setEditable(false);
        orarioField.setEditable(false);

        dataField.setText(LocalDate.now().toString());
        orarioField.setText(String.valueOf(LocalTime.now().truncatedTo(ChronoUnit.MINUTES)));

    }


    public boolean checkCampi() {
        Rilevazione.TipoPasto tipoPasto = this.pastoBox.getValue();
        Rilevazione.TipoRilevazione tipoRilevazione = this.quandoBox.getValue();
        String testoValore = this.valoreField.getText();

        if(tipoPasto == null){
            mostraAlert("Errore", "Il pasto non è stato selezionato", Alert.AlertType.ERROR);
            return false;
        }

        if(tipoRilevazione == null){
            mostraAlert("Errore", "Il momento della rilevazione non è stato selezionato", Alert.AlertType.ERROR);
            return false;
        }

        if (testoValore == null || testoValore.isEmpty()) {
            mostraAlert("Errore", "Il valore glicemico non è stato inserito", Alert.AlertType.ERROR);
            return false;
        }
        testoValore = testoValore.replace(",", ".");

        if (!testoValore.matches("\\d+(\\.\\d+)?")) {
            mostraAlert("Errore", "Il valore glicemico ha un valore non valido", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }



    @FXML
    private void aggiungiRilevazione() throws Exception {

        if(checkCampi()) {
            Rilevazione.TipoPasto tipoPasto = this.pastoBox.getValue();
            Rilevazione.TipoRilevazione tipoRilevazione = this.quandoBox.getValue();

            Double valore = Double.parseDouble(valoreField.getText().replace(",", "."));

            ds.addRilevazionePaziente(valore, tipoRilevazione.toString().toLowerCase(), BoxDashboardControllerPatient.u.getId(), tipoPasto.toString().toLowerCase());
            mostraAlert("Successo", "La registrazione glicemica è stata effettuata con successo", Alert.AlertType.INFORMATION);

            parentController.FlagRilevazioniLabel.setText("Oggi hai eseguito " + (parentController.countRilevazioni + 1) + " rilevazion" + (parentController.countRilevazioni == 1 ? "e" : "i"));

            inviaAvvisoAlMedico(valore, tipoRilevazione);
            parentController.updateGrafico();
            parentController.backToDashboard();
        }

    }

    public void setParentController(DashboardPatientController controller) {
        this.parentController = controller;
    }

    private void inviaAvvisoAlMedico(Double valore, Rilevazione.TipoRilevazione quando) throws Exception {

        Utente paziente = BoxDashboardControllerPatient.u;
        Utente medicoDiBase = ds.getMedicoDiBase(paziente.getId());
        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        if(quando.toString().equals("PRE")){
            if(valore < 80){
                mostraAlert("Attenzione!", "il valore glicemico rilevato (" + valore + ") risulta sotto i valori previsti per le assunzioni preprandiali", Alert.AlertType.WARNING);

                String oggetto = "⚠ Avviso Glicemia Bassa";
                String corpo = "Paziente: " + paziente.getNome() + " " + paziente.getCognome() +
                        "\nEmail: " + paziente.getEmail() +
                        "\n\nValore glicemia rilevato: " + valore +
                        "\nMomento della misurazione: PRIMA di un pasto.";

                ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now, oggetto, corpo, 'G', false);
                /*ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now,
                        "Avviso Glicemia bassa: " + paziente.getEmail(),
                        "Il paziente " + paziente.getNome() + " " + paziente.getCognome() + " ha rilevato una glicemia di valore: " + valore + " prima di un pasto",
                        'G', false);*/
            }
            if(valore > 130){
                mostraAlert("Attenzione!", "Il valore glicemico rilevato (" + valore + ") è sopra i valori previsti prima dei pasti.", Alert.AlertType.WARNING);
                String oggetto = "⚠ Avviso Glicemia Alta";
                String corpo = "Paziente: " + paziente.getNome() + " " + paziente.getCognome() +
                        "\nEmail: " + paziente.getEmail() +
                        "\n\nValore glicemia rilevato: " + valore +
                        "\nMomento della misurazione: prima di un pasto.";

                ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now, oggetto, corpo, 'G', false);
                /*ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now,
                        "Avviso Glicemia alta: " + paziente.getEmail(),
                        "Il paziente " + paziente.getNome() + " " + paziente.getCognome() + " ha rilevato una glicemia di valore: " + valore + " prima di un pasto",
                        'G', false);*/
            }
        }
        else if(quando.toString().equals("POST")){
            if(valore > 180){
                mostraAlert("Attenzione!", "Il valore glicemico rilevato (" + valore + ") è sopra i valori previsti dopo i pasti.", Alert.AlertType.WARNING);

                String oggetto = "⚠ Avviso Glicemia Alta";
                String corpo = "Paziente: " + paziente.getNome() + " " + paziente.getCognome() +
                        "\nEmail: " + paziente.getEmail() +
                        "\n\nValore glicemia rilevato: " + valore +
                        "\nMomento della misurazione: dopo un pasto.";

                ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now, oggetto, corpo, 'G', false);

                /*ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now,
                        "Avviso Glicemia alta: " + paziente.getEmail(),
                        "Il paziente " + paziente.getNome() + " " + paziente.getCognome() + " ha rilevato una glicemia di valore: " + valore + ", dopo di un pasto",
                        'G', false);*/
            }
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
