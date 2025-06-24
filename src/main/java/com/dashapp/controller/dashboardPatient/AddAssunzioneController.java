package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.controller.dashboardPatient.fascicolo.StatoFarmaco;
import com.dashapp.model.*;
//import com.dashapp.model.AssunzioneFarmaco;        da toigliere commento
import com.dashapp.services.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.stream.Collectors;

public class AddAssunzioneController extends AddController{


    DashboardPatientController parentController;
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

    private DataService ds;
    public void initialize() throws Exception {

        if(pazienteNonAderente(BoxDashboardControllerPatient.u.getId()))
            System.out.println("non hai aderito alla tua terapia");
        else
            System.out.println("BRAVO, hai aderito");

        farmaciDaAssumere.setEditable(false);
        quantitaField.setEditable(false);
        //Popola la comboBox
        ds = new DataService();


        List<Terapia> terapiePaziente= List.of(ds.getTerapiePaziente(BoxDashboardControllerPatient.u.getId()));

        ObservableList<Terapia> optionsTerapie = FXCollections.observableArrayList(terapiePaziente);
        terapiaIdBox.setItems(optionsTerapie);

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

        Map<Farmaco, StatoFarmaco> M = farmaciDaAssumere();
        farmaciDaAssumere.setText(mapToString(M));
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
            farmaci.add(f.toString());

        }

        ObservableList<String> options = FXCollections.observableArrayList(farmaci);
        farmacoAssuntoBox.setItems(options);



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
            // Ad esempio, aggiorni un campo testo:
            quantitaField.setText(String.valueOf(dose) + "mg");
        } else {
            quantitaField.clear();
        }


    }

    private Map<Farmaco, StatoFarmaco> farmaciDaAssumere() throws Exception {
        int idUtente = BoxDashboardControllerPatient.u.getId();

        // Assunzioni odierne
        List<Assunzione> assunzioniOggi = Arrays.stream(ds.getAssunzioniPaziente(idUtente))
                .filter(a -> a.getData().toLocalDate().equals(LocalDate.now()))
                .collect(Collectors.toList());

        // Tutte le associazioni farmaco per le terapie del paziente
        List<Terapia> terapie = Arrays.asList(ds.getTerapiePaziente(idUtente));
        List<AssociazioneFarmaco> associazioni = new ArrayList<>();
        for (Terapia t : terapie) {
            associazioni.addAll(Arrays.asList(ds.getAssociazioniFarmaciByTerapia(t.getId())));
        }

        Map<Farmaco, StatoFarmaco> mappa = new HashMap<>();

        for (AssociazioneFarmaco assoc : associazioni) {
            Farmaco farmaco = ds.getFarmacoById(assoc.getIdFarmaco());

            // Assunzioni fatte oggi per questa associazione
            long assunzioniFatte = assunzioniOggi.stream()
                    .filter(a -> a.getIdAssociazioneFarmaco() == assoc.getId())
                    .count();

            int assunzioniRimaste = assoc.getNumeroAssunzioni() - (int) assunzioniFatte;
            assunzioniRimaste = Math.max(0, assunzioniRimaste); // per sicurezza

            String stato = (assunzioniRimaste == 0) ? "già assunto" : "da assumere";
            mappa.put(farmaco, new StatoFarmaco(stato, assunzioniRimaste));
        }

        return mappa;
    }

    private String mapToString(Map<Farmaco, StatoFarmaco> mappa) {
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<Farmaco, StatoFarmaco> entry : mappa.entrySet()) {
            Farmaco farmaco = entry.getKey();
            StatoFarmaco stato = entry.getValue();

            sb.append(farmaco.getNome())
                    .append(" : ")
                    .append(stato.getStato())
                    .append(" (mancano ")
                    .append(stato.getAssunzioniRimaste())
                    .append(" assunzioni)\n");
        }
        return sb.toString();
    }


    public void setParentController(DashboardPatientController controller) {
        this.parentController = controller;
    }

    public boolean pazienteNonAderente(int idPaziente) throws Exception {
        DataService ds = new DataService();
        LocalDate oggi = LocalDate.now();

        // Trova tutte le terapie del paziente
        Terapia[] terapie = ds.getTerapiePaziente(idPaziente);

        for (Terapia terapia : terapie) {
            // Per ogni terapia, prendi le associazioni farmaco
            AssociazioneFarmaco[] associazioni = ds.getAssociazioniFarmaciByTerapia(terapia.getId());

            for (AssociazioneFarmaco af : associazioni) {
                int numeroAssunzioniPreviste = af.getNumeroAssunzioni();
                int giorniConsecutiviNonAderenti = 0;

                for (int i = 3; i >= 1; i--) {
                    LocalDate data = oggi.minusDays(i);

                    // Ottieni tutte le assunzioni per quel giorno
                    List<Assunzione> assunzioni = Arrays.stream(ds.getAssunzioniPaziente(idPaziente))
                            .filter(a -> a.getIdAssociazioneFarmaco() == af.getId())
                            .filter(a -> a.getData().toLocalDate().equals(data))
                            .collect(Collectors.toList());

                    if (assunzioni.size() < numeroAssunzioniPreviste) {
                        giorniConsecutiviNonAderenti++;
                        if (giorniConsecutiviNonAderenti >= 3) {
                            return true; // Paziente non aderente per almeno 3 giorni consecutivi
                        }
                    } else {
                        giorniConsecutiviNonAderenti = 0; // reset
                    }
                }
            }
        }

        return false; // Tutto ok
    }



}
