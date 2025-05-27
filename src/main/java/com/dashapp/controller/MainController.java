package com.dashapp.controller;

import com.dashapp.model.Terapia;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

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
    public void initialize() throws Exception {
        utenteCirclePane.setVisible(false);
        logoutLabel.setText("");

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

    public void EliminaTextNavbar() {
        utenteLabel.setText(null);
        logoutLabel.setText(null);
    }

    public void nascondiNavbar() {
        navBarContainer.setVisible(false);
    }

    public void mostraNavbar() {
        navBarContainer.setVisible(true);
    }
}
