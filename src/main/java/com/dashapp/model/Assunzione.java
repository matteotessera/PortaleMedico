package com.dashapp.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Assunzione {

    private int id;
    private LocalDateTime data;
    private int idAssociazioneFarmaco;
    private String stato;       // es. "assunto" o "dimenticato"
    private int dose;
    private int idPaziente;

    public Assunzione(){}

    // Costruttore
    public Assunzione(int id, LocalDateTime data, int idAssociazioneFarmaco, String stato, int dose, int idPaziente) {
        this.id = id;
        this.data = data;
        this.idAssociazioneFarmaco = idAssociazioneFarmaco;
        this.stato = stato;
        this.dose = dose;
        this.idPaziente = idPaziente;
    }

    // Metodo statico di factory
    public static Assunzione create(int id, LocalDateTime data, int idAssociazioneFarmaco, String stato, int dose, int idPaziente) {
        return new Assunzione(id, data, idAssociazioneFarmaco, stato, dose, idPaziente);
    }

    // Getter
    public int getId() {
        return id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public int getIdAssociazioneFarmaco() {
        return idAssociazioneFarmaco;
    }

    public String getStato() {
        return stato;
    }

    public int getDose() {
        return dose;
    }

    public int getIdPaziente() {
        return idPaziente;
    }

    // Setter
    public void setId(int id) {
        this.id = id;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public void setIdAssociazioneFarmaco(int idAssociazioneFarmaco) {
        this.idAssociazioneFarmaco = idAssociazioneFarmaco;
    }

    public void setStato(String stato) {
        this.stato = stato;
    }

    public void setDose(int dose) {
        this.dose = dose;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }
}
