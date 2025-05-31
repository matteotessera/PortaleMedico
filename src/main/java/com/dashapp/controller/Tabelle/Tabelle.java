package com.dashapp.controller.Tabelle;

import com.dashapp.model.Assunzione;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Rilevazione;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.util.List;

public class Tabelle {



    public void tabellaFarmaci(String titolo, List<Farmaco> farmaci, String textButton, Color color, VBox bodyContainer) {
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
    public void tabellaRilevazioni(String titolo, List<Rilevazione> rilevazioni, String textButton, Color color, VBox bodyContainer){
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

        double dataWidth = 120;
        double valoreWidth = 120;
        double tipoWidth = 150;
        double azioneWidth = 150;

        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label dataHeader = creaHeader("data", dataWidth);
        Label valoreHeader = creaHeader("valore", valoreWidth);
        Label tipoHeader = creaHeader("tipo", tipoWidth);


        intestazione.getChildren().addAll(
                dataHeader, valoreHeader, tipoHeader
        );

        listaUtentiBox.getChildren().add(intestazione);

        for (Rilevazione r : rilevazioni) {
            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);

            Label dataLabel = creaCell(r.getData().toString(), dataWidth);
            Label cognomeLabel = creaCell(r.getValore(), valoreWidth);
            Label tipoLabel = creaCell(r.getTipo(), tipoWidth);


            Button prendiInCaricoButton = new Button(textButton);
            prendiInCaricoButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            prendiInCaricoButton.setPrefWidth(azioneWidth);
            prendiInCaricoButton.setOnAction(e -> {
                System.out.println("Preso in carico: " + r.getValore() + " " + r.getData());
            });

            rigaUtente.getChildren().addAll(
                    dataLabel, cognomeLabel, tipoLabel, prendiInCaricoButton
            );

            listaUtentiBox.getChildren().add(rigaUtente);
        }

        // Margine tra una tabella e l'altra
        VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        VBox.setMargin(listaUtentiBox, new javafx.geometry.Insets(0, 0, 20, 0));

        bodyContainer.getChildren().add(titoloTabella);
        bodyContainer.getChildren().add(listaUtentiBox);
    }

    public void tabellaAssunzioni(String titolo, List<Assunzione> assunzioni, String textButton, Color color, VBox bodyContainer){
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

        double dataWidth = 120;
        double statoWidth = 120;
        double doseWidth = 150;
        double azioneWidth = 150;


        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label dataHeader = creaHeader("data", dataWidth);
        Label statoHeader = creaHeader("stato", statoWidth);
        Label doseHeader = creaHeader("dose", doseWidth);


        intestazione.getChildren().addAll(
                dataHeader, statoHeader, doseHeader
        );

        listaUtentiBox.getChildren().add(intestazione);

        for (Assunzione a : assunzioni) {
            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);

            Label dataLabel = creaCell(a.getData().toString(), dataWidth);
            Label statoLabel = creaCell(a.getStato(), statoWidth);
            Label doseLabel = creaCell(String.valueOf(a.getDose()), doseWidth);


            Button prendiInCaricoButton = new Button(textButton);
            prendiInCaricoButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            prendiInCaricoButton.setPrefWidth(azioneWidth);
            prendiInCaricoButton.setOnAction(e -> {
                System.out.println("Preso in carico: " + a.getDose() + " " + a.getData());
            });

            rigaUtente.getChildren().addAll(
                    dataLabel, statoLabel, doseLabel, prendiInCaricoButton
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
