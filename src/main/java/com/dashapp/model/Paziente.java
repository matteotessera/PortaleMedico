package com.dashapp.model;

public class Paziente {


    private String idPaziente;
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String email;
    private String telefono;
    private String via;
    private String comune;
    private String provincia;
    private String nazione;
    private String idMedico;

    // Costruttore
    public Paziente(String idPaziente, String nome, String cognome, String dataDiNascita,
                    String email, String telefono, String via, String comune,
                    String provincia, String nazione, String idMedico) {
        this.idPaziente = idPaziente;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.telefono = telefono;
        this.via = via;
        this.comune = comune;
        this.provincia = provincia;
        this.nazione = nazione;
        this.idMedico = idMedico;
    }

    public static Paziente create(String idPaziente, String nome, String cognome, String dataDiNascita,
                                  String email, String telefono, String via, String comune,
                                  String provincia, String nazione, String idMedico) {
        return new Paziente(idPaziente, nome, cognome, dataDiNascita,
                email, telefono, via, comune,
                provincia, nazione, idMedico);
    }

}
