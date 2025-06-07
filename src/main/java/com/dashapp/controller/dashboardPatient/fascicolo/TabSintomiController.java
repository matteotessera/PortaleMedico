package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Patologia;
import com.dashapp.model.Sintomo;
import com.dashapp.model.SintomoConcomitante;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TabSintomiController {

    @FXML
    private Button addSintomoButton;

    @FXML
    private Accordion sintomiAccordion;

    private List<SintomoConcomitante> listaSintomi;

    public void initialize() {

        //listaPatologie = ds.caricaPatologiePerPaziente();

        listaSintomi = new ArrayList<>();

        listaSintomi.add(new SintomoConcomitante(1, 1, "tosse", LocalDate.of(2025, 6, 1), "quotidiana", "leggera tosse persistente"));
        listaSintomi.add(new SintomoConcomitante(2, 1, "mal di testa", LocalDate.of(2025, 5, 25), "occasionale", "compare soprattutto la sera"));
        listaSintomi.add(new SintomoConcomitante(3, 2, "febbre", LocalDate.of(2025, 6, 5), "alta", "febbre sopra 38°C da 3 giorni"));
        
        aggiornaAccordion();

       
    }

    private void aggiornaAccordion() {

        sintomiAccordion.getPanes().clear();

        for (SintomoConcomitante s : listaSintomi) {
            // Crea contenuto visuale per ogni patologia
            VBox content = new VBox(5);
            Button eliminaButton = new Button("Elimina");
            eliminaButton.getStyleClass().add("eliminaButtonSintomo");
            content.setPadding(new Insets(10));
            content.getChildren().addAll(
                    new Label("Data inizio sintomo " + s.getDataInizio()),
                    new Label("Frequenza: " + s.getFrequenza()),
                    new Label("Note: " + s.getNote()),
                    eliminaButton
            );

            // Crea TitledPane con titolo = nome patologia
            TitledPane pane = new TitledPane(s.getSintomo(), content);


            pane.setUserData(s);


            sintomiAccordion.getPanes().add(pane);

            eliminaButton.setOnAction(e -> {
                //Aggiungi eliminazione da DB
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conferma eliminazione");
                alert.setHeaderText("Sei sicuro di voler eliminare questo sintomo?");
                alert.setContentText(s.getSintomo());

                // Mostra la finestra e aspetta la risposta
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    sintomiAccordion.getPanes().remove(pane);
                }
            });

        }
    }

    @FXML
    private void aggiungiSintomo(ActionEvent event) {

        TextField nomeField = new TextField();
        DatePicker dataInizioPicker = new DatePicker();
        TextField frequenzaField = new TextField();
        TextArea noteArea = new TextArea();
        Button salvaButton = new Button("Salva");
        salvaButton.getStyleClass().add("salvaButtonSintomo");


        VBox contenuto = new VBox(8);
        contenuto.getStyleClass().add("vbox-contenuto");

        contenuto.setPadding(new Insets(10));
        contenuto.getChildren().addAll(
                new Label("Nome Sintomo:"), nomeField,
                new Label("Data Inizio Sintomo:"), dataInizioPicker,
                new Label("Frequenza:"), frequenzaField,
                new Label("Note:"), noteArea,
                salvaButton
        );



        TitledPane nuovoSintomoPane = new TitledPane("Nuova Patologia", contenuto);
        sintomiAccordion.getPanes().add(nuovoSintomoPane);
        sintomiAccordion.setExpandedPane(nuovoSintomoPane);


        salvaButton.setOnAction(e -> {
            String nome = nomeField.getText();
            LocalDate data = dataInizioPicker.getValue();
            String note = noteArea.getText();
            String frequenza = frequenzaField.getText();


            //implementare tutti i controlli
            if (nome == null || nome.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Inserisci un nome valido.");
                alert.showAndWait();
                return;
            }

            // Aggiorna titolo del TitledPane
            nuovoSintomoPane.setText(nome);


            SintomoConcomitante nuova = new SintomoConcomitante(10, BoxDashboardControllerPatient.u.getId(), nome, data, frequenza, note);
            nuovoSintomoPane.setUserData(nuova);

            listaSintomi.add(nuova);
            aggiornaAccordion();

        });
    }

}
