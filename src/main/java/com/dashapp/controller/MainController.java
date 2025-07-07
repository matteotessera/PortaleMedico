package com.dashapp.controller;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.controller.dashboardPatient.DashboardPatientController;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.services.LoginService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.util.List;

public class MainController {

    @FXML
    private BorderPane mainContainer;

    @FXML
    private StackPane stackPane;

    @FXML
    private VBox sidebarVBox;

    @FXML
    private Node leftPanel;

    @FXML
    private Node topPanel;

    @FXML
    private Label utenteLabel;

    @FXML
    private Label logoutLabel;

    @FXML
    private Circle cerchioNavbar;

    @FXML
    private StackPane utenteCirclePane;

    @FXML
    private ImageView messageButton;

    @FXML
    private VBox navBarContainer;

    @FXML
    private Button homeButton;
    @FXML
    private Button profileButton;
    @FXML
    private Button messagesButton;
    @FXML
    private Button drugsButton;
    @FXML
    private Button therapyButton;

    private DashboardPatientController dashboardController;


    @FXML
    public void initialize() throws Exception {
        utenteCirclePane.setVisible(false);
        logoutLabel.setText("");

        if (!NavigatorView.isAuthenticated()) {
            hideSidebar();
        }
        NavigatorView.setMainController(this);


        setActiveButton(homeButton);

        homeButton.setOnAction(e -> setActiveButton(homeButton));
        profileButton.setOnAction(e -> setActiveButton(profileButton));
        messagesButton.setOnAction(e -> setActiveButton(messagesButton));
        drugsButton.setOnAction(e -> setActiveButton(drugsButton));
        therapyButton.setOnAction(e -> setActiveButton(therapyButton));
    }


    private void setActiveButton(Button active) {
        List<Button> buttons = List.of(homeButton, profileButton, messagesButton, drugsButton, therapyButton);
        for (Button b : buttons) {
            b.getStyleClass().remove("active");
            ImageView iv = (ImageView) b.getGraphic().lookup("ImageView");
            if (iv != null) {
                iv.setImage(getBlueIconForButton(b));
            }
        }

        if (!active.getStyleClass().contains("active")) {
            active.getStyleClass().add("active");
            ImageView iv = (ImageView) active.getGraphic().lookup("ImageView");
            if (iv != null) {
                iv.setImage(getWhiteIconForButton(active));
            }
        }
    }

    private Image getBlueIconForButton(Button b) {
        if (b == homeButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/home_menu.png"));
        if (b == profileButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/user_menu.png"));
        if (b == messagesButton)
            return new Image(getClass().getResourceAsStream("/com/dashapp/images/messaggi_menu.png"));
        if (b == drugsButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/farmaco_menu.png"));
        if (b == therapyButton)
            return new Image(getClass().getResourceAsStream("/com/dashapp/images/terapia_menu.png"));
        return null;
    }

    private Image getWhiteIconForButton(Button b) {
        if (b == homeButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/home_menu_w.png"));
        if (b == profileButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/user_menu_w.png"));
        if (b == messagesButton)
            return new Image(getClass().getResourceAsStream("/com/dashapp/images/messaggi_menu_w.png"));
        if (b == drugsButton)
            return new Image(getClass().getResourceAsStream("/com/dashapp/images/farmaco_menu_w.png"));
        if (b == therapyButton)
            return new Image(getClass().getResourceAsStream("/com/dashapp/images/terapia_menu_w.png"));
        return null;
    }


    public void nascondiComponentiMain() {
        hideSidebar();
        hideNavbar();
    }

    public void hideNavbar() {
        topPanel = mainContainer.getTop();
        mainContainer.setTop(null);
    }

    public void viewNavbar() {
        mainContainer.setTop(topPanel);
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

    //SCRIVO SULLA NAVBAR L'INIZIALE DEL NOME E DEL COGNOME DOPO IL LOGIN
    public void mostraTextNavbar() throws Exception {
        DataService ds = new DataService();
        String email = NavigatorView.getAuthenticatedUser();
        Utente u = ds.getUtenteByEmail(email);

        char nome = u.getNome().toUpperCase().charAt(0);
        char cognome = u.getCognome().toUpperCase().charAt(0);

        utenteLabel.setText(nome + "" + cognome);
        utenteLabel.setStyle("-fx-text-fill: #1b4965;");

        logoutLabel.setText("Logout");

        // Rendo visibile il cerchio con il testo
        utenteCirclePane.setVisible(true);
        messageButton.setVisible(true);
    }

    public void eliminaTextNavbar() {
        utenteLabel.setText(null);
        logoutLabel.setText(null);
    }

    public void nascondiNavbar() {
        navBarContainer.setVisible(false);
    }

    public void mostraNavbar() {
        navBarContainer.setVisible(true);
    }

    public void handleLogoutClick() {
        LoginService logninService = new LoginService();
        logninService.logOut();
        // da cambiare la view
    }

    @FXML
    public void vediProfilo() throws Exception {
        if (dashboardController != null) {
            dashboardController.vediProfilo();
        } else {
            // Se per qualche motivo non è ancora caricato, caricalo e poi chiama vediProfilo
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/DashBoardViewPatient.fxml"));
            Parent dashboardRoot = loader.load();
            dashboardController = loader.getController();

            stackPane.getChildren().clear();
            stackPane.getChildren().add(dashboardRoot);

            dashboardController.vediProfilo();
        }
    }

}
