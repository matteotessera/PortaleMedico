<?php

require 'server.php';

if (!isset($_GET['idTerapia'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "idTerapia" mancante']);
    exit;
}

$idTerapia = intval($_GET['idTerapia']);

$query = $conn->prepare("SELECT * FROM associazioni_farmaci WHERE id_terapia = ?");
$query->bind_param("i", $idTerapia);
$query->execute();
$result = $query->get_result();

$associazioni = [];

while ($row = $result->fetch_assoc()) {
    $associazioni[] = $row;
}

header('Content-Type: application/json');

if (count($associazioni) > 0) {
    echo json_encode($associazioni);
} else {
    http_response_code(404);
    echo json_encode(['error' => 'Nessuna associazione trovata']);
}
