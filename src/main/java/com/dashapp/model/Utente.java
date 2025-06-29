package com.dashapp.model;

import java.time.LocalDate;

public class Utente {
    private int id;
    private String password;
    private String ruolo;
    private String nome;
    private String cognome;
    private String codFiscale;
    private LocalDate dataNascita;
    private String email;
    private String telefono;
    private String indirizzo;
    private String genere;

    // Costruttore vuoto
    public Utente() {}

    // Costruttore completo
    public Utente(int id, String password, String ruolo, String nome, String cognome,
                  String codFiscale, LocalDate dataNascita, String email,
                  String telefono, String indirizzo, String genere) {
        this.id = id;
        this.password = password;
        this.ruolo = ruolo;
        this.nome = nome;
        this.cognome = cognome;
        this.codFiscale = codFiscale;
        this.dataNascita = dataNascita;
        this.email = email;
        this.telefono = telefono;
        this.indirizzo = indirizzo;
        this.genere = genere;
    }

    // Getter e Setter

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRuolo() {
        return ruolo;
    }

    public void setRuolo(String ruolo) {
        this.ruolo = ruolo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCognome() {
        return cognome;
    }

    public void setCognome(String cognome) {
        this.cognome = cognome;
    }

    public String getCodFiscale() {
        return codFiscale;
    }

    public void setCodFiscale(String codFiscale) {
        this.codFiscale = codFiscale;
    }

    public LocalDate getDataNascita() {
        return dataNascita;
    }

    public void setDataNascita(LocalDate dataNascita) {
        this.dataNascita = dataNascita;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    public void setIndirizzo(String indirizzo){
        this.indirizzo = indirizzo;
    }

    public void setGenere(String genere){
        this.genere = genere;
    }

    public String getGenere(){
        return genere;
    }

    public String toString(){
        String result = "";
            result = this.nome + " " + this.cognome + " " + this.email;
        return result;
    }
}
