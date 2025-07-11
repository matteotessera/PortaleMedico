package com.dashapp.controller;

import com.dashapp.controller.dashboardPatient.fascicolo.FascicoloPazienteController;
import com.dashapp.model.Utente;
import com.dashapp.services.DataService;
import com.dashapp.view.NavigatorView;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;

public class ProfiloPaziente {
    @FXML
    public TextField nomeField;
    @FXML
    public TextField cognomeField;
    @FXML
    public TextField dataNascitaField;
    @FXML
    public TextField codiceFiscaleField;
    @FXML
    public TextField telefonoField;
    @FXML
    public TextField emailField;
    @FXML
    public TextField indirizzoField;
    @FXML
    public TextField genereField;

    @FXML
    public Tab fattoriDiRischioButton;
    @FXML
    public Tab fascicoloButton;
    @FXML
    public AnchorPane fattoriDiRischioPane;
    @FXML
    public AnchorPane fascicoloPane;

    private Utente u;

    public void initialize() throws Exception {

        u = NavigatorView.getUtenteSelezionato();
        riempiCampi();
        loadTabs();

    }

    public void riempiCampi(){
        nomeField.setText(u.getNome());
        cognomeField.setText(u.getCognome());
        dataNascitaField.setText(u.getDataNascita().toString());
        codiceFiscaleField.setText(u.getCodFiscale());
        genereField.setText(u.getGenere());
        indirizzoField.setText(u.getIndirizzo());
        emailField.setText(u.getEmail());
        telefonoField.setText(u.getTelefono());
    }


    public void loadTabs() throws Exception {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/dashapp/fxml/DashBoardPatient/Fascicolo/FascicoloPaziente.fxml"));
        Parent fascicolo = loader.load();
        FascicoloPazienteController controller = loader.getController();
        controller.setPaziente(u);
        fascicoloPane.getChildren().add(fascicolo);



    }
}

