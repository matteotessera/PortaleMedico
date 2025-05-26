package com.dashapp.model;

import java.time.LocalDate;

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
}
