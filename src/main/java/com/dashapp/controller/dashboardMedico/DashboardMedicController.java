package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
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


public class DashboardMedicController {

        private DataService ds;

        @FXML
        private AnchorPane overlayPaneFarmaci;
        @FXML
        private AnchorPane overlayPanePazienti;
        @FXML
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

        private BoxDashboardController controller;



        public void initialize() throws Exception {              //Andra messo showAllFarmaci invece di showAddFarmaci

                mostraTextMeico();

                if (!mainContent.getChildren().isEmpty()) {
                        originalContent = (Parent) mainContent.getChildren().get(0);
                }
        }



        public void mostraBox() throws IOException {
                //showOverlay("AddPazienti.fxml", overlayPanePazienti);

                // Rimuovo tutto dal mainContent
                mainContent.getChildren().clear();

                // Carico il nuovo contenuto da FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardMedic/BoxDashboard.fxml"));
                Parent newContent = loader.load();

                controller = loader.getController();
                controller.setDashboardController(this);

                // Aggiungo il nuovo contenuto
                mainContent.getChildren().add(newContent);
        }

        public void aggiungiFarmaco() throws IOException {
                if (controller == null) {
                        mostraBox();
                }
                controller.aggiungiFarmaco();
        }


        public void listaPazienti() throws IOException {

                if (controller == null) {
                        mostraBox();
                }
                controller.listaPazienti();
        }

        public void assegnazioneMedPaz() throws IOException {

                if (controller == null) {
                        mostraBox();
                }
                controller.assegnazioneMedPaz();
        }

        public void listaFarmaci() throws IOException {

                if (controller == null) {
                        mostraBox();
                }
                controller.listaFarmaci();
        }



        public void showAddTerapia(){
                showOverlay("AddTerapia.fxml", overlayPaneTerapia);
        }

        @FXML
        public void backToDashboard() {
                mainContent.getChildren().clear();
                if (originalContent != null) {
                        mainContent.getChildren().add(originalContent);
                }
                controller = null;  // forza a ricaricare BoxDashboard quando serve
        }


        public void showOverlay(String fxml, Pane overlayPane){                   //inserire file da renderizzare, nome del pannello in cui renderizzarlo (nome == fixid)
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardMedic/" + fxml));
                        AnchorPane newPane = loader.load();

                        Object controller = loader.getController();
                        if (controller instanceof OverlayPaneAware) {                           //se il controller implementa il metodo setOverlayPane, allora...
                                ((OverlayPaneAware) controller).setOverlayPane(overlayPane);
                        }

                        // Pulisce contenuti vecchi e aggiunge il nuovo pane
                        overlayPane.getChildren().clear();

                        overlayPane.getChildren().add(newPane);
                        overlayPane.setVisible(true);
                        overlayPane.setPickOnBounds(true); // per catturare eventi (es. click)


                } catch (IOException e) {
                        e.printStackTrace();
                }
        }

        //Metodo per la visualizzazione del nome del medico e dell'immagine con iniziali nome e cognome
        public void mostraTextMeico() throws Exception {
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
