package com.dashapp.model;

public class FattoriDiRischio {
    private String id;
    private String idPaziente;
    private String note;

    // Costruttore vuoto
    public FattoriDiRischio() {}

    // Costruttore completo
    public FattoriDiRischio(String id, String idPaziente, String note) {
        this.id = id;
        this.idPaziente = idPaziente;
        this.note = note;
    }

    // Getter e Setter per id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    // Getter e Setter per idPaziente
    public String getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }

    // Getter e Setter per note
    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
