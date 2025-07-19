<?php

require 'server.php';

$query = $conn->prepare("SELECT * FROM utenti WHERE ruolo = 'medico'");
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessun utente trovato']);
    exit;
}

$utenti = [];

while ($row = $result->fetch_assoc()) {
    $utenti[] = $row;
}

header('Content-Type: application/json');
echo json_encode($utenti);
