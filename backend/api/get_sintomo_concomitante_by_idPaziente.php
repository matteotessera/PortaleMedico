<?php
require 'server.php';

if (!isset($_GET['paziente_id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'paziente_id mancante']);
    exit;
}

$pazienteId = intval($_GET['paziente_id']);

$stmt = $conn->prepare("SELECT * FROM sintomi_concomitanti WHERE paziente_id = ?");
$stmt->bind_param("i", $pazienteId);

if (!$stmt->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella query: ' . $stmt->error]);
    exit;
}

$result = $stmt->get_result();
$sintomi = [];

while ($row = $result->fetch_assoc()) {
    $sintomi[] = $row;
}

echo json_encode($sintomi);
?>
