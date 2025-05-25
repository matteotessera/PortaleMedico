package com.dashapp.controller;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import com.dashapp.services.LoginService;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.application.Platform;


public class LoginController {

    private LoginService loginService = new LoginService();

    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label statusLabel;
    

    @FXML
    public void initialize() {
        statusLabel.setVisible(false);
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
                    showMessage("Login effettuato con successo!");
                    //statusLabel.setText("Login effettuato! Ruolo: " + loginService.getUserRole());
                    NavigatorView.getMainController().viewSidebar();
                    NavigatorView.setAuthenticatedUser(email);
                    try {
                        NavigatorView.textNavbar();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                    try {
                        navigate(email);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                    //NavigatorView.navigateToDashboardMedic();
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
        }else{
            NavigatorView.navigateToDashboardPatient();
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