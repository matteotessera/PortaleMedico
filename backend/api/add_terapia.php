<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data ||
    !isset(
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

$idPaziente = $data['id_paziente'];
$idFarmaco = $data['id_farmaco'];
$numeroAssunzioni = $data['numero_assunzioni'];
$dose = $data['dose'];
$dataInizio = $data['data_inizio'];
$dataFine = $data['data_fine'];
$note = $data['note'];

// Inizio transazione per sicurezza
$conn->begin_transaction();

try {
    // 1. Inserisci nella tabella terapie (solo id_paziente, data_inizio, data_fine, note)
    $stmt = $conn->prepare("INSERT INTO terapie (data_inizio, data_fine, note, id_paziente) VALUES (?, ?, ?, ?)");
    if (!$stmt) {
        throw new Exception('Errore nella preparazione della query per terapie');
    }
    $stmt->bind_param("sssi", $dataInizio, $dataFine, $note, $idPaziente);
    if (!$stmt->execute()) {
        throw new Exception('Errore nell\'inserimento della terapia');
    }

    $idTerapia = $stmt->insert_id;
    $stmt->close();

    // 2. Inserisci nella tabella associazioni_farmaci (id_terapia, id_farmaco, numero_assunzioni, dose)
    $stmt2 = $conn->prepare("INSERT INTO associazioni_farmaci (id_terapia, id_farmaco, numero_assunzioni, dose) VALUES (?, ?, ?, ?)");
    if (!$stmt2) {
        throw new Exception('Errore nella preparazione della query per associazioni_farmaci');
    }
    $stmt2->bind_param("iiii", $idTerapia, $idFarmaco, $numeroAssunzioni, $dose);
    if (!$stmt2->execute()) {
        throw new Exception('Errore nell\'inserimento associazioni_farmaci');
    }

    $stmt2->close();

    // Commit della transazione
    $conn->commit();

    echo json_encode(['success' => true, 'id_terapia' => $idTerapia]);

} catch (Exception $e) {
    $conn->rollback();
    http_response_code(500);
    echo json_encode(['error' => $e->getMessage()]);
}

$conn->close();

