<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$id = intval($_GET['id']);

$query = $conn->prepare("SELECT * FROM utenti WHERE id = ?");
$query->bind_param("i", $id);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Utente non trovato']);
    exit;
}

$utente = $result->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($utente);
