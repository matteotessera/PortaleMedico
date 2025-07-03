package com.dashapp.controller;

import com.dashapp.Main;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

import java.io.IOException;

public class HomeController {
    @FXML
    public StackPane stackPane;
    @FXML
    public ImageView backgroundImage;
    @FXML
    public Label intestazioneLabel;

    public void initialize() {
        backgroundImage.setPreserveRatio(true);
        backgroundImage.setSmooth(true);

        Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Italic-VariableFont_wdth,wght.ttf"), 14);
        Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-VariableFont_wdth,wght.ttf"), 14);


        Font roboto = Font.loadFont(getClass().getResourceAsStream("/fonts/Roboto-Black.ttf"), 37);

        intestazioneLabel.setFont(roboto);

        stackPane.widthProperty().addListener((obs, oldVal, newVal) -> resizeImageView());
        stackPane.heightProperty().addListener((obs, oldVal, newVal) -> resizeImageView());
    }

    private void resizeImageView() {
        double paneWidth = stackPane.getWidth();
        double paneHeight = stackPane.getHeight();

        double imageWidth = backgroundImage.getImage().getWidth();
        double imageHeight = backgroundImage.getImage().getHeight();

        if (paneWidth / paneHeight > imageWidth / imageHeight) {
            // la finestra è più larga in proporzione, quindi usa fitWidth
            backgroundImage.setFitWidth(paneWidth);
            backgroundImage.setFitHeight(0); // lascia che si calcoli mantenendo proporzioni
        } else {
            // la finestra è più alta in proporzione, usa fitHeight
            backgroundImage.setFitHeight(paneHeight);
            backgroundImage.setFitWidth(0);
        }
    }
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
