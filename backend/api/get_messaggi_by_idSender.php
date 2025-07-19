<?php
require 'server.php';

if (!isset($_GET['id_sender'])) {
    http_response_code(400);
    echo json_encode(['error' => 'ID sender mancante']);
    exit;
}

$idSender = intval($_GET['id_sender']);

$result = $conn->query("SELECT * FROM messaggi WHERE id_sender = $idSender");

$messaggi = [];

while ($row = $result->fetch_assoc()) {
    $messaggi[] = $row;
}

echo json_encode($messaggi);
?>
