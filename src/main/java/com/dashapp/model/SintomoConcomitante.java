package com.dashapp.model;

import java.time.LocalDate;

public class SintomoConcomitante {

    private int id;
    private int paziente_id;
    private String sintomo;
    private LocalDate dataInizio;
    private String frequenza;
    private String note;

    // Costruttore di default
    public SintomoConcomitante() {
    }

    // Costruttore completo
    public SintomoConcomitante(int id, int paziente_id, String sintomo, LocalDate dataInizio, String frequenza, String note) {
        this.id = id;
        this.paziente_id = paziente_id;
        this.sintomo = sintomo;
        this.dataInizio = dataInizio;
        this.frequenza = frequenza;
        this.note = note;
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

    public String getSintomo() {
        return sintomo;
    }

    public void setSintomo(String sintomo) {
        this.sintomo = sintomo;
    }

    public LocalDate getDataInizio() {
        return dataInizio;
    }

    public void setDataInizio(LocalDate dataInizio) {
        this.dataInizio = dataInizio;
    }

    public String getFrequenza() {
        return frequenza;
    }

    public void setFrequenza(String frequenza) {
        this.frequenza = frequenza;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
