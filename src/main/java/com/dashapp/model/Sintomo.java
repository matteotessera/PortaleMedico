package com.dashapp.model;

import java.time.LocalDateTime;

public class Sintomo {

    private String id;
    private LocalDateTime data;  // ora è inclusa qui
    private String descrizione;
    private String idPaziente;

    // Costruttore
    public Sintomo(String id, LocalDateTime data, String descrizione, String idPaziente) {
        this.id = id;
        this.data = data;
        this.descrizione = descrizione;
        this.idPaziente = idPaziente;
    }

    // Metodo statico di factory
    public static Sintomo create(String id, LocalDateTime data, String descrizione, String idPaziente) {
        return new Sintomo(id, data, descrizione, idPaziente);
    }

    // Getter
    public int getId() {
        return Integer.parseInt(id);
    }

    public LocalDateTime getData() {
        return data;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public int getIdPaziente() {
        return Integer.parseInt(idPaziente);
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }
}
