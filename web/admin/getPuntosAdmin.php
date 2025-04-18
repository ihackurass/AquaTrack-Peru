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

// Preparar la consulta SQL para obtener todos los puntos
$sql = "SELECT x AS lat, y AS lon, distrito,estructura, codigo_id,direccion FROM puntos";
$result = $conn->query($sql);

// Verificar si hay resultados
if ($result->num_rows > 0) {
    $data = array();
    while ($row = $result->fetch_assoc()) {
        $data[] = $row;
    }
    echo json_encode(array("success" => true, "message" => $data));
} else {
    echo json_encode(array("success" => false, "message" => "No se encontraron resultados"));
}

// Cerrar la conexión
$conn->close();
?>
