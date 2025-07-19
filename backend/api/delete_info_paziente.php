<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'ID mancante o invalido']);
    exit;
}

$id = $data['id'];

// Prepara la query SQL per cancellare la riga con l'id specificato
$stmt = $conn->prepare("DELETE FROM infoPaziente WHERE id = ?");
$stmt->bind_param("i", $id);

if ($stmt->execute()) {
    if ($stmt->affected_rows > 0) {
        echo json_encode(['success' => true, 'message' => "Info paziente con id $id eliminata."]);
    } else {
        http_response_code(404);
        echo json_encode(['error' => 'Info paziente non trovata']);
    }
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore durante l\'eliminazione']);
}
?>