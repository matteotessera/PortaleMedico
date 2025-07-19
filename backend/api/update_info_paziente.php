<?php
require 'server.php';

$input = file_get_contents('php://input');
error_log("Input raw: " . $input);

$data = json_decode($input, true);

if (!$data || !isset($data['id'], $data['id_paziente'], $data['note'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = intval($data['id']);
$id_paziente = intval($data['id_paziente']);
$note = $data['note'];

if (is_array($note) || is_object($note)) {
    $note = json_encode($note);
}

error_log("ID record: $id");
error_log("ID paziente: $id_paziente");
error_log("Note JSON: $note");

$stmt = $conn->prepare("UPDATE info_pazienti SET id_paziente = ?, note = ? WHERE id = ?");
if (!$stmt) {
    error_log("Prepare failed: " . $conn->error);
    http_response_code(500);
    echo json_encode(['error' => 'Prepare failed: ' . $conn->error]);
    exit;
}

$stmt->bind_param("isi", $id_paziente, $note, $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Record aggiornato']);
} else {
    error_log("Execute failed: " . $stmt->error);
    http_response_code(500);
    echo json_encode(['error' => 'Errore aggiornamento: ' . $stmt->error]);
}