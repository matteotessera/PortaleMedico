<?php

require 'server.php';

if (!isset($_GET['idPaziente'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$idPaziente = intval($_GET['idPaziente']);

$query = $conn->prepare("SELECT * FROM terapie WHERE id_paziente = ?");
$query->bind_param("i", $idPaziente);
$query->execute();
$result = $query->get_result();

$terapie = [];

while ($row = $result->fetch_assoc()) {
    $terapie[] = $row;
}

header('Content-Type: application/json');
echo json_encode($terapie);
