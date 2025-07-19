<?php

require 'server.php';

if (!isset($_GET['id_utente'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id_utente" mancante']);
    exit;
}

$id_utente = intval($_GET['id_utente']);

$query = $conn->prepare("SELECT * FROM modifiche WHERE id_utente = ?");
$query->bind_param("i", $id_utente);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Accesso non trovato']);
    exit;
}

$modifiche = [];

while ($row = $result->fetch_assoc()) {
    $modifiche[] = $row;
}


header('Content-Type: application/json');
echo json_encode($modifiche);
