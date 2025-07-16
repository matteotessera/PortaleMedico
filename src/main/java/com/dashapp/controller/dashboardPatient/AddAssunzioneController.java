package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.ControlliSistema;
import com.dashapp.model.StatoFarmaco;
import com.dashapp.model.*;
import com.dashapp.services.DataService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;

public class AddAssunzioneController extends AddController {

    DashboardPatientController parentController;

    BoxDashboardControllerPatient parentBoxController;

    @FXML
    private ComboBox<String> farmacoAssuntoBox;
    @FXML
    private DatePicker dataField;
    @FXML
    private Spinner oraField;
    @FXML
    private Spinner minutiField;
    @FXML
    private TextField quantitaField;
    @FXML
    private ComboBox<Terapia> terapiaIdBox;
    @FXML
    private TableView<Farmaco> farmaciDaAssumere;
    @FXML
    private TableColumn<Farmaco, String> farmacoCol;
    @FXML
    private TableColumn<Farmaco, Integer> quantitaCol;

    private Map<Farmaco, StatoFarmaco> mappaAssunzioni;

    @FXML
    private Label erroreLabel;

    private boolean doAnyway;

    private DataService ds;

    @FXML
    public void initialize() {
        checkOra();

        // Task in background per caricare dati
        Task<Void> loadDataTask = new Task<>() {
            @Override
            protected Void call() throws Exception {
                ControlliSistema controlli = new ControlliSistema();
                int idPaziente = BoxDashboardControllerPatient.u.getId();

                ds = new DataService();

                List<Terapia> terapiePaziente = List.of(ds.getTerapiePaziente(idPaziente));
                Map<Farmaco, StatoFarmaco> mappa = controlli.farmaciDaAssumerePaziente(idPaziente);

                Platform.runLater(() -> {
                    terapiaIdBox.setItems(FXCollections.observableArrayList(terapiePaziente));
                    terapiaIdBox.setCellFactory(lv -> new ListCell<>() {
                        @Override
                        protected void updateItem(Terapia item, boolean empty) {
                            super.updateItem(item, empty);
                            if (empty || item == null) {
                                setGraphic(null);
                            } else {
                                Text nome = new Text("Terapia " + item.getId() + "\n");
                                nome.setStyle("-fx-font-weight: bold");

                                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                                Text dettagli = new Text("Data: " + item.getDataInizio().format(formatter) + " - " +
                                        item.getDataFine().format(formatter) + "\nDettagli: " + item.getNote());
                                TextFlow flow = new TextFlow(nome, dettagli);
                                setGraphic(flow);
                            }
                        }
                    });

                    mappaAssunzioni = mappa;

                    configuraTableView();
                    aggiornaFarmaciTable();

                    terapiaIdBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            try {
                                updateFarmaciComboBox(newVal.getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    farmacoAssuntoBox.getSelectionModel().selectedItemProperty().addListener((obs, oldVal, newVal) -> {
                        if (newVal != null) {
                            try {
                                updateDoseField(terapiaIdBox.getValue().getId());
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    });

                    if(!doAnyway && mappaAssunzioni != null) {
                        try {
                            assunzioniCompletate();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                    doAnyway = false;

                });

                return null;
            }
        };

        loadDataTask.setOnFailed(e -> {
            Throwable ex = loadDataTask.getException();
            ex.printStackTrace();
            // eventualmente log o alert
        });

        Thread th = new Thread(loadDataTask);
        th.setDaemon(true);
        th.start();



    }

    private void checkOra(){
        // Ore Spinner
        SpinnerValueFactory<Integer> oreFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 23, 8);
        oraField.setValueFactory(oreFactory);
        oraField.setEditable(true);
        oraField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                oraField.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
        });

        // Minuti Spinner
        SpinnerValueFactory<Integer> minutiFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0, 59, 0);
        minutiField.setValueFactory(minutiFactory);
        minutiField.setEditable(true);
        minutiField.getEditor().textProperty().addListener((obs, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                minutiField.getEditor().setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
    }

    private void configuraTableView() {
        farmacoCol.setCellValueFactory(cellData ->
                new javafx.beans.property.SimpleStringProperty(cellData.getValue().getNome())
        );        quantitaCol.setCellValueFactory(cellData -> {
            Farmaco farmaco = cellData.getValue();
            StatoFarmaco stato = mappaAssunzioni.get(farmaco);
            int rimaste = (stato != null) ? stato.getAssunzioniRimaste() : 0;
            return new javafx.beans.property.SimpleIntegerProperty(rimaste).asObject();
        });
    }

    private void aggiornaFarmaciTable() {
        if (mappaAssunzioni != null) {
            ObservableList<Farmaco> farmaciObservable = FXCollections.observableArrayList(mappaAssunzioni.keySet());
            farmaciDaAssumere.setItems(farmaciObservable);
        }
    }


    public boolean checkCampi() {
        LocalDate data = dataField.getValue();
        int ora = (int) oraField.getValue();
        int minuti = (int) minutiField.getValue();


        if(farmacoAssuntoBox.getValue() == null){
            mostraAlert("Errore", "Non è stato selezionato nessun farmaco", Alert.AlertType.ERROR);
            return false;
        }
        if(terapiaIdBox.getValue() == null){
            mostraAlert("Errore", "Non è stata selezionata nessuna terapia", Alert.AlertType.ERROR);
            return false;
        }
        if (data == null) {
            mostraAlert("Errore", "Devi inserire una data.", Alert.AlertType.ERROR);
            return false;
        }
        if (data.isAfter(LocalDate.now())) {
            mostraAlert("Errore", "La data non può essere nel futuro", Alert.AlertType.ERROR);
            return false;
        }
        if (ora < 0 || ora > 23) {
            mostraAlert("Errore", "L'ora ha un valore errato (deve essere tra 0 e 23)", Alert.AlertType.ERROR);
            return false;
        }
        if (minuti < 0 || minuti > 59) {
            mostraAlert("Errore", "I minuti hanno un valore errato (deve essere tra 0 e 59)", Alert.AlertType.ERROR);
            return false;
        }

        return true;
    }


    @FXML
    private void registraAssunzione() throws Exception {
        LocalTime ora;

        if(checkCampi()) {

            LocalDate data = dataField.getValue();
            LocalTime orario = LocalTime.of((Integer) oraField.getValue(), (Integer) minutiField.getValue());
            LocalDateTime nuovaData = LocalDateTime.of(data, orario);


            AssociazioneFarmaco[] associazioni = ds.getAssociazioniFarmaciByTerapia(terapiaIdBox.getValue().getId());
            Optional<AssociazioneFarmaco> risultato = Arrays.stream(associazioni)
                    .filter(a -> {
                        try {
                            Farmaco f = ds.getFarmacoById(a.getIdFarmaco());
                            return f.getNome().equals(farmacoAssuntoBox.getValue());
                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }
                    })
                    .findFirst();

            if (risultato.isPresent()) {
                ds.addAssunzione(risultato.get().getId(), data.atTime(orario), "assunto");
                mostraAlert("Successo", "Assunzione effettuata con successo!", Alert.AlertType.INFORMATION);

                parentController.FlagAssunzioniLabel.setText("Oggi hai assunto " + parentController.calcolaAssunzioniEffettuate() + " farmaci");
            }
            parentController.backToDashboard();
        }
    }

    private void updateFarmaciComboBox(int terapiaId) throws Exception {

        List<AssociazioneFarmaco> associazioni = List.of(ds.getAssociazioniFarmaciByTerapia(terapiaId));

        List<String> farmaci = new ArrayList<>();

        for (AssociazioneFarmaco a : associazioni) {
            Farmaco f = ds.getFarmacoById(a.getIdFarmaco());
            System.out.println(mappaAssunzioni);
            StatoFarmaco stato = mappaAssunzioni.get(f);
            if(stato == null || stato.getStato().equals("da assumere")) {
                System.out.println(f.getNome() + ": " + stato.getStato());
                farmaci.add(f.toString());
            }
        }

        ObservableList<String> options = FXCollections.observableArrayList(farmaci);
        if(options.isEmpty()) {
            farmacoAssuntoBox.setItems(null);
            farmacoAssuntoBox.setPromptText("Tutti i farmaci di questa terapia sono stati assunti");
        }else {
            farmacoAssuntoBox.setItems(options);
            farmacoAssuntoBox.setPromptText("Selezionare farmaco da assumere");
        }


    }

    private void updateDoseField(int terapiaId) throws Exception {
        List<AssociazioneFarmaco> associazioni = List.of(ds.getAssociazioniFarmaciByTerapia(terapiaId));
        String farmacoSelezionato = farmacoAssuntoBox.getValue();
        Farmaco farmacoSelezionatoObj = ds.getFarmacoByNome(farmacoSelezionato);

        Optional<AssociazioneFarmaco> match = associazioni.stream()
                .filter(a -> a.getIdFarmaco() == farmacoSelezionatoObj.getId())
                .findFirst();

        if (match.isPresent()) {
            int dose = match.get().getDose();
            quantitaField.setText(String.valueOf(dose) + "mg");
        } else {
            quantitaField.clear();
        }
    }

    public void setParentController(DashboardPatientController controller) {
        this.parentController = controller;
    }

    public void setParentBoxController(BoxDashboardControllerPatient controller) {
        this.parentBoxController = controller;
    }

    public void assunzioniCompletate() throws IOException {
        if (mappaAssunzioni == null) {
            //System.err.println("mappaAssunzioni non inizializzata, annullo assunzioniCompletate");
            return;
        }

        boolean daAssumere = mappaAssunzioni.values().stream()
                .anyMatch(stato -> "da assumere".equals(stato.getStato()));

        if (daAssumere) {
            return;
        }

        try {
            parentBoxController.bodyContainer.getChildren().clear();
            parentBoxController.bodyContainer.setAlignment(Pos.CENTER);

            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/NoAssunzioni.fxml"));
            Parent root = loader.load();

            parentBoxController.bodyContainer.getChildren().add(root);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void mostraAlert(String titolo, String contenuto, Alert.AlertType tipo) {
        Alert alert = new Alert(tipo);
        alert.setTitle(titolo);
        alert.setHeaderText(null);
        alert.setContentText(contenuto);
        alert.showAndWait();
    }

}
