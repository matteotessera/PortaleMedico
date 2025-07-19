<?php
require 'server.php';

if (!isset($_GET['email'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "email" mancante']);
    exit;
}

$email = $_GET['email'];

$query = $conn->prepare("SELECT * FROM utenti WHERE email = ?");
$query->bind_param("s", $email);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Utente non trovato']);
    exit;
}

$utente = $result->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($utente);
