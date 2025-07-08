package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.ControlliSistema;
import com.dashapp.model.StatoFarmaco;
import com.dashapp.model.*;
//import com.dashapp.model.AssunzioneFarmaco;        da toigliere commento
import com.dashapp.services.DataService;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.*;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class AddAssunzioneController extends AddController{


    DashboardPatientController parentController;

    BoxDashboardControllerPatient parentBoxController;
    @FXML
    private ComboBox<String> farmacoAssuntoBox;
    @FXML
    private DatePicker dataField;        //DA FARE: usare Datepicker
    @FXML
    private TextField oraField;
    @FXML
    private TextField quantitaField;            //NON NECESSARIA< prende valore automaticamente

    @FXML
    private ComboBox<Terapia> terapiaIdBox;
    @FXML
    private TextArea farmaciDaAssumere;

    private Map<Farmaco, StatoFarmaco> mappaAssunzioni;

    private boolean doAnyway;

    private DataService ds;
    @FXML
    public void initialize() {


        farmaciDaAssumere.setText("Caricamento farmaci in corso...");

        // Task in background


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
                    mappaAssunzioni = mappa;
                    farmaciDaAssumere.setText(mapToString(mappaAssunzioni));

                    // ✅ Solo ora aggiungi i listener: la mappa è inizializzata
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
                        assunzioniCompletate();
                    }
                    doAnyway = false;

                });

                return null;
            }
        };



        //gestisce errori nel caricamento
        loadDataTask.setOnFailed(e -> {
            Throwable ex = loadDataTask.getException();
            ex.printStackTrace();
            Platform.runLater(() -> farmaciDaAssumere.setText("Errore nel caricamento."));
        });

        // Avvia il task
        Thread th = new Thread(loadDataTask);
        th.setDaemon(true);
        th.start();




    }

    @FXML
    private void registraAssunzione() throws Exception {

        //logica




        LocalTime ora;
        try {
            ora = LocalTime.parse(this.oraField.getText());
        } catch (DateTimeParseException e) {
            System.err.println("Errore: orario non valido, usare formato HH:mm oppure HH:mm:ss");
            return;
        }

        LocalDate data;
        try{
            data = dataField.getValue();
        }catch (DateTimeParseException e){
            System.err.println("Errore: data non valida, usare formato YYY-MM-DD");
            return;
        }

        //DA SISTEMARE
        //AssunzioneFarmaco assunzione = new AssunzioneFarmaco("1", data, ora, quantita, "1", "1");
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
            ds.addAssunzione(risultato.get().getId(), data.atTime(ora), "assunto");
            parentController.FlagAssunzioniLabel.setText("Registra eventuali Sintomi e le tue assunzioni giornaliere\n" +
                    "Oggi hai assunto " + parentController.calcolaAssunzioniEffettuate() + " farmaci");
        }
        parentController.backToDashboard();
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
            farmacoAssuntoBox.setPromptText("TUTTI I FARMACI DI QUESTA TERAPIA SONO STATI ASSUNTI");
        }else {
            farmacoAssuntoBox.setItems(options);
            farmacoAssuntoBox.setPromptText("selezionare farmaco da assumere");
        }


    }

    private void updateDoseField(int terapiaId) throws Exception {
        int dose = 0;
        List<AssociazioneFarmaco> associazioni = List.of(ds.getAssociazioniFarmaciByTerapia(terapiaId));
        String farmacoSelezionato = farmacoAssuntoBox.getValue();
        Farmaco FarmacoSelezionato = ds.getFarmacoByNome(farmacoSelezionato);

        Optional<AssociazioneFarmaco> match = associazioni.stream()
                .filter(a -> a.getIdFarmaco() == FarmacoSelezionato.getId())
                .findFirst();

        if (match.isPresent()) {
            dose = match.get().getDose();
            quantitaField.setText(String.valueOf(dose) + "mg");
        } else {
            quantitaField.clear();
        }

    }

    private String mapToString(Map<Farmaco, StatoFarmaco> mappa) {
        StringBuilder sb = new StringBuilder();

        for (Map.Entry<Farmaco, StatoFarmaco> entry : mappa.entrySet()) {
            Farmaco farmaco = entry.getKey();
            StatoFarmaco stato = entry.getValue();
            System.out.println(stato.getStato());
            if(stato.getStato() == "da assumere") {
                sb.append(farmaco.getNome())
                        .append(" : ")
                        .append(stato.getStato())
                        .append(" (" + stato.getAssunzioniRimaste())
                        .append(stato.getAssunzioniRimaste()>1 ? " assunzioni)\n" : " assunzione)\n");
            }else{
                sb.append(farmaco.getNome())
                        .append(" : ")
                        .append(stato.getStato())
                        .append(" ✓ \n");

            }
        }
        return sb.toString();
    }


    public void setParentController(DashboardPatientController controller) {
        this.parentController = controller;
    }

    public void setParentBoxController(BoxDashboardControllerPatient controller) {
        this.parentBoxController = controller;
    }


    public void assunzioniCompletate(){

        if (mappaAssunzioni == null) {
            System.err.println("mappaAssunzioni non inizializzata, annullo assunzioniCompletate");
            return;
        }

        for (StatoFarmaco stato : mappaAssunzioni.values()) {

            if(stato.getStato() == "da assumere"){
                return;
            }
        }
        parentBoxController.bodyContainer.getChildren().clear();
        parentBoxController.bodyContainer.setAlignment(Pos.CENTER);
        Label completatoLabel = new Label("HAI ASSUNTO TUTTI I FARMACI DI OGGI");
        completatoLabel.setStyle(
                "-fx-font-size: 28px;" +
                        "-fx-text-fill: #cb6ce6;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 20;" +
                        "-fx-effect: dropshadow(gaussian, rgba(0,0,0,0.4), 4,0,0,2);" +
                        "-fx-alignment: center;"
        );

        Button assumiComunque = new Button("voglio comunque assumere un farmaco");
        assumiComunque.setStyle(
                "-fx-background-color: #cb6ce6;" +
                        "-fx-text-fill: white;" +
                        "-fx-font-size: 16px;" +
                        "-fx-font-weight: bold;" +
                        "-fx-padding: 10 20 10 20;" +
                        "-fx-background-radius: 8;" +
                        "-fx-cursor: hand;" +
                        "-fx-alignment: center;"
        );
        parentBoxController.bodyContainer.getChildren().add(completatoLabel);
        parentBoxController.bodyContainer.getChildren().add(assumiComunque);


        //NON VA, NON SO PERCHE
        assumiComunque.setOnAction(e -> {
            try {
                AddAssunzioneController nuovo = parentBoxController.aggiungiAssunzione();
                nuovo.setDoAnyway(true);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

    }

    public void setDoAnyway(boolean b){
        doAnyway = b;
    }





}
