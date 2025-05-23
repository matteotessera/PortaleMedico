package com.dashapp.model;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import javafx.fxml.FXML;
import javafx.scene.layout.Pane;

public class AddController implements OverlayPaneAware {


    //Questa classe viene Estesa da tutti i controllori Add per dashboadMedico e dashboardPatient,
    // definisce i metodi comuni

    private Pane overlayPane;

    @FXML
    private void exit(javafx.event.ActionEvent event) {
        // Nasconde il nodo genitore (la finestra)
        if(overlayPane!= null) {
            overlayPane.setVisible(false);
            overlayPane.getChildren().clear();
            overlayPane.setPrefWidth(0);
            overlayPane.setPrefHeight(0);
        }


    }

    public void setOverlayPane(Pane pane){
        this.overlayPane = pane;
    }
}
