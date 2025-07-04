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
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.HBox;
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
        u = ds.getUtenteByEmail(email);


        messaggiInviati = List.of(ds.getMessaggiByIdSender(u.getId()));
        messaggiRicevuti = List.of(ds.getMessaggiByIdReceiver(u.getId()));
        messaggi = new ArrayList<>();
        messaggi.addAll(messaggiInviati);
        messaggi.addAll(messaggiRicevuti);

        System.out.println(
                messaggi.getFirst().getId_receiver() + " .. " + messaggi.getFirst().getId_Sender());

        cellFactoryListView();
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


    private void showDmUtente(Utente paziente){

        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
            if(
                    (m.getId_Sender() == paziente.getId() && m.getId_receiver() == u.getId())
                    || (m.getId_Sender() == u.getId() && m.getId_receiver() == paziente.getId())
            ) {

                filtrati.add(m);

            }
        }
        listView.setItems(FXCollections.observableArrayList(filtrati));
    }


    private List<Messaggio> creaMessaggi() throws Exception {
        List<Messaggio> prova = new ArrayList<>();
        prova = List.of(ds.getMessaggiByIdReceiver(u.getId()));

        return prova;
    }

    //QUI PUOI MODIFICARE LASPETTO DEGLI ELEMENTI DELLA LISTVIEW
    private void cellFactoryListView(){
        listView.setCellFactory(lv -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    setStyle(""); // resetta eventuali stili precedenti
                    getStyleClass().setAll("list-cell");

                } else {
                    // Messaggi
                    if (item instanceof Messaggio m) {

                        //crea per ogni elemento messaggio, uan Vbox contenente due Label, Data (in grassetto) e Oggetto+Corpo
                        setGraphic(null);

                        Label dateTimeLabel = new Label(m.getDataInvio() + " " + m.getOrarioInvio());
                        dateTimeLabel.setStyle("-fx-font-weight: bold;");

                        Label bodyLabel = new Label(m.getOggetto() + "\n" + m.getCorpo());

// Vbox con il contenuto del messaggio
                        VBox vboxMessage = new VBox(dateTimeLabel, bodyLabel);
                        vboxMessage.setSpacing(2);
                        vboxMessage.setMaxWidth(800); // 👈 la larghezza massima del "fumetto"
                        vboxMessage.setStyle("-fx-background-color: inherit; -fx-padding: 10 10 10 10"); // eredita il colore dalla cella
                        vboxMessage.setPadding(new Insets(30, 30, 30 ,30)); // padding dentro la cella

// Wrapper per allineare a destra
                        HBox wrapper = new HBox(vboxMessage);
                        wrapper.prefWidthProperty().bind(this.widthProperty());
                        wrapper.setPadding(new Insets(30, 30, 30 ,30)); // padding dentro la cella
                        wrapper.setAlignment(Pos.CENTER_RIGHT);
                        setText(null);
                        setGraphic(wrapper);
                        getStyleClass().setAll("list-cell");



                            if(m.getId_receiver() == u.getId()) { //messaggi inviati da un utente al CurrentUser
                                getStyleClass().add("messaggio-ricevuto");
                                wrapper.setAlignment(Pos.CENTER_LEFT);

                                if(m.getTipo() == 'N'){     //se il messaggio e una avviso di Non aderenza
                                    getStyleClass().add("avviso-nonaderenza");
                                    vboxMessage.setStyle("-fx-background-color: #FBA660; -fx-background-radius: 10;");
                                }
                            }
                            else if(m.getId_Sender() == u.getId()){  //messaggi inviati dal CurrentUser al utente U
                                getStyleClass().add("messaggio-inviato");
                                wrapper.setAlignment(Pos.CENTER_RIGHT);
                                vboxMessage.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");

                            }



                    }
                    // Utenti
                    else if (item instanceof Utente u) {
                        setStyle(""); // resetta eventuali stili precedenti
                        setGraphic(null);   //toglie eventuali Vbox aggiunti
                        setText(u.toString());
                        getStyleClass().setAll("list-cell");

                    }
                }
            }
        });
    }




}
