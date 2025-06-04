package com.dashapp.view;

import com.dashapp.Main;
import com.dashapp.controller.MainController;
import com.dashapp.model.Utente;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import java.io.IOException;
import java.net.URL;

public class NavigatorView {
    // Reference to the main controller
    private static MainController mainController;

    // Current authenticated username
    private static String authenticatedUser = null;

    private static Utente paziente = null;

    /**
     * Set the main controller reference
     * @param controller The MainController instance
     */
    public static void setMainController(MainController controller) {
        mainController = controller;
    }

    public static MainController getMainController() {
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

    public static void navigateToAddFarmaci(){
        loadView("DashBoardMedic/AddFarmaci.fxml");
    }

    public static void navigateToDashboardMedic(){  loadView("DashBoardMedic/DashboardViewMedic.fxml"); }       //!!! TEMPORANEAMENTE MESSO DASHBOARD PATIENT

    public static void navigateToDashboardPatient(){
        loadView("DashBoardPatient/DashboardViewPatient.fxml");
    }

    public static void navigateToChangePassword(){
        loadView("CambioPassword.fxml");
    }

    public static void navigateToLogin(){
        loadView("LoginView.fxml");
    }

    public static void navigateToHome(){
        loadView("HomeView.fxml");
    }



    //GESTIONE LOGIN
    public static void setAuthenticatedUser(String email) {
        authenticatedUser = email;
    }

    public static void setUtenteSelezionato(Utente pazient){
        paziente = pazient;
    }

    public static String getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static Utente getUtenteSelezionato(){
        return paziente;
    }


    public static boolean isAuthenticated() {
        return authenticatedUser != null;
    }

    public static void textNavbar() throws Exception {
        MainController m = NavigatorView.getMainController();

        if (isAuthenticated()) {
            m.mostraTextNavbar();  // se autenticato, mostra il testo personalizzato
        } else {
            m.eliminaTextNavbar(); // se non autenticato, elimina testo
        }
    }


}
