package com.dashapp.controller.dashboardMedico;


import com.dashapp.model.AddController;
import com.dashapp.services.DataService;
import com.google.gson.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Properties;

public class AddFarmaciController extends AddController {

    @FXML
    public ComboBox principioComboBox;
    @FXML
    public ComboBox formaComboBox;
    @FXML
    public ComboBox posologiaComboBox;
    @FXML
    public ComboBox indicazioniComboBox;
    @FXML
    public ComboBox controindicazioniComboBox;
    @FXML
    public ComboBox effettiComboBox;
    @FXML
    public ComboBox interazioniComboBox;
    @FXML
    public ComboBox conservazioneComboBox;
    @FXML
    public Button generaDescrizioneButton;
    @FXML
    public TextArea descrizioneArea;
    @FXML
    public Button aggiungiFarmaco;
    @FXML
    private TextField nomeFarmacoField;


    @FXML
    private void initialize() {
        //setto i placeholder dei ComboBox
        principioComboBox.setPromptText("Seleziona principio attivo");
        formaComboBox.setPromptText("Seleziona forma farmaceutica");
        posologiaComboBox.setPromptText("Seleziona posologia");
        indicazioniComboBox.setPromptText("Seleziona indicazioni");
        controindicazioniComboBox.setPromptText("Seleziona controindicazioni");
        effettiComboBox.setPromptText("Seleziona effetti indesiderati");
        interazioniComboBox.setPromptText("Seleziona interazioni");
        conservazioneComboBox.setPromptText("Seleziona conservazione");

        opzioniComboBox();
    }

    //metodo di registrazione di un farmaco
    @FXML
    private void registraFarmaco() {
        if (isEmpty(nomeFarmacoField.getText()) || isEmpty(descrizioneArea.getText())) {
            mostraAlert("Attenzione", "I campi non possono essere vuoti!");
            return;
        }

        String descrizione = descrizioneArea.getText().replaceAll("[\\r\\n]+", " ");
        String nome = nomeFarmacoField.getText();

        try {
            DataService ds = new DataService();
            ds.addFarmaco(nome, descrizione);
            mostraAlert("Successo", "Farmaco aggiunto correttamente!");
            pulisciCampi();
        } catch (Exception e) {
            e.printStackTrace(); // utile per debug
            mostraAlert("Errore", "Si è verificato un errore nell'aggiunta del farmaco.");
        }
    }

    //una volta aggiunto il farmaco, vengono azzerati tutti i campi di input
    private void pulisciCampi() {
        nomeFarmacoField.clear();
        descrizioneArea.clear();
        principioComboBox.getSelectionModel().clearSelection();
        formaComboBox.getSelectionModel().clearSelection();
        posologiaComboBox.getSelectionModel().clearSelection();
        indicazioniComboBox.getSelectionModel().clearSelection();
        controindicazioniComboBox.getSelectionModel().clearSelection();
        effettiComboBox.getSelectionModel().clearSelection();
        interazioniComboBox.getSelectionModel().clearSelection();
        conservazioneComboBox.getSelectionModel().clearSelection();
    }



    private boolean isEmpty(String s) {
        return s == null || s.trim().isEmpty();
    }

    public void generaDescrizione(){
        String nome = nomeFarmacoField.getText();

        StringBuilder descrizioneBuilder = new StringBuilder();
        descrizioneBuilder.append("Farmaco: ").append(nome).append("\n");

        if (principioComboBox.getValue() != null && !principioComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Principi Attivi: ").append(principioComboBox.getValue()).append(".\n");
        }
        if (formaComboBox.getValue() != null && !formaComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Forme Farmaceutiche: ").append(formaComboBox.getValue()).append(".\n");
        }
        if (posologiaComboBox.getValue() != null && !posologiaComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Posologie: ").append(posologiaComboBox.getValue()).append(".\n");
        }
        if (indicazioniComboBox.getValue() != null && !indicazioniComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Indicazioni Terapeutiche: ").append(indicazioniComboBox.getValue()).append(".\n");
        }
        if (controindicazioniComboBox.getValue() != null && !controindicazioniComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Controindicazioni: ").append(controindicazioniComboBox.getValue()).append(".\n");
        }
        if (effettiComboBox.getValue() != null && !effettiComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Effetti Indesiderati: ").append(effettiComboBox.getValue()).append(".\n");
        }
        if (interazioniComboBox.getValue() != null && !interazioniComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Interazioni: ").append(interazioniComboBox.getValue()).append(".\n");
        }
        if (conservazioneComboBox.getValue() != null && !conservazioneComboBox.getValue().toString().isEmpty()) {
            descrizioneBuilder.append("Conservazione: ").append(conservazioneComboBox.getValue()).append(".\n");
        }

        descrizioneArea.setText(descrizioneBuilder.toString());
    }


    private void opzioniComboBox(){
        String[] principiAttivi = {
                "",
                "Paracetamolo",
                "Ibuprofene",
                "Amoxicillina",
                "Acido acetilsalicilico",
                "Loratadina",
                "Omeprazolo"
        };
        setOption(principioComboBox, principiAttivi);

        String[] formeFarmaceutiche = {
                "",
                "Compressa",
                "Capsula",
                "Sciroppo",
                "Fiala iniettabile",
                "Supposta",
                "Pomata",
                "Cerotto transdermico"
        };
        setOption(formaComboBox, formeFarmaceutiche);


        String[] controindicazioni = {
                "",
                "Ipersensibilità al principio attivo",
                "Gravidanza",
                "Allattamento",
                "Insufficienza epatica",
                "Insufficienza renale",
                "Età inferiore ai 12 anni"
        };
        setOption(controindicazioniComboBox, controindicazioni);


        String[] effettiIndesiderati = {
                "",
                "Nausea",
                "Mal di testa",
                "Sonnolenza",
                "Rash cutaneo",
                "Disturbi gastrointestinali",
                "Vertigini"
        };
        setOption(effettiComboBox, effettiIndesiderati);


        String[] posologie = {
                "",
                "1 compressa ogni 8 ore",
                "10 ml di sciroppo ogni 6 ore",
                "1 capsula al giorno",
                "2 fiale al bisogno",
                "Applicare 2 volte al giorno"
        };
        setOption(posologiaComboBox, posologie);


        String[] indicazioniTerapeutiche = {
                "",
                "Febbre",
                "Dolore muscolare",
                "Infezioni batteriche",
                "Allergie stagionali",
                "Reflusso gastroesofageo",
                "Mal di testa"
        };
        setOption(indicazioniComboBox, indicazioniTerapeutiche);


        String[] conservazione = {
                "",
                "Conservare a temperatura ambiente",
                "Conservare in frigorifero (2-8°C)",
                "Proteggere dalla luce",
                "Conservare in luogo asciutto"
        };
        setOption(conservazioneComboBox, conservazione);


        String[] interazioni = {
                "",
                "Non assumere con alcol",
                "Evitare con anticoagulanti",
                "Non associare a FANS",
                "Ridurre dose con farmaci sedativi",
                "Attenzione con antibiotici"
        };
        setOption(interazioniComboBox, interazioni);
    }

    private void setOption(ComboBox nomeComboBox, String[] options){
        nomeComboBox.getItems().addAll(options);
    }

    public void mostraAlert(String titolo, String contenuto) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(titolo);
        alert.setHeaderText(null);  // Puoi mettere un header se vuoi
        alert.setContentText(contenuto);
        alert.showAndWait();
    }





}
