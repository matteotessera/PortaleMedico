<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['valore'], $data['tipo'], $data['id_paziente'], $data['data'], $data['pasto'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$valore = floatval($data['valore']);
$tipo = $data['tipo'];
$idPaziente = intval($data['id_paziente']);
$dataSintomo = $data['data'];
$pasto = $data['pasto'];

// Prepara la query SQL per inserire
$stmt = $conn->prepare("INSERT INTO rilevazioni (valore, tipo, id_paziente, data, pasto) VALUES (?, ?, ?, ?, ?)");
$stmt->bind_param("dsiss", $valore, $tipo, $idPaziente, $dataSintomo, $pasto);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
