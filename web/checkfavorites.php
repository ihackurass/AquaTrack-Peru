<?php

$servername = "localhost";
$username = "root";
$password = "";
$dbname = "aguabenditaupn";

// Conexión a la base de datos
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar la conexión
if ($conn->connect_error) {
    die("Conexión fallida: " . $conn->connect_error);
}

// Obtener los datos del POST
$json_data = file_get_contents('php://input');
$data = json_decode($json_data);

$token = $data->token;
$codigoId = $data->codigoID;

$textDecrypted = descifrarAES($token);
$newJson = json_decode($textDecrypted,true);

$usernameByToken = $newJson["username"];

$sql = "SELECT * FROM favoritos WHERE user_id='$usernameByToken' AND codigo_id='$codigoId'";
$result = $conn->query($sql);


if ($result->num_rows > 0) {
    $response = array(
        "isFavorite" => true
    );
} else {
    // El producto no está en favoritos
    $response = array(
        "isFavorite" => false
    );
}

// Cerrar la conexión
$conn->close();

// Enviar respuesta en formato JSON
header('Content-Type: application/json');
echo json_encode($response);

function descifrarAES($token) {
    $secret_key = "AGUABENDITA2024UPNAGUABENDITA000";

    // Decodificar el token
    $data = base64url_decode($token);

    // Extraer el IV y el texto cifrado
    $iv = substr($data, 0, 16);
    $cipherText = substr($data, 16);

    // Desencriptar
    $originalText = openssl_decrypt($cipherText, 'aes-256-cbc', $secret_key, OPENSSL_RAW_DATA, $iv);

    return $originalText;
}

function base64url_decode($data) {
    $data = strtr($data, '-_', '+/');
    $padding = 4 - (strlen($data) % 4);
    if ($padding !== 4) {
        $data .= str_repeat('=', $padding);
    }
    return base64_decode($data);
}
?>