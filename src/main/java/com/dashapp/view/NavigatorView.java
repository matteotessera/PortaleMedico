package com.dashapp.view;

import com.dashapp.Main;
import com.dashapp.controller.MainControllerTeo;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class NavigatorView {
    // Reference to the main controller
    private static MainControllerTeo mainController;

    // Current authenticated username
    private static String authenticatedUser = null;

    /**
     * Set the main controller reference
     * @param controller The MainController instance
     */
    public static void setMainController(MainControllerTeo controller) {
        mainController = controller;
    }

    public static MainControllerTeo getMainController() {
        return mainController;
    }


    /**
     * Load and switch to a view
     * @param fxml The name of the FXML file to load
     */
    public static void loadView(String fxml) {
        try {
            URL fxmlUrl = Main.class.getResource("/com/dashapp/fxml/" + fxml);
            FXMLLoader loader = new FXMLLoader(fxmlUrl);
            Node view = loader.load();
            mainController.setContent(view);
        } catch (IOException e) {
            e.printStackTrace();
            System.err.println("Error loading view: " + fxml);
        }
    }

    public static void navigateToLogin() {
        loadView("LoginView.fxml");
    }

    public static void navigateToRegister() {
        loadView("RegisterView.fxml");
    }

    public static void navigateToLoginRegistrazione() {
        loadView("Login_Registrazione.fxml");
    }

    public static boolean isAuthenticated() {
        return authenticatedUser != null;
    }
}
