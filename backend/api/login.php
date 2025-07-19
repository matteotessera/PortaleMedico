<?php

if ($_SERVER['REQUEST_METHOD'] === 'OPTIONS') {
    exit;
}

require 'server.php';

if (!isset($_POST['email']) || !isset($_POST['password'])) {
    http_response_code(400);
    echo json_encode(['logged_in' => false, 'error' => 'Email o password mancante']);
    exit;
}

$email = $_POST['email'];
$password = $_POST['password'];

error_log("valori : " . $email . " " . $password);

$stmt = $conn->prepare("SELECT id, ruolo, password FROM utenti WHERE email = ?");
$stmt->bind_param("s", $email);
$stmt->execute();

$result = $stmt->get_result();
$utente = $result->fetch_assoc();

if (!$utente) {
    echo json_encode(['logged_in' => false, 'error' => 'Utente non trovato']);
    exit;
}

// Confronto password diretto (per progetto semplice)
if (password_verify($password,$utente['password'])) {
    echo json_encode(['logged_in' => true, 'id' => $utente['id'], 'ruolo' => $utente['ruolo']]);
} else {
    echo json_encode(['logged_in' => false, 'error' => 'Password errata']);
}

$stmt->close();
$conn->close();
