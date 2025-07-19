<?php
require 'server.php';

$input = file_get_contents('php://input');
$data = json_decode($input, true);

if (
    !$data || 
    !isset($data['id_sender'], $data['id_receiver'], $data['data_invio'], $data['ora_invio'], 
            $data['oggetto'], $data['corpo'], $data['tipo'], $data['letto'])
) {
    http_response_code(400);
    echo json_encode(['error' => 'Dati mancanti o invalidi']);
    exit;
}

$stmt = $conn->prepare("
    INSERT INTO messaggi 
    (id_sender, id_receiver, data_invio, ora_invio, oggetto, corpo, tipo, letto)
    VALUES (?, ?, ?, ?, ?, ?, ?, ?)
");
$stmt->bind_param("iissssss", 
    $data['id_sender'], 
    $data['id_receiver'], 
    $data['data_invio'], 
    $data['ora_invio'], 
    $data['oggetto'], 
    $data['corpo'], 
    $data['tipo'], 
    $data['letto']
);

if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento: ' . $stmt->error]);
}
?>
