package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.model.Rilevazione;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.services.LoginService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;


public class DashboardPatientController {
    @FXML
    private Label terapieLabel;

    private DataService ds;


    private AnchorPane overlayPaneTerapia;
    @FXML
    private StackPane mainContent;
    @FXML
    private Label doctorName;
    @FXML
    private StackPane utenteCirclePane;
    @FXML
    private Circle cerchioNavbar;
    @FXML
    private Label utenteLabel;

    private Parent originalContent;

    private BoxDashboardControllerPatient controller;

    @FXML
    private Label numeroFarmaci;
    @FXML
    private Label FlagRilevazioniLabel;

    public Utente u;


    public void initialize() throws Exception {              //Andra messo showAllFarmaci invece di showAddFarmaci
        LoginService l = new LoginService();
        DataService d = new DataService();
        terapieLabel.setText("Hai " + d.getNumeroTerapieMedico(l.getUserId()) + " terapie assegnate");
        mostraTextPaziente();

        if (!mainContent.getChildren().isEmpty()) {
            originalContent = (Parent) mainContent.getChildren().get(0);
        }

        ds = new DataService();

        String email = NavigatorView.getAuthenticatedUser();
        u = ds.getUtenteByEmail(email);

        Rilevazione[] rilevazioniUtente;
        try {
            rilevazioniUtente = ds.getRilevazioniUtente(u.getId());
        } catch (RuntimeException e) {
            // Se la chiamata fallisce (es. 404), consideriamo che non ci siano rilevazioni
            System.out.println("Nessuna rilevazione trovata per l'utente ID " + u.getId() + ": " + e.getMessage());
            rilevazioniUtente = new Rilevazione[0]; // array vuoto
        }

        LocalDate oggi = LocalDate.now();
        int count = 0;

        for (Rilevazione rilevazione : rilevazioniUtente) {
            LocalDate dataRilevazione = rilevazione.getData().toLocalDate();
            if (dataRilevazione.equals(oggi)) {
                count++;
            }
        }

        FlagRilevazioniLabel.setText("Oggi hai eseguito " + count + " rilevazion" + (count == 1 ? "e" : "i"));

    }



    public void mostraBox() throws Exception {
        //showOverlay("AddPazienti.fxml", overlayPanePazienti);

        // Rimuovo tutto dal mainContent
        mainContent.getChildren().clear();

        // Carico il nuovo contenuto da FXML
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/BoxDashboardPatient.fxml"));
        Parent newContent = loader.load();

        controller = loader.getController();
        controller.setDashboardController(this);

        // Aggiungo il nuovo contenuto
        mainContent.getChildren().add(newContent);
    }


    //FUNZIONI DEI BOTTONI
    public void ListaRilevazioni() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.listaRilevazioni();
    }
    public void aggiungiRilevazione() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.aggiungiRilevazione();
    }


    public void listaAssunzioni() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.listaAssunzioni();
    }
    public void aggiungiAssunzione() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.aggiungiAssunzione();
    }

    public void listaTerapie() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.listaTerapie();
    }

    public void aggiungiSintomo() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.aggiungiSintomo();
    }

    public void listaSintomi() throws Exception {

        if (controller == null) {
            mostraBox();
        }
        controller.listaSintomi();
    }

    public void fascicoloPaziente() throws Exception {
        if (controller == null) {
            mostraBox();
        }
        controller.fascicoloPaziente();
    }






    //_________
    @FXML
    public void backToDashboard() {
        mainContent.getChildren().clear();
        if (originalContent != null) {
            mainContent.getChildren().add(originalContent);
        }
        controller = null;  // forza a ricaricare BoxDashboard quando serve
    }




    //Metodo per la visualizzazione del nome del medico e dell'immagine con iniziali nome e cognome
    public void mostraTextPaziente() throws Exception {
        DataService ds = new DataService();
        String email = NavigatorView.getAuthenticatedUser();

        Utente u = ds.getUtenteByEmail(email);
        doctorName.setText("Dr. "+u.getNome()+" "+u.getCognome());

        char nome = u.getNome().toUpperCase().charAt(0);
        char cognome = u.getCognome().toUpperCase().charAt(0);

        utenteLabel.setText(nome + "" + cognome);
        utenteLabel.setStyle("-fx-text-fill: #1b4965;");

        // Rendo visibile il cerchio con il testo
        utenteCirclePane.setVisible(true);
    }







}
