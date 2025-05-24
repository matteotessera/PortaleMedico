package com.dashapp.controller;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeControllerTeo {

    @FXML
    private void handleLoginRegistrazione(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/MainViewTeo.fxml"));
        Parent mainRoot = loader.load();

        MainControllerTeo mainController = loader.getController();


        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/loginMatteo.fxml"));
        Node loginView = loginLoader.load();

        mainController.setContent(loginView);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.getScene().setRoot(mainRoot);
    }
}
