package com.dashapp.controller.dashboardMedico;

import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Region;
import javafx.scene.layout.StackPane;

import java.io.IOException;


public class DashboardMedicController {

        @FXML
        private AnchorPane overlayPaneFarmaci;
        @FXML
        private AnchorPane overlayPanePazienti;
        @FXML
        private AnchorPane overlayPaneTerapia;

        public void initialize() {              //Andra messo showAllFarmaci invece di showAddFarmaci
                showAddFarmaci();
                showAddPazienti();
                showAddTerapia();
        }

        public void showAddFarmaci(){
               showOverlay("AddFarmaci.fxml", overlayPaneFarmaci);
        }

        public void showAddPazienti(){
                showOverlay("AddPazienti.fxml", overlayPanePazienti);
        }

        public void showAddTerapia(){
                showOverlay("AddTerapia.fxml", overlayPaneTerapia);
        }



        public void showOverlay(String fxml, Pane overlayPane){                   //inserire file da renderizzare, nome del pannello in cui renderizzarlo (nome == fixid)
                try {
                        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoard/" + fxml));
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
