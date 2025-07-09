package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.Patologia;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TabPatologieController {

    @FXML
    private GridPane patologieGrid;

    private List<Patologia> listaPatologie;
    private DataService ds;
    private int idPaziente;

    public void initialize() throws Exception {
        ds = new DataService();
        idPaziente = BoxDashboardControllerPatient.u.getId();
        listaPatologie = List.of(ds.getPatologieByPaziente(idPaziente));

        aggiornaGrid();
    }

    private void aggiornaGrid() {
        patologieGrid.getChildren().clear();
        int col = 0;
        int row = 0;

        // Prima card: Aggiungi Patologia
        VBox addCard = creaCardAggiungi();
        patologieGrid.add(addCard, col, row);
        col++;

        // Card per ogni patologia
        for (Patologia p : listaPatologie) {
            VBox card = creaCardPatologia(p);
            patologieGrid.add(card, col, row);

            col++;
            if (col == 5) { // 4 colonne per riga
                col = 0;
                row++;
            }
        }
    }

    private VBox creaCardAggiungi() {
        VBox card = new VBox(10);
        card.setPrefSize(180, 180);
        card.setStyle("-fx-background-color: #ad343e; -fx-background-radius: 15; -fx-alignment: center; -fx-cursor: hand;");

        Text plus = new Text("+");
        plus.setStyle("-fx-font-size: 48; -fx-fill: white;");

        card.getChildren().addAll(plus);
        card.setOnMouseClicked(e -> mostraFormAggiunta());

        return card;
    }

    private VBox creaCardPatologia(Patologia p) {
        VBox card = new VBox(12);
        card.setPrefSize(180, 200);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 15; -fx-border-color: lightgray; -fx-border-radius: 15;");
        card.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        // Titolo patologia
        Text titolo = new Text(p.getNomePatologia().toUpperCase());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        titolo.setWrappingWidth(160);
        titolo.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Diagnosi label
        Label diagnosiLabel = new Label("Diagnosi");
        diagnosiLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label dataLabel = new Label(p.getDataDiagnosi() != null ? p.getDataDiagnosi().toString() : "-");
        dataLabel.setStyle("-fx-font-size: 12;");

        // Note label
        Label noteLabelTitle = new Label("Note");
        noteLabelTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label noteLabel = new Label(p.getNote() != null && !p.getNote().isBlank() ? p.getNote() : "-");
        noteLabel.setStyle("-fx-font-size: 12;");
        noteLabel.setWrapText(true);

        // Bottone elimina
        Button eliminaButton = new Button("Elimina");
        eliminaButton.setStyle("-fx-background-color: #e94f4f; -fx-text-fill: white; -fx-background-radius: 10; -fx-pref-height: 25; -fx-font-size: 12;");
        eliminaButton.setOnAction(ev -> eliminaPatologia(p));

        VBox infoBox = new VBox(5);
        infoBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(
                diagnosiLabel, dataLabel,
                noteLabelTitle, noteLabel
        );
        VBox.setMargin(eliminaButton, new Insets(12, 0, 0, 0));  // top, right, bottom, left
        infoBox.setPadding(new Insets(0, 10, 0, 10));

        card.getChildren().addAll(titolo, infoBox, eliminaButton);
        VBox.setVgrow(infoBox, Priority.ALWAYS);

        return card;
    }


    private void mostraFormAggiunta() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Nuova Patologia");

        VBox contenuto = new VBox(10);
        contenuto.setPadding(new Insets(10));

        TextField nomeField = new TextField();
        DatePicker dataDiagnosiPicker = new DatePicker();
        TextArea noteArea = new TextArea();

        contenuto.getChildren().addAll(
                new Label("Nome Patologia:"), nomeField,
                new Label("Data Diagnosi"), dataDiagnosiPicker,
                new Label("Note"), noteArea
        );

        dialog.getDialogPane().setContent(contenuto);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String nome = nomeField.getText();
            LocalDate data = dataDiagnosiPicker.getValue();
            String note = noteArea.getText();

            if (nome == null || nome.isBlank()) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Inserisci un nome valido.");
                alert.showAndWait();
                return;
            }
            if (data == null || data.isAfter(LocalDate.now())) {
                Alert alert = new Alert(Alert.AlertType.WARNING, "Inserisci una data valida.");
                alert.showAndWait();
                return;
            }


            try {
                ds.addPatologia(idPaziente, nome, data, note);
                listaPatologie = List.of(ds.getPatologieByPaziente(idPaziente));
                aggiornaGrid();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void eliminaPatologia(Patologia p) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questa patologia?");
        alert.setContentText(p.getNomePatologia());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ds.deletePatologia(p.getId());
                listaPatologie = List.of(ds.getPatologieByPaziente(idPaziente));
                aggiornaGrid();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
