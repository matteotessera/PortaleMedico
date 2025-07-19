<?php

require 'server.php';

if (!isset($_GET['idPaziente'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "idPaziente" mancante']);
    exit;
}

$idPaziente = intval($_GET['idPaziente']);

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
    WHERE terapie.id_paziente = ? ORDER BY data DESC
");
$query->bind_param("i", $idPaziente);
$query->execute();
$result = $query->get_result();

$assunzioni = [];

while ($row = $result->fetch_assoc()) {
    $assunzioni[] = $row;
}

header('Content-Type: application/json');

if (count($assunzioni) > 0) {
    echo json_encode($assunzioni);
} else {
    http_response_code(404);
    echo json_encode(['error' => 'Nessuna assunzione trovata']);
}
