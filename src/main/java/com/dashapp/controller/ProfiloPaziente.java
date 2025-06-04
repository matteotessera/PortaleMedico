package com.dashapp.controller;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class ProfiloPaziente {
    @FXML
    public TextField nomeField;
    @FXML
    public TextField cognomeField;
    @FXML
    public TextField dataNascitaField;
    @FXML
    public TextField codiceFiscaleField;
    @FXML
    public TextField telefonoField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField indirizzoField;
    @FXML
    public TextField genereField;

    private Utente u;

    public void initialize(){
        u = NavigatorView.getUtenteSelezionato();
        riempiCampi();
    }

    public void riempiCampi(){
        nomeField.setText(u.getNome());
        cognomeField.setText(u.getCognome());
        dataNascitaField.setText(u.getDataNascita().toString());
        codiceFiscaleField.setText(u.getCodFiscale());
        genereField.setText(u.getGenere());
        indirizzoField.setText(u.getIndirizzo());
        emailField.setText(u.getEmail());
        telefonoField.setText(u.getTelefono());
    }
}
