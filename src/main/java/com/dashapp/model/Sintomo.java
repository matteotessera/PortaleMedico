package com.dashapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Sintomo {

    private String idSintomo;
    private LocalDate data;
    private LocalTime ora;
    private String descrizione;
    private String idPaziente;

    // Costruttore
    public Sintomo(String idSintomo, LocalDate data, LocalTime ora, String descrizione, String idPaziente) {
        this.idSintomo = idSintomo;
        this.data = data;
        this.ora = ora;
        this.descrizione = descrizione;
        this.idPaziente = idPaziente;
    }

    // Metodo statico di factory
    public static Sintomo create(String idSintomo, LocalDate data, LocalTime ora, String descrizione, String idPaziente) {
        return new Sintomo(idSintomo, data, ora, descrizione, idPaziente);
    }

    // Getter
    public String getIdSintomo() {
        return idSintomo;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public String getIdPaziente() {
        return idPaziente;
    }

    // Setter
    public void setIdSintomo(String idSintomo) {
        this.idSintomo = idSintomo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }
}

