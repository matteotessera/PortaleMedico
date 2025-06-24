package com.dashapp.model;

public class StatoFarmaco {
    private String stato; // "da assumere" o "già assunto"
    private int assunzioniRimaste;

    public StatoFarmaco(String stato, int assunzioniRimaste) {
        this.stato = stato;
        this.assunzioniRimaste = assunzioniRimaste;
    }

    public String getStato() {
        return stato;
    }

    public int getAssunzioniRimaste() {
        return assunzioniRimaste;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setAssunzioniRimaste(int assunzioniRimaste) {
        this.assunzioniRimaste = assunzioniRimaste;
    }
}