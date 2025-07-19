<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data || 
    !isset($data['id'], $data['paziente_id'], $data['nome_patologia'], $data['data_diagnosi'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = intval($data['id']);
$pazienteId = intval($data['paziente_id']);
$nomePatologia = $data['nome_patologia'];
$dataDiagnosi = $data['data_diagnosi'];
$note = isset($data['note']) ? $data['note'] : null;

$stmt = $conn->prepare("
    UPDATE patologie SET paziente_id = ?, nome_patologia = ?, data_diagnosi = ?, note = ?
    WHERE id = ?
");
$stmt->bind_param("isssi", $pazienteId, $nomePatologia, $dataDiagnosi, $note, $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'aggiornamento: ' . $stmt->error]);
}
?>