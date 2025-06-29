package com.dashapp.controller;

import com.dashapp.controller.dashboardMedico.BoxDashboardController;
import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Messaggio;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
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
    private ListView<Object> listView;
    private List<Messaggio> messaggiRicevuti;
    private List<Messaggio> messaggiInviati;
    private List<Messaggio> messaggi;

    private Utente utenteSelezionato;
    private Messaggio messaggioSelezionato;

    @FXML
    private Label header1;
    @FXML
    private Label header2;
    @FXML
    private Label header3;

    private DataService ds;
    private Utente u;


    public void initialize() throws Exception {
        ds = new DataService();
        String email = NavigatorView.getAuthenticatedUser();
        Utente u = ds.getUtenteByEmail(email);


        //messaggiInviati = List.of(ds.getMessaggiByIdSender(UtenteId));
        //messaggiRicevuti = List.of(ds.getMessaggiByIdReceiver(UtenteId));

        showDm();

        // Aggiungo listener per quando si seleziona un messaggio
        listView.getSelectionModel().selectedItemProperty().addListener((obs, oldSel, newSel) -> {
            if (newSel != null) {
                    if(newSel instanceof Messaggio m){
                        messaggioSelezionato = m;
                    }
                    if(newSel instanceof Utente ut){
                        utenteSelezionato = ut;
                        showDmUtente(ut);
                    }
            }
        });


    }

    //METODI PER MEDICO
    @FXML
    private void showAvvisoGlicemiaPazienti(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
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

        for(Messaggio m: messaggiRicevuti){
            if(m.getTipo() == ('N'))
                filtrati.add(m);
        }

        listView.setItems(FXCollections.observableArrayList(filtrati));

        header2.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header3.setStyle("");
    }

    @FXML
    private void showDMMedico(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
            if(m.getTipo() == ('M'))
                filtrati.add(m);
        }
        listView.setItems(FXCollections.observableArrayList(filtrati));

        header3.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header2.setStyle("");
        header1.setStyle("");
    }


    //METODI PER PAZIENTE
    @FXML
    private void showDmPaziente(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
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

        for(Messaggio m: messaggiRicevuti){
            if(m.getTipo() == ('A'))
                filtrati.add(m);
        }
        listView.setItems(FXCollections.observableArrayList(filtrati));

        header2.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header3.setStyle("");
    }

    private void showDm() throws Exception {

        List<Utente> Pazienti = List.of(ds.getPazientiAssegnati());

        listView.setItems(FXCollections.observableArrayList(Pazienti));
    }


    private void showDmUtente(Utente u){

        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
            if(m.getId_Sender() == u.getId())
                filtrati.add(m);
        }

        listView.setItems(FXCollections.observableArrayList(filtrati));

    }







}
