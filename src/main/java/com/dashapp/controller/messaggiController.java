package com.dashapp.controller;

import com.dashapp.model.Messaggio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class messaggiController {

    @FXML
    private ListView<Messaggio> listView;


    public void initialize(){
        List<Messaggio> messaggi = new ArrayList<>();
        messaggi.add(new Messaggio(1, 101, LocalDate.now(), LocalTime.now(), "Appuntamento", "Non dimenticare il controllo."));
        messaggi.add(new Messaggio(2, 102, LocalDate.now().minusDays(1), LocalTime.of(15,30), "Promemoria", "Prendi la medicina alle 18."));
        messaggi.add(new Messaggio(3, 101, LocalDate.now().minusDays(2), LocalTime.of(9,0), "Saluti", "Buona guarigione!"));

        // Converto in ObservableList e imposto la lista nella ListView
        ObservableList<Messaggio> observableList = FXCollections.observableArrayList(messaggi);
        listView.setItems(observableList);

        // Aggiungo listener per quando si seleziona un messaggio
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                System.out.println("Messaggio selezionato: " + newSel.getOggetto() + " - " + newSel.getCorpo());
            }
        });
    }
}
