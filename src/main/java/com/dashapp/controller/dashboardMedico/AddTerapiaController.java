package com.dashapp.controller.dashboardMedico;

import com.dashapp.model.AddController;
import com.dashapp.model.Farmaco;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;

public class AddTerapiaController extends AddController {

    @FXML
    private ComboBox sceltaPaziente;
    @FXML
    private ComboBox sceltaFarmaco;
    @FXML
    private TextField nAssunzioniField;
    @FXML
    private TextField doseField;
    @FXML
    private ComboBox sceltaIndicazioni;
    @FXML
    private DatePicker dataInizioPicker;
    @FXML
    private DatePicker dataFinePicker;
    @FXML
    private TextArea descrizioneArea;
    @FXML

    private DataService ds;

    public void initialize() throws Exception {
        ds = new DataService();
        popolaComboBox();
    }

    public void popolaComboBox() throws Exception {
        String email = NavigatorView.getAuthenticatedUser();
        Utente [] utenti = ds.getPazientiByMedico(ds.getUtenteByEmail(email).getId());
        for(Utente u : utenti){
            sceltaPaziente.getItems().add(u.getNome()+" "+u.getCognome());
        }
        Farmaco [] farmaci = ds.getFarmaci();

        for(Farmaco f : farmaci){
            sceltaFarmaco.getItems().add(f.getNome());
        }

        String[] indicazioniAssunzione = {
                "Dopo i pasti",
                "Prima dei pasti",
                "Lontano dai pasti",
                "Durante i pasti",
                "A stomaco vuoto",
                "Prima di coricarsi",
                "Al mattino",
                "Alla sera",
                "Ogni 12 ore",
                "Ogni 8 ore",
                "Al bisogno",
                "Una volta al giorno",
                "Due volte al giorno",
                "Tre volte al giorno",
                "Ogni settimana",
                "Ogni mese",
                "Subito dopo colazione",
                "Subito dopo cena",
                "In caso di dolore",
                "In caso di febbre",
                "Non interrompere senza consulto medico"
        };

        sceltaIndicazioni.getItems().addAll(indicazioniAssunzione);


    }


    public void assegnaTerapia(){

    }
}
