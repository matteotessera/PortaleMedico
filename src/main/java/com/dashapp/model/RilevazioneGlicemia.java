package com.dashapp.model;


import java.time.LocalDate;
import java.time.LocalTime;


public class RilevazioneGlicemia {


        public enum TipoRilevazione {
            PREPRANDIALE,
            POSTPRANDIALE,

        }
        public enum TipoPasto {
            COLAZIONE,
            PRANZO,
            CENA
        }


        private String idRilevazione;
        private LocalDate data;      // formato consigliato: "yyyy-MM-dd"
        private LocalTime ora;       // formato consigliato: "HH:mm"
        private double valore;
        private TipoRilevazione tipo;
        private TipoPasto tipoPasto;
        private String idPaziente;

        // Costruttore
        public RilevazioneGlicemia(String idRilevazione, LocalDate data, LocalTime ora, double valore, TipoRilevazione tipo, TipoPasto tipoPasto, String idPaziente) {
            this.idRilevazione = idRilevazione;
            this.data = data;
            this.ora = ora;
            this.valore = valore;
            this.tipo = tipo;
            this.tipoPasto = tipoPasto;
            this.idPaziente = idPaziente;
        }

        // Metodo statico di factory
        public static RilevazioneGlicemia create(String idRilevazione, LocalDate data, LocalTime ora, double valore, TipoRilevazione tipo, TipoPasto tipoPasto, String idPaziente) {
            return new RilevazioneGlicemia(idRilevazione, data, ora, valore, tipo, tipoPasto, idPaziente);
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

        public TipoRilevazione getTipo() {
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

        public void setTipo(TipoRilevazione tipo) {
            this.tipo = tipo;
        }

        public void setTipoPasto(TipoPasto tipo) {
            this.tipoPasto = tipo;
        }

        public void setIdPaziente(String idPaziente) {
            this.idPaziente = idPaziente;
        }
    }


