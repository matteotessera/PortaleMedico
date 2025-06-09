package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.model.AddController;
//import com.dashapp.model.AssunzioneFarmaco;        da toigliere commento
import com.dashapp.model.AssociazioneFarmaco;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Terapia;
import com.dashapp.services.DataService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

public class AddAssunzioneController extends AddController{



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

    private DataService ds;
    public void initialize() throws Exception {

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
        }
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

}
