<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Chiudere account AWS – Guida</title>
    <style>
        body { font-family: Arial, sans-serif; background: #fefefe; padding: 2em; line-height: 1.6; color: #222; }
        h1 { color: #c62828; }
        h2 { color: #2e7d32; margin-top: 2em; }
        code, pre { background: #eee; padding: 4px 8px; border-radius: 4px; font-family: monospace; }
        ul { margin-top: 0.5em; }
    </style>
</head>
<body>
    <h1>🛑 Guida alla chiusura dell’account AWS</h1>

    <h2>1. Verifica che non ci siano risorse attive</h2>
    <p>Accedi alla console AWS e controlla che tutto sia disattivato o terminato:</p>
    <ul>
        <li><strong>EC2</strong>: Nessuna istanza attiva</li>
        <li><strong>EBS</strong>: Nessun volume residuo</li>
        <li><strong>S3</strong>: Nessun bucket attivo</li>
        <li><strong>Elastic IP</strong>: Nessun IP allocato</li>
    </ul>

    <h2>2. (Facoltativo) Imposta un budget con allarme a 0€</h2>
    <ul>
        <li>Vai su: <a href="https://console.aws.amazon.com/billing/home#/budgets" target="_blank">Billing → Budgets</a></li>
        <li>Crea un <strong>Cost Budget</strong> con importo massimo 0€</li>
        <li>Inserisci la tua email per ricevere avvisi</li>
    </ul>

    <h2>3. Chiudi l'account AWS</h2>
    <p>Quando sei sicuro di aver finito tutto:</p>
    <ol>
        <li>Vai su: <a href="https://console.aws.amazon.com/billing/home#/account" target="_blank">Account Settings</a></li>
        <li>Scorri fino in fondo alla pagina</li>
        <li>Clicca su <strong>"Chiudi account"</strong></li>
        <li>Conferma la chiusura accettando i termini</li>
    </ol>

    <p><strong>⚠️ Dopo circa 90 minuti non potrai più accedere alla console.</strong></p>

    <h2>🔒 Cosa succede dopo?</h2>
    <ul>
        <li>L’account verrà disattivato</li>
        <li>La carta di pagamento non sarà più usata</li>
        <li>Nessuna fatturazione futura sarà possibile</li>
    </ul>

    <p style="margin-top:2em;"><em>Guida generata automaticamente per il tuo progetto universitario. Sicurezza prima di tutto ✅</em></p>
</body>
</html>
