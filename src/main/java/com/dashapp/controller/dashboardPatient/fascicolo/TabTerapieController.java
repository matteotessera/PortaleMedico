package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.TerapiaConcomitante;
import com.dashapp.services.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TabTerapieController {

    @FXML private GridPane terapieGrid;
    @FXML private Button addTerapiaButton;

    private List<TerapiaConcomitante> listaTerapie;
    private DataService ds;
    private int idPaziente;

    public void initialize() throws Exception {
        ds = new DataService();
        idPaziente = BoxDashboardControllerPatient.u.getId();
        listaTerapie = List.of(ds.getTerapieConcomitantiByPaziente(idPaziente));
        aggiornaGrid();
    }

    private void aggiornaGrid() {
        terapieGrid.getChildren().clear();
        int col = 0, row = 0;

        // Card aggiunta terapia
        VBox addCard = creaCardAggiungi();
        terapieGrid.add(addCard, col, row);
        col++;

        for (TerapiaConcomitante t : listaTerapie) {
            VBox card = creaCardTerapia(t);
            terapieGrid.add(card, col, row);
            col++;
            if (col == 5) { col = 0; row++; }
        }
    }

    private VBox creaCardAggiungi() {
        VBox card = new VBox(10);
        card.setPrefSize(180, 180);
        card.setStyle("-fx-background-color: #9caf39; -fx-background-radius: 15; -fx-alignment: center; -fx-cursor: hand;");

        Text plus = new Text("+");
        plus.setStyle("-fx-font-size: 48; -fx-fill: white;");

        card.getChildren().add(plus);

        card.setOnMouseClicked(e -> mostraDialogAggiuntaTerapia());

        return card;
    }

    private VBox creaCardTerapia(TerapiaConcomitante t) {
        VBox card = new VBox(10);
        card.setPadding(new Insets(15, -20, 15, -20));
        card.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 15; -fx-border-color: lightgray; -fx-border-radius: 15;");
        card.setAlignment(Pos.TOP_CENTER);

        Text titolo = new Text(t.getFarmaco().toUpperCase());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        titolo.setTextAlignment(TextAlignment.CENTER);
        titolo.setWrappingWidth(250);

        GridPane grid = new GridPane();
        grid.setHgap(15);
        grid.setVgap(10);
        grid.setAlignment(Pos.CENTER);

        // Riga 0
        VBox inizioBox = createLabelValueBox("Data inizio", t.getDataInizio() != null ? t.getDataInizio().toString() : "-");
        VBox fineBox = createLabelValueBox("Data fine", t.getDataFine() != null ? t.getDataFine().toString() : "-");
        grid.add(inizioBox, 0, 0);
        grid.add(fineBox, 1, 0);

        // Riga 1
        VBox doseBox = createLabelValueBox("Dosaggio", t.getDose() != null ? t.getDose() : "-");
        VBox freqBox = createLabelValueBox("Frequenza", t.getFrequenza() != null ? t.getFrequenza() : "-");
        grid.add(doseBox, 0, 1);
        grid.add(freqBox, 1, 1);

        // Riga 2
        VBox noteBox = createLabelValueBox("Note", t.getIndicazioni() != null && !t.getIndicazioni().isBlank() ? t.getIndicazioni() : "-");
        grid.add(noteBox, 0, 2);
        GridPane.setColumnSpan(noteBox, 2);

        // Bottone elimina
        Button eliminaButton = new Button("Elimina");
        eliminaButton.setStyle("-fx-background-color: #e94f4f; -fx-text-fill: white; -fx-background-radius: 10; -fx-pref-height: 25; -fx-font-size: 12;");
        eliminaButton.setOnAction(ev -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Conferma eliminazione");
            alert.setHeaderText("Eliminare questa terapia?");
            alert.setContentText(t.getFarmaco());
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                try {
                    ds.deleteTerapiaConcomitante(t.getId());
                    listaTerapie = List.of(ds.getTerapieConcomitantiByPaziente(idPaziente));
                    aggiornaGrid();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        VBox.setMargin(eliminaButton, new Insets(12, 0, 0, 0));

        // Composizione card
        card.getChildren().addAll(titolo, grid, eliminaButton);
        VBox.setVgrow(grid, Priority.ALWAYS);

        return card;
    }



    private VBox createLabelValueBox(String labelText, String valueText) {
        VBox box = new VBox(3);
        box.setAlignment(Pos.TOP_LEFT);
        Label label = createLabelBold(labelText);
        Label value = new Label(valueText);
        value.setStyle("-fx-font-size: 13;");
        value.setWrapText(true);
        box.getChildren().addAll(label, value);
        return box;
    }


    private Label createLabelBold(String text) {
        Label label = new Label(text);
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        return label;
    }


    @FXML
    private void aggiungiTerapia(ActionEvent event) {
        mostraDialogAggiuntaTerapia();
    }

    private void mostraDialogAggiuntaTerapia() {
        Stage dialog = new Stage();
        dialog.initModality(Modality.APPLICATION_MODAL);
        dialog.setTitle("Nuova Terapia");

        TextField farmacoField = new TextField();
        DatePicker dataInizioPicker = new DatePicker();
        DatePicker dataFinePicker = new DatePicker();
        TextField dosaggioField = new TextField();
        TextField frequenzaField = new TextField();
        TextArea indicazioniArea = new TextArea();

        Button salvaButton = new Button("Salva");
        salvaButton.setStyle("-fx-background-color: #4CAF50; -fx-text-fill: white; -fx-background-radius: 10; -fx-pref-height: 30; -fx-font-size: 14;");

        VBox layout = new VBox(10);
        layout.setPadding(new Insets(15));
        layout.getChildren().addAll(
                new Label("Farmaco:"), farmacoField,
                new Label("Data Inizio:"), dataInizioPicker,
                new Label("Data Fine:"), dataFinePicker,
                new Label("Dosaggio (mg):"), dosaggioField,
                new Label("Frequenza:"), frequenzaField,
                new Label("Note:"), indicazioniArea,
                salvaButton
        );

        salvaButton.setOnAction(e -> {
            if (farmacoField.getText().isBlank()) {
                new Alert(Alert.AlertType.WARNING, "Inserisci il farmaco.").showAndWait();
                return;
            }
            double dosaggio;
            try {
                dosaggio = Double.parseDouble(dosaggioField.getText());
            } catch (NumberFormatException ex) {
                new Alert(Alert.AlertType.WARNING, "Il dosaggio deve essere un numero.").showAndWait();
                return;
            }

            try {
                ds.addTerapiaConcomitante(
                        idPaziente,
                        farmacoField.getText(),
                        dataInizioPicker.getValue(),
                        dataFinePicker.getValue(),
                        frequenzaField.getText(),
                        dosaggioField.getText(),
                        indicazioniArea.getText()
                );
                listaTerapie = List.of(ds.getTerapieConcomitantiByPaziente(idPaziente));
                aggiornaGrid();
                dialog.close();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });

        Scene scene = new Scene(layout);
        dialog.setScene(scene);
        dialog.showAndWait();
    }
}
