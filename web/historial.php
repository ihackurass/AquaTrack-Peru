<?php
header('Content-Type: application/json');
// Datos de conexión a la base de datos
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "aguabenditaupn";


// Crear conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die(json_encode(array("success" => false, "message" => "Error de conexión: " . $conn->connect_error)));
}

// Leer la entrada JSON desde la solicitud POST
$inputJSON = file_get_contents('php://input');
$input = json_decode($inputJSON, true);

// Obtener el valor de búsqueda del JSON
$token = isset($input['token']) ? $input['token'] : '';

$textDecrypted = descifrarAES($token);
$newJson = json_decode($textDecrypted, true);
$username = $newJson["username"];

// Preparar la consulta SQL, ordenando por fecha en orden ascendente
$sql = "SELECT * FROM historial WHERE user_id = ? ORDER BY fecha_actual DESC";
$stmt = $conn->prepare($sql);
$stmt->bind_param("s", $username);

// Ejecutar la consulta
$stmt->execute();
$result = $stmt->get_result();

// Verificar si hay resultados
$data = array();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    $response = array("success" => true, "data" => $data, "message" => "Exitoso");
} else {
    $response = array("success" => false, "data" => [], "message" => "No se encontraron resultados");
}

// Cerrar la conexión
$stmt->close();
$conn->close();

function descifrarAES($token) {
    $secret_key = "AGUABENDITA2024UPNAGUABENDITA000";

    $data = base64url_decode($token);

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

// Devolver la respuesta
echo json_encode($response);
?>
