<?php

require 'server.php';

$query = $conn->prepare("SELECT * FROM modifiche");
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessun Accesso trovato']);
    exit;
}

$modifiche = [];

while ($row = $result->fetch_assoc()) {
    $modifiche[] = $row;
}

header('Content-Type: application/json');
echo json_encode($modifiche);
