package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.Farmaco;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
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

    public void listaPazienti(){
        bodyContainer.getChildren().clear();

        List<Utente> utenti;
        try {
            utenti = List.of(ds.getUtenti());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Vedi";
        String titolo = "lista pazienti";
        tabellaUtenti(titolo, utenti, textButton, Color.web("#34bccc"));

        LabelBoxDashboard.setText("TUTTI I PAZIENTI");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }


    public void listaFarmaci(){
        bodyContainer.getChildren().clear();

        List<Farmaco> farmaco;
        try {
            farmaco = List.of(ds.getFarmaci());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Vedi";
        String titolo = "lista farmaci";
        tabellaFarmaci(titolo, farmaco, textButton, Color.web("#34bccc"));

        LabelBoxDashboard.setText("TUTTI I FARMACI");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }



    public void assegnazioneMedPaz() {
        bodyContainer.getChildren().clear();

        List<Utente> utenti;
        try {
            utenti = List.of(ds.getUtenti());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Prendi in carico";
        String titolo = "Pazienti senza assegnazione medica";
        tabellaUtenti(titolo, utenti, textButton, Color.web("#34bccc"));

        textButton = "Già assegnato";
        titolo = "Pazienti già assegnati ad un medico";
        tabellaUtenti(titolo, utenti, textButton, Color.web("#588157"));

        LabelBoxDashboard.setText("GESTIONE ASSEGNAZIONE PAZIENTI");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }

    public void aggiungiFarmaco() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("AGGIUNGI FARMACO");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardMedic/AddFarmaci.fxml"));
        Parent addFarmacoContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addFarmacoContent);

    }


    private void tabellaFarmaci(String titolo, List<Farmaco> farmaci, String textButton, Color color) {
        // Titolo della tabella
        Label titoloTabella = new Label(textButton);
        titoloTabella.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222;" +
                        "-fx-background-color: #f0f0f0;" +
                        "-fx-padding: 10;" +
                        "-fx-border-color: #d0d0d0;" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-border-style: solid;"
        );
        titoloTabella.setMaxWidth(Double.MAX_VALUE);
        titoloTabella.setAlignment(Pos.CENTER_LEFT);
        titoloTabella.setText(titolo);


        VBox listaFarmaciBox = new VBox(2);
        listaFarmaciBox.setPrefWidth(1000);
        listaFarmaciBox.setSpacing(5);

        double nomeWidth = 200;
        double descrizioneWidth = 600;
        double azioneWidth = 160;

        // Intestazione
        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label nomeHeader = creaHeader("Nome", nomeWidth);
        Label descrizioneHeader = creaHeader("Descrizione", descrizioneWidth);
        Label azioneHeader = creaHeader("Azione", azioneWidth);

        intestazione.getChildren().addAll(nomeHeader, descrizioneHeader, azioneHeader);
        listaFarmaciBox.getChildren().add(intestazione);

        // Riga per ogni farmaco
        for (Farmaco f : farmaci) {
            HBox rigaFarmaco = new HBox(10);
            rigaFarmaco.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaFarmaco.setAlignment(Pos.CENTER_LEFT);

            Label nomeLabel = creaCell(f.getNome(), nomeWidth);
            Label descrizioneLabel = creaCell(f.getDescrizione(), descrizioneWidth);

            Button azioneButton = new Button(textButton);
            azioneButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            azioneButton.setPrefWidth(azioneWidth);
            azioneButton.setOnAction(e -> {
                System.out.println("Azione su farmaco: " + f.getNome());
            });

            rigaFarmaco.getChildren().addAll(nomeLabel, descrizioneLabel, azioneButton);
            listaFarmaciBox.getChildren().add(rigaFarmaco);
        }

        // Margini tra una tabella e l'altra
        VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        VBox.setMargin(listaFarmaciBox, new javafx.geometry.Insets(0, 0, 20, 0));

        bodyContainer.getChildren().addAll(titoloTabella, listaFarmaciBox);
    }



    // METODO PER CREARE TABELLA @param (lista di utenti, il nome del bottone da visualizzare e il colore del bottone)
    private void tabellaUtenti(String titolo, List<Utente> utenti, String textButton, Color color){
        Label titoloTabella = new Label();
        titoloTabella.setText(titolo);
        titoloTabella.setStyle(
                "-fx-font-size: 18px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #222;" +
                        "-fx-background-color: #f0f0f0;" +
                        "-fx-padding: 10;" +
                        "-fx-border-color: #d0d0d0;" +
                        "-fx-border-width: 0 0 1 0;" +
                        "-fx-border-style: solid;"
        );
        titoloTabella.setMaxWidth(Double.MAX_VALUE);
        titoloTabella.setAlignment(Pos.CENTER_LEFT);

        VBox listaUtentiBox = new VBox(2);
        listaUtentiBox.setPrefWidth(2000);
        listaUtentiBox.setSpacing(5);

        double nomeWidth = 120;
        double cognomeWidth = 120;
        double cfWidth = 150;
        double nascitaWidth = 120;
        double indirizzoWidth = 150;
        double emailWidth = 200;
        double telWidth = 100;
        double azioneWidth = 160;

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

        // Margine tra una tabella e l'altra
        VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        VBox.setMargin(listaUtentiBox, new javafx.geometry.Insets(0, 0, 20, 0));

        bodyContainer.getChildren().add(titoloTabella);
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
