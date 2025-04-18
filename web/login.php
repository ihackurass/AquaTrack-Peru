<?php

// Configuración de la base de datos (ejemplo)
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "aguabenditaupn";

$conn = new mysqli($servername, $username, $password, $dbname);

if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}

$json_data = file_get_contents('php://input');
$data = json_decode($json_data);

$username = $data->username;
$password = $data->password;

$sql = "SELECT * FROM users WHERE username='$username' AND password='$password'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $token_payload = array(
        "username" => $username
    );
    
    // Generar token
    $token = cifrarAES(json_encode($token_payload));

    // Crear respuesta JSON con el token
    $response = array(
        "success" => true,
        "message" => "Inicio De Sesion Exitoso",
        "token" => $token
    );
} else {
    $response = array("success" => false,"message" => "Usuario y/o Contraseña Incorrectos");
}

// Enviar respuesta JSON
header('Content-Type: application/json');
echo json_encode($response);

// Cerrar conexión
$conn->close();


// Función para cifrar usando AES-256-CBC
function cifrarAES($datos) {
    $secret_key = "AGUABENDITA2024UPNAGUABENDITA000";
    $iv = openssl_random_pseudo_bytes(16);
    
    $cipherText = openssl_encrypt($datos, 'aes-256-cbc', $secret_key, OPENSSL_RAW_DATA, $iv);
    
    $token = base64url_encode($iv . $cipherText);
    
    return $token;
}

function base64url_encode($data) {
    return rtrim(strtr(base64_encode($data), '+/', '-_'), '=');
}



?>