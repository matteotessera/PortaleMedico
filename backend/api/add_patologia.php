<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data || 
    !isset($data['paziente_id'], $data['nome_patologia'], $data['data_diagnosi'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$pazienteId = intval($data['paziente_id']);
$nomePatologia = $data['nome_patologia'];
$dataDiagnosi = $data['data_diagnosi'];
$note = isset($data['note']) ? $data['note'] : null;

$stmt = $conn->prepare("
    INSERT INTO patologie (paziente_id, nome_patologia, data_diagnosi, note)
    VALUES (?, ?, ?, ?)
");
$stmt->bind_param("isss", $pazienteId, $nomePatologia, $dataDiagnosi, $note);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento: ' . $stmt->error]);
}
?>
