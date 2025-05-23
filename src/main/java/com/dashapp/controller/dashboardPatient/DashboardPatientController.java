package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.io.IOException;

public class DashboardPatientController {

    @FXML
    private AnchorPane overlayPaneAssunzioniSintomi;
    @FXML
    private AnchorPane overlayPaneGlicemia;
    @FXML
    private AnchorPane overlayPaneTerapia;

    public void initialize() {              //Andra messo showAllFarmaci invece di showAddFarmaci
        showAddAssunzione();

    }

    public void showAddAssunzione(){
        showOverlay("AddAssunzione.fxml", overlayPaneAssunzioniSintomi);
    }
    public void showAddSintomo(){
        showOverlay("AddSintomo.fxml", overlayPaneAssunzioniSintomi);
    }
    public void showAddGlicemia(){
        showOverlay("AddRilevazioneGlicemia.fxml", overlayPaneGlicemia);
    }





    public void showOverlay(String fxml, Pane overlayPane){                   //inserire file da renderizzare, nome del pannello in cui renderizzarlo (nome == fixid)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/" + fxml));
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
