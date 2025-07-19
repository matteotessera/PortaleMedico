<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = intval($data['id']);

// 1. delete assunzioni 
$stmt = $conn->prepare("DELETE FROM assunzioni WHERE id_associazione_farmaco = ?");
$stmt->bind_param("i", $id);
$stmt->execute();

// 2. delete associazione
$stmt = $conn->prepare("DELETE FROM associazioni_farmaci WHERE id = ?");
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    http_response_code(200);
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
