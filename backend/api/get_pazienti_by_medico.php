<?php

require 'server.php';

// Controlla che idMedico sia stato passato come parametro GET
if (!isset($_GET['idMedico'])) {
    http_response_code(400); // Bad Request
    echo json_encode(['error' => 'Parametro idMedico mancante']);
    exit;
}

$idMedico = $_GET['idMedico'];

// Query per selezionare tutti i pazienti associati a idMedico specificato
$query = $conn->prepare("
    SELECT u.* FROM utenti u
    INNER JOIN assegnazioni_medici a ON u.id = a.id_paziente
    WHERE a.id_medico = ? AND u.ruolo = 'paziente'
");

$query->bind_param('i', $idMedico);
$query->execute();
$result = $query->get_result();

if ($result->num_rows === 0) {
    http_response_code(404);
    echo json_encode(['error' => 'Nessun paziente associato a questo medico']);
    exit;
}

$utenti = [];

while ($row = $result->fetch_assoc()) {
    $utenti[] = $row;
}

header('Content-Type: application/json');
echo json_encode($utenti);
