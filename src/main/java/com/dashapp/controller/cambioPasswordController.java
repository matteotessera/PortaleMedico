package com.dashapp.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class cambioPasswordController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confermaPasswordField;

    @FXML
    private Label statusLabel;

    public void cambiaPassword(){
        String password = passwordField.getText();
        String confermaPassword = confermaPasswordField.getText();

        if (!password.equals(confermaPassword)) {
            showError("Le password non corrispondono");
            return;
        }

        if (password.length() < 6) {
            showError("La password deve contenere almeno 6 caratteri");
            return;
        }

        // Almeno una lettera (maiuscola o minuscola)
        if (!password.matches(".*[a-zA-Z].*")) {
            showError("La password deve contenere almeno una lettera");
            return;
        }

        // Almeno un numero
        if (!password.matches(".*[0-9].*")) {
            showError("La password deve contenere almeno un numero");
            return;
        }

        // Almeno un carattere speciale tra . * _ ? ! @
        if (!password.matches(".*[\\.\\*\\_\\?\\!@].*")) {
            showError("La password deve contenere almeno un carattere speciale tra . * _ ? ! @");
            return;
        }

        // Se tutte le condizioni sono rispettate
        statusLabel.setText("Password cambiata con successo!");
        statusLabel.setStyle("-fx-text-fill: green;");
        statusLabel.setVisible(true);
    }

    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
        statusLabel.setVisible(true);
    }

}
