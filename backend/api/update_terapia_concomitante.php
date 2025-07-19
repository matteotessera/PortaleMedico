<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data || 
    !isset(
        $data['id'], 
        $data['paziente_id'], 
        $data['farmaco'], 
        $data['data_inizio'], 
        $data['data_fine'], 
        $data['frequenza'], 
        $data['dose']
    )
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id           = intval($data['id']);
$pazienteId   = intval($data['paziente_id']);
$nomeFarmaco  = $data['farmaco'];
$dataInizio   = $data['data_inizio'];
$dataFine     = $data['data_fine'];
$frequenza    = $data['frequenza'];
$dose         = $data['dose'];
$indicazioni  = isset($data['indicazioni']) ? $data['indicazioni'] : null;

$stmt = $conn->prepare("
    UPDATE terapie_concomitanti SET
    paziente_id = ?, 
    farmaco = ?, 
    data_inizio = ?, 
    data_fine = ?, 
    frequenza = ?, 
    dose = ?, 
    indicazioni = ?
    WHERE id = ?
");
$stmt->bind_param("issssssi", $pazienteId, $nomeFarmaco, $dataInizio, $dataFine, $frequenza, $dose, $indicazioni, $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'aggiornamento: ' . $stmt->error]);
}
?>
