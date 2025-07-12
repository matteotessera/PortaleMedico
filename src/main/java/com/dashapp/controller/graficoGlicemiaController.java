package com.dashapp.controller;

import com.dashapp.controller.dashboardMedico.BoxDashboardController;
import com.dashapp.controller.dashboardMedico.DashboardMedicController;
import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.controller.dashboardPatient.DashboardPatientController;
import com.dashapp.model.Rilevazione;
import com.dashapp.view.NavigatorView;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Tooltip;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class graficoGlicemiaController {

        @FXML
        LineChart<String, Number> grafico;
        @FXML
        private NumberAxis labelAsseY;
        @FXML
        private CategoryAxis labelAsseX;

        @FXML
        private Button andamentoGeneraleButton;
        @FXML
        private Button prePostButton;
        @FXML
        private Button tipoPastoButton;
        @FXML
        private ComboBox<String> periodoBox;


        private List<Rilevazione> rilevazioni;
        private List<Rilevazione> rilevazioniPre;
        private List<Rilevazione> rilevazioniPost;

        private List<Rilevazione> rilevazioniColazione;
        private List<Rilevazione> rilevazioniPranzo;
        private List<Rilevazione> rilevazioniCena;

        private BoxDashboardControllerPatient boxControllerPatient;
        private DashboardPatientController dashboardControllerPatient;



        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

        private BoxDashboardController boxControllerMedic;
        private DashboardMedicController dashboardControllerMedic;


    @FXML
        public void initialize() {
            grafico.setTitle("Andamento glicemia");
            modificaGraficaGrafico();

            periodoBox.setItems(FXCollections.observableArrayList("Settimana", "Mese", "Tutte"));
            periodoBox.setValue("Tutte");


            periodoBox.setOnAction(e -> {
                // Aggiorna il grafico corrente in base al bottone attivo
                if (andamentoGeneraleButton.getStyle().contains("lightgreen")) {
                    popolaGraficoTotale();
                } else if (prePostButton.getStyle().contains("lightgreen")) {
                    popolaGraficoPrePost();
                } else if (tipoPastoButton.getStyle().contains("lightgreen")) {
                    popolaGraficoPasti();
                }
            });

        }


    public void setRilevazioni(List<Rilevazione> rilevazioni){
        this.rilevazioni = new ArrayList<>(rilevazioni);

        rilevazioni.sort(Comparator.comparing(Rilevazione::getData));

        this.rilevazioniPost = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getTipo().toString().equals("post"))
                        .toList()
        );

        this.rilevazioniPre = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getTipo().toString().equals("pre"))
                        .toList()
        );

        this.rilevazioniColazione = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getPasto().equals("colazione"))
                        .toList()
        );

        this.rilevazioniPranzo = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getPasto().equals("pranzo"))
                        .toList()
        );

        this.rilevazioniCena = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getPasto().equals("cena"))
                        .toList()
        );

    }

    @FXML
    public void popolaGraficoTotale(){
        andamentoGeneraleButton.setStyle("-fx-background-color: lightgreen");
        prePostButton.setStyle("");
        tipoPastoButton.setStyle("");

        XYChart.Series<String, Number> serie = new XYChart.Series<>();

        serie.setName("Tutte le rilevazioni glicemiche");

        for (Rilevazione r : filtraPerPeriodo(rilevazioni)) {
            String dataFormattata = r.getData().format(formatter);
            Number valore = Double.parseDouble(r.getValore());
            XYChart.Data<String, Number> dataPoint = creaToolTip(dataFormattata, valore, r);
            serie.getData().add(dataPoint);
        }

        // Pulisci grafico e aggiungi serie
        grafico.getData().clear();
        grafico.getData().add(serie);

    }

    @FXML
    public void popolaGraficoPrePost() {
        andamentoGeneraleButton.setStyle("");
        modificaGraficaGrafico();

        prePostButton.setStyle("-fx-background-color: lightgreen");
        tipoPastoButton.setStyle("");

        XYChart.Series<String, Number> seriePre = new XYChart.Series<>();
        XYChart.Series<String, Number> seriePost = new XYChart.Series<>();

        seriePre.setName("Rilevazioni Glicemiche Prima dei Pasti");
        seriePost.setName("Rilevazioni Glicemiche Dopo i Pasti");

        for (Rilevazione r : filtraPerPeriodo(rilevazioniPre)) {
            String dataFormattata = r.getData().format(formatter);
            Number valore = Double.parseDouble(r.getValore());
            XYChart.Data<String, Number> dataPoint = creaToolTip(dataFormattata, valore, r);
            seriePre.getData().add(dataPoint);
        }
        for (Rilevazione r : filtraPerPeriodo(rilevazioniPost)) {
            String dataFormattata = r.getData().format(formatter);
            Number valore = Double.parseDouble(r.getValore());
            XYChart.Data<String, Number> dataPoint = creaToolTip(dataFormattata, valore, r);
            seriePost.getData().add(dataPoint);
        }

        // Pulisci grafico e aggiungi serie
        grafico.getData().clear();
        grafico.getData().add(seriePre);
        grafico.getData().add(seriePost);

    }

    @FXML
    public void popolaGraficoPasti() {
        andamentoGeneraleButton.setStyle("");
        prePostButton.setStyle("");
        tipoPastoButton.setStyle("-fx-background-color: lightgreen");

        XYChart.Series<String, Number> serieColazione = new XYChart.Series<>();
        XYChart.Series<String, Number> seriePranzo = new XYChart.Series<>();
        XYChart.Series<String, Number> serieCena = new XYChart.Series<>();

        serieColazione.setName("Rilevazioni Glicemiche a Colazione");
        seriePranzo.setName("Rilevazioni Glicemiche a Pranzo");
        serieCena.setName("Rilevazioni Glicemiche a Cena");

        for (Rilevazione r : filtraPerPeriodo(rilevazioniColazione)) {
            String dataFormattata = r.getData().format(formatter);
            Number valore = Double.parseDouble(r.getValore());
            XYChart.Data<String, Number> dataPoint = creaToolTip(dataFormattata, valore, r);
            serieColazione.getData().add(dataPoint);
        }
        for (Rilevazione r : filtraPerPeriodo(rilevazioniPranzo)) {
            String dataFormattata = r.getData().format(formatter);
            Number valore = Double.parseDouble(r.getValore());
            XYChart.Data<String, Number> dataPoint = creaToolTip(dataFormattata, valore, r);
            seriePranzo.getData().add(dataPoint);
        }
        for (Rilevazione r : filtraPerPeriodo(rilevazioniCena)) {
            String dataFormattata = r.getData().format(formatter);
            Number valore = Double.parseDouble(r.getValore());
            XYChart.Data<String, Number> dataPoint = creaToolTip(dataFormattata, valore, r);
            serieCena.getData().add(dataPoint);
        }

        // Pulisci grafico e aggiungi serie
        grafico.getData().clear();
        grafico.getData().add(serieColazione);
        grafico.getData().add(seriePranzo);
        grafico.getData().add(serieCena);
    }


    private List<Rilevazione> filtraPerPeriodo(List<Rilevazione> lista) {
        String periodo = periodoBox.getValue();
        LocalDateTime now = LocalDateTime.now();

        if (rilevazioni == null) return new ArrayList<>();

        return switch (periodo) {
            case "Settimana" -> lista.stream()
                    .filter(r -> r.getData().isAfter(now.minusWeeks(1)))
                    .toList();
            case "Mese" -> lista.stream()
                    .filter(r -> r.getData().isAfter(now.minusMonths(1)))
                    .toList();
            default -> lista; // "Tutte"
        };
    }

    private void modificaGraficaGrafico() {
        // Cambia sfondo area interna del plot
        Node plotBackground = grafico.lookup(".chart-plot-background");
        if (plotBackground != null) {
            plotBackground.setStyle(
                    "-fx-background-color: white;" +
                            "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.1), 8, 0, 0, 2);"
            );
        }

        // Stile assi
        labelAsseY.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #333333;"
        );

        labelAsseX.setStyle(
                "-fx-font-size: 14px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-text-fill: #333333;"
        );

        // Personalizza linee di griglia (asse Y)
        grafico.lookupAll(".chart-horizontal-grid-lines, .chart-vertical-grid-lines").forEach(line -> {
            line.setStyle(
                    "-fx-stroke: #cccccc;" +      // grigio chiaro
                            "-fx-stroke-dash-array: 2 4;" // tratteggiato leggero
            );
        });

        // Imposta colore serie (se vuoi righe colorate più armoniose)


        // Qui un esempio per la prima serie
        if (!grafico.getData().isEmpty()) {
            XYChart.Series<String, Number> primaSerie = grafico.getData().get(0);
            primaSerie.getNode().setStyle("-fx-stroke: #0078D7; -fx-stroke-width: 2;"); // blu acceso con spessore

        }

    }

    private XYChart.Data<String, Number> creaToolTip(String dataFormattata, Number valore, Rilevazione r){
        XYChart.Data<String, Number> dataPoint = new XYChart.Data<>(dataFormattata, valore);

        // Crea tooltip con info dettagliate della rilevazione
        Tooltip tooltip = new Tooltip(
                "Data: " + dataFormattata + "\n" +
                        "Valore: " + valore + "\n" +
                        "Tipo: " + r.getTipo() + "\n" +
                        "Pasto: " + r.getPasto()
        );


        dataPoint.nodeProperty().addListener((obs, oldNode, newNode) -> {
            if (newNode != null) {
                Tooltip.install(newNode, tooltip);

                newNode.setOnMouseEntered(e -> newNode.setStyle("-fx-scale-x: 1.3; -fx-scale-y: 1.3;"));
                newNode.setOnMouseExited(e -> newNode.setStyle("-fx-scale-x: 1; -fx-scale-y: 1;"));
                newNode.setOnMouseClicked(e -> {
                    NavigatorView.setRilevaizoneSelezionata(r);
                    try {


                        dashboardControllerPatient.mostraBox();
                        dashboardControllerPatient.setBoxControllerGrafico(this);
                        boxControllerPatient.vediRilevazione();

                    } catch (Exception ex) {

                        try {
                            dashboardControllerMedic.mostraBox();
                            dashboardControllerMedic.setBoxControllerGrafico(this);
                            boxControllerMedic.vediRilevazione();

                        } catch (Exception exc) {
                            System.out.println("operazione non valida");
                        }

                    }

                });
            }
        });

        return dataPoint;
    }

    public void setBoxControllerPatient(BoxDashboardControllerPatient controller){
        this.boxControllerPatient = controller;
    }
    public void setDashboardControllerPatient(DashboardPatientController controller){
        this.dashboardControllerPatient = controller;
    }

    public void setBoxController(BoxDashboardController controller){
        this.boxControllerMedic = controller;
    }
    public void setDashboardControllerMedic(DashboardMedicController controller){
        this.dashboardControllerMedic = controller;
    }







}
