<?php
// Configurar la respuesta como JSON
//header('Content-Type: application/json');

$servername = "localhost"; 
$username = "root";
$password = "";
$dbname = "aguabenditaupn";

$aesKey = "AGUABENDITA2024UPNAGUABENDITA000";

function getConnection($servername, $username, $password, $dbname) {
    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die(json_encode(['success' => false, 'message' => 'Conexión a la base de datos fallida: ' . $conn->connect_error]));
    }
    return $conn;
}

function getInputData() {
    $inputJSON = file_get_contents('php://input');
    return json_decode($inputJSON, true);
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

function verifyUser($conn, $username, $oldPassword) {
    $sql = "SELECT * FROM users WHERE username = ? AND password = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ss", $username, $oldPassword);
    $stmt->execute();
    $result = $stmt->get_result();
    return $result->fetch_assoc();
}

// Función para actualizar nombre o email
function updateNameOrEmail($conn, $username, $name, $email) {
    $updates = [];
    $params = [];
    $types = "";

    if ($name) {
        $updates[] = "nombre = ?";
        $params[] = $name;
        $types .= "s";
    }
    if ($email) {
        $updates[] = "correo = ?";
        $params[] = $email;
        $types .= "s";
    }

    if (!empty($updates)) {
        $sql = "UPDATE users SET " . implode(", ", $updates) . " WHERE username = ?";
        $params[] = $username;
        $types .= "s";
        
        $stmt = $conn->prepare($sql);
        $stmt->bind_param($types, ...$params);

        if ($stmt->execute()) {
            return true;
        } else {
            return false;
        }

        $stmt->close();
    }
    return false;
}

function updatePassword($conn, $username, $old_password, $new_password) {
    $sql = "SELECT password FROM users WHERE username = ?";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("s", $username);
    $stmt->execute();
    $stmt->bind_result($current_password);
    $stmt->fetch();
    $stmt->close();

    if ($old_password === $current_password) {
        $sql = "UPDATE users SET password = ? WHERE username = ?";
        $stmt = $conn->prepare($sql);
        $stmt->bind_param("ss", $new_password, $username);

        if ($stmt->execute()) {
            return true;
        } else {
            return false;
        }

        $stmt->close();
    } else {
        return false;
    }
}

$conn = getConnection($servername, $username, $password, $dbname);

$input = getInputData();

if (isset($input['token'])) {
    $token = $conn->real_escape_string($input['token']);
    $data = descifrarAES($token, $aesKey);
    $newJson = json_decode($data, true);
    $username = $newJson['username'];

    if ($username !== null) {
        $name = isset($input['name']) ? $conn->real_escape_string($input['name']) : null;
        $email = isset($input['email']) ? $conn->real_escape_string($input['email']) : null;
        $oldPassword = isset($input['oldPassword']) ? $conn->real_escape_string($input['oldPassword']) : null;
        $newPassword = isset($input['newPassword']) ? $conn->real_escape_string($input['newPassword']) : null;

        $updates = [];
        $errorMessages = [];

        if ($name !== null && !empty($name) || $email !== null && !empty($email) ) {
            if (updateNameOrEmail($conn, $username, $name, $email)) {
                if ($name !== null) $updates[] = "Nombre";
                if ($email !== null) $updates[] = "Correo";
            } else {
                $errorMessages[] = "Error Al Actualizar La Información.";
            }
        }

        if ( ( $oldPassword !== null && !empty($oldPassword) ) && ($newPassword !== null && !empty($newPassword) )) {
            if (updatePassword($conn, $username, $oldPassword, $newPassword)) {
                $updates[] = "Contraseña";
            } else {
                $errorMessages[] = "Error Al Actualizar La Contraseña.";
            }
        }

        if (count($errorMessages) > 0) {
            $response = ['success' => false, 'message' => implode(" \n", $errorMessages)];
        }
        elseif (count($updates) > 0) { 
            $response = ['success' => true, 'message' => 'Su ' .implode(", ", $updates).' Fue Actualizado.'];
        }
        else {
            $response = ['success' => false, 'message' => 'Datos incompletos.'];
        }
    } else {
        $response = ['success' => false, 'message' => 'Token inválido.'];
    }
} else {
    $response = ['success' => false, 'message' => 'Token es obligatorio.'];
}

echo json_encode($response);
$conn->close();


?>
