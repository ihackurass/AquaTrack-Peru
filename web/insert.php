<?php
// Configuración de la conexión a la base de datos
$host = 'localhost';
$dbname = 'aguabenditaupn';
$user = 'root';
$password = '';

// Conectar a la base de datos MySQL
$conn = new mysqli($host, $user, $password, $dbname);

if ($conn->connect_error) {
    die("Error de conexión: " . $conn->connect_error);
}

function eliminarTildes($cadena) {
    $originales = array('À', 'Á', 'Â', 'Ã', 'Ä', 'Å', 'à', 'á', 'â', 'ã', 'ä', 'å', 
                        'È', 'É', 'Ê', 'Ë', 'è', 'é', 'ê', 'ë', 
                        'Ì', 'Í', 'Î', 'Ï', 'ì', 'í', 'î', 'ï', 
                        'Ò', 'Ó', 'Ô', 'Õ', 'Ö', 'Ø', 'ò', 'ó', 'ô', 'õ', 'ö', 'ø', 
                        'Ù', 'Ú', 'Û', 'Ü', 'ù', 'ú', 'û', 'ü', 
                        'Ý', 'ý', 'ÿ', 'Ñ', 'ñ', 'Ç', 'ç');
    $modificadas = array('A', 'A', 'A', 'A', 'A', 'A', 'a', 'a', 'a', 'a', 'a', 'a', 
                         'E', 'E', 'E', 'E', 'e', 'e', 'e', 'e', 
                         'I', 'I', 'I', 'I', 'i', 'i', 'i', 'i', 
                         'O', 'O', 'O', 'O', 'O', 'O', 'o', 'o', 'o', 'o', 'o', 'o', 
                         'U', 'U', 'U', 'U', 'u', 'u', 'u', 'u', 
                         'Y', 'y', 'y', 'N', 'n', 'C', 'c');

    return str_replace($originales, $modificadas, $cadena);
}

// Ruta del archivo
$file_path = 'final.txt';  // Ajusta la ruta según sea necesario

// Leer y procesar el archivo
if (($handle = fopen($file_path, 'r')) !== FALSE) {
    while (($data = fgetcsv($handle, 1000, ";")) !== FALSE) {
        if (count($data) < 7 || empty(trim($data[4])) || empty(trim($data[5]))) {
            continue; 
        }

        $numero = $conn->real_escape_string(trim($data[0]));
        $distrito = strtoupper(eliminarTildes($conn->real_escape_string(trim($data[1]))));
        $estructura = strtoupper(eliminarTildes($conn->real_escape_string(trim($data[2]))));
        $nombre = strtoupper(eliminarTildes($conn->real_escape_string(trim($data[3]))));
        $x = $conn->real_escape_string(trim($data[4]));
        $y = $conn->real_escape_string(trim($data[5]));
        $direccion = strtoupper(eliminarTildes($conn->real_escape_string(trim($data[6]))));

        // Intercambiar los valores de estructura y nombre si estructura es Rebombeo, Reservorio o Pozo
        if (in_array($estructura, array('REBOMBEO', 'RESERVORIO', 'POZO','RESERVORIO.','SURTIDOR','SECTOR 329','HIDRANTE DE EMERG.'))) {
            $temp = $estructura;
            $estructura = $nombre;
            $nombre = $temp;
        }

        // Verificar si ya existe una fila con los mismos valores de estructura y nombre
        $check_sql = "SELECT COUNT(*) as count FROM puntos WHERE estructura = '$estructura' AND codigo_id = '$nombre'";
        $result = $conn->query($check_sql);
        $row = $result->fetch_assoc();

        if ($row['count'] == 0) {
            // Insertar los datos en la tabla puntos si no hay duplicados
            $sql = "INSERT INTO puntos (distrito, estructura, direccion, codigo_id, x, y)
                    VALUES ('$distrito', '$nombre', '$direccion', '$estructura', '$x', '$y')";

            if (!$conn->query($sql)) {
                echo "Error al insertar datos: " . $conn->error . "\n";
            }
        }
    }
    fclose($handle);
}

// Cerrar la conexión
$conn->close();

echo "Datos insertados correctamente.";
?>
