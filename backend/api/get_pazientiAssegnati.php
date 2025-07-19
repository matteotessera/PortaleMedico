<?php

require 'server.php';

// Query per ottenere TUTTI i dati dei pazienti con medico assegnato
$query = $conn->prepare("
    SELECT u.*
    FROM utenti u
    JOIN assegnazioni_medici am ON u.id = am.id_paziente
");

// Esegui la query
$query->execute();
$result = $query->get_result();

$pazientiAssegnati = [];

// Cicla sui risultati e costruisci l'array associativo
while ($row = $result->fetch_assoc()) {
    $pazientiAssegnati[] = $row;
}

header('Content-Type: application/json');

// Restituisci i risultati in JSON
if (count($pazientiAssegnati) > 0) {
    echo json_encode($pazientiAssegnati);
} else {
    http_response_code(404);
    echo json_encode(['error' => 'Nessun paziente con medico assegnato trovato']);
}
