<?php

require 'server.php';

if (!isset($_GET['id_medico'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$id_medico = intval($_GET['id_medico']);

$sql = "SELECT t.id, t.data_inizio, t.data_fine, t.note, t.id_paziente
        FROM terapie t
        INNER JOIN assegnazioni_medici am ON t.id_paziente = am.id_paziente
        WHERE am.id_medico = ?";

$stmt = $conn->prepare($sql);
$stmt->bind_param("i", $id_medico);
$stmt->execute();

$result = $stmt->get_result();

$terapie = [];

while ($row = $result->fetch_assoc()) {
    $terapie[] = $row;
}

header('Content-Type: application/json');
echo json_encode($terapie);
