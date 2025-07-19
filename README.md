# 📋 Personal Patient Assistant (PPA)

Questo progetto è una **PPA (Personal Patient Assistant)** sviluppata in **JavaFX** per la parte frontend, con un **backend in PHP** che gestisce la comunicazione con il database.  
L’applicativo è pensato per aiutare pazienti, medici e amministratori nella gestione dei dati clinici, terapie, farmaci e molto altro, in modo semplice e intuitivo.

## ✏️ Descrizione

L’applicazione offre funzionalità diverse in base al ruolo dell’utente:

- **Paziente**: può inserire rilevazioni, sintomi, assunzioni e visualizzare il proprio fascicolo medico.
- **Medico**: può visualizzare e modificare le terapie dei propri pazienti, aggiungere nuovi farmaci e gestire i dati clinici.
- **Admin**: può gestire gli utenti del sistema (aggiungere, modificare e rimuovere) e supervisionare l’intero sistema.

Il backend in PHP espone API che consentono di salvare, modificare e recuperare i dati dal database in modo sicuro ed efficiente.

## 🔑 Credenziali di accesso predefinite

Per testare rapidamente l’applicativo, sono disponibili i seguenti utenti preconfigurati:

| Ruolo       | Email               | Password  |
|-------------|---------------------|-----------|
| Admin       | admin@admin.it      | pass.123  |
| Medico      | medico@medico.it    | pass.123  |
| Paziente    | paz@paz.it          | pass.123  |

> ⚠️ **Nota**: si consiglia di cambiare queste credenziali nella fase di deploy in produzione per motivi di sicurezza.

## 🛠 Tecnologie utilizzate

- **JavaFX** – per la realizzazione dell’interfaccia grafica.
- **PHP** – come backend per gestire l’accesso ai dati.
- **MySQL** o altro RDBMS – per il salvataggio dei dati.
- **HTTP client** – per la comunicazione tra frontend e backend.

## 🚀 Come eseguire il progetto

1. **Clonare il repository**:
   ```bash
   git clone https://github.com/matteotessera/PortaleMedico.git
