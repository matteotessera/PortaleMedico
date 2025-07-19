<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$idRilevazione = intval($_GET['id']);

$query = $conn->prepare("SELECT * FROM rilevazioni WHERE id = ? ORDER BY data DESC");
$query->bind_param("i", $idRilevazione);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Rilevazione non trovata']);
    exit;
}

$rilevazione = $result->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($rilevazione);
