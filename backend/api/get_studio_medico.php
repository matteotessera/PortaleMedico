<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$idMedico = intval($_GET['id']);

// Cerca il medico assegnato al paziente
$query = $conn->prepare("SELECT id_studio FROM assegnazioni_studi WHERE id_medico = ?");
$query->bind_param("i", $idMedico);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Studio non assegnato al Medico']);
    exit;
}

$row = $result->fetch_assoc();
$idStudio = $row['id_studio'];

// Ora prendi i dati del medico dalla tabella utenti
$query2 = $conn->prepare("SELECT * FROM studi WHERE id = ?");
$query2->bind_param("i", $idStudio);
$query2->execute();
$result2 = $query2->get_result();

if ($result2->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Studio non trovato']);
    exit;
}

$medico = $result2->fetch_assoc();

header('Content-Type: application/json');
echo json_encode($medico);
