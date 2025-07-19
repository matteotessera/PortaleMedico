<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo se i dati sono presenti e validi
if (
    !$data ||
    !isset($data['id'], $data['descrizione'], $data['id_paziente'], $data['data'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$idSintomo = intval($data['id']);
$descrizione = $data['descrizione'];
$idPaziente = intval($data['id_paziente']);
$dataSintomo = $data['data'];

// Prepara la query SQL per aggiornare il sintomo
$stmt = $conn->prepare("UPDATE sintomi SET descrizione = ?, id_paziente = ?, data = ? WHERE id = ?");
if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

$stmt->bind_param("sisi", $descrizione, $idPaziente, $dataSintomo, $idSintomo);

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
