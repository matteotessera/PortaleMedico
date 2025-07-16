package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.ControlliSistema;
import com.dashapp.controller.graficoGlicemiaController;
import com.dashapp.model.*;
import com.dashapp.services.DataService;
import com.dashapp.services.LoginService;
import com.dashapp.view.NavigatorView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;


public class DashboardPatientController {
    @FXML
    private Label terapieLabel;

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
    @FXML
    private Label numeroFarmaci;
    @FXML
    public Label FlagRilevazioniLabel;
    @FXML
    public Label FlagAssunzioniLabel;
    @FXML
    AnchorPane graficoContainer;

    private Parent originalContent;

    private BoxDashboardControllerPatient controller;

    private DataService ds;

    private AnchorPane overlayPaneTerapia;

    public Utente u;

    public int countRilevazioni;


    public void initialize() throws Exception {              //Andra messo showAllFarmaci invece di showAddFarmaci
        LoginService l = new LoginService();
        DataService d = new DataService();

        NavigatorView.setPatientController(this);
        //terapieLabel.setText("Hai " + d.getNumeroTerapieMedico(l.getUserId()) + " terapie assegnate");
        mostraTextPaziente();


        if (!mainContent.getChildren().isEmpty()) {
            originalContent = (Parent) mainContent.getChildren().get(0);
        }

        ds = new DataService();

        String email = NavigatorView.getAuthenticatedUser();
        u = ds.getUtenteByEmail(email);

        Rilevazione[] rilevazioniUtente;
        try {
            rilevazioniUtente = ds.getRilevazioniById(u.getId());
        } catch (RuntimeException e) {

            System.out.println("Nessuna rilevazione trovata per l'utente ID " + u.getId() + ": " + e.getMessage());
            rilevazioniUtente = new Rilevazione[0]; // array vuoto
        }

        LocalDate oggi = LocalDate.now();
        countRilevazioni = 0;

        for (Rilevazione rilevazione : rilevazioniUtente) {
            LocalDate dataRilevazione = rilevazione.getData().toLocalDate();
            if (dataRilevazione.equals(oggi)) {
                countRilevazioni++;
            }
        }


        /*FlagRilevazioniLabel.setText("Oggi hai eseguito " + countRilevazioni + " rilevazion" + (countRilevazioni == 1 ? "e" : "i"));

        FlagAssunzioniLabel.setText("Registra eventuali Sintomi e le tue assunzioni giornaliere\n" +
                                    "Oggi hai assunto " + calcolaAssunzioniEffettuate() + " farmaci");*/

        //RENDER DEL GRAFICO


        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/graficoGlicemia.fxml"));
        Parent content = loader.load();
        graficoGlicemiaController graficoController = loader.getController();

        graficoController.setRilevazioni(new ArrayList<>(Arrays.asList(rilevazioniUtente)));
        graficoController.setDashboardControllerPatient(this);
        graficoController.popolaGraficoTotale();

        graficoContainer.getChildren().add(content);
        graficoContainer.getStylesheets().add(String.valueOf(getClass().getResource("/com/dashapp/css/grafico.css")));

        Platform.runLater(() -> {
            try {
                controlloAssunzioniDimenticate();
            } catch (Exception e) {
                System.out.println("errore nel controllo assunzioni");
            }
        });

    }


    public void setBoxControllerGrafico(graficoGlicemiaController graficoController){
        graficoController.setBoxControllerPatient(controller);
    }

