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


        private Parent originalContent;



        public void initialize() {              //Andra messo showAllFarmaci invece di showAddFarmaci

                //PER IMPOSTARE NOME MEDICO SULLA DASHBOARD
                try {
                        ds=new DataService();
                        String email = NavigatorView.getAuthenticatedUser();
                        Utente utente = ds.getUtenteByEmail(email);
                        doctorName.setText("Dr. "+utente.getNome()+" "+utente.getCognome());
                } catch (Exception e) {
                        e.printStackTrace();
                        // eventualmente mostrare alert o loggare errore
                }

                if (!mainContent.getChildren().isEmpty()) {
                        originalContent = (Parent) mainContent.getChildren().get(0);
                }
        }

        public void showAddFarmaci(){
               showOverlay("AddFarmaci.fxml", overlayPaneFarmaci);
        }


        public void showAddPazienti() throws IOException {
                //showOverlay("AddPazienti.fxml", overlayPanePazienti);

                // Rimuovo tutto dal mainContent
                mainContent.getChildren().clear();

                // Carico il nuovo contenuto da FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardMedic/BoxDashboard.fxml"));
                Parent newContent = loader.load();

                BoxDashboardController controller = loader.getController();
                controller.setDashboardController(this);

                // Aggiungo il nuovo contenuto
                mainContent.getChildren().add(newContent);
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






}
