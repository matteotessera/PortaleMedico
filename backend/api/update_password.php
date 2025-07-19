<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id'], $data['password'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = $data['id'];
$password = $data['password'];

$hash = password_hash($password,PASSWORD_DEFAULT);

$stmt = $conn->prepare("UPDATE utenti SET password = ? WHERE id = ?");
if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

$stmt->bind_param("si", $hash, $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'message' => 'Password aggiornata']);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'aggiornamento della password']);
}

$stmt->close();
$conn->close();
