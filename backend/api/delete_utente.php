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

// Step 1: Elimina le righe in assegnazione_studi dove id_medico = id
$stmt1 = $conn->prepare("DELETE FROM assegnazioni_studi WHERE id_medico = ?");
$stmt1->bind_param("i", $id);
if (!$stmt1->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella assegnazione_studi']);
    exit;
}

// Step 2: Elimina le righe in accessi dove id_utente = id
$stmt2 = $conn->prepare("DELETE FROM accessi WHERE id_utente = ?");
$stmt2->bind_param("i", $id);
if (!$stmt2->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella accessi']);
    exit;
}

// Step 3: Elimina le righe in assegnazioni_medici dove id_medico = id o id_paziente = id
$stmt3 = $conn->prepare("DELETE FROM assegnazioni_medici WHERE id_medico = ? OR id_paziente = ?");
$stmt3->bind_param("ii", $id, $id);
if (!$stmt3->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella assegnazioni_medici']);
    exit;
}

// Step 4: Elimina le righe in info_pazienti dove id_paziente = id
$stmt4 = $conn->prepare("DELETE FROM info_pazienti WHERE id_paziente = ?");
$stmt4->bind_param("i", $id);
if (!$stmt4->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella info_pazienti']);
    exit;
}

// Step 5: Elimina le righe in messaggi dove id_sender = id o id_reciver = id
$stmt5 = $conn->prepare("DELETE FROM messaggi WHERE id_sender = ? OR id_receiver = ?");
$stmt5->bind_param("ii", $id, $id);
if (!$stmt5->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella messaggi']);
    exit;
}

// Step 6: Elimina le righe in modifiche dove id_utente = id
$stmt6 = $conn->prepare("DELETE FROM modifiche WHERE id_utente = ?");
$stmt6->bind_param("i", $id);
if (!$stmt6->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella modifiche']);
    exit;
}

// Step 7: Elimina le righe in patologie, rilevazioni, sintomi, sintomi_concomitanti, terapie_concomitanti dove id_paziente = id
$stmt7 = $conn->prepare("DELETE FROM patologie WHERE paziente_id = ?");
$stmt7->bind_param("i", $id);
if (!$stmt7->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella patologie']);
    exit;
}

$stmt8 = $conn->prepare("DELETE FROM rilevazioni WHERE id_paziente = ?");
$stmt8->bind_param("i", $id);
if (!$stmt8->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella rilevazioni']);
    exit;
}

$stmt9 = $conn->prepare("DELETE FROM sintomi WHERE id_paziente = ?");
$stmt9->bind_param("i", $id);
if (!$stmt9->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella sintomi']);
    exit;
}

$stmt10 = $conn->prepare("DELETE FROM sintomi_concomitanti WHERE paziente_id = ?");
$stmt10->bind_param("i", $id);
if (!$stmt10->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella sintomi_concomitanti']);
    exit;
}

$stmt11 = $conn->prepare("DELETE FROM terapie_concomitanti WHERE paziente_id = ?");
$stmt11->bind_param("i", $id);
if (!$stmt11->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella terapie_concomitanti']);
    exit;
}

// Step 8: Elimina le righe in associazione_farmaci dove id_terapia è nella tabella terapie con id_paziente = id
$stmt12 = $conn->prepare("DELETE FROM associazioni_farmaci WHERE id_terapia IN (SELECT id FROM terapie WHERE id_paziente = ?)");
$stmt12->bind_param("i", $id);
if (!$stmt12->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella associazione_farmaci']);
    exit;
}

// Step 9: Elimina le righe in terapie dove id_paziente = id
$stmt13 = $conn->prepare("DELETE FROM terapie WHERE id_paziente = ?");
$stmt13->bind_param("i", $id);
if (!$stmt13->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella terapie']);
    exit;
}

// Step 10: Elimina l'utente dalla tabella utenti
$stmt14 = $conn->prepare("DELETE FROM utenti WHERE id = ?");
$stmt14->bind_param("i", $id);
if (!$stmt14->execute()) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'eliminazione dalla tabella utenti']);
    exit;
}

// Risposta di successo
http_response_code(200);
echo json_encode(['success' => true, 'id' => $id]);
