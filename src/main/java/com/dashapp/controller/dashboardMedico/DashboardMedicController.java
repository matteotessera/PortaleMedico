package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
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
        @FXML
        public Label numeroPazienti;
        @FXML
        public Label numeroFarmaci;
        @FXML
        public Label numeroTerapie;

        private Parent originalContent;

        private BoxDashboardController controller;




        public void initialize() throws Exception {              //Andra messo showAllFarmaci invece di showAddFarmaci
                ds = new DataService();

                String email = NavigatorView.getAuthenticatedUser();
                int id = ds.getUtenteByEmail(email).getId();
                numeroPazienti.setText(String.valueOf(ds.getPazientiByMedico(id).length));
                numeroFarmaci.setText(String.valueOf(ds.getFarmaci().length));

                //DA rendere dinamico, altrmenti rimane fisso da qunado si avvia il portale

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


        public void aggiungiTerapia() throws IOException {
                if (controller == null) {
                        mostraBox();
                }
                controller.aggiungiTerapia();
        }

        @FXML
        public void backToDashboard() {
                mainContent.getChildren().clear();
                if (originalContent != null) {
                        mainContent.getChildren().add(originalContent);
                }
                controller = null;  // forza a ricaricare BoxDashboard quando serve
        }


        //Metodo per la visualizzazione del nome del medico e dell'immagine con iniziali nome e cognome
        public void mostraTextMeico() throws Exception {
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
