package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.SintomoConcomitante;
import com.dashapp.model.TerapiaConcomitante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TabTerapieController {

    @FXML
    private Button addTerapiaButton;

    @FXML
    private Accordion terapieAccordion;

    private List<TerapiaConcomitante> listaTerapie;

    public void initialize() {

        //listaPatologie = ds.caricaPatologiePerPaziente();

        listaTerapie = new ArrayList<>();

        listaTerapie.add(new TerapiaConcomitante(
                1, 1, "Paracetamolo",
                LocalDate.of(2025, 6, 1),
                LocalDate.of(2025, 6, 7),
                "Ogni 8 ore",
                "500 mg",
                "Assumere dopo i pasti"));

        listaTerapie.add(new TerapiaConcomitante(
                2, 1, "Ibuprofene",
                LocalDate.of(2025, 6, 2),
                LocalDate.of(2025, 6, 9),
                "Ogni 12 ore",
                "400 mg",
                "Solo in caso di dolore"));

        listaTerapie.add(new TerapiaConcomitante(
                3, 2, "Amoxicillina",
                LocalDate.of(2025, 6, 3),
                LocalDate.of(2025, 6, 10),
                "Ogni 8 ore",
                "1 g",
                "Assumere con abbondante acqua"));

        aggiornaAccordion();


    }

    private void aggiornaAccordion() {

        terapieAccordion.getPanes().clear(); // ⬅️ pulisce l'Accor

        for (TerapiaConcomitante t : listaTerapie) {
            // Crea contenuto visuale per ogni patologia
            VBox content = new VBox(5);
            Button eliminaButton = new Button("Elimina");
            eliminaButton.getStyleClass().add("eliminaButtonTerapia");
            content.setPadding(new Insets(10));
            content.getChildren().addAll(
                    new Label("Data inizio terapia " + t.getDataInizio()),
                    new Label("Data fine terapia: " + t.getDataFine()),
                    new Label("farmaco: " + t.getFarmaco()),
                    new Label("frequenza: " + t.getFrequenza()),
                    new Label("dosaggio: " + t.getDosaggio()),
                    new Label("indicazioni: " + t.getIndicazioni()),
                    eliminaButton
            );


            TitledPane pane = new TitledPane(t.getIndicazioni(), content);


            pane.setUserData(t);


            terapieAccordion.getPanes().add(pane);

            eliminaButton.setOnAction(e -> {
                //Aggiungi eliminazione da DB
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conferma eliminazione");
                alert.setHeaderText("Sei sicuro di voler eliminare questa terapia?");
                alert.setContentText(t.getIndicazioni());

                // Mostra la finestra e aspetta la risposta
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    terapieAccordion.getPanes().remove(pane);
                }
            });

        }
    }

    @FXML
    private void aggiungiTerapia(ActionEvent event) {

        TextField farmacoField = new TextField();
        DatePicker dataInizioPicker = new DatePicker();
        DatePicker dataFinePicker = new DatePicker();
        TextField dosaggioField = new TextField();
        TextField frequenzaField = new TextField();
        TextArea indicazioniField = new TextArea();
        Button salvaButton = new Button("Salva");
        salvaButton.getStyleClass().add("salvaButtonTerapia");


        VBox contenuto = new VBox(8);
        contenuto.getStyleClass().add("vbox-contenuto");

        contenuto.setPadding(new Insets(10));
        contenuto.getChildren().addAll(
                new Label("Farmaco:"), farmacoField,
                new Label("Data Inizio Terapia:"), dataInizioPicker,
                new Label("Data Fine Terapia:"), dataFinePicker,
                new Label("Dosaggio:"), dosaggioField,
                new Label("Frequenza:"), frequenzaField,
                new Label("Note:"), indicazioniField,
                salvaButton
        );



        TitledPane nuovoSintomoPane = new TitledPane("Nuova Patologia", contenuto);
        terapieAccordion.getPanes().add(nuovoSintomoPane);
        terapieAccordion.setExpandedPane(nuovoSintomoPane);


        salvaButton.setOnAction(e -> {
            String nome = farmacoField.getText();
            LocalDate dataInizio = dataInizioPicker.getValue();
            LocalDate dataFine = dataFinePicker.getValue();
            String dosaggio = dosaggioField.getText();
            String frequenza = frequenzaField.getText();
            String indicazioni = indicazioniField.getText();


            //implementare tutti i controlli
            if (nome == null || nome.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Inserisci un farmaco valido.");
                alert.showAndWait();
                return;
            }

            // Aggiorna titolo del TitledPane
            nuovoSintomoPane.setText(nome);


            TerapiaConcomitante nuova = new TerapiaConcomitante(10, BoxDashboardControllerPatient.u.getId(), nome, dataInizio, dataFine, frequenza, dosaggio, indicazioni);
            nuovoSintomoPane.setUserData(nuova);

            listaTerapie.add(nuova);
            aggiornaAccordion();

        });
    }

}
