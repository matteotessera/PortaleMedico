package com.dashapp.model;


public class Terapia {

    private String idTerapia;
    private String dataInizio;
    private String dataFine;
    private String note;
    private String idMedico;
    private String idPaziente;

    // Costruttore
    public Terapia(String idTerapia, String dataInizio, String dataFine,
                   String note, String idMedico, String idPaziente) {
        this.idTerapia = idTerapia;
        this.dataInizio = dataInizio;
        this.dataFine = dataFine;
        this.note = note;
        this.idMedico = idMedico;
        this.idPaziente = idPaziente;
    }

    // Metodo statico create
    public static Terapia create(String idTerapia, String dataInizio, String dataFine,
                                 String note, String idMedico, String idPaziente) {
        return new Terapia(idTerapia, dataInizio, dataFine, note, idMedico, idPaziente);
    }

}