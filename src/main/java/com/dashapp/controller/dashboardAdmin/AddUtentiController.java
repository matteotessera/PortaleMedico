package com.dashapp.controller.dashboardAdmin;


import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.interactive.form.PDAcroForm;
import org.apache.pdfbox.pdmodel.interactive.form.PDField;

import com.dashapp.model.AddController;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;

import java.io.*;
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

            String templatePath = "/com/dashapp/fileCredenziali/template.pdf";  // percorso nel resources
            String outputFileName = "fileCredenziali/Diary_credenziali_" + nome + cognome + ".pdf";
            creaFileCredenziali(templatePath, outputFileName, email, password);

        } catch (Exception e) {
            e.printStackTrace();
            showError("Errore durante il salvataggio dell'utente nel database.");
        }

    }

    public void creaFileCredenziali (String templateResourcePath, String outputFileName, String username, String password) throws IOException {
        InputStream templateStream = com.dashapp.test.TestPdfBox.class.getResourceAsStream(templateResourcePath);
        if (templateStream == null) {
            throw new IOException("Template PDF non trovato: " + templateResourcePath);
        }

        try (PDDocument pdfDocument = PDDocument.load(templateStream)) {
            PDAcroForm acroForm = pdfDocument.getDocumentCatalog().getAcroForm();

            if (acroForm != null) {
                PDField usernameField = acroForm.getField("EmailField");
                PDField passwordField = acroForm.getField("PasswordField");

                if (usernameField != null) {
                    usernameField.setValue(username);
                } else {
                    System.err.println("Campo 'username' non trovato nel PDF");
                }
                if (passwordField != null) {
                    passwordField.setValue(password);
                } else {
                    System.err.println("Campo 'password' non trovato nel PDF");
                }

                acroForm.flatten();  // rende il form non modificabile
            } else {
                System.err.println("Il PDF non contiene un modulo interattivo");
            }

            pdfDocument.save(outputFileName);
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
