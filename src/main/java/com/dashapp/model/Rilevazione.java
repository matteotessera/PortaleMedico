package com.dashapp.model;

import java.time.LocalDateTime;

public class Rilevazione {

    private String id;
    private LocalDateTime data;  // data e ora insieme
    private String valore;
    private String tipo;
    private String idPaziente;
    private String pasto;

    public enum TipoRilevazione {
        POST,
        PRE,

    }
    public enum TipoPasto {
        COLAZIONE,
        PRANZO,
        CENA
    }
    // Costruttore
    public Rilevazione(String id, LocalDateTime data, String valore, String tipo, String idPaziente, String pasto) {
        this.id = id;
        this.data = data;
        this.valore = valore;
        this.tipo = tipo;
        this.idPaziente = idPaziente;
        this.pasto = pasto;
    }

    // Factory method
    public static Rilevazione create(String id, LocalDateTime data, String valore, String tipo, String idPaziente, String pasto) {
        return new Rilevazione(id, data, valore, tipo, idPaziente, pasto);
    }

    // Getter
    public int getId() {
        return Integer.parseInt(id);
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getValore() {
        return valore;
    }

    public String getTipo() {
        return tipo;
    }

    public int getIdPaziente() {
        return Integer.parseInt(idPaziente);
    }


    public String getPasto() { return pasto; }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setValore(String valore) {
        this.valore = valore;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }

    public void setPasto(String pasto) { this.pasto = pasto; }
}
