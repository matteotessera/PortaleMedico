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
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;

import java.io.IOException;
import java.util.List;

public class FascicoloPazienteController {
    @FXML
    private StackPane contentPane;

    @FXML
    private Button PatologiaButton;

    @FXML
    private Button TerapiaButton;

    @FXML
    private Button SintomoButton;

    private Utente paziente;

    @FXML
    private HBox hBoxButton;


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
        if (b == PatologiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/patologie_menu.png"));
        if (b == TerapiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/terapia_menu_2.png"));
        if (b == SintomoButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/sintomi_menu.png"));
        return null;
    }

    private Image getWhiteIconForButton(Button b) {
        if (b == PatologiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/patologie_menu_w.png"));
        if (b == TerapiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/terapia_menu_2_w.png"));
        if (b == SintomoButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/sintomi_menu_w.png"));
        return null;
    }

    public void mostraSoloStackPane(){
        hBoxButton.setVisible(false);
        hBoxButton.setManaged(false);
    }

    @FXML
    public void showPatologie() throws Exception {
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
    public void showSintomi() throws Exception {
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
    public void showTerapie() throws Exception {
        //Hterapie.setStyle(activeStyle + "-fx-background-color: #f2af2933;");
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/TabTerapie.fxml"));
        Parent showTerapieContent = loader.load();
        TabTerapieController tabcontroller = loader.getController();
        tabcontroller.setIdPaziente(paziente);
        contentPane.getStylesheets().add(getClass().getResource("/com/dashapp/css/fascicoloPaziente.css").toExternalForm());
        contentPane.getChildren().add(showTerapieContent);
    }

}
