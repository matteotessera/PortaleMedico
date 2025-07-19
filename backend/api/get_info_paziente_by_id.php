<?php

require 'server.php';

if (!isset($_GET['id_paziente'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id_paziente" mancante']);
    exit;
}

$id_paziente = intval($_GET['id_paziente']);

$query = $conn->prepare("SELECT * FROM info_pazienti WHERE id_paziente = ?");
$query->bind_param("i", $id_paziente);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Paziente non trovato']);
    exit;
}

$paziente = $result->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($paziente);