package com.dashapp.model;

import java.time.LocalDateTime;

public class Accesso {
    private int id;
    private int idUtente;
    private LocalDateTime data;

    public Accesso() {
    }

    public Accesso(int id, int idUtente, LocalDateTime data) {
        this.id = id;
        this.idUtente = idUtente;
        this.data = data;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUtente() {
        return idUtente;
    }

    public void setIdUtente(int idUtente) {
        this.idUtente = idUtente;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "Accesso{" +
                "id=" + id +
                ", idUtente=" + idUtente +
                ", data=" + data +
                '}';
    }
}
