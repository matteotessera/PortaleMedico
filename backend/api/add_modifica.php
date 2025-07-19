<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['data'], $data['id_utente'], $data['tabella'], $data['colonna'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$data = $data['data'];
$id_utente = $data['id_utente'];
$tabella = $data['tabella'];
$colonna = $data['colonna'];

// Prepara la query SQL per inserire
$stmt = $conn->prepare("INSERT INTO accessi (data, id_utente, tabella, colonna) VALUES (?, ?, ?, ?)");
$stmt->bind_param("siss", $data, $id_utente, $tabella, $colonna);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
