<?php
require 'server.php';

// Prendi tutti gli utenti
$result = $conn->query("SELECT id, password FROM utenti");

if (!$result) {
    die("Errore nella query: " . $conn->error);
}

while ($row = $result->fetch_assoc()) {
    $id = $row['id'];
    $passwordChiara = $row['password'];

    // Controllo semplice: salto se la password sembra già hashata (es. inizia con $2y$ che è tipico di bcrypt)
    if (strpos($passwordChiara, '$2y$') === 0) {
        continue; // password già hashata, salto
    }

    // Hash della password
    $passwordHash = password_hash($passwordChiara, PASSWORD_DEFAULT);

    // Aggiorna la password nel DB
    $stmt = $conn->prepare("UPDATE utenti SET password = ? WHERE id = ?");
    $stmt->bind_param('si', $passwordHash, $id);
    $stmt->execute();
    $stmt->close();
}

echo "Password aggiornate con hashing.";

$conn->close();
