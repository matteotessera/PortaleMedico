<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo se i dati sono presenti e validi
if (
    !$data ||
    !isset($data['id'], $data['valore'], $data['tipo'], $data['id_paziente'], $data['data'], $data['pasto'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$idRilevazione = intval($data['id']);
$valore = floatval($data['valore']);
$tipo = $data['tipo'];
$idPaziente = intval($data['id_paziente']);
$dataRilevazione = $data['data'];
$pasto = $data['pasto'];

// Prepara la query SQL per aggiornare la rilevazione
$stmt = $conn->prepare("UPDATE rilevazioni SET valore = ?, tipo = ?, id_paziente = ?, data = ?, pasto = ? WHERE id = ?");
if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

$stmt->bind_param("dsissi", $valore, $tipo, $idPaziente, $dataRilevazione, $pasto, $idRilevazione);

// Esegui la query e restituisci il risultato
if ($stmt->execute()) {
    if ($stmt->affected_rows > 0) {
        echo json_encode(['success' => true]);
    } else {
        http_response_code(404);
        echo json_encode(['error' => 'Nessuna riga trovata o dati identici']);
    }
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'aggiornamento: ' . $stmt->error]);
}

$stmt->close();
$conn->close();
?>
