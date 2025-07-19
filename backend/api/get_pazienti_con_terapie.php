<?php

require 'server.php';

// Query: seleziona tutti i pazienti che hanno almeno una terapia
$query = $conn->prepare("
    SELECT * FROM utenti 
    WHERE ruolo = 'paziente' 
    AND id IN (
        SELECT DISTINCT id_paziente FROM terapie
    )
");

$query->execute();
$result = $query->get_result();

// Array per contenere i pazienti trovati
$pazienti = [];

while ($row = $result->fetch_assoc()) {
    $pazienti[] = $row;
}

// Imposta il content-type su JSON
header('Content-Type: application/json');

// Restituisci l'array, anche se vuoto
echo json_encode($pazienti);
