package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Patologia;
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

public class TabPatologieController {

    @FXML
    private Button addPatologiaButton;

    @FXML
    private Accordion patologieAccordion;

    private List<Patologia> listaPatologie;

    private DataService ds;
    private int idPaziente;
    public void initialize() throws Exception {

        //listaPatologie = ds.caricaPatologiePerPaziente();
        ds = new DataService();

        idPaziente = BoxDashboardControllerPatient.u.getId();

        listaPatologie = List.of(ds.getPatologieByPaziente(idPaziente));
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
                    try {
                        ds.deletePatologia(p.getId());
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
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
        Button salvaButton = new Button("Salva");
        salvaButton.getStyleClass().add("salvaButtonPatologia");


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

            try {
                ds.addPatologia(BoxDashboardControllerPatient.u.getId(), nome, data, note);
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            try {
                listaPatologie = List.of(ds.getPatologieByPaziente(idPaziente));
            } catch (Exception ex) {
                throw new RuntimeException(ex);
            }

            aggiornaAccordion();
        });
    }

}
