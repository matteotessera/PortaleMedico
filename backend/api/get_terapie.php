<?php

require 'server.php';

$query = $conn->prepare("SELECT * FROM terapie");
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessuna terapia trovata']);
    exit;
}

$farmaci = [];

while ($row = $result->fetch_assoc()) {
    $farmaci[] = $row;
}

header('Content-Type: application/json');
echo json_encode($farmaci);
