package com.dashapp.controller;

import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;

public class MainControllerTeo {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private AnchorPane contentPane;

    @FXML
    private VBox sidebarVBox;



    @FXML
    public void initialize() {
        if(!NavigatorView.isAuthenticated()){
            sidebarVBox.setVisible(false);
        }
    }

    public void setContent(Node content) {
        contentPane.getChildren().setAll(content);
    }
}
