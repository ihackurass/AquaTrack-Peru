<?php

// Conexión a la base de datos (ejemplo)
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "aguabenditaupn";

// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

// Recibir datos JSON del cuerpo de la solicitud
$json_data = file_get_contents('php://input');
$data = json_decode($json_data);

// Obtener datos del JSON
$username = $data->username;
$password = $data->password;
$correo = $data->correo;
$keysecret = $data->keysecret;

$stmt = $conn->prepare("SELECT * FROM users WHERE username = ?");
$stmt->bind_param("s", $username);
$stmt->execute();
$result = $stmt->get_result();

if ($result->num_rows > 0) {
    // El usuario ya existe
    $response = array("success" => false, "message" => "El Usuario Ya Esta Registrado");
} else {
    // Insertar nuevo usuario
    $stmt = $conn->prepare("INSERT INTO users (username, password, correo,keysecret) VALUES (?, ?, ?,?) ");
    $stmt->bind_param("ssss", $username, $password, $correo,$keysecret);

    if ($stmt->execute()) {
        $response = array("success" => true, "message" => "Registro Exitoso");
    } else {
        $response = array("success" => false, "message" => "Error al registrar usuario");
    }
}

// Enviar respuesta JSON
header('Content-Type: application/json');
echo json_encode($response);

// Cerrar conexión
$conn->close();

?>