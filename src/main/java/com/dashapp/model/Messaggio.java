package com.dashapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class Messaggio {

    private int id;
    private int id_Sender;      //id paziente/ medico ce ha inviato
    private LocalDate dataInvio;
    private LocalTime orarioInvio;
    private String oggetto;
    private String corpo;



    public Messaggio(int id, int id_Sender, LocalDate dataInvio, LocalTime orarioInvio, String oggetto, String corpo) {
        this.id = id;
        this.id_Sender = id_Sender;
        this.dataInvio = dataInvio;
        this.orarioInvio = orarioInvio;
        this.oggetto = oggetto;
        this.corpo = corpo;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_Sender() {
        return id_Sender;
    }

    public void setId_Sender(int id_Sender) {
        this.id_Sender = id_Sender;
    }

    public LocalDate getDataInvio() {
        return dataInvio;
    }

    public void setDataInvio(LocalDate dataInvio) {
        this.dataInvio = dataInvio;
    }

    public LocalTime getOrarioInvio() {
        return orarioInvio;
    }

    public void setOrarioInvio(LocalTime orarioInvio) {
        this.orarioInvio = orarioInvio;
    }

    public String getOggetto() {
        return oggetto;
    }

    public void setOggetto(String oggetto) {
        this.oggetto = oggetto;
    }

    public String getCorpo() {
        return corpo;
    }

    public void setCorpo(String corpo) {
        this.corpo = corpo;
    }

    public String toString(){
        String result = "";

        result += this.oggetto + "\n" + this.corpo;
        return result;
    }
}
