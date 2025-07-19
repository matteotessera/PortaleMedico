<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$idPaziente = intval($_GET['id']);

// Cerca il medico assegnato al paziente
$query = $conn->prepare("SELECT id_medico FROM assegnazioni_medici WHERE id_paziente = ?");
$query->bind_param("i", $idPaziente);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Medico non assegnato al paziente']);
    exit;
}

$row = $result->fetch_assoc();
$idMedico = $row['id_medico'];

// Ora prendi i dati del medico dalla tabella utenti
$query2 = $conn->prepare("SELECT * FROM utenti WHERE id = ?");
$query2->bind_param("i", $idMedico);
$query2->execute();
$result2 = $query2->get_result();

if ($result2->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Medico non trovato']);
    exit;
}

$medico = $result2->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($medico);
