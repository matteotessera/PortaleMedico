<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo che tutti i campi obbligatori siano presenti
if (
    !$data || 
    !isset(
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

// Recupero i dati dal JSON
$pazienteId   = intval($data['paziente_id']);
$nomeFarmaco  = $data['farmaco'];
$dataInizio   = $data['data_inizio'];
$dataFine     = $data['data_fine'];
$frequenza    = $data['frequenza'];
$dose         = $data['dose'];
$indicazioni  = isset($data['indicazioni']) ? $data['indicazioni'] : null;

// Prepara la query SQL per inserire
$stmt = $conn->prepare("
    INSERT INTO terapie_concomitanti 
    (paziente_id, farmaco, data_inizio, data_fine, frequenza, dose, indicazioni)
    VALUES (?, ?, ?, ?, ?, ?, ?)
");
$stmt->bind_param("issssss", $pazienteId, $nomeFarmaco, $dataInizio, $dataFine, $frequenza, $dose, $indicazioni);

// Esegui e gestisci il risultato
if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento: ' . $stmt->error]);
}
?>
