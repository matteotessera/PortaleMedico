<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$id = intval($_GET['id']);

$query = $conn->prepare("SELECT * FROM terapie WHERE id = ?");
$query->bind_param("i", $id);
$query->execute();
$result = $query->get_result();

$terapia = $result->fetch_assoc();

header('Content-Type: application/json');

if ($terapia) {
    echo json_encode($terapia);
} else {
    http_response_code(404);
    echo json_encode(['error' => 'Terapia non trovata']);
}
