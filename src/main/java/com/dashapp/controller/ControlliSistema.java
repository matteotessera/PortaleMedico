package com.dashapp.controller;

import com.dashapp.controller.dashboardPatient.BoxDashboardControllerPatient;
import com.dashapp.model.*;
import com.dashapp.services.DataService;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class ControlliSistema {


    //RITORNA TRUE SE: negli ultimi 3 giorni, il paziente NON ha seguito correttamente le prescrizioni
    public boolean pazienteNonAderente(int idPaziente) throws Exception {
        DataService ds = new DataService();
        LocalDate oggi = LocalDate.now();

        // Trova tutte le terapie del paziente
        Terapia[] terapie = ds.getTerapiePaziente(idPaziente);

        for (Terapia terapia : terapie) {
            // Per ogni terapia, prendi le associazioni farmaco
            AssociazioneFarmaco[] associazioni = ds.getAssociazioniFarmaciByTerapia(terapia.getId());

            for (AssociazioneFarmaco af : associazioni) {
                int numeroAssunzioniPreviste = af.getNumeroAssunzioni();
                int giorniConsecutiviNonAderenti = 0;

                for (int i = 3; i >= 1; i--) {
                    LocalDate data = oggi.minusDays(i);

                    // Ottieni tutte le assunzioni per quel giorno
                    Assunzione[] tutteAssunzioni;
                    try {
                        tutteAssunzioni = ds.getAssunzioniPaziente(idPaziente);
                    } catch (Exception e) {
                        // Nessuna assunzione registrata
                        tutteAssunzioni = new Assunzione[0];
                    }

                    List<Assunzione> assunzioni = Arrays.stream(tutteAssunzioni)
                            .filter(a -> a.getIdAssociazioneFarmaco() == af.getId())
                            .filter(a -> a.getData().toLocalDate().equals(data))
                            .collect(Collectors.toList());

                    if (assunzioni.size() < numeroAssunzioniPreviste) {
                        giorniConsecutiviNonAderenti++;
                        if (giorniConsecutiviNonAderenti >= 3) {
                            return true; // Paziente non aderente per almeno 3 giorni consecutivi
                        }
                    } else {
                        giorniConsecutiviNonAderenti = 0; // reset
                    }
                }
            }
        }

        return false; // Tutto ok
    }

    //RITORNA mappa con [ Nome farmaco, StatoFarmaco]  Stato farmaco = [assunto/non assunto, nr assunzioni rimanenti]
    public Map<Farmaco, StatoFarmaco> farmaciDaAssumerePaziente(int idPaziente) throws Exception {
        DataService ds;
        ds = new DataService();

        List<Assunzione> assunzioniOggi = Arrays.stream(ds.getAssunzioniPaziente(idPaziente))
                .filter(a -> a.getData().toLocalDate().equals(LocalDate.now()))
                .collect(Collectors.toList());

        // Tutte le associazioni farmaco per le terapie del paziente
        List<Terapia> terapie = Arrays.asList(ds.getTerapiePaziente(idPaziente));
        List<AssociazioneFarmaco> associazioni = new ArrayList<>();
        for (Terapia t : terapie) {
            associazioni.addAll(Arrays.asList(ds.getAssociazioniFarmaciByTerapia(t.getId())));
        }

        Map<Farmaco, StatoFarmaco> mappa = new HashMap<>();

        for (AssociazioneFarmaco assoc : associazioni) {
            Farmaco farmaco = ds.getFarmacoById(assoc.getIdFarmaco());

            // Assunzioni fatte oggi per questa associazione
            long assunzioniFatte = assunzioniOggi.stream()
                    .filter(a -> a.getIdAssociazioneFarmaco() == assoc.getId())
                    .count();

            int assunzioniRimaste = assoc.getNumeroAssunzioni() - (int) assunzioniFatte;
            assunzioniRimaste = Math.max(0, assunzioniRimaste); // per sicurezza

            String stato = (assunzioniRimaste == 0) ? "già assunto" : "da assumere";
            mappa.put(farmaco, new StatoFarmaco(stato, assunzioniRimaste));
        }

        return mappa;
    }

}
