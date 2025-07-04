package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.Tabelle.Tabelle;
import com.dashapp.controller.messaggiController;
import com.dashapp.model.*;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;


import java.io.IOException;
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


    public void listaRilevazioni(){
        bodyContainer.getChildren().clear();

        List<Rilevazione> rilevazioni;
        try {
            rilevazioni = List.of(ds.getRilevazioniById(u.getId()));
        } catch (Exception e) {
            rilevazioni = List.of(new Rilevazione[0]); // array vuoto
        }

        String textButton = "Modifica";
        String titolo = "lista rilevazioni";
        tabella.tabellaRilevazioni(titolo, rilevazioni, textButton, Color.web("#2BD18D"), bodyContainer, this);

        LabelBoxDashboard.setText("TUTTE LE TUE RILEVAZIONI PASSATE");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }

    public void listaAssunzioni() throws Exception {
        bodyContainer.getChildren().clear();

        List<Assunzione> assunzioni;
        try {
            assunzioni = List.of(ds.getAssunzioniPaziente(u.getId()));
        } catch (Exception e) {
            assunzioni = List.of(new Assunzione[0]); // array vuoto
        }

        String textButton = "Visualizza";
        String titolo = "lista assunzioni";
        tabella.tabellaAssunzioni(titolo, assunzioni, textButton, Color.web("#2BD18D"), bodyContainer, this);

        LabelBoxDashboard.setText("TUTTE LE TUE ASSUNZIONI");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
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
        String titolo = "lista terapie";
        tabella.tabellaTerapie(titolo, terapie, textButton, Color.web("#34bccc"), bodyContainer, this);

        LabelBoxDashboard.setText("TUTTE LE TUE TERAPIE");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
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
        String titolo = "lista sintomi";
        tabella.tabellaSintomo(titolo, sintomi, textButton, Color.web("#2BD18D"), bodyContainer, this);

        LabelBoxDashboard.setText("TUTTI I TUOI SINTOMI PASSATI:");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }



    public void aggiungiRilevazione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("AGGIUNGI RILEVAZIONE GLICEMICA");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #ef233c");
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

        LabelBoxDashboard.setText("AGGIUNGI ASSUNZIONE FARMACO");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #cb6ce6");
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

        LabelBoxDashboard.setText("AGGIUNGI SINTOMO");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #0078ff");
        LabelBoxDashboard.setAlignment(Pos.CENTER);



        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/AddSintomo.fxml"));
        Parent addAssunzioneContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addAssunzioneContent);
    }

    public void fascicoloPaziente()throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("FASCICOLO MEDICO");
        LabelBoxDashboard.setStyle("-fx-font-family: 'Roboto ExtraBold'; -fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill:  #1e3746;");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/FascicoloPaziente.fxml"));
        Parent fascicolo = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(fascicolo);

    }


    public void vediSintomo() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna ai sintomi");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaSintomi());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/SintomiView.fxml"));
        Parent addFarmacoContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addFarmacoContent);
    }
    public void vediRilevazione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna alle rilevazioni");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaRilevazioni());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/RilevazioneView.fxml"));
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

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/AssunzioneView.fxml"));
        Parent content = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(content);
    }
    public void vediTerapia() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("\uD83E\uDC14 Torna alle terapie");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 16px; -fx-text-fill: black;");
        LabelBoxDashboard.setOnMouseClicked(event -> listaTerapie());

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/TerapiaView.fxml"));
        Parent content = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(content);
    }

    public void mostraMessaggi() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("Casella Messaggi");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 22px; -fx-text-fill: #cb6ce6; ");


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/MessaggiView.fxml"));
        Parent addMessContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addMessContent);
    }


}
