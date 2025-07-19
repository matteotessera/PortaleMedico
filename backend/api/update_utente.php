<?php
require 'server.php';

// Legge il JSON in input
$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controlla che l'id utente sia presente
if (empty($data['id'])) {
    http_response_code(400);
    echo json_encode(['error' => "Campo 'id' mancante o vuoto"]);
    exit;
}

// Campi aggiornabili (password facoltativa, se vuota non viene aggiornata)
$required = ['id', 'ruolo', 'nome', 'cognome', 'codFiscale', 'dataNascita', 'email', 'telefono', 'genere', 'indirizzo'];
foreach ($required as $field) {
    if (!isset($data[$field])) {
        http_response_code(400);
        echo json_encode(['error' => "Campo '$field' mancante"]);
        exit;
    }
}

$id = $data['id'];
$ruolo = $data['ruolo'];
$nome = $data['nome'];
$cognome = $data['cognome'];
$codFiscale = $data['codFiscale'];
$dataNascita = $data['dataNascita'];
$email = $data['email'];
$telefono = $data['telefono'];
$genere = $data['genere'];
$indirizzo = $data['indirizzo'];

// Se è stata fornita una password nuova, la hashamo
$password = null;
if (!empty($data['password'])) {
    $password = password_hash($data['password'], PASSWORD_DEFAULT);
}

if ($password !== null) {
    // Query con aggiornamento password
    $stmt = $conn->prepare("UPDATE utenti SET password = ?, ruolo = ?, nome = ?, cognome = ?, cod_fiscale = ?, data_nascita = ?, email = ?, telefono = ?, genere = ?, indirizzo = ? WHERE id = ?");
    if (!$stmt) {
        http_response_code(500);
        echo json_encode(['error' => 'Errore nella preparazione della query']);
        exit;
    }
    $stmt->bind_param("ssssssssssi", $password, $ruolo, $nome, $cognome, $codFiscale, $dataNascita, $email, $telefono, $genere, $indirizzo, $id);
} else {
    // Query senza aggiornamento password
    $stmt = $conn->prepare("UPDATE utenti SET ruolo = ?, nome = ?, cognome = ?, cod_fiscale = ?, data_nascita = ?, email = ?, telefono = ?, genere = ?, indirizzo = ? WHERE id = ?");
    if (!$stmt) {
        http_response_code(500);
        echo json_encode(['error' => 'Errore nella preparazione della query']);
        exit;
    }
    $stmt->bind_param("sssssssssi", $ruolo, $nome, $cognome, $codFiscale, $dataNascita, $email, $telefono, $genere, $indirizzo, $id);
}

// Esecuzione
if ($stmt->execute()) {
    echo json_encode(['success' => true, 'affected_rows' => $stmt->affected_rows]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'aggiornamento']);
}

$stmt->close();
$conn->close();