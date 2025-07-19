<?php
require 'server.php';

if (!isset($_GET['paziente_id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro paziente_id mancante']);
    exit;
}

$pazienteId = intval($_GET['paziente_id']);

$stmt = $conn->prepare("SELECT * FROM patologie WHERE paziente_id = ?");
$stmt->bind_param("i", $pazienteId);
$stmt->execute();
$result = $stmt->get_result();

$patologie = [];
while ($row = $result->fetch_assoc()) {
    $patologie[] = $row;
}

echo json_encode($patologie);
?>