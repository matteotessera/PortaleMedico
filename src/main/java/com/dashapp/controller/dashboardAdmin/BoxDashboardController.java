package com.dashapp.controller.dashboardAdmin;

import com.dashapp.controller.ProfiloController;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class BoxDashboardController {

    @FXML
    private VBox bodyContainer;

    @FXML
    private Label LabelBoxDashboard;

    private DashboardAdminController dashboardController;

    private DataService ds;


    @FXML
    public void initialize() {
        ds = new DataService();
    }

    public void setDashboardController(DashboardAdminController dashboardController) {
        this.dashboardController = dashboardController;
    }

    @FXML
    private void BackToDashboard() throws IOException {
        if (dashboardController != null) {
            dashboardController.backToDashboard();
        }
    }

    public void listaUtenti() {
        bodyContainer.getChildren().clear();

        List<Utente> utenti;
        try {
            utenti = new ArrayList<>(List.of(ds.getUtenti()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Vedi";
        String titolo = null;

        // Tasti filtro
        HBox ordinamentoBox = new HBox(10);
        ordinamentoBox.setAlignment(Pos.CENTER_LEFT);
        ordinamentoBox.setPadding(new Insets(10, 0, 10, 0));


        // ComboBox
        ComboBox<String> campoOrdinamento = new ComboBox<>();
        campoOrdinamento.getItems().addAll("Nome", "Cognome", "Data di nascita", "Sesso");
        campoOrdinamento.setPromptText("Ordina per");
        campoOrdinamento.setStyle("-fx-background-color: white; " + "-fx-border-color: #1e3746; " + "-fx-background-radius: 8px; " + "-fx-border-radius: 8px; " + "-fx-text-fill: #1e3746;");
        campoOrdinamento.setPrefHeight(30);

        ComboBox<String> ordine = new ComboBox<>();
        ordine.getItems().addAll("Crescente", "Decrescente");
        ordine.setPromptText("Ordine");
        ordine.setStyle("-fx-background-color: white; " + "-fx-border-color: #1e3746; " + "-fx-background-radius: 8px; " + "-fx-border-radius: 8px; " + "-fx-text-fill: #1e3746;");
        ordine.setPrefHeight(30);

        // Bottone per applicare ordinamento
        Button ordinaButton = new Button("Ordina");
        ordinaButton.setStyle("-fx-background-color: #1e3746;; -fx-text-fill: white; -fx-font-family: 'Roboto Black';");
        ordinaButton.setPrefHeight(20);

        ordinamentoBox.getChildren().addAll(campoOrdinamento, ordine, ordinaButton);
        bodyContainer.getChildren().add(ordinamentoBox);

        // Metodo per caricare la tabella con lista ordinata
        Runnable aggiornaTabella = () -> {
            bodyContainer.getChildren().removeIf(node -> node != ordinamentoBox); // pulisce tranne i controlli sopra
            tabellaUtenti(titolo, utenti, textButton, Color.web("#1e3746"));
        };

        aggiornaTabella.run();

        // Azione sul bottone di ordinamento
        ordinaButton.setOnAction(e -> {
            String campo = campoOrdinamento.getValue();
            String tipoOrdine = ordine.getValue();

            if (campo == null || tipoOrdine == null) {
                return;
            }

            Comparator<Utente> comparator = null;

            switch (campo) {
                case "Nome":
                    comparator = Comparator.comparing(Utente::getNome, String.CASE_INSENSITIVE_ORDER);
                    break;
                case "Cognome":
                    comparator = Comparator.comparing(Utente::getCognome, String.CASE_INSENSITIVE_ORDER);
                    break;
                case "Data di nascita":
                    comparator = Comparator.comparing(Utente::getDataNascita);
                    break;
                case "Sesso":
                    comparator = Comparator.comparing(Utente::getGenere, String.CASE_INSENSITIVE_ORDER);
                    break;
            }

            if (comparator != null) {
                if ("Decrescente".equals(tipoOrdine)) {
                    comparator = comparator.reversed();
                }
                utenti.sort(comparator);
                aggiornaTabella.run();
            }
        });


        //tabellaUtenti(titolo, utenti, textButton, Color.web("#34bccc"));

        LabelBoxDashboard.setText("Lista utenti");
        LabelBoxDashboard.setStyle("-fx-font-family: 'Roboto Black'; -fx-font-size: 22px; -fx-text-fill: #1e3746; ");
    }


    public void aggiungiUtente() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Aggiungi utente");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-text-fill: #cb6ce6; ");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardAdmin/AddUtenti.fxml"));
        Parent addFarmacoContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addFarmacoContent);
    }


    public void listaFarmaci() {
        bodyContainer.getChildren().clear();

        List<Farmaco> farmaci;
        try {
            farmaci = new ArrayList<>(List.of(ds.getFarmaci()));  // lista mutabile per sort
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Vedi";
        String titolo = null;

        // HBox per i controlli di ordinamento
        HBox ordinamentoBox = new HBox(10);
        ordinamentoBox.setAlignment(Pos.CENTER_LEFT);
        ordinamentoBox.setPadding(new Insets(10, 0, 10, 0));

        // ComboBox per scegliere il campo di ordinamento (Nome o Descrizione)
        ComboBox<String> campoOrdinamento = new ComboBox<>();
        campoOrdinamento.getItems().addAll("Nome", "Descrizione");
        campoOrdinamento.setPromptText("Ordina per");
        campoOrdinamento.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: #1e3746; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-text-fill: #1e3746;"
        );
        campoOrdinamento.setPrefHeight(30);

        // ComboBox per scegliere l'ordine (Crescente o Decrescente)
        ComboBox<String> ordine = new ComboBox<>();
        ordine.getItems().addAll("Crescente", "Decrescente");
        ordine.setPromptText("Ordine");
        ordine.setStyle(
                "-fx-background-color: white; " +
                        "-fx-border-color: #1e3746; " +
                        "-fx-background-radius: 8px; " +
                        "-fx-border-radius: 8px; " +
                        "-fx-text-fill: #1e3746;"
        );
        ordine.setPrefHeight(30);

        // Bottone per applicare ordinamento
        Button ordinaButton = new Button("Ordina");
        ordinaButton.setStyle("-fx-background-color: #1e3746; -fx-text-fill: white; -fx-font-family: 'Roboto Black';");
        ordinaButton.setPrefHeight(20);

        ordinamentoBox.getChildren().addAll(campoOrdinamento, ordine, ordinaButton);
        bodyContainer.getChildren().add(ordinamentoBox);

        // Metodo per aggiornare la tabella con la lista ordinata
        Runnable aggiornaTabella = () -> {
            bodyContainer.getChildren().removeIf(node -> node != ordinamentoBox);
            tabellaFarmaci(titolo, farmaci, textButton, Color.web("#1e3746"));
        };

        aggiornaTabella.run();

        // Azione sul bottone di ordinamento
        ordinaButton.setOnAction(e -> {
            String campo = campoOrdinamento.getValue();
            String tipoOrdine = ordine.getValue();

            if (campo == null || tipoOrdine == null) {
                return;
            }

            Comparator<Farmaco> comparator = null;

            switch (campo) {
                case "Nome":
                    comparator = Comparator.comparing(Farmaco::getNome, String.CASE_INSENSITIVE_ORDER);
                    break;
                case "Descrizione":
                    comparator = Comparator.comparing(Farmaco::getDescrizione, String.CASE_INSENSITIVE_ORDER);
                    break;
            }

            if (comparator != null) {
                if ("Decrescente".equals(tipoOrdine)) {
                    comparator = comparator.reversed();
                }
                farmaci.sort(comparator);
                aggiornaTabella.run();
            }
        });

        LabelBoxDashboard.setText("Tutti i farmaci");
        LabelBoxDashboard.setStyle("-fx-font-family: 'Roboto Black'; -fx-font-size: 22px; -fx-text-fill: #1e3746;");
    }

    public void aggiungiFarmaco() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Aggiungi farmaco");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-text-fill: #cb6ce6; ");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardAdmin/AddFarmaci.fxml"));
        Parent addFarmacoContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addFarmacoContent);
    }

    public void vediPofiloPaziente() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna ai pazienti");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaUtenti());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/AnagraficaView.fxml"));
        Parent addFarmacoContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addFarmacoContent);
    }

    private void tabellaFarmaci(String titolo, List<Farmaco> farmaci, String textButton, Color color) {
        // Titolo della tabella
        Label titoloTabella = new Label(titolo);

        if(titolo != null) {
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
        }

        VBox listaFarmaciBox = new VBox(2);
        listaFarmaciBox.setPrefWidth(1000);
        listaFarmaciBox.setSpacing(5);

        double nomeWidth = 80;
        double descrizioneWidth = 600;
        double azioneWidth = 90;
        double spazioTraBottoni = 10;

        // Intestazione
        HBox intestazione = new HBox(spazioTraBottoni);
        intestazione.setStyle("-fx-background-color: #e0e0e0; -fx-padding: 8;");
        intestazione.setAlignment(Pos.CENTER_LEFT);

        Label nomeHeader = creaHeader("Nome", nomeWidth);
        Label descrizioneHeader = creaHeader("Descrizione", descrizioneWidth);

        // Larghezza intestazione azione = due bottoni + spazio tra loro
        double azioneTotalWidth = azioneWidth * 2 + spazioTraBottoni;
        Label azioneHeader = creaHeader("Azione", azioneTotalWidth);

        intestazione.getChildren().addAll(nomeHeader, descrizioneHeader, azioneHeader);
        listaFarmaciBox.getChildren().add(intestazione);

        // Riga per ogni farmaco
        for (Farmaco f : farmaci) {
            HBox rigaFarmaco = new HBox(spazioTraBottoni);
            rigaFarmaco.setStyle("-fx-padding: 5; -fx-alignment: CENTER_LEFT; -fx-background-color: #f9f9f9;");
            rigaFarmaco.setAlignment(Pos.CENTER_LEFT);

            Label nomeLabel = creaCell(f.getNome(), nomeWidth);
            Label descrizioneLabel = creaCell(f.getDescrizione(), descrizioneWidth);

            // Bottone Azione
            Button azioneButton = new Button(textButton);
            azioneButton.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            azioneButton.setPrefWidth(azioneWidth);
            azioneButton.setOnAction(e -> {
                System.out.println("Azione su farmaco: " + f.getNome());
            });

            // Bottone Elimina
            Button eliminaButton = new Button("Elimina");
            eliminaButton.setStyle(
                    "-fx-background-color: #d9534f;" +
                            "-fx-text-fill: white;"
            );
            eliminaButton.setPrefWidth(azioneWidth);
            eliminaButton.setOnAction(e -> {

                String titoloAlert = "Conferma eliminazione";
                String text = "Sei sicuro di eliminare il farmaco " + f.getNome() +"?";

                Optional<ButtonType> result = alertEliminazione(titoloAlert, text);
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    try {
                        ds.deleteFarmaco(f.getId());
                        System.out.println("Farmaco eliminato");
                        listaFarmaci();

                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                } else {
                    System.out.println("Eliminazione annullata");
                }
            });

            rigaFarmaco.getChildren().addAll(nomeLabel, descrizioneLabel, azioneButton, eliminaButton);
            listaFarmaciBox.getChildren().add(rigaFarmaco);
        }

        // Margini tra una tabella e l'altra
        if (titolo != null) {
            VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        }
        VBox.setMargin(listaFarmaciBox, new javafx.geometry.Insets(0, 0, 20, 0));

        bodyContainer.getChildren().addAll(titoloTabella, listaFarmaciBox);
    }




    // METODO PER CREARE TABELLA @param (lista di utenti, il nome del bottone da visualizzare e il colore del bottone)
    private void tabellaUtenti(String titolo, List<Utente> utenti, String textButton, Color color){

        Label titoloTabella = new Label();

        if(titolo != null){
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
        }


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

            Button buttonUtente = new Button(textButton);
            buttonUtente.setStyle(
                    "-fx-background-color: " + toHex(color) + ";" +
                            "-fx-text-fill: white;"
            );
            buttonUtente.setPrefWidth(azioneWidth);
            buttonUtente.setOnAction(e -> {
                    NavigatorView.setUtenteSelezionato(u);
                    try {
                        vediPofiloPaziente();
                    } catch (IOException ex) {
                        throw new RuntimeException(ex);
                    }
            });

            rigaUtente.getChildren().addAll(
                    nomeLabel, cognomeLabel, cfLabel, nascitaLabel, indirizzoLabel, emailLabel, telLabel, buttonUtente
            );

            listaUtentiBox.getChildren().add(rigaUtente);
        }

        // Margine tra una tabella e l'altra
        if (titolo != null) {
            VBox.setMargin(titoloTabella, new javafx.geometry.Insets(20, 0, 5, 0));
        }
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

    public Optional<ButtonType> alertEliminazione(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        return alert.showAndWait();
    }

    public void mostraProfilo() throws IOException {
        bodyContainer.getChildren().clear();

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/ProfiloView.fxml"));
        Parent profiloContent = loader.load();

        LabelBoxDashboard.setText("Profilo");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");

        bodyContainer.getChildren().add(profiloContent);
    }

}
