<?php
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "aguabenditaupn";

$response = array();
$response['success'] = false; // Valor inicial de success
$response['message'] = ''; // Mensaje inicial

// Crear la conexión
$conn = new mysqli($servername, $username, $password, $dbname);

// Verificar la conexión
if ($conn->connect_error) {
    $response['message'] = "Conexión fallida: " . $conn->connect_error;
    echo json_encode($response);
    exit();
}

$sql = "SELECT distrito, codigo_id, estructura, direccion FROM puntos";
$result = $conn->query($sql);

$data = array();

if ($result->num_rows > 0) {
    // Recorrer los resultados y agruparlos por distrito
    while($row = $result->fetch_assoc()) {
        $distrito = $row["distrito"];
        if (!isset($data[$distrito])) {
            $data[$distrito] = array();
        }
        $data[$distrito][] = array(
            "codigo_id" => $row["codigo_id"],
            "estructura" => $row["estructura"],
            "direccion" => $row["direccion"]
        );
    }
    $response['success'] = true;
    $response['message'] = 'Datos obtenidos exitosamente';
    $response['data'] = $data;
} else {
    $response['message'] = 'No se encontraron resultados';
}

$conn->close();

// Imprimir los datos en formato JSON
header('Content-Type: application/json');
echo json_encode($response, JSON_PRETTY_PRINT);
?>