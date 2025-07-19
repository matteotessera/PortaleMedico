<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (!$data || !isset($data['id'])) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$id = intval($data['id']);

// Step 1: Elimina dalla tabella assunzioni dove id_associazione_farmaco corrisponde a id_farmaco
$stmt1 = $conn->prepare("DELETE FROM assunzioni WHERE id_associazione_farmaco IN (SELECT id_farmaco FROM associazioni_farmaci WHERE id_farmaco = ?)");
$stmt1->bind_param("i", $id);
if (!$stmt1->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella assunzioni']);
    exit;
}

// Step 2: Elimina dalla tabella associazione_farmaci dove id_farmaco corrisponde al farmaco da eliminare
$stmt2 = $conn->prepare("DELETE FROM associazioni_farmaci WHERE id_farmaco = ?");
$stmt2->bind_param("i", $id);
if (!$stmt2->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella associazione_farmaci']);
    exit;
}

// Step 3: Elimina il farmaco dalla tabella farmaci
$stmt3 = $conn->prepare("DELETE FROM farmaci WHERE id = ?");
$stmt3->bind_param("i", $id);
if (!$stmt3->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella farmaci']);
    exit;
}

// Step 4: Controlla se ci sono terapie senza farmaci associati
$stmt4 = $conn->prepare("SELECT t.id FROM terapie t LEFT JOIN associazioni_farmaci af ON af.id_terapia = t.id WHERE af.id_farmaco IS NULL");
$stmt4->execute();
$result4 = $stmt4->get_result();

// Se ci sono terapie senza farmaci associati, cancellale
while ($row = $result4->fetch_assoc()) {
    $therapyId = $row['id'];
    $stmt5 = $conn->prepare("DELETE FROM terapie WHERE id = ?");
    $stmt5->bind_param("i", $therapyId);
    if (!$stmt5->execute()) {
        http_response_code(500);
        echo json_encode(['error' => 'Errore nell\'eliminazione della terapia senza farmaci associati']);
        exit;
    }
}

// Risposta di successo
http_response_code(200);
echo json_encode(['success' => true, 'id' => $id]);
