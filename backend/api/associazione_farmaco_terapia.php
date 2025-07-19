<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data ||
    !isset($data['id_terapia'], $data['id_farmaco'], $data['numero_assunzioni'], $data['dose'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$idTerapia = $data['id_terapia'];
$idFarmaco = $data['id_farmaco'];
$numeroAssunzioni = $data['numero_assunzioni'];
$dose = $data['dose'];

$stmt = $conn->prepare("INSERT INTO associazioni_farmaci (id_terapia, id_farmaco, numero_assunzioni, dose) VALUES (?, ?, ?, ?)");
if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

$stmt->bind_param("iiii", $idTerapia, $idFarmaco, $numeroAssunzioni, $dose);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}

$stmt->close();
$conn->close();
