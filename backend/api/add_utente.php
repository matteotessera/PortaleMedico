<?php
require 'server.php';

// Legge il JSON in input
$input = file_get_contents('php://input');
$data = json_decode($input, true);

// Controlla che tutti i campi necessari siano presenti
$required = ['password', 'ruolo', 'nome', 'cognome', 'codFiscale', 'dataNascita', 'email', 'telefono', 'genere','indirizzo'];
foreach ($required as $field) {
    if (empty($data[$field])) {
        http_response_code(400);
        echo json_encode(['error' => "Campo '$field' mancante o vuoto"]);
        exit;
    }
}

// Assegna le variabili
$password = $data['password'];       // in produzione, ricordati di hasharla!
$ruolo = $data['ruolo'];
$nome = $data['nome'];
$cognome = $data['cognome'];
$codFiscale = $data['codFiscale'];
$dataNascita = $data['dataNascita'];
$email = $data['email'];
$telefono = $data['telefono'];
$genere = $data['genere'];
$indirizzo = $data['indirizzo'];

// Prepara la query per inserire i dati nella tabella utenti
$stmt = $conn->prepare("INSERT INTO utenti (password, ruolo, nome, cognome, cod_fiscale, data_nascita, email, telefono, genere, indirizzo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

if (!$stmt) {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nella preparazione della query']);
    exit;
}

// Bind dei parametri, tutti stringhe
$stmt->bind_param("ssssssssss", password_hash($password,PASSWORD_DEFAULT), $ruolo, $nome, $cognome, $codFiscale, $dataNascita, $email, $telefono, $genere, $indirizzo);

// Esecuzione
if ($stmt->execute()) {
    echo json_encode(['success' => true, 'id' => $stmt->insert_id]);
} else {
    http_response_code(500);
    echo json_encode(['error' => 'Errore nell\'inserimento']);
}

$stmt->close();
$conn->close();
