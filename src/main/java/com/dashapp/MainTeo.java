package com.dashapp;

import com.dashapp.model.UserRepository;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.net.URL;

public class MainTeo extends Application {

    private static UserRepository userRepository = new UserRepository();

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Load the main application view
        URL mainViewUrl = getClass().getResource("/com/dashapp/fxml/HomeViewTeo.fxml");

        FXMLLoader loader = new FXMLLoader(mainViewUrl);
        Parent root = loader.load();

        // Set up the scene
        Scene scene = new Scene(root, 800, 600);

        // Load and apply CSS
        URL cssUrl = getClass().getResource("/com/dashapp/css/styles.css");
        if (cssUrl != null) {
            scene.getStylesheets().add(cssUrl.toExternalForm());
        } else {
            System.out.println("CSS file not found: /com/dashapp/css/styles.css");
        }

        // Configure and show the stage
        primaryStage.setTitle("Portale Medico");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * Get the application-wide user repository
     */
    public static UserRepository getUserRepository() {
        return userRepository;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
