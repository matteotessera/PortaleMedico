<?php

require 'server.php';

if (!isset($_GET['nome'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "nome" mancante']);
    exit;
}

$nome = $_GET['nome'];

$query = $conn->prepare("SELECT * FROM farmaci WHERE nome = ?");
$query->bind_param("s", $nome);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Farmaco non trovato']);
    exit;
}

$utente = $result->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($utente );