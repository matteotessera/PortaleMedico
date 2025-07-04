package com.dashapp.controller.dashboardMedico;

import com.dashapp.controller.ControlliSistema;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;


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
        @FXML
        public HBox messaggiBox;

        private Parent originalContent;

        private BoxDashboardController controller;

        private int idCurrentUser;




        public void initialize() throws Exception {              //Andra messo showAllFarmaci invece di showAddFarmaci
                ds = new DataService();

                String email = NavigatorView.getAuthenticatedUser();
                idCurrentUser = ds.getUtenteByEmail(email).getId();
                numeroPazienti.setText(String.valueOf(ds.getPazientiByMedico(idCurrentUser).length));
                numeroFarmaci.setText(String.valueOf(ds.getFarmaci().length));

                //DA rendere dinamico, altrmenti rimane fisso da qunado si avvia il portale

                mostraTextMeico();
                if (!mainContent.getChildren().isEmpty()) {
                        originalContent = (Parent) mainContent.getChildren().get(0);
                }

                //controllaPazienti();


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

        public void assegnazioneMedPaz() throws Exception {

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


        public void mostraMessaggi() throws IOException{
                if (controller == null) {
                        mostraBox();
                }
                controller.mostraMessaggi();
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


        public void controllaPazienti() throws Exception {
                List<Utente> pazientiAssociati = List.of(ds.getPazientiByMedico(idCurrentUser));
                ControlliSistema controlli = new ControlliSistema();
                for(Utente p: pazientiAssociati) {
                        if (controlli.pazienteNonAderente(p.getId())) {
                        mostraAlert("AVVISO", "Il paziente: " + p.getNome() + " " + p.getCognome() + " non ha aderito alle sue prescrizioni negli ultimi 3 giorni");
                        ds.addMessaggio(p.getId(), idCurrentUser, LocalDate.now(), LocalTime.now(),
                                "Avviso paziente: " + p.getNome() + " " + p.getCognome() + " " + p.getEmail(),
                                "il paziente non ha aderito alle sue prescrizioni negli ultimi 3 giorni",
                                'N', false);
                        }
                }
        }


        public void mostraAlert(String titolo, String contenuto) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle(titolo);
                alert.setHeaderText(null);  // Puoi mettere un header se vuoi
                alert.setContentText(contenuto);
                alert.showAndWait();
        }



}
