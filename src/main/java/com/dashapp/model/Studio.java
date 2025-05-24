package com.dashapp.model;

public class Studio {

    private String id;
    private String nome;
    private String indirizzo;

    // Costruttore
    public Studio(String id, String nome, String indirizzo) {
        this.id = id;
        this.nome = nome;
        this.indirizzo = indirizzo;
    }

    // Metodo statico di factory
    public static Studio create(String id, String nome, String indirizzo) {
        return new Studio(id, nome, indirizzo);
    }

    // Getter
    public String getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public String getIndirizzo() {
        return indirizzo;
    }

    // Setter
    public void setId(String id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setIndirizzo(String indirizzo) {
        this.indirizzo = indirizzo;
    }
}
