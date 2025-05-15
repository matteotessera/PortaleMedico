package com.dashapp.model;

public class Medico {

    private String id;      //Codice fiscale
    private String nome;
    private String cognome;
    private String dataDiNascita;
    private String email;
    private Integer telefono;
    //Chiavi secondarie
    private String idStudio;
    private String idTerapia;

    public Medico(String id, String nome, String cognome,
                  String dataDiNascita, String email,
                  Integer telefono, String idStudio, String idTerapia){
        this.id = id;
        this.nome = nome;
        this.cognome = cognome;
        this.dataDiNascita = dataDiNascita;
        this.email = email;
        this.telefono = telefono;
        this.idStudio = idStudio;
        this.idTerapia = idTerapia;
    }

    public static Medico create(String id, String nome, String cognome,
                                String dataDiNascita, String email,
                                Integer telefono, String idStudio, String idTerapia){
        return new Medico(id, nome, cognome, dataDiNascita, email, telefono, idStudio, idTerapia);
    }

    public String getId() {
        return id;
    }

}
