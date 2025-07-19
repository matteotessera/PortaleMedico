<?php

require 'server.php';

$query = $conn->prepare("SELECT * FROM accessi");
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessun Accesso trovato']);
    exit;
}

$accessi = [];

while ($row = $result->fetch_assoc()) {
    $accessi[] = $row;
}

header('Content-Type: application/json');
echo json_encode($accessi);
