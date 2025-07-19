<?php

require 'server.php';

// Query per selezionare utenti che NON sono presenti nella tabella assegnazioni_medici come id_paziente
$query = $conn->prepare("
    SELECT * FROM utenti
    WHERE ruolo = 'paziente' 
    AND id NOT IN (
        SELECT id_paziente FROM assegnazioni_medici
    ) ORDER BY cognome
");
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessun utente senza medico trovato']);
    exit;
}

$utenti = [];

while ($row = $result->fetch_assoc()) {
    $utenti[] = $row;
}

header('Content-Type: application/json');
echo json_encode($utenti);
