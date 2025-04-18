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

// Obtener los valores de búsqueda del JSON
$address = isset($input['address']) ? $input['address'] : '';
$token = isset($input['token']) ? $input['token'] : '';

// Verificar que los valores no estén vacíos
if (empty($address) || empty($token)) {
    die(json_encode(array("success" => false, "message" => "El 'address' y 'token' son requeridos")));
}

$textDecrypted = descifrarAES($token);
$newJson = json_decode($textDecrypted, true);
$username = $newJson["username"];

$sql = "SELECT * FROM puntos WHERE distrito LIKE ? OR estructura LIKE ? OR direccion LIKE ? OR codigo_id LIKE ?";
$stmt = $conn->prepare($sql);
$search_param = "%" . $address . "%";
$stmt->bind_param("ssss", $search_param, $search_param, $search_param, $search_param);

$stmt->execute();
$result = $stmt->get_result();

$data = array();
if ($result->num_rows > 0) {
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    $response = array("success" => true, "data" => $data, "message" => "Exitoso");
} else {
    $response = array("success" => false, "data" => [], "message" => "No se encontraron resultados");
}

// Insertar en la tabla historial
date_default_timezone_set('America/Lima');
$fecha_actual = (new DateTime())->format('Y-m-d H:i:s');
$cantidad = $result->num_rows;

$insert_sql = "INSERT INTO historial (user_id, fecha_actual, cantidad, valorBuscado) VALUES (?, ?, ?, ?)";
$insert_stmt = $conn->prepare($insert_sql);
$insert_stmt->bind_param("ssis", $username, $fecha_actual, $cantidad, $address);
$insert_stmt->execute();

// Cerrar las conexiones
$stmt->close();
$insert_stmt->close();
$conn->close();

// Devolver la respuesta
echo json_encode($response);

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
?>
