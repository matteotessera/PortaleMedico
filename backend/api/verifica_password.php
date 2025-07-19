<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id'], $data['passwordAttuale'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti']);
    exit;
}

$id = $data['id'];
$passwordAttuale = $data['passwordAttuale'];

// Recupera hash della password dal DB
$stmt = $conn->prepare("SELECT password FROM utenti WHERE id = ?");
$stmt->bind_param("i", $id);
$stmt->execute();
$stmt->bind_result($hash);
$stmt->fetch();
$stmt->close();

if (!$hash) {
    http_response_code(404);
    echo json_encode(['error' => 'Utente non trovato']);
    exit;
}

// Verifica password
if (password_verify($passwordAttuale, $hash)) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(401);
    echo json_encode(['error' => 'Password errata']);
}

$conn->close();