package com.dashapp.model;

public class Studio {

    private String idStudio;
    private String nome;
    private String via;
    private String comune;
    private String provincia;
    private String nazione;
    private String telefono;
    private String email;

    // Costruttore
    public Studio(String idStudio, String nome, String via, String comune, String provincia, String nazione, String telefono, String email) {
        this.idStudio = idStudio;
        this.nome = nome;
        this.via = via;
        this.comune = comune;
        this.provincia = provincia;
        this.nazione = nazione;
        this.telefono = telefono;
        this.email = email;
    }

    // Metodo statico di factory
    public static Studio create(String idStudio, String nome, String via, String comune, String provincia, String nazione, String telefono, String email) {
        return new Studio(idStudio, nome, via, comune, provincia, nazione, telefono, email);
    }

    // Getter
    public String getIdStudio() {
        return idStudio;
    }

    public String getNome() {
        return nome;
    }

    public String getVia() {
        return via;
    }

    public String getComune() {
        return comune;
    }

    public String getProvincia() {
        return provincia;
    }

    public String getNazione() {
        return nazione;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getEmail() {
        return email;
    }

    // Setter
    public void setIdStudio(String idStudio) {
        this.idStudio = idStudio;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setVia(String via) {
        this.via = via;
    }

    public void setComune(String comune) {
        this.comune = comune;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }

    public void setNazione(String nazione) {
        this.nazione = nazione;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
