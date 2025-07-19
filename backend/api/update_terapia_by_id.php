<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controllo campi obbligatori
if (
    !$data ||
    !isset(
        $data['id_terapia'],
        $data['id_paziente'],
        $data['id_farmaco'],
        $data['numero_assunzioni'],
        $data['dose'],
        $data['data_inizio'],
        $data['data_fine'],
        $data['note']
    )
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

// Estrai dati
$idTerapia = $data['id_terapia'];
$idPaziente = $data['id_paziente'];
$idFarmaco = $data['id_farmaco'];
$numeroAssunzioni = $data['numero_assunzioni'];
$dose = $data['dose'];
$dataInizio = $data['data_inizio'];
$dataFine = $data['data_fine'];
$note = $data['note'];

// Inizia transazione
$conn->begin_transaction();

try {
    // 1. Aggiorna la tabella terapie
    $stmt = $conn->prepare("UPDATE terapie SET data_inizio = ?, data_fine = ?, note = ?, id_paziente = ? WHERE id = ?");
    if (!$stmt) {
        throw new Exception('Errore nella preparazione della query per aggiornare la terapia');
    }
    $stmt->bind_param("sssii", $dataInizio, $dataFine, $note, $idPaziente, $idTerapia);
    if (!$stmt->execute()) {
        throw new Exception('Errore nell\'aggiornamento della terapia');
    }
    $stmt->close();

    // 2. Aggiorna la tabella associazioni_farmaci
    $stmt2 = $conn->prepare("UPDATE associazioni_farmaci SET id_farmaco = ?, numero_assunzioni = ?, dose = ? WHERE id_terapia = ?");
    if (!$stmt2) {
        throw new Exception('Errore nella preparazione della query per aggiornare associazione farmaci');
    }
    $stmt2->bind_param("iiii", $idFarmaco, $numeroAssunzioni, $dose, $idTerapia);
    if (!$stmt2->execute()) {
        throw new Exception('Errore nell\'aggiornamento di associazioni_farmaci');
    }
    $stmt2->close();

    // Commit
    $conn->commit();

    echo json_encode(['success' => true]);

} catch (Exception $e) {
    $conn->rollback();
    http_response_code(500);
    echo json_encode(['error' => $e->getMessage()]);
}

$conn->close();