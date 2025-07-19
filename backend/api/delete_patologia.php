<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'ID mancante']);
    exit;
}

$id = intval($data['id']);

$stmt = $conn->prepare("DELETE FROM patologie WHERE id = ?");
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella cancellazione: ' . $stmt->error]);
}
?>
