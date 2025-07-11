package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Utente;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class FascicoloPazienteController {

    @FXML
    private Label Hpatologie;

    @FXML
    private Label Hsintomi;

    @FXML
    private Label Hterapie;

    @FXML
    private StackPane contentPane;

    @FXML
    private Button PatologiaButton;

    @FXML
    private Button TerapiaButton;

    @FXML
    private Button SintomoButton;

    private Utente paziente;


    private String activeStyle = "-fx-font-weight: bold; -fx-background-color: #F8FFBC; -fx-border-width: 1; -fx-font-size: 20px;";

    @FXML
    private void initialize() throws Exception {

        /*Hsintomi.setStyle("-fx-font-weight: bold; -fx-border-color: #0C0E02; -fx-border-width: 1; ");
        Hterapie.setStyle("-fx-font-weight: bold;  -fx-border-color: #0C0E02; -fx-border-width: 1;");
        Hpatologie.setStyle(activeStyle);*/

    }

    public void setPaziente(Utente u) throws Exception {
        paziente = u;

        setActiveButton(PatologiaButton);

        PatologiaButton.setOnAction(e -> handleButtonClick(PatologiaButton));
        TerapiaButton.setOnAction(e -> handleButtonClick(TerapiaButton));
        SintomoButton.setOnAction(e -> handleButtonClick(SintomoButton));

        showPatologie();
    }

    private void setActiveButton(Button active) throws Exception {
        List<Button> buttons = List.of(PatologiaButton, TerapiaButton, SintomoButton);
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
            if (active == PatologiaButton) {
                showPatologie();
            } else if (active == TerapiaButton) {
                showTerapie();
            } else if (active == SintomoButton) {
                showSintomi();
            }
        }
    }

    private void handleButtonClick(Button button) {
        try {
            setActiveButton(button);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Image getBlueIconForButton(Button b) {
        if (b == PatologiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/home_menu.png"));
        if (b == TerapiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/user_menu.png"));
        if (b == SintomoButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/messaggi_menu.png"));
        return null;
    }

    private Image getWhiteIconForButton(Button b) {
        if (b == PatologiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/home_menu_w.png"));
        if (b == TerapiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/user_menu_w.png"));
        if (b == SintomoButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/messaggi_menu_w.png"));
        return null;
    }


    @FXML
    void showPatologie() throws Exception {
        resetLabelStyles();
        //Hpatologie.setStyle(activeStyle + "-fx-background-color: #ad343e33;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/TabPatologie.fxml"));
        Parent showPatologieContent = loader.load();
        TabPatologieController tabcontroller = loader.getController();
        tabcontroller.setIdPaziente(paziente);
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showPatologieContent);
    }

    @FXML
    void showSintomi() throws Exception {
        resetLabelStyles();
        //Hsintomi.setStyle(activeStyle + "-fx-background-color: #9caf3933;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/TabSintomi.fxml"));
        Parent showSintomiContent = loader.load();
        TabSintomiController tabcontroller = loader.getController();
        tabcontroller.setIdPaziente(paziente);
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showSintomiContent);
    }

    @FXML
    void showTerapie() throws Exception {
        resetLabelStyles();
        //Hterapie.setStyle(activeStyle + "-fx-background-color: #f2af2933;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/TabTerapie.fxml"));
        Parent showTerapieContent = loader.load();
        TabTerapieController tabcontroller = loader.getController();
        tabcontroller.setIdPaziente(paziente);
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showTerapieContent);
    }

    private void resetLabelStyles() {
        //Hpatologie.setStyle("");
        //Hsintomi.setStyle("");
        //Hterapie.setStyle("");
    }



}
