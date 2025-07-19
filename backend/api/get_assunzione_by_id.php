<?php

require 'server.php';

if (!isset($_GET['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "id" mancante']);
    exit;
}

$idAssunzione = intval($_GET['id']);

$query = $conn->prepare("
    SELECT 
        assunzioni.id,
        assunzioni.data,
        assunzioni.id_associazione_farmaco,
        assunzioni.stato,
        associazioni_farmaci.dose,
        terapie.id_paziente
    FROM assunzioni
    JOIN associazioni_farmaci ON assunzioni.id_associazione_farmaco = associazioni_farmaci.id
    JOIN terapie ON associazioni_farmaci.id_terapia = terapie.id
    WHERE assunzioni.id = ? ORDER BY data DESC
");
$query->bind_param("i", $idAssunzione);
$query->execute();
$result = $query->get_result();

$assunzione = $result->fetch_assoc();

header('Content-Type: application/json');

if ($assunzione) {
    echo json_encode($assunzione);
} else {
    http_response_code(404);
    echo json_encode(['error' => 'Assunzione non trovata']);
}
