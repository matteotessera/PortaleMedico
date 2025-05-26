package com.dashapp.model;

public class AssociazioneFarmaco {

    private int id;
    private int idTerapia;
    private int idFarmaco;
    private int numeroAssunzioni;
    private int dose;

    public AssociazioneFarmaco() {}
    // Costruttore
    public AssociazioneFarmaco(int id, int idTerapia, int idFarmaco, int numeroAssunzioni, int dose) {
        this.id = id;
        this.idTerapia = idTerapia;
        this.idFarmaco = idFarmaco;
        this.numeroAssunzioni = numeroAssunzioni;
        this.dose = dose;
    }

    // Metodo statico di factory
    public static AssociazioneFarmaco create(int id, int idTerapia, int idFarmaco, int numeroAssunzioni, int dose) {
        return new AssociazioneFarmaco(id, idTerapia, idFarmaco, numeroAssunzioni, dose);
    }

    // Getter
    public int getId() {
        return id;
    }

    public int getIdTerapia() {
        return idTerapia;
    }

    public int getIdFarmaco() {
        return idFarmaco;
    }

    public int getNumeroAssunzioni() {
        return numeroAssunzioni;
    }

    public int getDose() {
        return dose;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setIdTerapia(int idTerapia) {
        this.idTerapia = idTerapia;
    }

    public void setIdFarmaco(int idFarmaco) {
        this.idFarmaco = idFarmaco;
    }

    public void setNumeroAssunzioni(int numeroAssunzioni) {
        this.numeroAssunzioni = numeroAssunzioni;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }
}
