package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.ProfiloController;
import com.dashapp.controller.Tabelle.Tabelle;
import com.dashapp.controller.dashboardPatient.fascicolo.FascicoloPazienteController;
import com.dashapp.controller.messaggiController;
import com.dashapp.model.*;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class BoxDashboardControllerPatient {

    @FXML
    public VBox bodyContainer;

    @FXML
    private Label LabelBoxDashboard;

    private DashboardPatientController dashboardController;

    private DataService ds;


    private Tabelle tabella;

    public static Utente u;


    @FXML
    public void initialize() {
        ds = new DataService();
    }

    public void setDashboardController(DashboardPatientController dashboardController) throws Exception {
        this.dashboardController = dashboardController;
        tabella = new Tabelle();
        String email = NavigatorView.getAuthenticatedUser();
        u = ds.getUtenteByEmail(email);
    }

    @FXML
    private void BackToDashboard() throws IOException {
        if (dashboardController != null) {
            dashboardController.backToDashboard();
        }
    }





    public void listaRilevazioni() {
        bodyContainer.getChildren().clear();

        // Colore e stile pulsanti
        String styleButton = "-fx-background-color: #1e3746; -fx-text-fill: white; -fx-font-weight: bold;";

        // HBox per i filtri
        HBox filtroBox = new HBox(10);
        filtroBox.setAlignment(Pos.CENTER_LEFT);

        Button oggiButton = new Button("Solo oggi");
        oggiButton.setStyle(styleButton);

        Button tutteButton = new Button("Tutte");
        tutteButton.setStyle(styleButton);

        Button dataButton = new Button("Seleziona data");
        dataButton.setStyle(styleButton);

        DatePicker datePicker = new DatePicker();
        datePicker.setVisible(false);

        filtroBox.getChildren().addAll(oggiButton, tutteButton, dataButton);
        bodyContainer.getChildren().add(filtroBox);
        bodyContainer.getChildren().add(datePicker);

        VBox tableContainer = new VBox();
        bodyContainer.getChildren().add(tableContainer);

        // Caricamento rilevazioni tramite metodo centralizzato
        List<Rilevazione> rilevazioni = caricaRilevazioni();

        String textButton = "Modifica";
        tabella.tabellaRilevazioni(rilevazioni, textButton, Color.web("#1e3746"), tableContainer, this);

        LabelBoxDashboard.setText("Rilevazioni glicemiche");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);

        LocalDate oggi = LocalDate.now();

        // Eventi pulsanti
        oggiButton.setOnAction(e -> {
            List<Rilevazione> filtrateOggi = rilevazioni.stream()
                    .filter(r -> r.getData().toLocalDate().equals(oggi))
                    .toList();

            tableContainer.getChildren().clear();
            tabella.tabellaRilevazioni(filtrateOggi, textButton, Color.web("#1e3746"), tableContainer, this);
        });

        tutteButton.setOnAction(e -> {
            tableContainer.getChildren().clear();
            tabella.tabellaRilevazioni(rilevazioni, textButton, Color.web("#1e3746"), tableContainer, this);
        });

        dataButton.setOnAction(e -> datePicker.show());

        datePicker.setOnAction(e -> {
            LocalDate dataSelezionata = datePicker.getValue();
            if (dataSelezionata != null) {
                dataButton.setText(dataSelezionata.toString());

                List<Rilevazione> filtrateData = rilevazioni.stream()
                        .filter(r -> r.getData().toLocalDate().equals(dataSelezionata))
                        .toList();

                tableContainer.getChildren().clear();
                tabella.tabellaRilevazioni(filtrateData, textButton, Color.web("#1e3746"), tableContainer, this);
            }
        });
    }

    public void profilo() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Profilo");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/ProfiloView.fxml"));
        Parent profilo = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(profilo);
    }


    private List<Rilevazione> caricaRilevazioni() {
        try {
            return List.of(ds.getRilevazioniById(u.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // lista vuota in caso di errore
        }
    }


    public void listaAssunzioni() throws Exception {
        bodyContainer.getChildren().clear();

        // Tasti filtro
        HBox filtroBox = new HBox(10);
        filtroBox.setAlignment(Pos.CENTER_LEFT);

        // Colore uniforme
        Color coloreButton = Color.web("#1e3746");
        String styleButton = "-fx-background-color: #1e3746; -fx-text-fill: white; -fx-font-weight: bold;";

        Button oggiButton = new Button("Solo oggi");
        oggiButton.setStyle(styleButton);

        Button tutteButton = new Button("Tutte");
        tutteButton.setStyle(styleButton);

        Button dataButton = new Button("Seleziona data");
        dataButton.setStyle(styleButton);

        DatePicker datePicker = new DatePicker();
        datePicker.setVisible(false); // nascosto
        

        filtroBox.getChildren().addAll(oggiButton, tutteButton, dataButton);
        bodyContainer.getChildren().add(filtroBox);
        bodyContainer.getChildren().add(datePicker); // opzionale: puoi anche non aggiungerlo se non serve visivamente

        VBox tableContainer = new VBox();
        bodyContainer.getChildren().add(tableContainer);

        // Caricamento assunzioni
        final List<Assunzione> assunzioni = caricaAssunzioni();

        String textButton = "Modifica";
        tabella.tabellaAssunzioni(assunzioni, textButton, Color.web("#1e3746"), tableContainer, this);

        LabelBoxDashboard.setText("Assunzioni");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);

        LocalDate oggi = LocalDate.now();

        oggiButton.setOnAction(e -> {
            List<Assunzione> filtrateOggi = assunzioni.stream()
                    .filter(a -> a.getData().toLocalDate().equals(oggi))
                    .toList();

            tableContainer.getChildren().clear();
            try {
                tabella.tabellaAssunzioni(filtrateOggi, textButton, Color.web("#1e3746"), tableContainer, this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        tutteButton.setOnAction(e -> {
            tableContainer.getChildren().clear();
            try {
                tabella.tabellaAssunzioni(assunzioni, textButton, Color.web("#1e3746"), tableContainer, this);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        dataButton.setOnAction(e -> datePicker.show());

        datePicker.setOnAction(e -> {
            LocalDate dataSelezionata = datePicker.getValue();
            if (dataSelezionata != null) {
                dataButton.setText(dataSelezionata.toString());

                List<Assunzione> filtrateData = assunzioni.stream()
                        .filter(a -> a.getData().toLocalDate().equals(dataSelezionata))
                        .toList();

                tableContainer.getChildren().clear();
                try {
                    tabella.tabellaAssunzioni(filtrateData, textButton, Color.web("#1e3746"), tableContainer, this);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    private List<Assunzione> caricaAssunzioni() {
        try {
            return List.of(ds.getAssunzioniPaziente(u.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return List.of(); // ritorna lista vuota in caso di errore
        }
    }


    public void listaTerapie(){
        bodyContainer.getChildren().clear();

        List<Terapia> terapie;
        try {

            terapie = List.of(ds.getTerapiePaziente(u.getId()));
        } catch (Exception e) {
            terapie = List.of(new Terapia[0]); // array vuoto
        }

        String textButton = "Vedi";
        tabella.tabellaTerapie(terapie, textButton, Color.web("#1e3746"), bodyContainer, this);

        LabelBoxDashboard.setText("Terapie");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }

    public void listaSintomi(){
        bodyContainer.getChildren().clear();

        List<Sintomo> sintomi;
        try {
            System.out.println(u.getId());
            sintomi = List.of(ds.getSintomiById(u.getId()));
        } catch (Exception e) {
            sintomi = List.of(new Sintomo[0]); // array vuoto
        }

        String textButton = "Modifica";
        tabella.tabellaSintomo(sintomi, textButton, Color.web("#1e3746"), bodyContainer, this);

        LabelBoxDashboard.setText("Sintomi");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }



    public void aggiungiRilevazione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Aggiungi rilevazione glicemica");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/AddRilevazioneGlicemia.fxml"));
        Parent addRilevazioneContent = loader.load();
        AddRilevazioneGlicemiaController controller = loader.getController();
        controller.setParentController(dashboardController);

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addRilevazioneContent);
    }

    public AddAssunzioneController aggiungiAssunzione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Aggiungi assunzione");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/AddAssunzione.fxml"));
        Parent addAssunzioneContent = loader.load();
        AddAssunzioneController controller = loader.getController();
        controller.setParentController(dashboardController);
        controller.setParentBoxController(this);


        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addAssunzioneContent);
        controller.assunzioniCompletate();

        return controller;


    }

    public void aggiungiSintomo() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Aggiungi sintomo");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/AddSintomo.fxml"));
        Parent addAssunzioneContent = loader.load();
        AddSintomoController controller = loader.getController();
        controller.setParentController(dashboardController);
        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addAssunzioneContent);
    }

    public void fascicoloPaziente() throws Exception {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Fascicolo medico");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/FascicoloPaziente.fxml"));
        Parent fascicolo = loader.load();
        FascicoloPazienteController controller = loader.getController();
        controller.setPaziente(BoxDashboardControllerPatient.u);
        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(fascicolo);

    }


    public void vediSintomo() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna ai sintomi");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaSintomi());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/ViewSintomo.fxml"));
        Parent addFarmacoContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addFarmacoContent);
    }
    public void vediRilevazione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna alle rilevazioni");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaRilevazioni());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/ViewRilevazione.fxml"));
        Parent content = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(content);
    }
    public void vediAssunzione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna alle assunzioni");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> {
            try {
                listaAssunzioni();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        });

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/ViewAssunzione.fxml"));
        Parent content = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(content);
    }
    public void vediTerapia() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna alle terapie");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaTerapie());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/ViewTerapia.fxml"));
        Parent content = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(content);
    }

    public void mostraMessaggi() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Casella Messaggi");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #1e3746; -fx-font-family: 'Roboto Black';");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/MessaggiView.fxml"));
        Parent addMessContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addMessContent);
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
