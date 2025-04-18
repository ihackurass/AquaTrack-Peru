<?php

// Verificar si se recibió una solicitud POST
if ($_SERVER["REQUEST_METHOD"] == "POST") {

    // Obtener los valores del POST
    $json_data = file_get_contents('php://input');
    $data = json_decode($json_data);
    $codigoID = isset($data->codigoID) ? $data->codigoID : '';
    $type = isset($data->type) ? $data->type : '';
    $token = isset($data->token) ? $data->token : '';

    $textDecrypted = descifrarAES($token);
    $newJson = json_decode($textDecrypted,true);
    $username = $newJson["username"];

    // Validar que se recibieron todos los parámetros necesarios
    if (empty($codigoID) || empty($type) || empty($username)) {
        $response['success'] = false;
        $response['message'] = "Error: Falta de informacion.";
        echo json_encode($response);
        exit;
    }

    define('DB_SERVER', 'localhost');
    define('DB_USERNAME', 'root');
    define('DB_PASSWORD', '');
    define('DB_NAME', 'aguabenditaupn');

    function conectar() {
        $conexion = new mysqli(DB_SERVER, DB_USERNAME, DB_PASSWORD, DB_NAME);
        if ($conexion === false) {
            die("Error: No se pudo conectar a la base de datos." . $conexion->connect_error);
        }
        return $conexion;
    }

    function agregarFavorito($codigoID, $username) {
        $conexion = conectar();
        $codigoID = $conexion->real_escape_string($codigoID);
        $username = $conexion->real_escape_string($username);
        $query = "INSERT INTO favoritos (codigo_id, user_id) VALUES ('$codigoID', '$username')";
        if ($conexion->query($query) === true) {
            return true;
        } else {
            return false;
        }
        $conexion->close();
    }

    function eliminarFavorito($codigoID, $username) {
        $conexion = conectar();
        $codigoID = $conexion->real_escape_string($codigoID);
        $username = $conexion->real_escape_string($username);
        $query = "DELETE FROM favoritos WHERE codigo_id = '$codigoID' AND user_id = '$username'";
        if ($conexion->query($query) === true) {
            return true;
        } else {
            return false;
        }
        $conexion->close();
    }

    // Verificar el valor de 'type' y realizar la acción correspondiente
    if ($type == 'add') {
        $resultado = agregarFavorito($codigoID, $username);
        if ($resultado) {
            $response['success'] = true;
            $response['message'] = "Punto agregado como favorito.";
        } else {
            $response['success'] = false;
            $response['message'] = "Error al agregar como favorito.";
        }
    } elseif ($type == 'delete') {
        $resultado = eliminarFavorito($codigoID, $username);
        if ($resultado) {
            $response['success'] = true;
            $response['message'] = "Punto eliminado de favorito.";
        } else {
            $response['success'] = false;
            $response['message'] = "Error al eliminar de favorito.";
        }
    } else {
        $response['success'] = false;
        $response['message'] = "Acción no válida";
    }

    header('Content-Type: application/json');
    echo json_encode($response);

} else {
    echo json_encode(array("success" => false, "message" => "La Solicitud Debe Ser POST"));
}
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
