<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data ||
    !isset($data['id_assunzione_farmaco'], $data['data'], $data['stato'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$idAssociazioneFarmaco = $data['id_assunzione_farmaco'];
$dataAssunzione = $data['data'];
$stato = $data['stato'];

// Prepara la query SQL per inserire nella tabella assunzioni
$stmt = $conn->prepare("INSERT INTO assunzioni (id_associazione_farmaco, data, stato) VALUES (?, ?, ?)");
if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

$stmt->bind_param("iss", $idAssociazioneFarmaco, $dataAssunzione, $stato);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}

$stmt->close();
$conn->close();
