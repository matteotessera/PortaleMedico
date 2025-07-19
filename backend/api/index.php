<!DOCTYPE html>
<html lang="it">
<head>
    <meta charset="UTF-8">
    <title>Server Info – Progetto Universitario</title>
    <style>
        body { font-family: Arial, sans-serif; background: #f2f2f2; padding: 2em; color: #333; }
        h1 { color: #005f8f; }
        code { background: #eee; padding: 2px 4px; border-radius: 4px; }
        section { background: #fff; padding: 1em; margin-bottom: 1em; border-radius: 8px; box-shadow: 0 0 8px rgba(0,0,0,0.05); }
    </style>
</head>
<body>
    <h1>🖥️ Server EC2 – Progetto Universitario</h1>

    <section>
        <h2>📦 Specifiche Server</h2>
        <ul>
            <li>IP pubblico: <code>3.123.253.157</code></li>
            <li>Ubuntu 24.04 LTS – t2.micro (1 GB RAM / 30 GB disco)</li>
            <li>Nginx + PHP 8.3 + MariaDB 10.11</li>
        </ul>
    </section>

    <section>
        <h2>🔌 Connessione Database</h2>
        <ul>
            <li>Database: <code>dataBase</code></li>
            <li>Utente: <code>admin</code></li>
            <!-- <li>Password: <code>farmaci</code></li> -->
            <li>Password: <code>*******</code></li>
        </ul>
    </section>

    <section>
        <h2>🔐 Sicurezza</h2>
        <ul>
            <li>UFW attivo: solo porte <code>22 (SSH)</code>, <code>80 (HTTP)</code>, <code>443 (HTTPS)</code> aperte</li>
            <li>Fail2Ban attivo su SSH (5 tentativi → ban 10 minuti)</li>
            <li>Swap file: 1 GB attivo</li>
            <li>Certificato SSL: autofirmato</li>
        </ul>
    </section>

    <section>
        <h2>🌐 Accessi</h2>
        <ul>
            <li><a href="https://3.123.253.157/phpmyadmin" target="_blank">phpMyAdmin</a></li>
            <li><a href="https://3.123.253.157/shutdown.php" target="_blank">procedura di shotdown del server</a></li>
        </ul>
    </section>

    <section>
        <h2>🧠 Stato PHP → MariaDB</h2>
        <p>
        <?php
        $host = 'localhost';
        $dbname = 'dataBase';
        $user = 'admin';
        $pass = 'farmaci';
        try {
            $pdo = new PDO("mysql:host=$host;dbname=$dbname", $user, $pass);
            echo "✅ Connessione al database riuscita!";
        } catch (PDOException $e) {
            echo "❌ Connessione fallita: " . $e->getMessage();
        }
        ?>
        </p>
    </section>
</body>
</html>
