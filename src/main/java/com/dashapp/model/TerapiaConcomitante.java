package com.dashapp.model;

import java.time.LocalDate;

public class TerapiaConcomitante {

    private int id;
    private int paziente_id;
    private String farmaco;
    private LocalDate dataInizio;
    private LocalDate dataFine;
    private String frequenza;
    private String dose;
    private String indicazioni;

    // Costruttore
    public TerapiaConcomitante(int id, int paziente_id, String farmaco, LocalDate dataInizio, LocalDate dataFine,
                               String frequenza, String dose, String indicazioni) {
        this.id = id;
        this.paziente_id = paziente_id;
        this.farmaco = farmaco;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.frequenza = frequenza;
        this.dose = dose;
        this.indicazioni = indicazioni;
    }

    public TerapiaConcomitante() {
    }

    // Getter e Setter
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPaziente_id() {
        return paziente_id;
    }

    public void setPaziente_id(int paziente_id) {
        this.paziente_id = paziente_id;
    }

    public String getFarmaco() {
        return farmaco;
    }

    public void setFarmaco(String farmaco) {
        this.farmaco = farmaco;
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

    public String getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(String frequenza) {
        this.frequenza = frequenza;
    }

    public String getDose() {
        return dose;
    }

    public void setDose(String dose) {
        this.dose = dose;
    }

    public String getIndicazioni() {
        return indicazioni;
    }

    public void setIndicazioni(String indicazioni) {
        this.indicazioni = indicazioni;
    }
}
