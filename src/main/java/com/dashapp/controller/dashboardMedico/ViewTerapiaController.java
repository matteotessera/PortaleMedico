package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.AssociazioneFarmaco;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Terapia;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import org.controlsfx.control.CheckComboBox;

import javax.annotation.processing.Generated;
import java.util.List;

public class ViewTerapiaController {
    @FXML
    public VBox VboxTerapia;

    @FXML
    private ComboBox<Terapia> sceltaTerapia;

    @FXML
    private Button modificaButton;

    @FXML
    private ComboBox<String> sceltaFarmaco;

    @FXML
    private TextField nAssunzioniField;

    @FXML
    private TextField doseField;

    @FXML
    private DatePicker dataInizioPicker;

    @FXML
    private DatePicker dataFinePicker;

    @FXML
    private TextArea note;

    @FXML
    private Button annullaButton;

    @FXML
    private Button confermaButton;

    @FXML
    private Label statusLabel;

    private DataService ds;


    private List<Terapia> terapieUtente;

    @FXML
    public void initialize() throws Exception {
        this.ds = new DataService();

        VboxTerapia.setVisible(false);

        popolaCampiIniziali();

        disabilitaInput();

    }

    public void popolaCampiIniziali() throws Exception {
        terapieUtente = List.of(ds.getTerapiePaziente(NavigatorView.getUtenteSelezionato().getId()));

        sceltaTerapia.getItems().addAll(terapieUtente);

        Farmaco [] farmaci = ds.getFarmaci();

        for(Farmaco f : farmaci){
            sceltaFarmaco.getItems().add(f.getNome());
        }

        sceltaFarmaco.setEditable(false);
    }

    public void disabilitaInput(){
        modificaButton.setVisible(true);

        nAssunzioniField.setEditable(false);
        doseField.setEditable(false);
        note.setEditable(false);
        sceltaFarmaco.setEditable(false);
        dataInizioPicker.setDisable(true);
        dataFinePicker.setDisable(true);

        annullaButton.setVisible(false);
        confermaButton.setVisible(false);
        annullaButton.setManaged(false);
        confermaButton.setManaged(false);
    }

    public void abilitaInput(){
        modificaButton.setVisible(false);

        nAssunzioniField.setEditable(true);
        doseField.setEditable(true);
        note.setEditable(true);
        sceltaFarmaco.setEditable(true);
        dataInizioPicker.setDisable(false);
        dataFinePicker.setDisable(false);

        annullaButton.setVisible(true);
        confermaButton.setVisible(true);
        annullaButton.setManaged(true);
        confermaButton.setManaged(true);
    }

    public void modifica(){
        abilitaInput();
    }

    public void sceltaTerapia() throws Exception {
        Terapia terapiaSelezionata = sceltaTerapia.getValue();
        if (terapiaSelezionata != null) {
            caricaDatiTerapia(terapiaSelezionata.getId());
        }
    }

    public void caricaDatiTerapia(int idTerapia) throws Exception {
        VboxTerapia.setVisible(true);
        Terapia t = ds.getTerapiaById(idTerapia);
        AssociazioneFarmaco [] af = ds.getAssociazioniFarmaciByTerapia(idTerapia);

        nAssunzioniField.setText(Integer.toString(af[0].getNumeroAssunzioni()));
        doseField.setText(Integer.toString(af[0].getDose()));
        note.setText(t.getNote());
        sceltaFarmaco.setValue(ds.getFarmacoById(af[0].getIdFarmaco()).getNome());
        dataInizioPicker.setValue(t.getDataInizio());
        dataFinePicker.setValue(t.getDataFine());
    }

    public void annulla(){
        disabilitaInput();
    }

    public void inviaModifiche(){
    }

}