    public void updateGrafico() throws Exception {
        Rilevazione[] rilevazioniUpdate = (ds.getRilevazioniById(u.getId()));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/graficoGlicemia.fxml"));
        Parent content = loader.load();
        graficoGlicemiaController graficoController = loader.getController();

        graficoController.setRilevazioni(new ArrayList<>(Arrays.asList(rilevazioniUpdate)));
        graficoController.popolaGraficoTotale();
        graficoContainer.getChildren().add(content);

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

    public void inizializzaBox() throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/BoxDashboardPatient.fxml"));
        Parent newContent = loader.load();

        controller = loader.getController();
        controller.setDashboardController(this);
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

    public void mostraMessaggi() throws Exception {
        if (controller == null) {
            mostraBox();
        }
        controller.mostraMessaggi();
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


    public void reload() throws Exception {
        initialize();
        mostraTextPaziente();
    }

    //Metodo per la visualizzazione del nome del medico e dell'immagine con iniziali nome e cognome
    public void mostraTextPaziente() throws Exception {
        DataService ds = new DataService();
        String email = NavigatorView.getAuthenticatedUser();

        Utente u = ds.getUtenteByEmail(email);
        doctorName.setText(u.getNome()+" "+u.getCognome());

        char nome = u.getNome().toUpperCase().charAt(0);
        char cognome = u.getCognome().toUpperCase().charAt(0);

        utenteLabel.setText(nome + "" + cognome);
        utenteLabel.setStyle("-fx-text-fill: #1b4965;");

        // Rendo visibile il cerchio con il testo
        utenteCirclePane.setVisible(true);
    }




    public int calcolaAssunzioniEffettuate() throws Exception {
            int count = 0;

            //OTTENGO LE ASSUNZIONI CHE HA FATTO OGGI IL PAZIENTE
             List<Assunzione> assunzioniPaziente = List.of(ds.getAssunzioniPaziente(u.getId()));
             List<Assunzione> assunzioniOggi = assunzioniPaziente.stream()
                    .filter(assunzione -> assunzione.getData().toLocalDate().equals(LocalDate.now()))
                    .collect(Collectors.toList());

             return assunzioniOggi.size();
    }

    public void controlloAssunzioniDimenticate() throws Exception {
        ControlliSistema controllo = new ControlliSistema();
        LocalDate ieri = LocalDate.now().minusDays(1);

        List<Farmaco> farmaciDimenticati = controllo.farmaciDimenticati(u.getId());

        List<Messaggio> messaggiPaziente = Arrays.asList(ds.getMessaggiByIdSender(u.getId()));
        List<Messaggio> messaggiFiltrati = messaggiPaziente.stream().filter(m -> m.getTipo() =='A').collect(Collectors.toList());


        for(Farmaco f: farmaciDimenticati){
            boolean giaInviato = messaggiFiltrati.stream().anyMatch(m ->
                    ( m.getDataInvio().isEqual(ieri) || m.getDataInvio().isEqual(ieri.plusDays(1)) )
                            &&
                            m.getOggetto() != null &&
                            m.getOggetto().contains(f.getNome())
            );

            if (giaInviato) {
                // Salta invio se già mandato
                continue;
            }

            mostraAlert(
                    "AVVISO",
                    "In data: " + ieri + " hai dimenticato di assumere il farmaco: " + f.getNome()
            );

            ds.addMessaggio(
                    u.getId(),
                    u.getId(),
                    LocalDate.now(),
                    LocalTime.now(),
                    "Farmaco: " + f.getNome() + " dimenticato",
                    "In data: " + ieri + " hai dimenticato di assumere il farmaco: " + f.getNome(),
                    'A',
                    false
            );
        }



    }



    public void vediProfilo() throws Exception {
        // Pulisci il contenuto centrale
        mainContent.getChildren().clear();

        // Carico il BoxDashboardPatient (container)
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/BoxDashboardPatient.fxml"));
        Parent boxDashboard = loader.load();

        // Prendo il controller del box
        controller = loader.getController();

        // Passo il riferimento al DashboardPatientController se serve
        controller.setDashboardController(this);

        // Aggiungo il boxDashboard alla parte centrale
        mainContent.getChildren().add(boxDashboard);

        // Chiamo il metodo per caricare il profilo dentro il box
        controller.mostraProfilo();
    }

    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);  // Puoi mettere un header se vuoi
        alert.setContentText(contenuto);
        alert.showAndWait();
    }


}
