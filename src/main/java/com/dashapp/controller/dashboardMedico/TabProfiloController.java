package com.dashapp.controller.dashboardMedico;

import com.dashapp.controller.dashboardPatient.fascicolo.FascicoloPazienteController;
import com.dashapp.controller.graficoGlicemiaController;
import com.dashapp.model.Rilevazione;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.ListCell;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TabProfiloController {
    @FXML
    public Button profiloButton;
    @FXML
    public Button cartellaMedicaButton;
    @FXML
    public Button fattoriRischioButton;
    @FXML
    public Button glicemiaButton;
    @FXML
    public StackPane contentPane;
    @FXML
    public ScrollPane graficoPane;
    @FXML
    public AnchorPane fascicoloPane;
    @FXML
    public ComboBox cartellaComboBox;

    private DashboardMedicController dashController;
    private Utente paziente;
    private DataService ds;

    public void initialize() throws Exception {
       paziente = NavigatorView.getUtenteSelezionato();
       ds = new DataService();
       setProfilo(paziente);
       cartellaComboBox.getItems().setAll("Patologie", "Terapie Concomitanti", "Sintomi Concomitanti");

        cartellaComboBox.setOnAction(e -> {
            try {
                showCartellaMedica();
                // Non rimuovere la classe qui, la gestiamo con il listener di focus
                if (cartellaComboBox.getValue() != null) {
                    cartellaComboBox.getStyleClass().add("active");
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        cartellaComboBox.focusedProperty().addListener((obs, oldVal, newVal) -> {
            if (newVal) {
                cartellaComboBox.getStyleClass().add("active");
            } else {
                cartellaComboBox.getStyleClass().remove("active");
            }
        });
        cartellaComboBox.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText(null);
                    setStyle("");
                } else {
                    setText(item);
                    // Sfondo trasparente e testo scuro
                    setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
                }
            }
        });
    }

    public void setDashController(DashboardMedicController c){
        this.dashController = c;
    }


    public void setProfilo(Utente u) throws Exception {
        paziente = u;

        setActiveButton(profiloButton);

        profiloButton.setOnAction(e -> handleButtonClick(profiloButton));
        cartellaMedicaButton.setOnAction(e -> handleButtonClick(cartellaMedicaButton));
        fattoriRischioButton.setOnAction(e -> handleButtonClick(fattoriRischioButton));
        glicemiaButton.setOnAction(e -> handleButtonClick(glicemiaButton));

        showProfilo();
    }

    private void setActiveButton(Button active) throws Exception {
        List<Button> buttons = List.of(profiloButton, cartellaMedicaButton, fattoriRischioButton, glicemiaButton);
        for (Button b : buttons) {
            b.getStyleClass().remove("active");
            ImageView iv = (ImageView) b.getGraphic().lookup("ImageView");
            if (iv != null) {
                iv.setImage(getBlueIconForButton(b));
            }
        }

        if (active != cartellaMedicaButton) {
            cartellaComboBox.setValue(null);
            cartellaComboBox.setPromptText("Cartella medica");
            cartellaComboBox.getStyleClass().remove("active");
            cartellaComboBox.getParent().requestFocus();
        }
        cartellaComboBox.setButtonCell(new ListCell<String>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                super.updateItem(item, empty);
                if (empty || item == null) {
                    setText("Cartella medica");
                    setStyle("-fx-background-color: transparent; -fx-text-fill: #1e3746;");
                } else {
                    setText(item);
                    setStyle("-fx-background-color: transparent; -fx-text-fill: white;");
                }
            }
        });

        if (!active.getStyleClass().contains("active")) {
            active.getStyleClass().add("active");
            ImageView iv = (ImageView) active.getGraphic().lookup("ImageView");
            if (iv != null) {
                iv.setImage(getWhiteIconForButton(active));
            }
            if (active == profiloButton) {
                showProfilo();
            } else if (active == cartellaMedicaButton) {
                showCartellaMedica();
            } else if (active == fattoriRischioButton) {
                showFattoriDiRischio();
            }
            else if (active == glicemiaButton) {
                showGrafico();
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
        if (b == profiloButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/user_menu.png"));
        if (b == cartellaMedicaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/fascicolo_menu.png"));
        if (b == fattoriRischioButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/patologie_menu.png"));
        if (b == glicemiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/grafico_menu.png"));
        return null;
    }

    private Image getWhiteIconForButton(Button b) {
        if (b == profiloButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/user_menu_w.png"));
        if (b == cartellaMedicaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/fascicolo_menu_w.png"));
        if (b == fattoriRischioButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/patologie_menu_w.png"));
        if (b == glicemiaButton) return new Image(getClass().getResourceAsStream("/com/dashapp/images/grafico_menu_w.png"));
        return null;
    }

    @FXML
    void showProfilo() throws Exception {
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/AnagraficaView.fxml"));
        Parent showProfilo = loader.load();
        contentPane.getChildren().add(showProfilo);
    }

    @FXML
    void showCartellaMedica() throws Exception {
        Object select = cartellaComboBox.getValue();
        if(select != null){
            contentPane.getChildren().clear();
            select = cartellaComboBox.getValue().toString();
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/FascicoloPaziente.fxml"));
            Parent fascicolo = loader.load();
            FascicoloPazienteController controller = loader.getController();
            controller.setPaziente(paziente);
            controller.mostraSoloStackPane();
            if(select.equals("Patologie")){
                controller.showPatologie();
            } else if (select.equals("Terapie Concomitanti")) {
                controller.showTerapie();
            }
            else if(select.equals("Sintomi Concomitanti")){
                controller.showSintomi();
            }
            contentPane.getChildren().add(fascicolo);
        }

    }

    @FXML
    void showFattoriDiRischio() throws Exception {
        contentPane.getChildren().clear();
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardMedic/AddInfoPaziente.fxml"));
        Parent showFattori = loader.load();
        contentPane.getChildren().add(showFattori);
    }

    @FXML
    void showGrafico() throws Exception {
        contentPane.getChildren().clear();
        FXMLLoader loaderGrafico = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/graficoGlicemia.fxml"));
        Parent grafico = loaderGrafico.load();
        graficoGlicemiaController graficoController = loaderGrafico.getController();
        Rilevazione[] rilevazioniUtente = ds.getRilevazioniById(paziente.getId());

        graficoController.setRilevazioni(new ArrayList<>(Arrays.asList(rilevazioniUtente)));
        graficoController.setDashboardControllerMedic(dashController);
        graficoController.popolaGraficoTotale();

        contentPane.getChildren().add(grafico);
        contentPane.getStylesheets().add(String.valueOf(getClass().getResource("/com/dashapp/css/grafico.css")));
    }

}
