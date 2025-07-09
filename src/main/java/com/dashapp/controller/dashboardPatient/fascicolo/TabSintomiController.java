package com.dashapp.controller.dashboardPatient.fascicolo;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.SintomoConcomitante;
import com.dashapp.services.DataService;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public class TabSintomiController {

    @FXML
    private GridPane sintomiGrid;

    private List<SintomoConcomitante> listaSintomi;
    private DataService ds;
    private int idPaziente;

    public void initialize() throws Exception {
        ds = new DataService();
        idPaziente = BoxDashboardControllerPatient.u.getId();

        listaSintomi = List.of(ds.getSintomiConcomitantiByPaziente(idPaziente));

        aggiornaSintomiGrid();
    }

    private void aggiornaSintomiGrid() {
        sintomiGrid.getChildren().clear();
        int col = 0;
        int row = 0;

        // Card aggiunta sintomo
        VBox addCard = creaCardAggiungi();
        sintomiGrid.add(addCard, col, row);
        col++;

        for (SintomoConcomitante s : listaSintomi) {
            VBox card = creaCardSintomo(s);
            sintomiGrid.add(card, col, row);
            col++;

            // ogni 4 colonne vai a capo
            if (col == 5) {
                col = 0;
                row++;
            }
        }
    }

    private VBox creaCardAggiungi() {
        VBox card = new VBox(10);
        card.setPrefSize(180, 180);
        card.setStyle("-fx-background-color: #9caf39; -fx-background-radius: 15; -fx-alignment: center; -fx-cursor: hand;");

        Text plus = new Text("+");
        plus.setStyle("-fx-font-size: 48; -fx-fill: white;");

        card.getChildren().add(plus);

        card.setOnMouseClicked(e -> mostraFormAggiunta());

        return card;
    }

    private VBox creaCardSintomo(SintomoConcomitante s) {
        VBox card = new VBox(12);
        card.setPrefSize(180, 200);
        card.setPadding(new Insets(15));
        card.setStyle("-fx-background-color: #f4f4f4; -fx-background-radius: 15; -fx-border-color: lightgray; -fx-border-radius: 15;");
        card.setAlignment(javafx.geometry.Pos.TOP_CENTER);

        // Titolo sintomo
        Text titolo = new Text(s.getDescrizione().toUpperCase());
        titolo.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
        titolo.setWrappingWidth(160);
        titolo.setTextAlignment(javafx.scene.text.TextAlignment.CENTER);

        // Data inizio label
        Label dataLabelTitle = new Label("Data inizio");
        dataLabelTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label dataLabel = new Label(s.getDataInizio() != null ? s.getDataInizio().toString() : "-");
        dataLabel.setStyle("-fx-font-size: 12;");

        // Frequenza label
        Label frequenzaLabelTitle = new Label("Frequenza");
        frequenzaLabelTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label frequenzaLabel = new Label(s.getFrequenza() != null && !s.getFrequenza().isBlank() ? s.getFrequenza() : "-");
        frequenzaLabel.setStyle("-fx-font-size: 12;");

        // Note label
        Label noteLabelTitle = new Label("Note");
        noteLabelTitle.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        Label noteLabel = new Label(s.getNote() != null && !s.getNote().isBlank() ? s.getNote() : "-");
        noteLabel.setStyle("-fx-font-size: 12;");
        noteLabel.setWrapText(true);

        // Bottone elimina
        Button eliminaButton = new Button("Elimina");
        eliminaButton.setStyle("-fx-background-color: #e94f4f; -fx-text-fill: white; -fx-background-radius: 10; -fx-pref-height: 25; -fx-font-size: 12;");
        eliminaButton.setOnAction(ev -> eliminaSintomo(s));

        // InfoBox
        VBox infoBox = new VBox(5);
        infoBox.setAlignment(javafx.geometry.Pos.CENTER_LEFT);
        infoBox.getChildren().addAll(
                dataLabelTitle, dataLabel,
                frequenzaLabelTitle, frequenzaLabel,
                noteLabelTitle, noteLabel
        );
        infoBox.setPadding(new Insets(0, 10, 0, 10));


        VBox.setMargin(eliminaButton, new Insets(12, 0, 0, 0)); // top, right, bottom, left

        card.getChildren().addAll(titolo, infoBox, eliminaButton);
        VBox.setVgrow(infoBox, Priority.ALWAYS);

        return card;
    }


    private void mostraFormAggiunta() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.setTitle("Nuovo Sintomo");

        VBox contenuto = new VBox(10);
        contenuto.setPadding(new Insets(10));

        TextField nomeField = new TextField();
        DatePicker dataInizioPicker = new DatePicker();
        TextField frequenzaField = new TextField();
        TextArea noteArea = new TextArea();

        contenuto.getChildren().addAll(
                new Label("Nome Sintomo:"), nomeField,
                new Label("Data Inizio:"), dataInizioPicker,
                new Label("Frequenza:"), frequenzaField,
                new Label("Note:"), noteArea
        );

        dialog.getDialogPane().setContent(contenuto);
        dialog.getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();

        if (result.isPresent() && result.get() == ButtonType.OK) {
            String nome = nomeField.getText();
            LocalDate data = dataInizioPicker.getValue();
            String frequenza = frequenzaField.getText();
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
                ds.addSintomoConcomitante(idPaziente, nome, data, frequenza, note);
                listaSintomi = List.of(ds.getSintomiConcomitantiByPaziente(idPaziente));
                aggiornaSintomiGrid();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void eliminaSintomo(SintomoConcomitante s) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Conferma eliminazione");
        alert.setHeaderText("Sei sicuro di voler eliminare questo sintomo?");
        alert.setContentText(s.getDescrizione());

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                ds.deleteSintomoConcomitante(s.getId());
                listaSintomi = List.of(ds.getSintomiConcomitantiByPaziente(idPaziente));
                aggiornaSintomiGrid();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
