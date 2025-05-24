package com.dashapp.controller;

import com.dashapp.Main;
import com.dashapp.model.User;
import com.dashapp.model.UserRepository;
import com.dashapp.view.NavigatorView;
import com.dashapp.controller.MainControllerTeo;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {
    @FXML
    private TextField emailField;
    
    @FXML
    private PasswordField passwordField;
    
    @FXML
    private Label statusLabel;
    
    private UserRepository userRepository;
    
    @FXML
    public void initialize() {
        userRepository = Main.getUserRepository();
        statusLabel.setVisible(false);
    }
    
    @FXML
    private void handleLogin() {
        String email = emailField.getText();
        String password = passwordField.getText();
        
        if (email.isEmpty() && password.isEmpty()) {
            showError("Email e password non inseriti");
            return;
        }
        if(email.isEmpty()){
            showError("Email non inserita");
            return;
        }
        if(password.isEmpty()){
            showError("Password non inserita");
            return;
        }


        User user = userRepository.getUser(email);
        if (user != null && user.checkPassword(password)) {
            // Login successful

            NavigatorView.getMainController().viewSidebar();
            NavigatorView.setAuthenticatedUser(email);
            NavigatorView.navigateToDashboardMedic();

        } else {
            showError("Email o password non corretti");
        }
    }
    
    @FXML
    private void handleRegister() {
        NavigatorView.navigateToRegister();
    }
    
    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
        statusLabel.setVisible(true);
    }
}