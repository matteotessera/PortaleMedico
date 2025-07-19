<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo campi obbligatori (id e almeno uno degli altri)
if (
    !$data || 
    !isset($data['id'], $data['paziente_id'], $data['descrizione'], $data['data_inizio'], $data['frequenza'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = intval($data['id']);
$pazienteId = intval($data['paziente_id']);
$descrizione = $data['descrizione'];
$dataInizio = $data['data_inizio'];
$frequenza = $data['frequenza'];
$note = isset($data['note']) ? $data['note'] : null;

$stmt = $conn->prepare("
    UPDATE sintomi_concomitanti
    SET paziente_id = ?, descrizione = ?, data_inizio = ?, frequenza = ?, note = ?
    WHERE id = ?
");
$stmt->bind_param("issssi", $pazienteId, $descrizione, $dataInizio, $frequenza, $note, $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore durante l\'aggiornamento: ' . $stmt->error]);
}
?>