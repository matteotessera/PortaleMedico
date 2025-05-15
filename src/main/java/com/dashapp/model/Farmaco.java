package com.dashapp.model;

    public class Farmaco {

        private String idFarmaco;
        private String nome;
        private String descrizione;

        // Costruttore
        public Farmaco(String idFarmaco, String nome, String descrizione) {
            this.idFarmaco = idFarmaco;
            this.nome = nome;
            this.descrizione = descrizione;
        }

        // Metodo statico di factory
        public static Farmaco create(String idFarmaco, String nome, String descrizione) {
            return new Farmaco(idFarmaco, nome, descrizione);
        }

        // Getter
        public String getIdFarmaco() {
            return idFarmaco;
        }

        public String getNome() {
            return nome;
        }

        public String getDescrizione() {
            return descrizione;
        }

        // Setter
        public void setIdFarmaco(String idFarmaco) {
            this.idFarmaco = idFarmaco;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setDescrizione(String descrizione) {
            this.descrizione = descrizione;
        }
    }


