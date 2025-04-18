<!DOCTYPE html>
<html>
<head>
    <title>Mapa con Leaflet y MySQL</title>
    <meta charset="utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">

    <!-- Incluir Leaflet CSS -->
    <link rel="stylesheet" href="https://unpkg.com/leaflet/dist/leaflet.css" />
    <!-- Incluir Leaflet JavaScript -->
    <script src="https://unpkg.com/leaflet/dist/leaflet.js"></script>
    <!-- Incluir jQuery -->
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>

    <style>
        html, body {
            height: 100%;
            margin: 0;
        }
        #map {
            height: 100%;
            width: 100%;
            position: relative;
            z-index: 1;
        }
        .delete-button {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 10px;
            font-size: 14px;
            font-weight: bold;
            color: #ffffff;
            background-color: #ff0000;
            border: none;
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .delete-button:hover {
            background-color: #cc0000;
        }
        .modal {
            display: none; 
            position: fixed; 
            z-index: 2; 
            left: 0;
            top: 0;
            width: 100%; 
            height: 100%; 
            overflow: auto; 
            background-color: rgb(0,0,0); 
            background-color: rgba(0,0,0,0.4); 
            padding-top: 60px; 
        }
        .modal-content {
            background-color: #fefefe;
            margin: 5% auto; 
            padding: 20px;
            border: 1px solid #888;
            width: 300px; 
            border-radius: 10px;
        }
        .close {
            color: #aaa;
            float: right;
            font-size: 28px;
            font-weight: bold;
        }
        .close:hover,
        .close:focus {
            color: black;
            text-decoration: none;
            cursor: pointer;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            border-radius: 8px;
            box-sizing: border-box;
            border: 1px solid #ccc;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            margin-top: 10px;
            font-size: 14px;
            font-weight: bold;
            color: #ffffff;
            background-color: #007bff;
            border: none;
            border-radius: 8px;
            text-align: center;
            cursor: pointer;
            text-decoration: none;
            transition: background-color 0.3s ease;
        }
        .btn:hover {
            background-color: #0056b3;
        }
    </style>
</head>
<body>
    <div id="map"></div>

    <!-- Modal para agregar nuevo punto -->
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <h2>Agregar Nuevo Punto</h2>
            <div class="form-group">
                <label for="estructura">Estructura:</label>
                <input type="text" id="estructura" name="estructura">
            </div>
            <div class="form-group">
                <label for="codigo_id">Código ID:</label>
                <input type="text" id="codigo_id" name="codigo_id">
            </div>
            <div class="form-group">
                <label for="direccion">Dirección:</label>
                <input type="text" id="direccion" name="direccion">
            </div>
            <div class="form-group">
                <label for="distrito">Distrito:</label>
                <input type="text" id="distrito" name="distrito">
            </div>
            <button class="btn" id="savePoint">Guardar</button>
        </div>
    </div>

    <script>
        var map = L.map('map').setView([-12.0464, -77.0428], 13); // Coordenadas iniciales de Lima, Perú

        L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
            attribution: '&copy; <a href="https://www.openstreetmap.org/copyright">OpenStreetMap</a> contributors'
        }).addTo(map);

        var currentMarkers = [];
        var newPointLat, newPointLon;

        function addMarkers(data) {
            currentMarkers.forEach(function(marker) {
                map.removeLayer(marker);
            });
            currentMarkers = [];

            data.forEach(function(marker) {
                var popupContent = "Distrito: " + marker.distrito +"<br>Estructura: " + marker.estructura + "<br>Código: " + marker.codigo_id + 
                    "<br>Direccion: " + marker.direccion + "<br><button class='delete-button' onclick='deleteMarker(\"" + marker.codigo_id + "\", \"" + marker.estructura + "\")'>Eliminar</button>";
                var newMarker = L.marker([marker.lat, marker.lon]).addTo(map)
                    .bindPopup(popupContent);
                newMarker.codigo_id = marker.codigo_id; // Guardar el ID del marcador
                newMarker.estructura = marker.estructura; // Guardar la estructura del marcador
                currentMarkers.push(newMarker);
            });
        }

        function updateMarkers() {
            $.ajax({
                url: 'getPuntosAdmin.php',
                method: 'GET',
                dataType: 'json',
                success: function(data) {
                    if (data.success) {
                        addMarkers(data.message);
                    } else {
                        alert("No se encontraron resultados");
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Error al obtener los datos:', textStatus, errorThrown);
                }
            });
        }

        function deleteMarker(markerId, estructuraId) {
            if (confirm("¿Estás seguro de que deseas eliminar este punto?")) {
                $.ajax({
                    url: 'deletePuntosAdmin.php',
                    method: 'POST',
                    data: { codigo_id: markerId, estructura: estructuraId },
                    success: function(data) {
                        if (data.success) {
                            currentMarkers = currentMarkers.filter(function(marker) {
                                if (marker.codigo_id === markerId && marker.estructura === estructuraId) {
                                    map.removeLayer(marker);
                                    return false;
                                }
                                return true;
                            });
                            alert("Punto eliminado exitosamente");
                        } else {
                            alert("Error al eliminar el punto: " + data.message);
                        }
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        console.error('Error al eliminar el punto:', textStatus, errorThrown);
                    }
                });
            }
        }

        function addNewPoint() {
            var estructura = $('#estructura').val();
            var codigo_id = $('#codigo_id').val();
            var direccion = $('#direccion').val();
            var distrito = $('#distrito').val();

            $.ajax({
                url: 'addPuntosAdmin.php',
                method: 'POST',
                data: {
                    lat: newPointLat,
                    lon: newPointLon,
                    estructura: estructura,
                    codigo_id: codigo_id,
                    direccion: direccion,
                    distrito: distrito
                },
                success: function(data) {
                    if (data.success) {
                        alert("Punto agregado exitosamente");
                        updateMarkers();
                        modal.style.display = "none";
                        clearModalFields();
                    } else {
                        alert("Error al agregar el punto: " + data.message);
                    }
                },
                error: function(jqXHR, textStatus, errorThrown) {
                    console.error('Error al agregar el punto:', textStatus, errorThrown);
                }
            });
        }

        setInterval(updateMarkers, 5000);
        updateMarkers();

        function clearModalFields() {
            $('#estructura').val('');
            $('#codigo_id').val('');
            $('#direccion').val('');
            $('#distrito').val('');
        }
        map.on('click', function(e) {
            var lat = e.latlng.lat;
            var lon = e.latlng.lng;
            var popupContent = "Latitud: " + lat + "<br>Longitud: " + lon;
            var popup = L.popup()
                .setLatLng(e.latlng)
                .setContent(popupContent)
                .openOn(map);
        });

        map.on('dblclick', function(e) {
            newPointLat = e.latlng.lat;
            newPointLon = e.latlng.lng;
            modal.style.display = "block";
        });

        var modal = document.getElementById("myModal");
        var span = document.getElementsByClassName("close")[0];
        span.onclick = function() {
            modal.style.display = "none";
        }
        window.onclick = function(event) {
            if (event.target == modal) {
                modal.style.display = "none";
            }
        }
        $('#savePoint').click(function() {
            addNewPoint();
        });
    </script>
</body>
</html>
