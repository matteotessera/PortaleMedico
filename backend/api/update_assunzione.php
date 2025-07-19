<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo se i dati sono presenti e validi
if (
    !$data ||
    !isset($data['id'], $data['data'], $data['stato'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$idAssunzione = $data['id'];
$dataAssunzione = $data['data'];
$stato = $data['stato'];

// Prepara la query SQL per aggiornare la riga
$stmt = $conn->prepare("UPDATE assunzioni SET data = ?, stato = ? WHERE id = ?");
if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

$stmt->bind_param("ssi", $dataAssunzione, $stato, $idAssunzione);

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
    echo json_encode(['error' => 'Errore nell\'aggiornamento']);
}

$stmt->close();
$conn->close();
?>
