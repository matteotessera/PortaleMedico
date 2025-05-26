package com.dashapp.controller;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;

public class cambioPasswordController {

    @FXML
    private PasswordField passwordField;

    @FXML
    private PasswordField confermaPasswordField;

    @FXML
    private Label statusLabel;

    @FXML
    public void initialize() {
        statusLabel.setVisible(false);
    }


    public void cambiaPassword() throws Exception {
        String password = passwordField.getText();
        String confermaPassword = confermaPasswordField.getText();
        statusLabel.setVisible(true);


        if(password.isEmpty() || confermaPassword.isEmpty()){
            showError("Password non inserita");
            return;
        }

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

            DataService ds = new DataService();
            String email = NavigatorView.getAuthenticatedUser();
            Utente u = ds.getUtenteByEmail(email);

            ds.updatePassword(u.getId(), password);

            // Se tutte le condizioni sono rispettate
            statusLabel.setText("Password cambiata con successo!");
            statusLabel.setStyle("-fx-text-fill: green;");
            statusLabel.setVisible(true);


            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Informazione");
            alert.setHeaderText(null);
            alert.setContentText("Password cambiata con successo!");
            alert.showAndWait();

            NavigatorView.navigateToLogin();
    }

    private void showError(String message) {
        statusLabel.setText(message);
        statusLabel.setStyle("-fx-text-fill: red;");
        statusLabel.setVisible(true);
    }

}
