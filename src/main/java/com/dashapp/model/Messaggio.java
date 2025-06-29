package com.dashapp.model;

import java.sql.Struct;
import java.time.LocalDate;
import java.time.LocalTime;

public class Messaggio {

    private int id;
    private int id_Sender;      //id paziente/ medico ce ha inviato
    private int id_receiver;
    private LocalDate dataInvio;
    private LocalTime orarioInvio;
    private String oggetto;
    private String corpo;
    private char tipo;        // G = glicemia elevata, N = non aderente alle prescrizioni per 3 giorni consecutivi
                                // A= avviso, hai dimenticato di assumere farmaco x, M = messaggio diretto dal medico
    private boolean letto;

    public Messaggio(int id, int id_Sender, int id_receiver, char tipo, LocalDate dataInvio, LocalTime orarioInvio, String oggetto, String corpo, boolean letto) {
        this.id = id;
        this.id_Sender = id_Sender;
        this.dataInvio = dataInvio;
        this.orarioInvio = orarioInvio;
        this.oggetto = oggetto;
        this.corpo = corpo;
        this.tipo = tipo;
        this.letto = false;
        this.id_receiver = id_receiver;
    }

    public Messaggio() {

    }

    public void setTipo(char tipo){
        this.tipo = tipo;
    }

    public char getTipo(){
        return tipo;
    }

    public int getId() {
        return id;
    }

    public boolean getLetto(){
        return letto;
    }

    public void setLetto(boolean letto){
        this.letto = letto;
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

    public int getId_receiver() {
        return id_receiver;
    }

    public void setId_receiver(int id_receiver) {
        this.id_receiver = id_receiver;
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
