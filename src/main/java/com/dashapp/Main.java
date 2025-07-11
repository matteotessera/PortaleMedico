package com.dashapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {

        //Carico i Font
        loadAllFonts();


        // Load the main application view
        URL mainViewUrl = getClass().getResource("/com/dashapp/fxml/HomeView.fxml");

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
        primaryStage.setTitle("Diary");
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public static void loadAllFonts() {
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto-Regular.ttf"), 12);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto-SemiBold.ttf"), 12);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto-Black.ttf"), 12);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto-Medium.ttf"), 12);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/Roboto-Bold.ttf"), 12);
        Font.loadFont(Main.class.getResourceAsStream("/fonts/LeagueSpartan-ExtraBold.ttf"), 12);


    }




    public static void main(String[] args) {
        launch(args);
    }
}
