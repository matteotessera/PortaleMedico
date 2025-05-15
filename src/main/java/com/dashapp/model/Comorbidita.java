package com.dashapp.model;
public class Comorbidita {


    public enum TipoComorbidita {
        SINTOMO,
        PATOLOGIA,
        TERAPIA
    }

    private String id;
    private TipoComorbidita tipo;
    private String periodo;
    private String descrizione;

    // Costruttore
    public Comorbidita(String id, TipoComorbidita tipo, String periodo, String descrizione) {
        this.id = id;
        this.tipo = tipo;
        this.periodo = periodo;
        this.descrizione = descrizione;
    }

    // Metodo statico di factory
    public static Comorbidita create(String id, TipoComorbidita tipo, String periodo, String descrizione) {
        return new Comorbidita(id, tipo, periodo, descrizione);
    }

    // Getter
    public String getId() {
        return id;
    }

    public TipoComorbidita getTipo() {
        return tipo;
    }

    public String getPeriodo() {
        return periodo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setTipo(TipoComorbidita tipo) {
        this.tipo = tipo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }
}


