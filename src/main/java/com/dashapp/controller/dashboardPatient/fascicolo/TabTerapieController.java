package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.TerapiaConcomitante;
import com.dashapp.services.DataService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
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
        VBox card = new VBox(12);
        card.setPrefSize(360, 180); // Più larga, meno alta
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 15; -fx-border-color: lightgray; -fx-border-radius: 15;");
        card.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        Text titolo = new Text(t.getFarmaco().toUpperCase());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        titolo.setWrappingWidth(320);
        titolo.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        GridPane grid = new GridPane();
        grid.setHgap(20);
        grid.setVgap(6);

        // Riga 0
        grid.add(createLabelBold("Data inizio:"), 0, 0);
        grid.add(new Label(t.getDataInizio() != null ? t.getDataInizio().toString() : "-"), 1, 0);

        // Riga 1
        grid.add(createLabelBold("Data fine:"), 0, 1);
        grid.add(new Label(t.getDataFine() != null ? t.getDataFine().toString() : "-"), 1, 1);

        // Riga 2
        grid.add(createLabelBold("Dosaggio:"), 0, 2);
        grid.add(new Label(t.getDose() != null ? t.getDose() : "-"), 1, 2);

        // Riga 3
        grid.add(createLabelBold("Frequenza:"), 0, 3);
        grid.add(new Label(t.getFrequenza() != null ? t.getFrequenza() : "-"), 1, 3);

        // Riga 4: Note su due colonne
        Label noteTitle = createLabelBold("Note:");
        noteTitle.setMaxWidth(Double.MAX_VALUE);
        GridPane.setColumnSpan(noteTitle, 2);
        grid.add(noteTitle, 0, 4);

        Label noteValue = new Label(t.getIndicazioni() != null && !t.getIndicazioni().isBlank() ? t.getIndicazioni() : "-");
        noteValue.setWrapText(true);
        noteValue.setMaxWidth(320);
        GridPane.setColumnSpan(noteValue, 2);
        grid.add(noteValue, 0, 5);

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
        card.getChildren().addAll(titolo, grid, eliminaButton);
        VBox.setVgrow(grid, Priority.ALWAYS);

        return card;
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
                new Label("Dosaggio:"), dosaggioField,
                new Label("Frequenza:"), frequenzaField,
                new Label("Note:"), indicazioniArea,
                salvaButton
        );

        salvaButton.setOnAction(e -> {
            if (farmacoField.getText().isBlank()) {
                new Alert(Alert.AlertType.WARNING, "Inserisci il farmaco.").showAndWait();
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
