<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Verifica presenza id, nome e descrizione
if (!$data || !isset($data['id'], $data['nome'], $data['descrizione'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = $data['id'];
$nome = $data['nome'];
$descrizione = $data['descrizione'];

// Prepara la query SQL per aggiornare il farmaco
$stmt = $conn->prepare("UPDATE farmaci SET nome = ?, descrizione = ? WHERE id = ?");
$stmt->bind_param("ssi", $nome, $descrizione, $id);

if ($stmt->execute()) {
    if ($stmt->affected_rows > 0) {
        echo json_encode(['success' => true]);
    } else {
        http_response_code(404);
        echo json_encode(['error' => 'Farmaco non trovato o dati identici']);
    }
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'aggiornamento']);
}

$stmt->close();
$conn->close();
