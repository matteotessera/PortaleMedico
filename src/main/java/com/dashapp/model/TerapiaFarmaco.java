package com.dashapp.model;

public class TerapiaFarmaco {

    private String idTerapia;
    private String idFarmaco;
    private int nrAssunzioni;
    private double quantita;
    private String indicazioni;
    private String idTerapiaFarmaco;

    // Costruttore
    public TerapiaFarmaco(String idTerapia, String idFarmaco, int nrAssunzioni, double quantita, String indicazioni, String idTerapiaFarmaco) {
        this.idTerapia = idTerapia;
        this.idFarmaco = idFarmaco;
        this.nrAssunzioni = nrAssunzioni;
        this.quantita = quantita;
        this.indicazioni = indicazioni;
        this.idTerapiaFarmaco = idTerapiaFarmaco;
    }

    // Metodo statico di factory
    public static TerapiaFarmaco create(String idTerapia, String idFarmaco, int nrAssunzioni, double quantita, String indicazioni, String idTerapiaFarmaco) {
        return new TerapiaFarmaco(idTerapia, idFarmaco, nrAssunzioni, quantita, indicazioni, idTerapiaFarmaco);
    }

    // Getter
    public String getIdTerapia() {
        return idTerapia;
    }

    public String getIdFarmaco() {
        return idFarmaco;
    }

    public int getNrAssunzioni() {
        return nrAssunzioni;
    }

    public double getQuantita() {
        return quantita;
    }

    public String getIndicazioni() {
        return indicazioni;
    }

    public String getIdTerapiaFarmaco() {
        return idTerapiaFarmaco;
    }

    // Setter
    public void setIdTerapia(String idTerapia) {
        this.idTerapia = idTerapia;
    }

    public void setIdFarmaco(String idFarmaco) {
        this.idFarmaco = idFarmaco;
    }

    public void setNrAssunzioni(int nrAssunzioni) {
        this.nrAssunzioni = nrAssunzioni;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public void setIndicazioni(String indicazioni) {
        this.indicazioni = indicazioni;
    }

    public void setIdTerapiaFarmaco(String idTerapiaFarmaco) {
        this.idTerapiaFarmaco = idTerapiaFarmaco;
    }
}