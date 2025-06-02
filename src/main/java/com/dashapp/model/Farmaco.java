package com.dashapp.model;

    public class Farmaco {

        private int id;
        private String nome;
        private String descrizione;

        // Costruttore
        public Farmaco(int id, String nome, String descrizione) {
            this.id = id;
            this.nome = nome;
            this.descrizione = descrizione;
        }

        // Metodo statico di factory
        public static Farmaco create(int id, String nome, String descrizione) {
            return new Farmaco(id, nome, descrizione);
        }

        // Getter
        public int getId() {
            return id;
        }

        public String getNome() {
            return nome;
        }

        public String getDescrizione() {
            return descrizione;
        }

        // Setter
        public void setId(int id) {
            this.id = id;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public String toString(){
            return this.nome;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }
    }


