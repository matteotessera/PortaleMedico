<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['descrizione'], $data['id_paziente'], $data['data'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$descrizione = $data['descrizione'];
$idPaziente = intval($data['id_paziente']);
$dataSintomo = $data['data'];

// Prepara la query SQL per inserire
$stmt = $conn->prepare("INSERT INTO sintomi (descrizione, id_paziente, data) VALUES (?, ?, ?)");
$stmt->bind_param("sis", $descrizione, $idPaziente, $dataSintomo);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento: ' . $stmt->error]);
}
