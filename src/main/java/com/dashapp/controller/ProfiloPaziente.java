package com.dashapp.controller;

import com.dashapp.controller.dashboardMedico.DashboardMedicController;
import com.dashapp.controller.dashboardPatient.DashboardPatientController;
import com.dashapp.controller.dashboardPatient.fascicolo.FascicoloPazienteController;
import com.dashapp.model.Rilevazione;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.time.LocalDate;

public class ProfiloPaziente {
    @FXML
    public TextField nomeField;
    @FXML
    public TextField cognomeField;
    @FXML
    public DatePicker dataNascitaField;
    @FXML
    public TextField codiceFiscaleField;
    @FXML
    public TextField telefonoField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField indirizzoField;
    @FXML
    public ComboBox genereField;

    @FXML
    public Tab fattoriDiRischioButton;
    @FXML
    public Tab fascicoloButton;
    @FXML
    public AnchorPane fattoriDiRischioPane;
    @FXML
    public AnchorPane fascicoloPane;
    @FXML
    public ScrollPane graficoPane;

    private DashboardMedicController dashController;
    @FXML
    public Button annullaButton;
    @FXML
    public Button confermaButton;
    @FXML
    public Button modificaButton;

    private Utente u;
    private DataService ds;

    public void initialize() throws Exception {
        ds = new DataService();
        u = NavigatorView.getUtenteSelezionato();

        genereField.getItems().setAll("M", "F");

        riempiCampi();
        disabilitaCampi();
        loadTabs();
    }

    public void disabilitaCampi(){

        modificaButton.setVisible(true);
        modificaButton.setManaged(true);

        nomeField.setEditable(false);
        cognomeField.setEditable(false);
        dataNascitaField.setDisable(true);
        codiceFiscaleField.setEditable(false);
        genereField.setEditable(true);
        indirizzoField.setEditable(false);
        emailField.setEditable(false);
        telefonoField.setEditable(false);

        confermaButton.setVisible(false);
        confermaButton.setManaged(false);

        annullaButton.setVisible(false);
        annullaButton.setManaged(false);
    }

    public void abilitaCampi(){

        modificaButton.setVisible(false);
        modificaButton.setManaged(false);

        nomeField.setEditable(true);
        cognomeField.setEditable(true);
        dataNascitaField.setDisable(false);
        codiceFiscaleField.setEditable(true);
        genereField.setDisable(false);
        indirizzoField.setEditable(true);
        emailField.setEditable(true);
        telefonoField.setEditable(true);

        confermaButton.setVisible(true);
        confermaButton.setManaged(true);

        annullaButton.setVisible(true);
        annullaButton.setManaged(true);
    }

    public void riempiCampi(){
        nomeField.setText(u.getNome());
        cognomeField.setText(u.getCognome());
        dataNascitaField.setValue(u.getDataNascita());
        codiceFiscaleField.setText(u.getCodFiscale());
        genereField.setValue(u.getGenere());
        indirizzoField.setText(u.getIndirizzo());
        emailField.setText(u.getEmail());
        telefonoField.setText(u.getTelefono());
    }


    public void loadTabs() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/FascicoloPaziente.fxml"));
        Parent fascicolo = loader.load();
        FascicoloPazienteController controller = loader.getController();
        controller.setPaziente(u);
        fascicoloPane.getChildren().add(fascicolo);
    }


    public void modificaDati() throws Exception {
        abilitaCampi();
    }

    public void inviaDati() throws Exception {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        LocalDate data = dataNascitaField.getValue();
        String codFisc = codiceFiscaleField.getText();
        String tel = telefonoField.getText();
        String email = emailField.getText();
        String indirizzo = indirizzoField.getText();
        String genere = genereField.getValue().toString();

        if (nome.isEmpty() || cognome.isEmpty() || codFisc.isEmpty() || data == null ||
                genere == null || indirizzo.isEmpty() || email.isEmpty() || tel.isEmpty()) {
            mostraAlert("Errore", "Tutti i campi devono essere compilati");
            return;
        }

        if (data.isAfter(LocalDate.now())) {
            mostraAlert("Errore", "La data di nascita non può essere nel futuro");
            return;
        }

        // Controllo telefono: almeno 10 numeri
        if (!tel.matches("\\d{10,}")) {
            mostraAlert("Errore", "Il numero di telefono deve contenere almeno 10 cifre numeriche");
            return;
        }

        // Controllo email con regex base
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            mostraAlert("Errore", "L'indirizzo email non è valido");
            return;
        }

        if (codFisc.length() != 16 || !codFisc.matches("^[A-Z0-9]{16}$")) {
            mostraAlert("Errore", "Codice fiscale non valido. Deve essere lungo 16 caratteri e contenere solo lettere maiuscole e numeri.");
            return;
        }

        Utente newU = new Utente();
        ds.updateUtenteSenzaPw(u.getId() ,u.getRuolo(), nome, cognome, data, email, tel, indirizzo, genere, codFisc);
        mostraAlert("Successo", "Dati modificati con successo");

        initialize();
    }

    public void annullaButton() throws Exception {
        initialize();
    }

    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }
}

