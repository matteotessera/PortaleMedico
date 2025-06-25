package com.dashapp.controller;

import com.dashapp.model.Messaggio;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class messaggiController {

    @FXML
    private ListView<Messaggio> listView;
    private List<Messaggio> messaggi;

    @FXML
    private Label header1;
    @FXML
    private Label header2;
    @FXML
    private Label header3;


    public void initialize(){

        // Listmessaggi = ds.getMessaggiById(idUtente)   // popola la ListView con i messaggi associati al utente;

        messaggi = new ArrayList<>();
        messaggi.add(new Messaggio(1, 101, 'G', LocalDate.now(), LocalTime.now(),
                "Rilevazione Glicemia elevata: xxx", "Non dimenticare il controllo.", false));
        messaggi.add(new Messaggio(2, 102, 'G', LocalDate.now().minusDays(1), LocalTime.of(15,30),
                "Rilevazione Glicemia elevata: yyy", "Prendi la medicina alle 18.", false));

        messaggi.add(new Messaggio(3, 101, 'N', LocalDate.now().minusDays(2), LocalTime.of(9,0),
                "Paziente x non ha aderito alle prescrizioni ", "Buona guarigione!", false));

        showTutti();

        // Aggiungo listener per quando si seleziona un messaggio
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                System.out.println("Messaggio selezionato: " + newSel.getOggetto() + " - " + newSel.getCorpo());
            }
        });


    }

    @FXML
    private void showAvvisoGlicemiaPazienti(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggi){
            if(m.getTipo() == ('G'))
                filtrati.add(m);
        }

        listView.setItems(FXCollections.observableArrayList(filtrati));

        header3.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header2.setStyle("");
        header1.setStyle("");
    }
    @FXML
    private void showPazientiNonAderenti(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggi){
            if(m.getTipo() == ('N'))
                filtrati.add(m);
        }

        listView.setItems(FXCollections.observableArrayList(filtrati));

        header2.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header3.setStyle("");
    }

    @FXML
    private void showMessaggiMedico(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggi){
            if(m.getTipo() == ('M'))
                filtrati.add(m);
        }
        listView.setItems(FXCollections.observableArrayList(filtrati));

        header3.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header2.setStyle("");
        header1.setStyle("");
    }
    @FXML
    private void showAvvisiDimenticanze(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggi){
            if(m.getTipo() == ('A'))
                filtrati.add(m);
        }
        listView.setItems(FXCollections.observableArrayList(filtrati));

        header2.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header3.setStyle("");
    }

    @FXML
    private void showTutti(){
        listView.setItems(FXCollections.observableArrayList(messaggi));

        header1.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header2.setStyle("");
        header3.setStyle("");

    }


}
