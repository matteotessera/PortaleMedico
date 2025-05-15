package com.dashapp.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class AssunzioneFarmaco {

    private String idAssunzione;
    private LocalDate data;      // formato consigliato: "yyyy-MM-dd"
    private LocalTime ora;       // formato consigliato: "HH:mm"
    private double quantita;
    private String idPaziente;
    private String idFarmaco;

    // Costruttore
    public AssunzioneFarmaco(String idAssunzione, LocalDate data, LocalTime ora, double quantita, String idPaziente, String idFarmaco) {
        this.idAssunzione = idAssunzione;
        this.data = data;
        this.ora = ora;
        this.quantita = quantita;
        this.idPaziente = idPaziente;
        this.idFarmaco = idFarmaco;
    }

    // Metodo statico di factory
    public static AssunzioneFarmaco create(String idAssunzione, LocalDate data, LocalTime ora, double quantita, String idPaziente, String idFarmaco) {
        return new AssunzioneFarmaco(idAssunzione, data, ora, quantita, idPaziente, idFarmaco);
    }

    // Getter
    public String getIdAssunzione() {
        return idAssunzione;
    }

    public LocalDate getData() {
        return data;
    }

    public LocalTime getOra() {
        return ora;
    }

    public double getQuantita() {
        return quantita;
    }

    public String getIdPaziente() {
        return idPaziente;
    }

    public String getIdFarmaco() {
        return idFarmaco;
    }

    // Setter
    public void setIdAssunzione(String idAssunzione) {
        this.idAssunzione = idAssunzione;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public void setQuantita(double quantita) {
        this.quantita = quantita;
    }

    public void setIdPaziente(String idPaziente) {
        this.idPaziente = idPaziente;
    }

    public void setIdFarmaco(String idFarmaco) {
        this.idFarmaco = idFarmaco;
    }
}
