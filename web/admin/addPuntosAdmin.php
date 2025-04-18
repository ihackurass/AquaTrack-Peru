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

// Obtener los datos del punto a insertar
$lat = isset($_POST['lat']) ? $_POST['lat'] : '';
$lon = isset($_POST['lon']) ? $_POST['lon'] : '';
$estructura = isset($_POST['estructura']) ? $_POST['estructura'] : '';
$codigo_id = isset($_POST['codigo_id']) ? $_POST['codigo_id'] : '';
$direccion = isset($_POST['direccion']) ? $_POST['direccion'] : '';
$distrito = isset($_POST['distrito']) ? $_POST['distrito'] : '';

if ($lat && $lon && $estructura && $codigo_id && $direccion && $distrito) {
    // Preparar la consulta SQL para insertar el nuevo punto
    $sql = "INSERT INTO puntos (x, y, estructura, codigo_id, direccion, distrito) VALUES (?, ?, ?, ?, ?, ?)";
    $stmt = $conn->prepare($sql);
    $stmt->bind_param("ddssss", $lat, $lon, $estructura, $codigo_id, $direccion, $distrito);

    // Ejecutar la consulta
    if ($stmt->execute()) {
        echo json_encode(array("success" => true, "message" => "Punto agregado exitosamente"));
    } else {
        echo json_encode(array("success" => false, "message" => "Error al agregar el punto"));
    }

    // Cerrar la declaración
    $stmt->close();
} else {
    echo json_encode(array("success" => false, "message" => "Faltan datos para agregar el punto"));
}

// Cerrar la conexión
$conn->close();
?>