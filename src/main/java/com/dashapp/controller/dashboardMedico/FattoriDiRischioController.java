package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.Patologia;
import com.dashapp.model.Utente;
import com.dashapp.model.FattoriDiRischio;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class FattoriDiRischioController
{


    @FXML
    public GridPane FattoriDiRischioGrid;

    private List<FattoriDiRischio> listaFattoriRischio;
    private DataService ds;
    private int idPaziente;

    public void initialize() throws Exception {
        ds = new DataService();
    }

    public void setIdPaziente(Utente u) throws Exception {
        idPaziente = u.getId();
        listaFattoriRischio = List.of();
        aggiornaGrid();
    }

    private void aggiornaGrid() {
        FattoriDiRischioGrid.getChildren().clear();
        int col = 0;
        int row = 0;

        // Prima card: Aggiungi Patologia
        VBox addCard = creaCardAggiungi();
        FattoriDiRischioGrid.add(addCard, col, row);
        col++;

        // Card per ogni patologia
        for (FattoriDiRischio f : listaFattoriRischio) {
            VBox card = creaCardFattoreDiRischio(f);
            FattoriDiRischioGrid.add(card, col, row);

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

    private VBox creaCardFattoreDiRischio(FattoriDiRischio f) {
        VBox card = new VBox(12);
        card.setPrefSize(180, 200);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 15; -fx-border-color: lightgray; -fx-border-radius: 15;");
        card.setAlignment(Pos.TOP_CENTER);

        // Titolo patologia
        Text titolo = new Text(f.getId().toUpperCase());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        titolo.setWrappingWidth(160);
        titolo.setTextAlignment(TextAlignment.CENTER);

        // Diagnosi label
        Label diagnosiLabel = new Label("Diagnosi");
        diagnosiLabel.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label dataLabel = new Label(f.getNote() != null ? f.getNote().toString() : "-");
        dataLabel.setStyle("-fx-font-size: 12;");

        // Note label
        Label noteLabelTitle = new Label("Note");
        noteLabelTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label noteLabel = new Label(f.getNote() != null && !f.getNote().isBlank() ? f.getNote() : "-");
        noteLabel.setStyle("-fx-font-size: 12;");
        noteLabel.setWrapText(true);

        // Bottone elimina
        Button eliminaButton = new Button("Elimina");
        eliminaButton.setStyle("-fx-background-color: #e94f4f; -fx-text-fill: white; -fx-background-radius: 10; -fx-pref-height: 25; -fx-font-size: 12;");
        eliminaButton.setOnAction(ev -> eliminaFattoreDiRischio(f));

        VBox infoBox = new VBox(5);
        infoBox.setAlignment(Pos.CENTER_LEFT);
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


        TextArea noteArea = new TextArea();

        contenuto.getChildren().addAll(
                new Label("Note"), noteArea
        );

        dialog.getDialogPane().setContent(contenuto);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String note = noteArea.getText();



            try {
               //ds.addPatologia(idPaziente, nome, data, note);
                listaFattoriRischio = List.of();        //fare chiamata
                aggiornaGrid();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void eliminaFattoreDiRischio(FattoriDiRischio f) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questa patologia?");
        alert.setContentText(f.getNote());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                //ds.deletePatologia(f.getId());
                listaFattoriRischio = List.of();        //fare chiamata
                aggiornaGrid();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

}
