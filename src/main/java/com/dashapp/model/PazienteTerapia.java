package com.dashapp.model;

public class PazienteTerapia {

    private String idPaziente;
    private String idTerapia;

    // Costruttore
    public PazienteTerapia(String idPaziente, String idTerapia) {
        this.idPaziente = idPaziente;
        this.idTerapia = idTerapia;
    }

    // Metodo statico di factory
    public static PazienteTerapia create(String idPaziente, String idTerapia) {
        return new PazienteTerapia(idPaziente, idTerapia);
    }

    // Getter
    public String getIdPaziente() {
        return idPaziente;
    }

    public String getIdTerapia() {
        return idTerapia;
    }

    // Setter
    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }

    public void setIdTerapia(String idTerapia) {
        this.idTerapia = idTerapia;
    }
}
