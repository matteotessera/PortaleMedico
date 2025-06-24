package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Patologia;
import com.dashapp.model.Sintomo;
import com.dashapp.model.SintomoConcomitante;
import com.dashapp.services.DataService;
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
    private DataService ds;
    private int idPaziente;

    public void initialize() throws Exception {
        ds = new DataService();
        idPaziente = BoxDashboardControllerPatient.u.getId();


        listaSintomi = List.of( ds.getSintomiConcomitantiByPaziente(idPaziente) );
        aggiornaAccordion();

       
    }

    private void aggiornaAccordion() throws Exception {

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
            TitledPane pane = new TitledPane(s.getDescrizione(), content);


            pane.setUserData(s);


            sintomiAccordion.getPanes().add(pane);

            eliminaButton.setOnAction(e -> {
                //Aggiungi eliminazione da DB
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conferma eliminazione");
                alert.setHeaderText("Sei sicuro di voler eliminare questo sintomo?");
                alert.setContentText(s.getDescrizione());

                // Mostra la finestra e aspetta la risposta
                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK){
                    try {
                        ds.deleteSintomoConcomitante(s.getId());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
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


            int nuovaId;
            try {
               nuovaId = ds.addSintomoConcomitante(BoxDashboardControllerPatient.u.getId(), nome, data, frequenza, note);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            SintomoConcomitante nuova = new SintomoConcomitante(nuovaId, BoxDashboardControllerPatient.u.getId(), nome, data, frequenza, note);
            nuovoSintomoPane.setUserData(nuova);

            try {
                listaSintomi = List.of( ds.getSintomiConcomitantiByPaziente(idPaziente) );
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }
            try {
                aggiornaAccordion();
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }


        });
    }

}
