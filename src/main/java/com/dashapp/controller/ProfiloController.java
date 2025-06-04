package com.dashapp.controller;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.util.converter.LocalDateStringConverter;

import java.time.LocalDate;

public class ProfiloController {
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
    public TextField genereField;
    @FXML
    public PasswordField passwordField;
    @FXML
    public PasswordField confermaPasswordField;

}
