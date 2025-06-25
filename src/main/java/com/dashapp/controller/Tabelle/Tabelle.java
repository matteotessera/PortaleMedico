package com.dashapp.controller.Tabelle;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.*;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Tabelle {

    private DataService ds;

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

        double dataWidth = 1000;
        double valoreWidth = 1000;
        double tipoWidth = 1000;
        double pastoWidth = 1000;
        double azioneWidth = 1000;

        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label dataHeader = creaHeader("data", dataWidth);
        Label valoreHeader = creaHeader("valore", valoreWidth);
        Label tipoHeader = creaHeader("tipo", tipoWidth);
        Label pastoHeader = creaHeader("pasto", pastoWidth);
        Label fillHeader = creaHeader("", pastoWidth);


        intestazione.getChildren().addAll(
                dataHeader, valoreHeader, tipoHeader, pastoHeader, fillHeader
        );

        listaUtentiBox.getChildren().add(intestazione);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Rilevazione r : rilevazioni) {
            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);
            String dataFormattata = r.getData().format(formatter);

            Label dataLabel = creaCell(dataFormattata, dataWidth);
            Label cognomeLabel = creaCell(r.getValore(), valoreWidth);
            Label tipoLabel = creaCell(r.getTipo(), tipoWidth);
            Label pastoLabel = creaCell(r.getPasto(), pastoWidth);



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
                    dataLabel, cognomeLabel, tipoLabel, pastoLabel, prendiInCaricoButton
            );

            listaUtentiBox.getChildren().add(rigaUtente);
        }

        // Margine tra una tabella e l'altra
        VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        VBox.setMargin(listaUtentiBox, new javafx.geometry.Insets(0, 0, 20, 0));

        bodyContainer.getChildren().add(titoloTabella);
        bodyContainer.getChildren().add(listaUtentiBox);
    }

    public void tabellaAssunzioni(String titolo, List<Assunzione> assunzioni, String textButton, Color color, VBox bodyContainer) throws Exception {
        ds = new DataService();

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

        double dataWidth = 1000;
        double statoWidth = 1000;
        double doseWidth = 1000;
        double farmacoWidth = 1000;
        double azioneWidth = 1000;


        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label dataHeader = creaHeader("data", dataWidth);
        Label statoHeader = creaHeader("stato", statoWidth);
        Label doseHeader = creaHeader("dose", doseWidth);
        Label farmacoHeader = creaHeader("farmaco", farmacoWidth);
        Label fillHeader= creaHeader("", farmacoWidth);


        intestazione.getChildren().addAll(
                dataHeader, farmacoHeader,  doseHeader, statoHeader, fillHeader
        );

        listaUtentiBox.getChildren().add(intestazione);

        List<Assunzione> assunzioniOrdinate = new ArrayList<>(assunzioni);
        assunzioniOrdinate.sort(Comparator.comparing(Assunzione::getData));

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        for (Assunzione a : assunzioniOrdinate) {
            LocalDateTime data = a.getData();
            String dataFormattata = data.format(formatter);


            AssociazioneFarmaco associazioneFarmaco = ds.getAssociazioneFarmacoById( a.getIdAssociazioneFarmaco());
            Farmaco farmaco = ds.getFarmacoById(associazioneFarmaco.getIdFarmaco());


            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);

            Label dataLabel = creaCell(dataFormattata, dataWidth);
            Label statoLabel = creaCell(a.getStato(), statoWidth);
            Label farmacoLabel = creaCell(farmaco.getNome(), farmacoWidth);
            Label doseLabel = creaCell(String.valueOf(associazioneFarmaco.getDose()), doseWidth);




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
                    dataLabel, farmacoLabel, doseLabel, statoLabel, prendiInCaricoButton
            );

            listaUtentiBox.getChildren().add(rigaUtente);
        }

        // Margine tra una tabella e l'altra
        VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        VBox.setMargin(listaUtentiBox, new javafx.geometry.Insets(0, 0, 20, 0));

        bodyContainer.getChildren().add(titoloTabella);
        bodyContainer.getChildren().add(listaUtentiBox);
    }

    public void tabellaTerapie(String titolo, List<Terapia> terapie, String textButton, Color color, VBox bodyContainer){
        ds = new DataService();

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

        double dataInizioWidth = 1000;
        double dataFineWidth = 1000;
        double noteWidth = 1000;
        double azioneWidth = 1000;


        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label inizioHeader = creaHeader("Inzio", dataInizioWidth);
        Label fineHeader = creaHeader("Fine", dataFineWidth);
        Label noteHeader = creaHeader("note", noteWidth);
        Label fillHeader = creaHeader("", noteWidth);



        intestazione.getChildren().addAll(
               inizioHeader, fineHeader, noteHeader, fillHeader
        );

        listaUtentiBox.getChildren().add(intestazione);



        for (Terapia t : terapie) {
            LocalDate dataInizio = t.getDataInizio();
            LocalDate dataFine = t.getDataFine();





            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);

            Label dataLabel = creaCell(dataInizio.toString(), dataInizioWidth);
            Label statoLabel = creaCell(dataFine.toString(), dataFineWidth);
            Label doseLabel = creaCell(String.valueOf(t.getNote()), noteWidth);



            Button prendiInCaricoButton = new Button(textButton);
            prendiInCaricoButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            prendiInCaricoButton.setPrefWidth(azioneWidth);
            prendiInCaricoButton.setOnAction(e -> {
                System.out.println("Preso in carico: " + t.getNote());
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

    public void tabellaSintomo(String titolo, List<Sintomo> sintomo, String textButton, Color color, VBox bodyContainer, BoxDashboardControllerPatient controller){
        ds = new DataService();

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

        double dataWidth = 1000;
        double descrizioneWidth = 1000;
        double azioneWidth = 1000;


        HBox intestazione = new HBox(10);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label inizioHeader = creaHeader("Data", dataWidth);
        Label fineHeader = creaHeader("Descrizone", descrizioneWidth);
        Label fillHeader = creaHeader("", descrizioneWidth);



        intestazione.getChildren().addAll(
                inizioHeader, fineHeader, fillHeader
        );

        listaUtentiBox.getChildren().add(intestazione);


        List<Sintomo> sintomiOrdinati = new ArrayList<>(sintomo);
        sintomiOrdinati.sort(Comparator.comparing(Sintomo::getData));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        for (Sintomo s : sintomiOrdinati) {
            LocalDateTime data = s.getData();
            String dataFormattata = data.format(formatter);





            HBox rigaUtente = new HBox(10);
            rigaUtente.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaUtente.setAlignment(Pos.CENTER_LEFT);

            Label dataLabel = creaCell(dataFormattata, dataWidth);
            Label descLabel = creaCell(s.getDescrizione(), descrizioneWidth);




            Button prendiInCaricoButton = new Button(textButton);
            prendiInCaricoButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            prendiInCaricoButton.setPrefWidth(azioneWidth);
            prendiInCaricoButton.setOnAction(e -> {
                System.out.println("Preso in carico: " + s.getDescrizione());
                try {
                    NavigatorView.setSintomoSelezionato(s);
                    controller.vediSintomo();
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            });

            rigaUtente.getChildren().addAll(
                    dataLabel, descLabel, prendiInCaricoButton
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
