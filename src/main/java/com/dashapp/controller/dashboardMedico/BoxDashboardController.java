package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.awt.*;
import java.io.IOException;
import java.util.List;

public class BoxDashboardController {

    @FXML
    private VBox bodyContainer;

    @FXML
    private Label LabelBoxDashboard;

    private DashboardMedicController dashboardController;

    private DataService ds;

    @FXML
    public void initialize() {
        ds = new DataService();
        assegnazioneMedPaz();
    }

    public void setDashboardController(DashboardMedicController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void BackToDashboard() throws IOException {
        if (dashboardController != null) {
            dashboardController.backToDashboard();
        }
    }

    private void assegnazioneMedPaz() {
        List<Utente> utenti;
        try {
            utenti = List.of(ds.getUtenti());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Prendi in carico";
        tabella(utenti, textButton, Color.web("#53FF45"));

        textButton = "Paziente già assegnato";
        tabella(utenti, textButton, Color.web("#34bccc"));

        LabelBoxDashboard.setText("Gestione assegnazione utenti");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px");


    }

    private void tabella(List<Utente> utenti, String textButton, Color color){
        VBox listaUtentiBox = new VBox(2);
        listaUtentiBox.setPrefWidth(2000);

        double nomeWidth = 120;
        double cognomeWidth = 120;
        double cfWidth = 150;
        double nascitaWidth = 120;
        double indirizzoWidth = 150;
        double emailWidth = 200;
        double telWidth = 100;
        double azioneWidth = 160;

        // Intestazione
        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label nomeHeader = creaHeader("Nome", nomeWidth);
        Label cognomeHeader = creaHeader("Cognome", cognomeWidth);
        Label cfHeader = creaHeader("Codice Fiscale", cfWidth);
        Label nascitaHeader = creaHeader("Data Nascita", nascitaWidth);
        Label indirizzoHeader = creaHeader("Indirizzo", indirizzoWidth);
        Label emailHeader = creaHeader("Email", emailWidth);
        Label telHeader = creaHeader("Telefono", telWidth);
        Label azioneHeader = creaHeader("Azione", azioneWidth);

        intestazione.getChildren().addAll(
                nomeHeader, cognomeHeader, cfHeader, nascitaHeader, indirizzoHeader, emailHeader, telHeader, azioneHeader
        );

        listaUtentiBox.getChildren().add(intestazione);

        // Riga per ogni utente
        for (Utente u : utenti) {
            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);

            Label nomeLabel = creaCell(u.getNome(), nomeWidth);
            Label cognomeLabel = creaCell(u.getCognome(), cognomeWidth);
            Label cfLabel = creaCell(u.getCodFiscale(), cfWidth);
            Label nascitaLabel = creaCell(
                    u.getDataNascita() != null ? u.getDataNascita().toString() : "-", nascitaWidth);
            Label indirizzoLabel = creaCell(u.getIndirizzo(), indirizzoWidth);
            Label emailLabel = creaCell(u.getEmail(), emailWidth);
            Label telLabel = creaCell(u.getTelefono(), telWidth);

            Button prendiInCaricoButton = new Button(textButton);
            prendiInCaricoButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            prendiInCaricoButton.setPrefWidth(azioneWidth);
            prendiInCaricoButton.setOnAction(e -> {
                System.out.println("Preso in carico: " + u.getNome() + " " + u.getCognome());
            });

            rigaUtente.getChildren().addAll(
                    nomeLabel, cognomeLabel, cfLabel, nascitaLabel, indirizzoLabel, emailLabel, telLabel, prendiInCaricoButton
            );

            listaUtentiBox.getChildren().add(rigaUtente);
        }

        bodyContainer.getChildren().add(listaUtentiBox);
    }

    private String toHex(Color color) {
        return String.format("#%02X%02X%02X",
                (int)(color.getRed()*255),
                (int)(color.getGreen()*255),
                (int)(color.getBlue()*255));
    }


    // Metodo di utilità per intestazione
    private Label creaHeader(String text, double width) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold;");
        label.setPrefWidth(width);
        label.setAlignment(Pos.CENTER_LEFT);
        return label;
    }

    // Metodo di utilità per celle
    private Label creaCell(String text, double width) {
        Label label = new Label(text != null ? text : "-");
        label.setPrefWidth(width);
        label.setAlignment(Pos.CENTER_LEFT);
        label.setMaxWidth(Double.MAX_VALUE);
        HBox.setHgrow(label, Priority.ALWAYS);
        return label;
    }
}
