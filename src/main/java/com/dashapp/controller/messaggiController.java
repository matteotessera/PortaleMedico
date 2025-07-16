package com.dashapp.controller;

import com.dashapp.controller.dashboardMedico.BoxDashboardController;
import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Messaggio;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Priority;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;
import org.w3c.dom.ls.LSException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
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

        if(u.getRuolo().equals("paziente")){
            this.setHeader2("Assunzioni dimenticate");
            header2.setOnMouseClicked(e -> showAssunzioniDimenticate());
            header3.setManaged(false);
            header3.setVisible(false);
        }


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
                        Platform.runLater(() -> showDmUtente(ut));      //aspetta che il click venga processato prima di eseguire la funzione, cosi non avviene index out of bounds
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
        listView.getSelectionModel().clearSelection();
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

        listView.getSelectionModel().clearSelection();
        listView.setItems(FXCollections.observableArrayList(filtrati));

        header3.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header2.setStyle("");
    }

    @FXML
    private void showAssunzioniDimenticate(){
        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggiRicevuti){
            if(m.getTipo() == ('A'))
                filtrati.add(m);
        }

        listView.getSelectionModel().clearSelection();
        listView.setItems(FXCollections.observableArrayList(filtrati));

        header2.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
        header1.setStyle("");
        header3.setStyle("");
    }



    @FXML
    private void showDm() throws Exception {

        if(u.getRuolo().equals("paziente")) {
            Utente medico = ds.getMedicoDiBase(u.getId());

            listView.getSelectionModel().clearSelection();
            listView.setItems(FXCollections.observableArrayList(medico));

            header1.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
            header2.setStyle("");
            header3.setStyle("");
        }else if(u.getRuolo().equals("medico")){
            List<Utente> Pazienti = List.of(ds.getPazientiAssegnati());

            listView.getSelectionModel().clearSelection();
            listView.setItems(FXCollections.observableArrayList(Pazienti));


            header1.setStyle("-fx-font-weight: bold; -fx-background-color: lightblue;");
            header2.setStyle("");
            header3.setStyle("");
        }
    }


    private void showDmUtente(Utente utente){

        List<Messaggio> filtrati = new ArrayList<>();

        for(Messaggio m: messaggi){
            if(
                    (m.getId_Sender() == utente.getId() && m.getId_receiver() == u.getId())
                    || (m.getId_Sender() == u.getId() && m.getId_receiver() == utente.getId())
            ) {
                filtrati.add(m);
            }
        }
        filtrati.sort(
                Comparator
                        .comparing(Messaggio::getDataInvio)
                        .thenComparing(Messaggio::getOrarioInvio)
        );

        listView.getSelectionModel().clearSelection();
        listView.setItems(FXCollections.observableArrayList(filtrati));

        if (!filtrati.isEmpty()) {
            listView.scrollTo(filtrati.size() - 1);
        }
    }



    //QUI PUOI MODIFICARE LASPETTO DEGLI ELEMENTI DELLA LISTVIEW
    private void cellFactoryListView(){
        listView.setCellFactory(lv -> new ListCell<Object>() {
            @Override
            protected void updateItem(Object item, boolean empty) {
                super.updateItem(item, empty);

                //CELLE VUOTE
                if (empty || item == null) {
                    setGraphic(null);
                    setText(null);
                    setStyle(""); // resetta eventuali stili precedenti
                    getStyleClass().setAll("list-cell");

                } else {

                    // Messaggi
                    if (item instanceof Messaggio m) {

                        Label dateTimeLabel = new Label(m.getDataInvio() + " " + m.getOrarioInvio());
                        dateTimeLabel.setStyle("-fx-font-weight: normal; -fx-font-size: 13; -fx-text-fill: #555555;");

                        Label oggettoLabel = new Label(m.getOggetto());
                        oggettoLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 16; -fx-text-fill: #222222;");

                        Label corpoLabel = new Label(m.getCorpo());
                        corpoLabel.setWrapText(true);
                        corpoLabel.setStyle("-fx-font-size: 14; -fx-text-fill: #333333;");
                        corpoLabel.setMaxWidth(780);  // per far andare a capo se troppo lungo

                        VBox vboxMessage = new VBox(dateTimeLabel, oggettoLabel, corpoLabel);
                        vboxMessage.setSpacing(6);
                        vboxMessage.setMaxWidth(800);
                        vboxMessage.setPadding(new Insets(15, 20, 15, 20));
                        vboxMessage.setStyle("-fx-background-color: inherit;");

                        HBox wrapper = new HBox(vboxMessage);
                        wrapper.setPrefWidth(700);
                        wrapper.setPadding(new Insets(10, 20, 10, 20));

                        // Allinea in base a mittente/destinatario
                        if (m.getId_receiver() == u.getId()) {
                            wrapper.setAlignment(Pos.CENTER_LEFT);
                            vboxMessage.setStyle("-fx-background-color: lightgreen; -fx-background-radius: 10;");
                        } else if (m.getId_Sender() == u.getId()) {
                            wrapper.setAlignment(Pos.CENTER_RIGHT);
                            vboxMessage.setStyle("-fx-background-color: lightblue; -fx-background-radius: 10;");
                        }

                        // Colori speciali in base al tipo messaggio
                        if (m.getTipo() == 'N') {
                            vboxMessage.setStyle("-fx-background-color: #FBA660; -fx-background-radius: 10;");
                        }
                        if (m.getTipo() == 'G') {
                            vboxMessage.setStyle("-fx-background-color: #FFFC68; -fx-background-radius: 10;");
                        }

                        setText(null);
                        setGraphic(wrapper);

                    }
                    // Utenti
                    else if (item instanceof Utente uSelezionato) {
                        setStyle(""); // resetta eventuali stili precedenti
                        setGraphic(null);   //toglie eventuali Vbox aggiunti
                        getStyleClass().setAll("list-cell");

                        Label infoUtente = new Label(uSelezionato.toString());
                        Button scriviButton = new Button("Invia messaggio");
                        scriviButton.setStyle("-fx-background-color: #1e3746;");

                        Region spacer = new Region();
                        HBox.setHgrow(spacer, Priority.ALWAYS); // fa sì che il Region si espanda e spinga il bottone a destra

                        HBox wrapper = new HBox(infoUtente, spacer, scriviButton);
                        wrapper.setSpacing(10);
                        wrapper.setAlignment(Pos.CENTER_LEFT); // allinea verticalmente al centro

                        setGraphic(wrapper);

                        scriviButton.setOnAction(e -> {
                            DmForm(uSelezionato);
                        });
                    }
                }
            }
        });
    }


    private void setHeader1(String text){
        header1.setText(text);
    }
    private void setHeader2(String text){
        header2.setText(text);
    }
    private void setHeader3(String text){
        header3.setText(text);
    }

    private void DmForm(Utente destinatario){
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Invia Messaggio a " + destinatario.getEmail());

        // Campi input
        TextField oggettoField = new TextField();
        oggettoField.setPromptText("Oggetto");

        TextArea corpoArea = new TextArea();
        corpoArea.setPromptText("Corpo del messaggio");
        corpoArea.setWrapText(true);

        // Bottone invia
        VBox buttonwrap = new VBox();
        Button inviaButton = new Button("Invia");
        inviaButton.setPrefWidth(200);
        inviaButton.setStyle("-fx-background-color: lightgreen");
        buttonwrap.getChildren().add(inviaButton);
        buttonwrap.setPrefWidth(1000);
        buttonwrap.setAlignment(Pos.CENTER);

        inviaButton.setOnAction(ev -> {
            String oggetto = oggettoField.getText();
            String corpo = corpoArea.getText();

            // Qui puoi validare i dati
            if (oggetto.isEmpty() || corpo.isEmpty()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Oggetto e corpo non possono essere vuoti.", ButtonType.OK);
                alert.showAndWait();
                return;
            }

            try {
                // Esempio: chiamare il tuo metodo per salvare il messaggio
                DataService ds = new DataService();
                corpo = corpo.replace("\n", " ").replace("\r", " ");
                ds.addMessaggio(
                        u.getId(), // id mittente
                        destinatario.getId(),           // id destinatario
                        LocalDate.now(),
                        LocalTime.now(),
                        oggetto,
                        corpo,
                        'D',      // o altro tipo
                        false     // letto = false
                );

                dialog.close();

                Alert conferma = new Alert(Alert.AlertType.INFORMATION, "Messaggio inviato!", ButtonType.OK);
                conferma.showAndWait();
                reloadMessaggi();
                showDmUtente(destinatario);


            } catch (Exception ex) {
                ex.printStackTrace();
                Alert errore = new Alert(Alert.AlertType.ERROR, "Errore durante l'invio del messaggio.", ButtonType.OK);
                errore.showAndWait();
            }
        });

        // Layout del form
        VBox vbox = new VBox(10,
                new Label("Oggetto:"), oggettoField,
                new Label("Corpo:"), corpoArea,
                buttonwrap
        );
        vbox.setPadding(new Insets(20));
        vbox.setPrefWidth(400);

        Scene scene = new Scene(vbox);
        dialog.setScene(scene);
        dialog.showAndWait();
    }

    private void reloadMessaggi() throws Exception {
        messaggiInviati = List.of(ds.getMessaggiByIdSender(u.getId()));
        messaggiRicevuti = List.of(ds.getMessaggiByIdReceiver(u.getId()));
        messaggi = new ArrayList<>();
        messaggi.addAll(messaggiInviati);
        messaggi.addAll(messaggiRicevuti);
    }

}
