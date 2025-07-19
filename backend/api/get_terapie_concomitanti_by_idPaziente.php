<?php
require 'server.php';

if (!isset($_GET['paziente_id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro paziente_id mancante']);
    exit;
}

$pazienteId = intval($_GET['paziente_id']);

$stmt = $conn->prepare("SELECT * FROM terapie_concomitanti WHERE paziente_id = ?");
$stmt->bind_param("i", $pazienteId);

if ($stmt->execute()) {
    $result = $stmt->get_result();
    $terapie = [];

    while ($row = $result->fetch_assoc()) {
        $terapie[] = $row;
    }

    echo json_encode($terapie);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella lettura: ' . $stmt->error]);
}
?>
