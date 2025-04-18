<?php
// Configuración de cabecera para permitir solicitudes desde cualquier origen
header("Access-Control-Allow-Origin: *");
header("Content-Type: application/json; charset=UTF-8");

// Datos de conexión a la base de datos
$servername = "localhost";
$username = "root";
$password = "";
$dbname = "aguabenditaupn";

// Función para obtener la conexión a la base de datos
function getConnection($servername, $username, $password, $dbname) {
    $conn = new mysqli($servername, $username, $password, $dbname);
    if ($conn->connect_error) {
        die(json_encode(['success' => false, 'message' => 'Conexión fallida: ' . $conn->connect_error]));
    }
    return $conn;
}

// Función para obtener los puntos de abastecimiento
function getPuntos($conn) {
    try {
        $sql = "SELECT x, y, distrito, estructura, direccion, codigo_id FROM puntos";
        $result = $conn->query($sql);

        $puntos = array();

        if ($result->num_rows > 0) {
            while($row = $result->fetch_assoc()) {
                $puntos[] = array(
                    "lat" => $row["x"],
                    "lon" => $row["y"],
                    "distrito" => $row["distrito"],
                    "estructura" => $row["estructura"],
                    "direccion" => $row["direccion"],
                    "codigo" => $row["codigo_id"]
                );
            }
            return ['success' => true, 'message' => 'Puntos de abastecimiento recuperados exitosamente.', 'data' => $puntos];
        } else {
            return ['success' => false, 'message' => 'No se encontraron puntos de abastecimiento.'];
        }
    } catch (Exception $e) {
        return ['success' => false, 'message' => 'Error al recuperar los puntos de abastecimiento: ' . $e->getMessage()];
    }
}

$conn = getConnection($servername, $username, $password, $dbname);

$response = getPuntos($conn);

echo json_encode($response);

// Cerrar la conexión
$conn->close();
?>
