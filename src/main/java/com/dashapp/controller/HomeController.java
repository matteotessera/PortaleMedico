package com.dashapp.controller;

import com.dashapp.Main;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {

    @FXML
    private void handleLoginRegistrazione(javafx.event.ActionEvent event) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/MainView.fxml"));
        Parent mainRoot = loader.load();

        MainController mainController = loader.getController();

        FXMLLoader loginLoader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/LoginView.fxml"));


        Node loginView = loginLoader.load();

        mainController.setContent(loginView);

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        stage.getScene().setRoot(mainRoot);
    }
}
