<?php
header('Content-Type: application/json');

$host = 'localhost';
$dbname = 'aguabenditaupn';
$user = 'root';
$password = '';

$conn = new mysqli($host, $user, $password, $dbname);

// Verificar conexión
if ($conn->connect_error) {
    die(json_encode(array("success" => false, "message" => "Error en la conexión: " . $conn->connect_error)));
}

// Obtener datos de la solicitud POST
$data = json_decode(file_get_contents('php://input'), true);
$user = $data['username'];
$newPassword = $data['password'];
$keysecret = $data['keysecret'];

// Verificar si el usuario existe
$sql = "SELECT keysecret FROM users WHERE username = '$user'";
$result = $conn->query($sql);

if ($result->num_rows > 0) {
    $row = $result->fetch_assoc();
    $dbKeysecret = $row['keysecret'];
    
    if ($dbKeysecret == $keysecret) {

        $updateSql = "UPDATE users SET password = '$newPassword' WHERE username = '$user'";
        if ($conn->query($updateSql) === TRUE) {
            echo json_encode(array("success" => true, "message" => "Contraseña actualizada correctamente."));
        } else {
            echo json_encode(array("success" => false, "message" => "Error al actualizar la contraseña"));
        }
    } else {
        echo json_encode(array("success" => false, "message" => "La Clave Secreta No Coincide"));
    }
} else {
    echo json_encode(array("success" => false, "message" => "El Usuario No Existe."));
}

$conn->close();
?>
