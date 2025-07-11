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




    @FXML
    private void aggiungiRilevazione() throws Exception {
        // crea nuovo tipo RilevazioneGlicemia
        Rilevazione.TipoPasto tipoPasto = this.pastoBox.getValue();
        Rilevazione.TipoRilevazione tipoRilevazione = this.quandoBox.getValue();

        Double valore;
        try {
            valore = Double.parseDouble(this.valoreField.getText());
        } catch (NumberFormatException e) {
            System.err.println("Errore: valore glicemia non valido");
            erroreLabel.setText("Errore: valore glicemia non valido");
            erroreLabel.setStyle("-fx-text-fill: red");
            return; // oppure mostra un alert
        }

        LocalTime orario;
        try {
            orario = LocalTime.parse(this.orarioField.getText()); // formato: "HH:mm" o "HH:mm:ss"
        } catch (DateTimeParseException e) {
            System.err.println("Errore: orario non valido, formato: \"HH:mm\" o \"HH:mm:ss\"");
            erroreLabel.setText("Errore: orario non valido, formato: \"HH:mm\" o \"HH:mm:ss\"");
            erroreLabel.setStyle("-fx-text-fill: red");
            return; // oppure mostra un alert per l'utente
        }


        if(tipoRilevazione == null){
            System.err.println("Errore: tipo rilevazione non selezionato");
            erroreLabel.setText("Errore: tipo rilevazione non selezionato");
            erroreLabel.setStyle("-fx-text-fill: red");
            return; // oppure mostra un alert per l'utente
        }
        if(tipoPasto == null){
            System.err.println("Errore: pasto non selezionato");
            erroreLabel.setText("Errore: pasto non selezionato");
            erroreLabel.setStyle("-fx-text-fill: red");
            return; // oppure mostra un alert per l'utente
        }


        ds.addRilevazionePaziente(valore, tipoRilevazione.toString().toLowerCase(), BoxDashboardControllerPatient.u.getId(), tipoPasto.toString().toLowerCase());
        erroreLabel.setText("Oggetto inviato con successo");
        erroreLabel.setStyle("-fx-text-fill: green");

        parentController.FlagRilevazioniLabel.setText("Oggi hai eseguito " + (parentController.countRilevazioni+1) + " rilevazion" + (parentController.countRilevazioni == 1 ? "e" : "i"));

        inviaAvvisoAlMedico(valore, tipoRilevazione);
        parentController.updateGrafico();
        parentController.backToDashboard();

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
                mostraAlert("ATTENZIONE!", "il valore glicemico rilevato (" + valore + ") risulta sotto i valori previsti per le assunzioni preprandiali");
                ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now,
                        "Avviso Glicemia bassa: " + paziente.getEmail(),
                        "Il paziente " + paziente.getNome() + " " + paziente.getCognome() + " ha rilevato una glicemia di valore: " + valore + " prima di un pasto",
                        'G', false);
            }
            if(valore > 130){
                mostraAlert("ATTENZIONE!", "Il valore glicemico rilevato (" + valore + ") è sopra i valori previsti prima dei pasti.");
                ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now,
                        "Avviso Glicemia alta: " + paziente.getEmail(),
                        "Il paziente " + paziente.getNome() + " " + paziente.getCognome() + " ha rilevato una glicemia di valore: " + valore + " prima di un pasto",
                        'G', false);
            }
        }
        else if(quando.toString().equals("POST")){
            if(valore > 180){
                mostraAlert("ATTENZIONE!", "Il valore glicemico rilevato (" + valore + ") è sopra i valori previsti dopo i pasti.");
                ds.addMessaggio(paziente.getId(), medicoDiBase.getId(), today, now,
                        "Avviso Glicemia alta: " + paziente.getEmail(),
                        "Il paziente " + paziente.getNome() + " " + paziente.getCognome() + " ha rilevato una glicemia di valore: " + valore + ", dopo di un pasto",
                        'G', false);
            }
        }


    }

    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle(titolo);
        alert.setHeaderText(null);  // Puoi mettere un header se vuoi
        alert.setContentText(contenuto);
        alert.showAndWait();
    }





}
