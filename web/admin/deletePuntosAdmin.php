<?php
header('Content-Type: application/json');

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

// Obtener el ID del punto a eliminar
$punto_id = isset($_POST['codigo_id']) ? $_POST['codigo_id'] : '';
$estructura = isset($_POST['estructura']) ? $_POST['estructura'] : '';

if ($punto_id && $estructura) {
    // Preparar la consulta SQL para eliminar el punto
    $sql = "DELETE FROM puntos WHERE codigo_id = ? AND estructura = ?";
    $stmt = $conn->prepare($sql);

    // Vincular los parámetros de manera segura
    $stmt->bind_param("ss", $punto_id, $estructura);

    // Ejecutar la consulta
    if ($stmt->execute()) {
        if ($stmt->affected_rows > 0) {
            echo json_encode(array("success" => true, "message" => "Punto eliminado exitosamente"));
        } else {
            echo json_encode(array("success" => false, "message" => "No se encontró el punto para eliminar"));
        }
    } else {
        echo json_encode(array("success" => false, "message" => "Error al eliminar el punto"));
    }

    // Cerrar la declaración
    $stmt->close();
} else {
    echo json_encode(array("success" => false, "message" => "ID del punto no proporcionado o estructura no proporcionada"));
}

// Cerrar la conexión
$conn->close();
?>
