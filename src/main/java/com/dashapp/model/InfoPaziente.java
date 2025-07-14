package com.dashapp.model;

import com.google.gson.JsonElement;

public class InfoPaziente {
    private int id;
    private int idPaziente;
    private JsonElement note;  // cambia da String a JsonElement

    // Costruttore vuoto
    public InfoPaziente() {}

    // Costruttore completo
    public InfoPaziente(int id, int idPaziente, JsonElement note) {
        this.id = id;
        this.idPaziente = idPaziente;
        this.note = note;
    }

    // Getter e Setter per id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    // Getter e Setter per idPaziente
    public int getIdPaziente() {
        return idPaziente;
    }

    public void setIdPaziente(int idPaziente) {
        this.idPaziente = idPaziente;
    }

    // Getter e Setter per note
    public JsonElement getNote() {
        return note;
    }

    public void setNote(JsonElement note) {
        this.note = note;
    }

    // Metodo di utilità per ottenere la nota come stringa JSON
    public String getNoteAsString() {
        return note != null ? note.toString() : null;
    }
}
