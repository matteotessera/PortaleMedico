<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo dei dati richiesti
if (
    !$data || 
    !isset($data['descrizione'], $data['paziente_id'], $data['data_inizio'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

// Recupero i dati dal JSON
$pazienteId = intval($data['paziente_id']);
$descrizione = $data['descrizione'];
$dataInizio = $data['data_inizio'];
$frequenza = $data['frequenza'];
$note = isset($data['note']) ? $data['note'] : null;

// Prepara la query SQL per inserire
$stmt = $conn->prepare("
    INSERT INTO sintomi_concomitanti 
    (paziente_id, descrizione, data_inizio, frequenza, note) 
    VALUES (?, ?, ?, ?, ?)
");
$stmt->bind_param("issss", $pazienteId, $descrizione, $dataInizio, $frequenza, $note);

// Esegui e gestisci il risultato
if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento: ' . $stmt->error]);
}
?>
