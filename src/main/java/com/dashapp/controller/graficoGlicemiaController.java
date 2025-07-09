package com.dashapp.controller;

import com.dashapp.model.Rilevazione;
import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class graficoGlicemiaController {

        @FXML
        LineChart<String, Number> grafico;

        private List<Rilevazione> rilevazioni;
        private List<Rilevazione> rilevazioniPre;
        private List<Rilevazione> rilevazioniPost;


        private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");




        @FXML
        public void initialize() {
            grafico.setTitle("Andamento glicemia");

        }


    public void setRilevazioni(List<Rilevazione> rilevazioni){
        this.rilevazioni = new ArrayList<>(rilevazioni);

        this.rilevazioniPost = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getTipo().toString().equals("post"))
                        .toList()
        );

        this.rilevazioniPre = new ArrayList<>(
                rilevazioni.stream()
                        .filter(r -> r.getTipo().toString().equals("pre"))
                        .toList()
        );

    }


    public void popolaGrafico(){

            XYChart.Series<String, Number> seriePre = new XYChart.Series<>();      //crea una serie (una linea)
            XYChart.Series<String, Number> seriePost = new XYChart.Series<>();      //crea una serie (una linea)
            seriePre.setName("Rilevazioni Glicemiche Prima dei Pasti");
            seriePost.setName("Rilevazioni Glicemiche Dopo i Pasti");

            rilevazioniPre.sort(Comparator.comparing(Rilevazione::getData));
            rilevazioniPost.sort(Comparator.comparing(Rilevazione::getData));


            for (Rilevazione r : rilevazioniPre) {
                String dataFormattata = r.getData().format(formatter);

                Number valore = Double.parseDouble(r.getValore());
                seriePre.getData().add(new XYChart.Data<>(dataFormattata, valore));
            }
            for (Rilevazione r : rilevazioniPost) {
                String dataFormattata = r.getData().format(formatter);

                Number valore = Double.parseDouble(r.getValore());
                seriePost.getData().add(new XYChart.Data<>(dataFormattata, valore));
            }

            // Pulisci grafico e aggiungi serie
            grafico.getData().clear();
            grafico.getData().add(seriePre);
            grafico.getData().add(seriePost);

        }

}
