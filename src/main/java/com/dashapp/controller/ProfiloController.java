package com.dashapp.controller;

import com.dashapp.Main;
import com.dashapp.controller.dashboardMedico.DashboardMedicController;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

public class ProfiloController {
    @FXML
    private TextField nomeField;
    @FXML
    private TextField cognomeField;
    @FXML
    private DatePicker dataNascitaField;
    @FXML
    private TextField codiceFiscaleField;
    @FXML
    private TextField telefonoField;
    @FXML
    private TextField emailField;
    @FXML
    private TextField indirizzoField;
    @FXML
    private ComboBox genereField;
    @FXML
    private PasswordField passwordAttualeField;
    @FXML
    private PasswordField nuovaPasswordField;

    @FXML
    private PasswordField confermaNuovaPasswordField;

    @FXML
    private Button modificaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Button annullaButton;
    @FXML
    private Button modificaButtonPw;

    @FXML
    private Button annullaButtonPw;

    @FXML
    private Button confermaButtonPw;


    private DataService ds;
    private Utente u;
    private int idUtente;


    public void initialize() throws Exception {
        ds = new DataService();
        String email = NavigatorView.getAuthenticatedUser();
        idUtente = ds.getUtenteByEmail(email).getId();
        u = ds.getUtenteById(ds.getUtenteByEmail(email).getId());
        genereField.getItems().removeAll();
        genereField.getItems().addAll("M", "F");


        modificaButton.setVisible(true);
        modificaButton.setManaged(true);

        modificaButtonPw.setVisible(true);
        modificaButtonPw.setManaged(true);

        confermaButton.setVisible(false);
        confermaButton.setManaged(false);

        annullaButton.setVisible(false);
        annullaButton.setManaged(false);

        annullaButtonPw.setVisible(false);
        annullaButtonPw.setManaged(false);

        confermaButtonPw.setVisible(false);
        confermaButtonPw.setManaged(false);

        nomeField.setEditable(false);
        cognomeField.setEditable(false);
        dataNascitaField.setDisable(true);
        codiceFiscaleField.setEditable(false);
        telefonoField.setEditable(false);
        emailField.setEditable(false);
        indirizzoField.setEditable(false);
        genereField.setEditable(false);

        caricaDati();
    }

    public void caricaDati(){
        nomeField.setText(u.getNome());
        cognomeField.setText(u.getCognome());
        dataNascitaField.setValue(u.getDataNascita());
        codiceFiscaleField.setText(u.getCodFiscale());
        telefonoField.setText(u.getTelefono());
        emailField.setText(u.getEmail());
        indirizzoField.setText(u.getIndirizzo());
        genereField.setValue(u.getGenere());
    }

    public void modificaDati() throws Exception {
        modificaButton.setVisible(false);
        confermaButton.setVisible(true);
        annullaButton.setVisible(true);
        modificaButton.setManaged(false);
        confermaButton.setManaged(true);
        annullaButton.setManaged(true);;

        nomeField.setEditable(true);
        cognomeField.setEditable(true);
        dataNascitaField.setDisable(false);
        codiceFiscaleField.setEditable(true);
        telefonoField.setEditable(true);
        emailField.setEditable(true);
        indirizzoField.setEditable(true);
        genereField.setEditable(true);
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

        // Controllo campi vuoti
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

        ds.updateUtenteSenzaPw(idUtente ,u.getRuolo(), nome, cognome, data, email, tel, indirizzo, genere, codFisc);
        mostraAlert("Successo", "Dati modificati con successo");
        ricarica();
        initialize();

    }

    public void ricarica() throws Exception {
        MainController mainController = NavigatorView.getMainController();

        if(u.getRuolo().equals("admin")){
            NavigatorView.getAdminController().reload();

        } else if (u.getRuolo().equals("medico")) {
            NavigatorView.getMedicController().reload();

        } else if (u.getRuolo().equals("paziente")) {
            NavigatorView.getPatientController().reload();
        }

    }

    public void annullaButton() throws Exception {
        initialize();
    }

    public void modificaPassword(){
        modificaButtonPw.setVisible(false);
        confermaButtonPw.setVisible(true);
        annullaButtonPw.setVisible(true);
        modificaButtonPw.setManaged(false);
        confermaButtonPw.setManaged(true);
        annullaButtonPw.setManaged(true);;

        passwordAttualeField.setEditable(true);
        nuovaPasswordField.setEditable(true);
        confermaNuovaPasswordField.setEditable(true);
    }

    public void inviaPassword() throws Exception {
        String pwAttuale = passwordAttualeField.getText();
        String nuovaPw = nuovaPasswordField.getText();
        String confermaPw = confermaNuovaPasswordField.getText();

        if(pwAttuale.isEmpty() || nuovaPw.isEmpty() || confermaPw.isEmpty()){
            mostraAlert("Errore", "Tutti i campo devono essere compilati");
            return;
        }

        if(!ds.verificaPassword(u.getId(), pwAttuale)){
            mostraAlert("Errore", "La password attuale non è corretta");
            return;
        }

        if(!nuovaPw.equals(confermaPw)) {
            mostraAlert("Errore", "Le nuove passoword non corrispondono");
            return;
        }

        if (nuovaPw.length() < 6) {
            mostraAlert("Errore", "La password deve contenere almeno 6 caratteri");
            return;
        }

        if (!nuovaPw.matches(".*[a-zA-Z].*")) {
            mostraAlert("Errore", "La password deve contenere almeno una lettera");
            return;
        }

        // Almeno un numero
        if (!nuovaPw.matches(".*[0-9].*")) {
            mostraAlert("Errore", "La password deve contenere almeno un numero");
            return;
        }

        // Almeno un carattere speciale tra . * _ ? ! @
        if (!nuovaPw.matches(".*[\\.\\*\\_\\?\\!@].*")) {
            mostraAlert("Errore","La password deve contenere almeno un carattere speciale tra . * _ ? ! @");
            return;
        }

        ds.updatePassword(u.getId(), nuovaPw);

        mostraAlert("Successo", "Password modificata con successo");
        pulisciCampi();
        initialize();
    }


    public void pulisciCampi(){
        passwordAttualeField.setText("");
        nuovaPasswordField.setText("");
        confermaNuovaPasswordField.setText("");
    }


    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }



}
