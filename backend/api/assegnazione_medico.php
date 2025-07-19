<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['idPaziente'], $data['idMedico'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$idPaziente = $data['idPaziente'];
$idMedico = $data['idMedico'];
// Prepara la query SQL per inserire
$stmt = $conn->prepare("INSERT INTO assegnazioni_medici (id_medico, id_paziente) VALUES (?, ?)");
$stmt->bind_param("ss", $idMedico, $idPaziente);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}
