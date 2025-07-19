<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$idPaziente = intval($_GET['id']);

$query = $conn->prepare("SELECT * FROM rilevazioni WHERE id_paziente = ? ORDER BY data DESC");
$query->bind_param("i", $idPaziente);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessuna rilevazione trovata']);
    exit;
}

$rilevazioni = [];

while ($row = $result->fetch_assoc()) {
    $rilevazioni[] = $row;
}

header('Content-Type: application/json');
echo json_encode($rilevazioni);
