package com.dashapp.controller;

import com.dashapp.view.NavigatorView;
import com.dashapp.view.ViewNavigator;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

import java.util.Stack;

public class MainControllerTeo {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private StackPane stackPane;

    @FXML
    private VBox sidebarVBox;



    @FXML
    public void initialize() {
        if(!NavigatorView.isAuthenticated()){
            hideSidebar();
        }

        NavigatorView.setMainController(this);
    }

    public void hideSidebar() {
        mainContainer.setLeft(null);
    }

    public void setContent(Node content) {
        stackPane.getChildren().setAll(content);
    }
}
