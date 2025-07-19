<?php
require 'server.php';

$result = $conn->query("SELECT * FROM messaggi");

$messaggi = [];

while ($row = $result->fetch_assoc()) {
    $messaggi[] = $row;
}

echo json_encode($messaggi);
?>