package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Patologia;
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

public class TabPatologieController {

    @FXML
    private Button addPatologiaButton;

    @FXML
    private Accordion patologieAccordion;

    private List<Patologia> listaPatologie;

    public void initialize() {

        //listaPatologie = ds.caricaPatologiePerPaziente();

        listaPatologie = new ArrayList<>();

        listaPatologie.add(new Patologia(1, 1, "Ipertensione", LocalDate.of(2020, 5, 12), "Controllata con farmaci ACE-inibitori."));
        listaPatologie.add(new Patologia(2, 1, "Asma bronchiale", LocalDate.of(2018, 3, 20), "Attacchi occasionali, uso di broncodilatatori."));
        listaPatologie.add(new Patologia(3, 1, "Celiachia", LocalDate.of(2022, 10, 1), "Dieta priva di glutine da allora."));

        aggiornaAccordion();

    }

    private void aggiornaAccordion() {
        patologieAccordion.getPanes().clear();

        for (Patologia p : listaPatologie) {
            VBox content = new VBox(5);
            Button eliminaButton = new Button("Elimina");
            eliminaButton.getStyleClass().add("eliminaButtonPatologia");
            content.setPadding(new Insets(10));
            content.getChildren().addAll(
                    new Label("Diagnosi dal: " + p.getDataDiagnosi()),
                    new Label("Note: " + p.getNote()),
                    eliminaButton
            );

            TitledPane pane = new TitledPane(p.getNomePatologia(), content);
            pane.setUserData(p);

            eliminaButton.setOnAction(e -> {
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Conferma eliminazione");
                alert.setHeaderText("Sei sicuro di voler eliminare questa patologia?");
                alert.setContentText(p.getNomePatologia());

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    listaPatologie.remove(p);
                    aggiornaAccordion();
                }
            });

            patologieAccordion.getPanes().add(pane);
        }
    }

    @FXML
    private void aggiungiPatologia(ActionEvent event) {

        TextField nomeField = new TextField();
        DatePicker dataDiagnosiPicker = new DatePicker();
        TextArea noteArea = new TextArea();
        Button salvaButton = new Button("SalvaButtonPatologia");


        VBox contenuto = new VBox(8);
        contenuto.getStyleClass().add("vbox-contenuto");

        contenuto.setPadding(new Insets(10));
        contenuto.getChildren().addAll(
                new Label("Nome Patologia:"), nomeField,
                new Label("Data Diagnosi:"), dataDiagnosiPicker,
                new Label("Note:"), noteArea,
                salvaButton
        );



        TitledPane nuovaPatologiaPane = new TitledPane("Nuova Patologia", contenuto);
        patologieAccordion.getPanes().add(nuovaPatologiaPane);
        patologieAccordion.setExpandedPane(nuovaPatologiaPane);

        // Gestione salvataggio (opzionale)
        salvaButton.setOnAction(e -> {
            String nome = nomeField.getText();
            LocalDate data = dataDiagnosiPicker.getValue();
            String note = noteArea.getText();

            if (nome == null || nome.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Inserisci un nome valido.");
                alert.showAndWait();
                return;
            }

            // Aggiorna titolo del TitledPane
            nuovaPatologiaPane.setText(nome);

            // (opzionale) Salva nel database o aggiungilo a una lista
            Patologia nuova = new Patologia(10, BoxDashboardControllerPatient.u.getId(), nome, data, note);
            nuovaPatologiaPane.setUserData(nuova);

            listaPatologie.add(nuova);
            aggiornaAccordion();
        });
    }

}
