<?php

require 'server.php';

if (!isset($_GET['idAssociazioneFarmaco'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Parametro "idAssociazioneFarmaco" mancante']);
    exit;
}

$idAssociazioneFarmaco = intval($_GET['idAssociazioneFarmaco']);

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
    WHERE assunzioni.id_associazione_farmaco = ? ORDER BY data DESC
");
$query->bind_param("i", $idAssociazioneFarmaco);
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
    //echo json_encode(['error' => 'Nessuna assunzione trovata']);
}
