<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$id = intval($_GET['id']);

$query = $conn->prepare("SELECT * FROM associazioni_farmaci WHERE id = ?");
$query->bind_param("i", $id);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Associazione farmaco non trovata']);
    exit;
}

$associazione_farmaco = $result->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($associazione_farmaco);
