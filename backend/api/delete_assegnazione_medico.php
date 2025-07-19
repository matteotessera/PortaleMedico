<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id_paziente'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id_paziente = intval($data['id_paziente']);

// Prepara la query SQL per inserire
$stmt = $conn->prepare("DELETE FROM assegnazioni_medici WHERE id_paziente = ?");
$stmt->bind_param("i", $id_paziente);

if ($stmt->execute()) {
    http_response_code(200);
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
