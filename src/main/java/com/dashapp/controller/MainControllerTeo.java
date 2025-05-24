package com.dashapp.controller;

import com.dashapp.view.NavigatorView;
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
    private Node leftPanel;



    @FXML
    public void initialize() {
        if(!NavigatorView.isAuthenticated()){
            hideSidebar();
        }

        NavigatorView.setMainController(this);
    }

    public void hideSidebar() {
        leftPanel = mainContainer.getLeft();
        mainContainer.setLeft(null);
    }

    public void viewSidebar() {
        mainContainer.setLeft(leftPanel);
    }



    public void setContent(Node content) {
        stackPane.getChildren().setAll(content);
    }
}
