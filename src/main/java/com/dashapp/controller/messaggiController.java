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
import org.w3c.dom.ls.LSException;

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
        messaggiRicevuti = creaMessaggi();

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

        header2.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header3.setStyle("");
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

        header3.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header2.setStyle("");
    }


    @FXML
    private void showDm() throws Exception {

        List<Utente> Pazienti = List.of(ds.getPazientiAssegnati());

        listView.setItems(FXCollections.observableArrayList(Pazienti));

        header1.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header2.setStyle("");
        header3.setStyle("");
    }


    private void showDmUtente(Utente u){

        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
            if(m.getId_Sender() == u.getId())
                filtrati.add(m);
        }

        listView.setItems(FXCollections.observableArrayList(filtrati));

    }


    private List<Messaggio> creaMessaggi(){
        List<Messaggio> prova = new ArrayList<>();
        Messaggio messaggio1 = new Messaggio(
                1,                // id
                2,              // id_Sender (ipotetico paziente o medico)
                'G',              // tipo: glicemia elevata
                LocalDate.of(2025, 6, 15),
                LocalTime.of(8, 30),
                "Glicemia elevata",
                "La tua glicemia è risultata sopra i valori normali.",
                false
        );

        Messaggio messaggio2 = new Messaggio(
                2,                // id
                2,              // id_Sender
                'N',              // tipo: non aderente
                LocalDate.of(2025, 6, 16),
                LocalTime.of(9, 45),
                "Non aderenza alla terapia",
                "Non stai seguendo correttamente la terapia da 3 giorni.",
                false
        );

        Messaggio messaggio3 = new Messaggio(
                3,                // id
                4,              // id_Sender
                'M',              // tipo: messaggio diretto medico
                LocalDate.of(2025, 6, 17),
                LocalTime.of(10, 15),
                "Promemoria visita",
                "Ricordati la visita di controllo prevista per domani alle ore 11:00.",
                false
        );
        prova.add(messaggio1);
        prova.add(messaggio2);
        prova.add(messaggio3);

        return prova;
    }







}
