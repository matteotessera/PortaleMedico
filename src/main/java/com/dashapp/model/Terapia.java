package com.dashapp.model;

import com.dashapp.services.DataService;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Terapia {
    private int id;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String note;
    private int idPaziente;


    // Costruttore vuoto
    public Terapia() {}

    // Costruttore completo
    public Terapia(int id, LocalDate dataInizio, LocalDate dataFine, String note, int idPaziente) {
        this.id = id;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.note = note;
        this.idPaziente = idPaziente;
    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public LocalDate getDataFine() {
        return dataFine;
    }

    public void setDataFine(LocalDate dataFine) {
        this.dataFine = dataFine;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public int getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }

    @Override
    public String toString() {

        DataService ds = new DataService();
        List<Farmaco> farmaci;
        try {
            List<AssociazioneFarmaco> ass = List.of(ds.getAssociazioniFarmaciByTerapia(id));
            List<Integer> IDfarmaci = ass.stream()
                    .map(AssociazioneFarmaco::getIdFarmaco)
                    .collect(Collectors.toList());
            farmaci = new ArrayList<>();
            for (Integer idFarmaco : IDfarmaci) {
                try {
                    farmaci.add(ds.getFarmacoById(idFarmaco));
                } catch (Exception e) {
                    e.printStackTrace(); // oppure loggalo o mostra un messaggio
                }
            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        return "Terapia:" + id + "\n" +
                "\u2022 dataInizio: " + dataInizio + "\n" +
                "\u2022 dataFine:" + dataFine + "\n" +
                "\u2022 Farmaci:" +  farmaci  + "\n" +
                "\u2022 note:'" + note + '\''
                ;

    }

}
