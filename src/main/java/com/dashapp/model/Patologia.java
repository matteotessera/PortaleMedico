package com.dashapp.model;

import java.time.LocalDate;

public class Patologia {
    private int pazienteId;
    private String nomePatologia;
    private LocalDate dataDiagnosi;
    private String note;


    public Patologia(int pazienteId, String nomePatologia, LocalDate dataDiagnosi, String note) {
        this.pazienteId = pazienteId;
        this.nomePatologia = nomePatologia;
        this.dataDiagnosi = dataDiagnosi;
        this.note = note;
    }

    // Getter e Setter

    public int getPazienteId() {
        return pazienteId;
    }

    public void setPazienteId(int pazienteId) {
        this.pazienteId = pazienteId;
    }

    public String getNomePatologia() {
        return nomePatologia;
    }

    public void setNomePatologia(String nomePatologia) {
        this.nomePatologia = nomePatologia;
    }

    public LocalDate getDataDiagnosi() {
        return dataDiagnosi;
    }

    public void setDataDiagnosi(LocalDate dataDiagnosi) {
        this.dataDiagnosi = dataDiagnosi;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}