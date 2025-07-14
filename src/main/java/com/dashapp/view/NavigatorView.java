package com.dashapp.view;

import com.dashapp.Main;
import com.dashapp.controller.MainController;
import com.dashapp.controller.dashboardAdmin.DashboardAdminController;
import com.dashapp.controller.dashboardMedico.DashboardMedicController;
import com.dashapp.controller.dashboardPatient.DashboardPatientController;
import com.dashapp.model.*;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;

import javax.management.relation.RoleInfoNotFoundException;
import java.io.IOException;
import java.net.URL;

public class NavigatorView {
    // Reference to the main controller
    private static MainController mainController;

    // Current authenticated username
    private static String authenticatedUser = null;

    private static Utente paziente = null;
    private static Farmaco farmaco = null;
    private static Sintomo sintomo = null;
    private static Rilevazione rilevazione = null;
    private static Assunzione assunzione = null;
    private static Terapia terapia = null;
    private static DashboardAdminController adminController;
    private static DashboardMedicController medicController;
    private static DashboardPatientController patientController;


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
            Object controller = loader.getController();

            if (controller instanceof DashboardMedicController) {
                setMedicController((DashboardMedicController) controller);
            } else if (controller instanceof DashboardAdminController) {
                setAdminController((DashboardAdminController) controller);
            } else if (controller instanceof DashboardPatientController) {
                setPatientController((DashboardPatientController) controller);
            }
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

    public static void navigateToDashboardAdmin(){
        loadView("DashBoardAdmin/DashboardViewAdmin.fxml");
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

    public static String getAuthenticatedUser() {
        return authenticatedUser;
    }

    public static void setUtenteSelezionato(Utente pazient){
        paziente = pazient;
    }

    public static Utente getUtenteSelezionato(){
        return paziente;
    }

    public static void setFarmacoSelezionato(Farmaco farmac){
        farmaco = farmac;
    }

    public static Farmaco getFarmacoSelezionato(){
        return farmaco;
    }

    public static void setSintomoSelezionato(Sintomo sintom){
        sintomo = sintom;
    }

    public static Sintomo getSintomoSelezionato(){
        return sintomo;
    }

    public static void setRilevaizoneSelezionata(Rilevazione rilevaz){
        rilevazione = rilevaz;
    }

    public static Rilevazione getRilevazioneSelezionata(){
        return rilevazione;
    }

    public static void setAssunzioneSelezionata(Assunzione assunz){ assunzione = assunz;}
    public static Assunzione getAssunzioneSelezionata(){ return assunzione;};

    public static void setTerapiaSelezionata(Terapia terap){ terapia = terap;}

    public static Terapia getTerapiaSelezionata(){ return terapia;};

    public static void setAdminController(DashboardAdminController controller) {
        adminController = controller;
    }

    public static DashboardAdminController getAdminController() {
        return adminController;
    }

    public static void setMedicController(DashboardMedicController controller) {
        medicController = controller;
    }

    public static DashboardMedicController getMedicController() {
        return medicController;
    }

    public static void setPatientController(DashboardPatientController controller) {
        patientController = controller;
    }

    public static DashboardPatientController getPatientController() {
        return patientController;
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
