package com.dashapp.controller.dashboardAdmin;

import com.dashapp.model.Accesso;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.WeekFields;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.stream.Collectors;


public class DashboardAdminController {

        private DataService ds;

        @FXML
        private AnchorPane overlayPaneFarmaci;
        @FXML
        private AnchorPane overlayPanePazienti;
        @FXML
        private AnchorPane overlayPaneTerapia;
        @FXML
        private StackPane mainContent;
        @FXML
        private Label doctorName;
        @FXML
        private StackPane utenteCirclePane;
        @FXML
        private Circle cerchioNavbar;
        @FXML
        private Label utenteLabel;
        @FXML
        public Label numeroUtenti;
        @FXML
        public Label numeroFarmaci;

        @FXML
        public ComboBox utenteComboBox;

        private Parent originalContent;

        private BoxDashboardController controller;

        private int nUtenti;

        @FXML
        private LineChart<String, Number> graficoAndamento;

        @FXML
        private CategoryAxis xAxis;

        @FXML
        private NumberAxis yAxis;

        public void initialize() throws Exception {
                ds = new DataService();

                // Caricamento utenti e ComboBox
                caricaComboBox();

                numeroUtenti.setText(String.valueOf(nUtenti));
                numeroFarmaci.setText(String.valueOf(ds.getFarmaci().length));

                mostraTextMeico();

                if (!mainContent.getChildren().isEmpty()) {
                        originalContent = (Parent) mainContent.getChildren().get(0);
                }

                // Listener selezione utente
                utenteComboBox.setOnAction(e -> {
                        Utente u = (Utente) utenteComboBox.getValue();
                        if (u != null) {
                                try {
                                        aggiornaGrafico(u.getId());
                                } catch (Exception ex) {
                                        ex.printStackTrace();
                                }
                        }
                });
        }

        private void aggiornaGrafico(int userId) throws Exception {
                graficoAndamento.getData().clear();

                final Accesso[] accessi = ds.getAccessiByUtente(userId);

                final LocalDate oggi = LocalDate.now();
                final LocalDate trentaGiorniFa = oggi.minusDays(30);

                List<Accesso> accessiUltimi30Giorni = Arrays.stream(accessi)
                        .filter(a -> {
                                LocalDate data = a.getData().toLocalDate();  // <-- qui prendi solo la data
                                return data != null && !data.isBefore(trentaGiorniFa) && !data.isAfter(oggi);
                        })
                        .collect(Collectors.toList());

                Map<LocalDate, Long> accessiPerGiorno = accessiUltimi30Giorni.stream()
                        .collect(Collectors.groupingBy(
                                a -> a.getData().toLocalDate(),  // <-- raggruppa per data senza ora
                                TreeMap::new,
                                Collectors.counting()
                        ));

                XYChart.Series<String, Number> serie = new XYChart.Series<>();
                serie.setName("Accessi ultimi 30 giorni per giorno");

                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM");

                for (LocalDate giorno = trentaGiorniFa; !giorno.isAfter(oggi); giorno = giorno.plusDays(1)) {
                        long count = accessiPerGiorno.getOrDefault(giorno, 0L);
                        serie.getData().add(new XYChart.Data<>(giorno.format(formatter), count));
                }

                graficoAndamento.getData().add(serie);
        }

        public void caricaComboBox() throws Exception {
                Utente[] utenti = ds.getUtenti();

                // Pulisco prima la combo se serve
                utenteComboBox.getItems().clear();

                for (Utente u : utenti) {
                        if (!u.getRuolo().equals("Admin")) {
                                nUtenti++;
                                utenteComboBox.getItems().add(u);
                        }
                }

                // Definisco come mostrare i nomi nella lista a discesa
                utenteComboBox.setCellFactory(lv -> new ListCell<Utente>() {
                        @Override
                        protected void updateItem(Utente item, boolean empty) {
                                super.updateItem(item, empty);
                                setText(empty || item == null ? "" : item.getNome() + " " + item.getCognome());
                        }
                });

                // Definisco come mostrare il nome dell'elemento selezionato nel campo della combo
                utenteComboBox.setButtonCell(new ListCell<Utente>() {
                        @Override
                        protected void updateItem(Utente item, boolean empty) {
                                super.updateItem(item, empty);
                                setText(empty || item == null ? "" : item.getNome() + " " + item.getCognome());
                        }
                });
        }


        public void mostraBox() throws IOException {
                // Rimuovo tutto dal mainContent
                mainContent.getChildren().clear();

                // Carico il nuovo contenuto da FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardAdmin/BoxDashboard.fxml"));
                Parent newContent = loader.load();

                controller = loader.getController();
                controller.setDashboardController(this);

                // Aggiungo il nuovo contenuto
                mainContent.getChildren().add(newContent);
        }

        public void aggiungiFarmaco() throws IOException {
                if (controller == null) {
                        mostraBox();
                }
                controller.aggiungiFarmaco();
        }

        public void aggiungiUtente()throws IOException {
                if (controller == null) {
                        mostraBox();
                }
                controller.aggiungiUtente();
        }

        public void listaUtenti() throws IOException {

                if (controller == null) {
                        mostraBox();
                }
                controller.listaUtenti();
        }

        public void listaFarmaci() throws IOException {

                if (controller == null) {
                        mostraBox();
                }
                controller.listaFarmaci();
        }


        @FXML
        public void backToDashboard() {
                mainContent.getChildren().clear();
                if (originalContent != null) {
                        mainContent.getChildren().add(originalContent);
                }
                controller = null;  // forza a ricaricare BoxDashboard quando serve
        }


        //Metodo per la visualizzazione del nome del medico e dell'immagine con iniziali nome e cognome
        public void mostraTextMeico() throws Exception {
                String email = NavigatorView.getAuthenticatedUser();

                Utente u = ds.getUtenteByEmail(email);
                doctorName.setText("Dr. "+u.getNome()+" "+u.getCognome());

                char nome = u.getNome().toUpperCase().charAt(0);
                char cognome = u.getCognome().toUpperCase().charAt(0);

                utenteLabel.setText(nome + "" + cognome);
                utenteLabel.setStyle("-fx-text-fill: #1b4965;");

                // Rendo visibile il cerchio con il testo
                utenteCirclePane.setVisible(true);
        }

        public void vediProfilo() throws Exception {
                // Pulisci il contenuto centrale
                mainContent.getChildren().clear();

                // Carico il BoxDashboardPatient (container)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardAdmin/BoxDashboard.fxml"));
                Parent boxDashboard = loader.load();

                // Prendo il controller del box
                controller = loader.getController();

                // Passo il riferimento al DashboardPatientController se serve
                controller.setDashboardController(this);

                // Aggiungo il boxDashboard alla parte centrale
                mainContent.getChildren().add(boxDashboard);

                // Chiamo il metodo per caricare il profilo dentro il box
                controller.mostraProfilo();
        }




}
