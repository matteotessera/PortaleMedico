<?php
require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'ID mancante']);
    exit;
}

$id = intval($_GET['id']);

$stmt = $conn->prepare("DELETE FROM messaggi WHERE id = ?");
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    echo json_encode(['success' => true]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella cancellazione: ' . $stmt->error]);
}
?>
