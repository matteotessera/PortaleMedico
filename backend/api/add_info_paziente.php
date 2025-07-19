<?php
require 'server.php';

$input = file_get_contents('php://input');
error_log("Input raw: " . $input);

$data = json_decode($input, true);

if (!$data || !isset($data['id_paziente'], $data['note'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id_paziente = $data['id_paziente'];
$note = $data['note'];

if (is_array($note) || is_object($note)) {
    $note = json_encode($note);
}
error_log("ID paziente: $id_paziente");
error_log("Note JSON: $note");

$stmt = $conn->prepare("INSERT INTO info_pazienti (id_paziente, note) VALUES (?, ?)");
if (!$stmt) {
    error_log("Prepare failed: " . $conn->error);
    http_response_code(500);
    echo json_encode(['error' => 'Prepare failed: ' . $conn->error]);
    exit;
}

$stmt->bind_param("is", $id_paziente, $note);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    error_log("Execute failed: " . $stmt->error);
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento: ' . $stmt->error]);
}