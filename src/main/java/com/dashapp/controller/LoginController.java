package com.dashapp.controller;

import com.dashapp.model.Terapia;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import com.dashapp.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Platform;

import java.time.LocalDate;


public class LoginController {

    private LoginService loginService = new LoginService();

    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label statusLabel;

    private int idUtente;

    private DataService ds;
    

    @FXML
    public void initialize() {
        statusLabel.setVisible(false);

        this.ds = new DataService();

        // Esegui handleLogin premendo Enter in emailField
        emailField.setOnAction(event -> handleLogin());

        // Esegui handleLogin premendo Enter in passwordField
        passwordField.setOnAction(event -> handleLogin());
    }
    
    @FXML
    private void handleLogin() {
        statusLabel.setVisible(true);
        String email = emailField.getText().trim();
        String password = passwordField.getText();

        if (email.isEmpty() && password.isEmpty()) {
            showError("Email e password non inseriti");
            return;
        }
        if (email.isEmpty()) {
            showError("Email non inserita");
            return;
        }
        if(password.isEmpty()){
            showError("Password non inserita");
            return;
        }


        statusLabel.setText("Login in corso...");
        statusLabel.setStyle("-fx-text-fill: green;");

        loginService.login(email, password).thenAccept(success -> {
            Platform.runLater(() -> {
                if (success) {
                    NavigatorView.setAuthenticatedUser(email);

                    //se la password è <6 vuoldire che è il primo accesso e l'utente deve cambire password
                    if(password.length() < 6){
                        NavigatorView.navigateToChangePassword();
                    }else {
                        //Aggiungo accesso cosi per monitorare
                        try {
                            ds.addAccesso(ds.getUtenteByEmail(email).getId());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                        showMessage("Login effettuato con successo!");

                        NavigatorView.getMainController().viewSidebar();
                        try {
                            navigate(email);
                            NavigatorView.textNavbar();
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    }
                } else {
                    showError("Email o password non corretti");
                }
            });
        });

    }

    private void navigate(String email) throws Exception {
        DataService ds = new DataService();
        Utente u = ds.getUtenteByEmail(email);

        if(u.getRuolo().equals("medico")){
            NavigatorView.navigateToDashboardMedic();
        }else if(u.getRuolo().equals("paziente")){
            NavigatorView.navigateToDashboardPatient();
        }else if(u.getRuolo().equals("admin")){
            NavigatorView.navigateToDashboardAdmin();
        }
    }

    private void showMessage(String message){
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: green;");
        statusLabel.setVisible(true);
    }

    
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
        statusLabel.setVisible(true);
    }
}