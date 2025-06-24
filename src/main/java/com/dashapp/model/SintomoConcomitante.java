package com.dashapp.model;

import java.time.LocalDate;

public class SintomoConcomitante {

    private int id;
    private int paziente_id;
    private String descrizione;
    private LocalDate dataInizio;
    private String frequenza;
    private String note;

    public SintomoConcomitante(){};

    // Costruttore completo
    public SintomoConcomitante(int id, int paziente_id, String descrizione, LocalDate dataInizio, String frequenza, String note) {
        this.id = id;
        this.paziente_id = paziente_id;
        this.descrizione = descrizione;
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

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
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
