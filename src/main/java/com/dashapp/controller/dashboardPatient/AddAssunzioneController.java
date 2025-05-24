package com.dashapp.controller.dashboardPatient;

import com.dashapp.controller.dashboardMedico.OverlayPaneAware;
import com.dashapp.model.AddController;
//import com.dashapp.model.AssunzioneFarmaco;        da toigliere commento
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class AddAssunzioneController extends AddController{

    private Pane overlayPane;

    @FXML
    private ComboBox<String> farmacoAssuntoBox;
    @FXML
    private TextField dataField;        //DA FARE: usare Datepicker
    @FXML
    private TextField oraField;
    @FXML
    private TextField quantitaField;

    public void initialize(){


        //Popola la comboBox
        List<String> farmaci = fetchFarmaci();
        ObservableList<String> options = FXCollections.observableArrayList(farmaci);
        farmacoAssuntoBox.setItems(options);
    }

    @FXML
    private void registraAssunzione(){

        //logica

        Double quantita;
        try {
            quantita = Double.parseDouble(this.quantitaField.getText());
        } catch (NumberFormatException e) {
            System.err.println("Errore: valore glicemia non valido");
            return; // oppure mostra un alert
        }

        LocalTime ora;
        try {
            ora = LocalTime.parse(this.oraField.getText());
        } catch (DateTimeParseException e) {
            System.err.println("Errore: orario non valido, usare formato HH:mm oppure HH:mm:ss");
            return;
        }

        LocalDate data;
        try{
            data = LocalDate.parse(this.dataField.getText());
        }catch (DateTimeParseException e){
            System.err.println("Errore: data non valida, usare formato YYY-MM-DD");
            return;
        }

        //DA SISTEMARE
        //AssunzioneFarmaco assunzione = new AssunzioneFarmaco("1", data, ora, quantita, "1", "1");
    }


    private List<String> fetchFarmaci(){
        // fa il fetch dei farmaci assegnati al utente, per ora e un esempio
        List<String> farmaci = new ArrayList<String>();
        farmaci.add("farmaco1");
        farmaci.add("farmaco2");
        return farmaci;
    }


}
