<?php
require 'server.php';

if (!isset($_GET['id_receiver'])) {
    http_response_code(400);
    echo json_encode(['error' => 'ID receiver mancante']);
    exit;
}

$idReceiver = intval($_GET['id_receiver']);

$result = $conn->query("SELECT * FROM messaggi WHERE id_receiver = $idReceiver");

$messaggi = [];

while ($row = $result->fetch_assoc()) {
    $messaggi[] = $row;
}

echo json_encode($messaggi);
?>
