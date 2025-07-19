<?php
require 'server.php';

$input = file_get_contents('php://input');
$dataIn = json_decode($input, true);

if (!$dataIn || !isset($dataIn ['data'], $dataIn ['id_utente'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$data= $dataIn ['data'];
$id_utente = $dataIn ['id_utente'];
// Prepara la query SQL per inserire
$stmt = $conn->prepare("INSERT INTO accessi (data, id_utente) VALUES (?, ?)");
$stmt->bind_param("si", $data, $id_utente);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
