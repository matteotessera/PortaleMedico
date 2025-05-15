package com.dashapp.model;

public class MedicoTerapia {

    private String idMedico;
    private String idTerapia;

    // Costruttore
    public MedicoTerapia(String idMedico, String idTerapia) {
        this.idMedico = idMedico;
        this.idTerapia = idTerapia;
    }

    // Metodo statico di factory
    public static MedicoTerapia create(String idMedico, String idTerapia) {
        return new MedicoTerapia(idMedico, idTerapia);
    }

    // Getter
    public String getIdMedico() {
        return idMedico;
    }

    public String getIdTerapia() {
        return idTerapia;
    }

    // Setter
    public void setIdMedico(String idMedico) {
        this.idMedico = idMedico;
    }

    public void setIdTerapia(String idTerapia) {
        this.idTerapia = idTerapia;
    }
}
