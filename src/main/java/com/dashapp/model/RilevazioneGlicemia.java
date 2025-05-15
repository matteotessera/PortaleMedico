package com.dashapp.model;


import java.time.LocalDate;
import java.time.LocalTime;


public class RilevazioneGlicemia {


        public enum TipoComorbidita {
            PREPASTO,
            POSTPASTO,
        }


        private String idRilevazione;
        private LocalDate data;      // formato consigliato: "yyyy-MM-dd"
        private LocalTime ora;       // formato consigliato: "HH:mm"
        private double valore;
        private TipoComorbidita tipo;
        private String idPaziente;

        // Costruttore
        public RilevazioneGlicemia(String idRilevazione, LocalDate data, LocalTime ora, double valore, TipoComorbidita tipo, String idPaziente) {
            this.idRilevazione = idRilevazione;
            this.data = data;
            this.ora = ora;
            this.valore = valore;
            this.tipo = tipo;
            this.idPaziente = idPaziente;
        }

        // Metodo statico di factory
        public static RilevazioneGlicemia create(String idRilevazione, LocalDate data, LocalTime ora, double valore, TipoComorbidita tipo, String idPaziente) {
            return new RilevazioneGlicemia(idRilevazione, data, ora, valore, tipo, idPaziente);
        }

        // Getter (opzionali, utili per accedere ai campi)
        public String getIdRilevazione() {
            return idRilevazione;
        }

        public LocalDate getData() {
            return data;
        }

        public LocalTime getOra() {
            return ora;
        }

        public double getValore() {
            return valore;
        }

        public TipoComorbidita getTipo() {
            return tipo;
        }

        public String getIdPaziente() {
            return idPaziente;
        }

        // Setter (opzionali, se vuoi rendere i campi modificabili)
        public void setIdRilevazione(String idRilevazione) {
            this.idRilevazione = idRilevazione;
        }

        public void setData(LocalDate data) {
            this.data = data;
        }

        public void setOra(LocalTime ora) {
            this.ora = ora;
        }

        public void setValore(double valore) {
            this.valore = valore;
        }

        public void setTipo(TipoComorbidita tipo) {
            this.tipo = tipo;
        }

        public void setIdPaziente(String idPaziente) {
            this.idPaziente = idPaziente;
        }
    }


