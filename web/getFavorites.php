<?php

$servername = "localhost";
$usernameDB = "root";
$passwordDB = "";
$dbname = "aguabenditaupn";

function conectarDB($servername, $usernameDB, $passwordDB, $dbname) {
    $conn = new mysqli($servername, $usernameDB, $passwordDB, $dbname);
    if ($conn->connect_error) {
        die("Error de conexión: " . $conn->connect_error);
    }
    return $conn;
}

function descifrarAES($token, $secret_key) {
    $data = base64url_decode($token);
    $iv = substr($data, 0, 16);
    $cipherText = substr($data, 16);
    return openssl_decrypt($cipherText, 'aes-256-cbc', $secret_key, OPENSSL_RAW_DATA, $iv);
}

function base64url_decode($data) {
    $data = strtr($data, '-_', '+/');
    $padding = 4 - (strlen($data) % 4);
    if ($padding !== 4) {
        $data .= str_repeat('=', $padding);
    }
    return base64_decode($data);
}

function getCodigoIdPorUsername($username, $conn) {
    $sql = "SELECT p.codigo_id, p.distrito, p.estructura, p.direccion
FROM puntos AS p
WHERE p.codigo_id IN (
    SELECT f.codigo_id
    FROM favoritos AS f
    WHERE f.user_id = ?
);
";
    
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $result = $stmt->get_result();

    $detalles = [];
    while ($row = $result->fetch_assoc()) {
        $detalles[] = [
            'codigo_id' => $row['codigo_id'],
            'distrito' => $row['distrito'],
            'estructura' => $row['estructura'],
            'direccion' => $row['direccion']
        ];
    }
    $stmt->close();
    return $detalles;
}


// Verificación de la solicitud POST
if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $json_data = file_get_contents('php://input');
    $data = json_decode($json_data);
    $token = isset($data->token) ? $data->token : '';

    if (isset($token)) {
        $secret_key = "AGUABENDITA2024UPNAGUABENDITA000";
        $textDecrypted = descifrarAES($token, $secret_key);
        $newJson = json_decode($textDecrypted, true);

        $conn = conectarDB($servername, $usernameDB, $passwordDB, $dbname);
        $username = $newJson['username'];
        $codigoIds = getCodigoIdPorUsername($username, $conn);
        $conn->close();

        echo json_encode([
                'success' => true,
                'message' => 'Exitoso',
                'username' => $username,
                'favoritos_list' => $codigoIds
        ]);
    } else {
        echo json_encode(['success' => false, 'message' => 'Error: Falta de informacion']);
    }
} else {
    echo json_encode(['success' => false, 'message' => 'Método no permitido, se espera una solicitud POST']);
}
?>
