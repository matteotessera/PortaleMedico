<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['nome'], $data['descrizione'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$nome = $data['nome'];
$descrizione = $data['descrizione'];
// Prepara la query SQL per inserire
$stmt = $conn->prepare("INSERT INTO farmaci (nome, descrizione) VALUES (?, ?)");
$stmt->bind_param("ss", $nome, $descrizione);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
