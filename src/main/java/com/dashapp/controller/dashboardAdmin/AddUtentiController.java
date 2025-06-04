package com.dashapp.controller.dashboardAdmin;

import com.dashapp.model.AddController;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.util.Random;

public class AddUtentiController extends AddController {

    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private TextField codiceFiscaleField;
    @FXML
    private DatePicker dataField;
    @FXML
    private ComboBox genereComboBox;
    @FXML
    private TextField indirizzoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField telefonoField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox ruoloComboBox;

    @FXML
    private Label statoAggiungiUtente;


    private DataService ds;



    public void initialize(){
        ds = new DataService();
        String [] genere = {"M", "F"};
        genereComboBox.getItems().addAll(genere);

        String [] ruolo = {"Medico", "Paziente", "Admin"};
        ruoloComboBox.getItems().addAll(ruolo);
    }

    public void aggiungiUtente() throws Exception {
        String nome = nomeField.getText();
        String cognome = cognomeField.getText();
        String codiceFiscale = codiceFiscaleField.getText();
        LocalDate dataNascita = dataField.getValue();
        String genere = (String) genereComboBox.getValue();
        String indirizzo = indirizzoField.getText();
        String email = emailField.getText();
        String telefono = telefonoField.getText();
        String password = passwordField.getText();
        String ruolo = (String) ruoloComboBox.getValue();

        // Controllo campi vuoti
        if (nome.isEmpty() || cognome.isEmpty() || codiceFiscale.isEmpty() || dataNascita == null ||
                genere == null || indirizzo.isEmpty() || email.isEmpty() || telefono.isEmpty() ||
                password.isEmpty() || ruolo == null) {
            showError("Errore: tutti i campi devono essere compilati");
            return;
        }

        // Controllo telefono: almeno 10 numeri
        if (!telefono.matches("\\d{10,}")) {
            showError("Errore: il numero di telefono deve contenere almeno 10 cifre numeriche");
            return;
        }

        // Controllo email con regex base
        if (!email.matches("^[\\w.-]+@[\\w.-]+\\.\\w{2,}$")) {
            showError("Errore: l'indirizzo email non è valido");
            return;
        }

        if (codiceFiscale.length() != 16 || !codiceFiscale.matches("^[A-Z0-9]{16}$")) {
            showError("Codice fiscale non valido. Deve essere lungo 16 caratteri e contenere solo lettere maiuscole e numeri.");
            return;
        }

        // Controllo password: esattamente 5 caratteri
        if (password.length() != 5) {
            showError("Errore: la password deve essere composta esattamente da 5 caratteri");
            return;
        }

        try {
            ds.addUtente(password, ruolo, nome, cognome, codiceFiscale, dataNascita, email, telefono, indirizzo, genere);
            showSuccess("Utente aggiunto correttamente.");
        } catch (Exception e) {
            e.printStackTrace();
            showError("Errore durante il salvataggio dell'utente nel database.");
        }

    }


    @FXML
    private void generaPasswordCasuale() {
        String caratteri = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder password = new StringBuilder();
        Random rnd = new Random();

        for (int i = 0; i < 5; i++) {
            int index = rnd.nextInt(caratteri.length());
            password.append(caratteri.charAt(index));
        }
        passwordField.setText(password.toString());
    }

    public void showError(String message) {
        statoAggiungiUtente.setText(message);
        statoAggiungiUtente.setStyle("-fx-text-fill: red;");
        statoAggiungiUtente.setVisible(true);
    }

    public void showSuccess(String message) {
        statoAggiungiUtente.setText(message);
        statoAggiungiUtente.setStyle("-fx-text-fill: green;");
        statoAggiungiUtente.setVisible(true);
    }


    }
