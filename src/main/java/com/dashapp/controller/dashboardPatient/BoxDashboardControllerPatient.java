package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.Tabelle.Tabelle;
import com.dashapp.controller.dashboardMedico.DashboardMedicController;
import com.dashapp.model.Assunzione;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Rilevazione;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
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

public class BoxDashboardControllerPatient {

    @FXML
    private VBox bodyContainer;

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

    public void listaRilevazioni() throws Exception {
        bodyContainer.getChildren().clear();

        List<Rilevazione> rilevazioni;
        try {
            rilevazioni = List.of(ds.getRilevazioniUtente(u.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Vedi";
        String titolo = "lista rilevazioni";
        tabella.tabellaRilevazioni(titolo, rilevazioni, textButton, Color.web("#34bccc"), bodyContainer);

        LabelBoxDashboard.setText("TUTTI I PAZIENTI");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }


    public void listaAssunzioni(){
        bodyContainer.getChildren().clear();

        List<Assunzione> assunzioni;
        try {
            assunzioni = List.of(ds.getAssunzioniPaziente(u.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        String textButton = "Vedi";
        String titolo = "lista assunzioni";
        tabella.tabellaAssunzioni(titolo, assunzioni, textButton, Color.web("#34bccc"), bodyContainer);

        LabelBoxDashboard.setText("TUTTE LE TUE ASSUNZIONI");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);
    }



    public void aggiungiRilevazione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("AGGIUNGI RILEVAZIONE GLICEMICA");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/AddRilevazioneGlicemia.fxml"));
        Parent addRilevzioneContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addRilevzioneContent);
    }

    public void aggiungiAssunzione() throws IOException {
        bodyContainer.getChildren().clear();

        LabelBoxDashboard.setText("AGGIUNGI ASSUNZIONE FARMACO");
        LabelBoxDashboard.setStyle("-fx-font-weight: bold; -fx-font-size: 24px; -fx-text-alignment: center; -fx-text-fill: #34bccc");
        LabelBoxDashboard.setAlignment(Pos.CENTER);


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashboardPatient/AddAssunzione.fxml"));
        Parent addAssunzioneContent = loader.load();

        // Aggiungo il contenuto caricato al bodyContainer
        bodyContainer.getChildren().add(addAssunzioneContent);
    }

}
