/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Entidades;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import javax.crypto.Cipher;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.List;
import javax.crypto.spec.IvParameterSpec;
import javax.swing.JOptionPane;
import org.json.JSONArray;
import org.json.JSONObject;

public class UserClient {

    private static final String SERVER_URL = "http://localhost/";
    private static final byte[] SECRET_KEY = "AGUABENDITA2024UPNAGUABENDITA000".getBytes();

    private static String accessToken = null;

    public static JSONObject register(String username, String password, String correo, String keysecret) {
        JSONObject result = new JSONObject();

        try {
            JSONObject json = new JSONObject();

            json.put("username", username);
            json.put("password", password);
            json.put("correo", correo);
            json.put("keysecret", keysecret);

            String response = sendPostRequest("register.php", json.toString());
            //System.out.print(response);
            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");

            result.put("success", success);
            result.put("message", message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.put("success", false);
            result.put("message", e.getMessage());

        }
        return result;
    }

    public static JSONObject login(String username, String password) {
        JSONObject result = new JSONObject();

        try {
            JSONObject json = new JSONObject();
            json.put("username", username);
            json.put("password", password);

            String response = sendPostRequest("login.php", json.toString());

            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");
            if (success) {
                accessToken = jsonResponse.getString("token");
            }
            result.put("success", success);
            result.put("message", message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.put("success", false);
            result.put("message", e.getMessage());

        }
        return result;
    }

    public static boolean isLoggedIn() {

        return accessToken != null && isTokenValid(accessToken);
    }

    private static byte[] base64url_decode(String input) {
        String base64 = input.replace('-', '+').replace('_', '/');
        switch (base64.length() % 4) {
            case 0:
                break;
            case 2:
                base64 += "==";
                break;
            case 3:
                base64 += "=";
                break;
            default:
                throw new IllegalArgumentException("String de base64url inválida");
        }
        return Base64.getDecoder().decode(base64);
    }

    private static String decifrarAES(String token, byte[] clave) {
        try {
            byte[] tokenBytes = base64url_decode(token);

            byte[] iv = new byte[16];
            byte[] cipherText = new byte[tokenBytes.length - 16];
            System.arraycopy(tokenBytes, 0, iv, 0, 16);
            System.arraycopy(tokenBytes, 16, cipherText, 0, tokenBytes.length - 16);

            SecretKey secretKey = new SecretKeySpec(clave, "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, secretKey, new IvParameterSpec(iv));

            byte[] decryptedBytes = cipher.doFinal(cipherText);
            return new String(decryptedBytes);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static boolean isTokenValid(String token) {
        String datosDecifrados = decifrarAES(token, SECRET_KEY);
        return datosDecifrados != null;
    }

    private static String sendPostRequest(String endpoint, String json) throws Exception {
        URL url = new URL(SERVER_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("POST");
        connection.setRequestProperty("Content-Type", "application/json");
        connection.setDoOutput(true);

        try (DataOutputStream outputStream = new DataOutputStream(connection.getOutputStream())) {
            outputStream.writeBytes(json);
            outputStream.flush();
        }

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }

        connection.disconnect();

        return response.toString();
    }

    private static String sendGetRequest(String endpoint) throws Exception {
        URL url = new URL(SERVER_URL + endpoint);
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();

        connection.setRequestMethod("GET");
        connection.setRequestProperty("Content-Type", "application/json");

        StringBuilder response = new StringBuilder();
        try (BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
            String line;
            while ((line = in.readLine()) != null) {
                response.append(line);
            }
        }

        connection.disconnect();

        return response.toString();
    }

    public static JSONObject PointFav(String codigo, String action) {
        JSONObject result = new JSONObject();

        try {
            JSONObject json = new JSONObject();
            json.put("codigoID", codigo);
            json.put("token", accessToken);

            String valorPost;
            if (action.equals("a")) {
                valorPost = "add";
            } else {
                valorPost = "delete";
            }
            json.put("type", valorPost);

            String response = sendPostRequest("favorites.php", json.toString());
            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");

            result.put("success", success);
            result.put("message", message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.put("success", false);
            result.put("message", e.getMessage());

        }
        return result;

    }

    public static JSONObject getPuntosByKey(String address) {
        JSONObject response = new JSONObject();
        JSONArray dataArray = new JSONArray();

        try {
            // Create JSON object to send
            JSONObject requestData = new JSONObject();
            requestData.put("address", address);
            requestData.put("token", accessToken);

            // Send POST request
            String jsonResponse = sendPostRequest("getPuntosByKey.php", requestData.toString());
            System.out.print(jsonResponse);
            JSONObject parsed = new JSONObject(jsonResponse);

            boolean success = parsed.getBoolean("success");
            String message = parsed.getString("message");
            JSONArray data = parsed.getJSONArray("data");
            if (success) {

                for (int i = 0; i < data.length(); i++) {
                    JSONObject jsonObject = data.getJSONObject(i);
                    double lat = jsonObject.getDouble("x");
                    double lon = jsonObject.getDouble("y");
                    String distrito = jsonObject.getString("distrito");
                    String estructura = jsonObject.getString("estructura");
                    String direccion = jsonObject.getString("direccion");
                    String codigo = jsonObject.getString("codigo_id");

                    JSONObject dataObject = new JSONObject();
                    dataObject.put("lat", lat);
                    dataObject.put("lon", lon);
                    dataObject.put("distrito", distrito);
                    dataObject.put("estructura", estructura);
                    dataObject.put("direccion", direccion);
                    dataObject.put("codigo", codigo);

                    dataArray.put(dataObject);
                }
            }

            response.put("success", success);
            response.put("message", message);
            response.put("datos", dataArray);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    public static boolean isFavorite(String codigo) {
        try {
            JSONObject json = new JSONObject();
            json.put("token", accessToken);
            json.put("codigoID", codigo);

            String response = sendPostRequest("checkfavorites.php", json.toString());
            //System.out.print(response);
            JSONObject jsonResponse = new JSONObject(response);
            return jsonResponse.getBoolean("isFavorite");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }

    }

    public static JSONObject getFavoritos() {
        JSONObject result = new JSONObject();

        try {
            JSONObject json = new JSONObject();
            json.put("token", accessToken);

            String response = sendPostRequest("getFavorites.php", json.toString());

            JSONObject jsonResponse = new JSONObject(response);

            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");
            String favoritosList = jsonResponse.optString("favoritos_list", "[]");
            //System.out.print(favoritosList);
            JSONArray favoritosArray = new JSONArray(favoritosList);
            List<List<String>> datosList = new ArrayList<>();
            for (int i = 0; i < favoritosArray.length(); i++) {
                JSONObject fav = favoritosArray.getJSONObject(i);
                List<String> details = List.of(
                        fav.getString("distrito"),
                        fav.getString("estructura"),
                        fav.getString("codigo_id"),
                        fav.getString("direccion")
                );
                datosList.add(details);
            }

            JSONArray datosJSONArray = new JSONArray();
            for (List<String> details : datosList) {
                datosJSONArray.put(new JSONArray(details));
            }

            result.put("success", success);
            result.put("message", message);
            result.put("favoritos_list", datosJSONArray);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        //System.out.print(result);
        return result;
    }

    public static JSONObject getPuntos() {
        JSONObject response = new JSONObject();
        JSONArray dataArray = new JSONArray();

        try {
            String jsonResponse = sendGetRequest("coordinates.php");

            JSONObject parsed = new JSONObject(jsonResponse);

            boolean success = parsed.getBoolean("success");
            String message = parsed.getString("message");
            JSONArray data = parsed.getJSONArray("data");

            //JSONArray data = new JSONArray(jsonResponse);
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");
                String distrito = jsonObject.getString("distrito");
                String estructura = jsonObject.getString("estructura");
                String direccion = jsonObject.getString("direccion");
                String codigo = jsonObject.getString("codigo");

                JSONObject dataObject = new JSONObject();
                dataObject.put("lat", lat);
                dataObject.put("lon", lon);
                dataObject.put("distrito", distrito);
                dataObject.put("estructura", estructura);
                dataObject.put("direccion", direccion);
                dataObject.put("codigo", codigo);

                dataArray.put(dataObject);
            }

            response.put("success", success);
            response.put("message", message);
            response.put("datos", dataArray);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    public static JSONObject getHistorial() {
        JSONObject response = new JSONObject();
        JSONArray dataArray = new JSONArray();

        try {
            JSONObject requestData = new JSONObject();
            requestData.put("token", accessToken);

            String jsonResponse = sendPostRequest("historial.php", requestData.toString());

            JSONObject parsed = new JSONObject(jsonResponse);
            boolean success = parsed.getBoolean("success");
            String message = parsed.getString("message");
            JSONArray data = parsed.getJSONArray("data");

            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                String userId = jsonObject.getString("user_id");
                String fechaActual = jsonObject.getString("fecha_actual");
                int cantidad = jsonObject.getInt("cantidad");
                String valorBuscado = jsonObject.getString("valorBuscado");

                JSONObject dataObject = new JSONObject();
                dataObject.put("user_id", userId);
                dataObject.put("fecha_actual", fechaActual);
                dataObject.put("cantidad", cantidad);
                dataObject.put("valorBuscado", valorBuscado);

                dataArray.put(dataObject);
            }

            response.put("success", success);
            response.put("message", message);
            response.put("datos", dataArray);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }
    
    public static void logout()
    {
        accessToken = null;
    }

    public static JSONObject filtrarDistritos() {
        JSONObject response = new JSONObject();
        JSONObject filtrado = new JSONObject();

        try {
            String jsonResponse = sendGetRequest("filtrarDistritos.php");

            JSONObject parsed = new JSONObject(jsonResponse);

            boolean success = parsed.getBoolean("success");
            String message = parsed.getString("message");

            if (success) {
                JSONObject data = parsed.getJSONObject("data");

                for (String distrito : data.keySet()) {
                    JSONArray distritoArray = data.getJSONArray(distrito);
                    JSONArray newData = new JSONArray();

                    for (int i = 0; i < distritoArray.length(); i++) {
                        JSONObject jsonObject = distritoArray.getJSONObject(i);

                        String codigo_id = jsonObject.getString("codigo_id");
                        String estructura = jsonObject.getString("estructura");
                        String direccion = jsonObject.getString("direccion");

                        JSONObject newResponse = new JSONObject();
                        newResponse.put("codigo_id", codigo_id);
                        newResponse.put("estructura", estructura);
                        newResponse.put("direccion", direccion);

                        newData.put(newResponse);
                    }

                    filtrado.put(distrito, newData);
                }
            }

            response.put("success", success);
            response.put("message", message);
            response.put("datos", filtrado);

        } catch (Exception e) {
            e.printStackTrace();
            response.put("success", false);
            response.put("message", e.getMessage());
        }

        return response;
    }

    public static JSONObject UpdateInfo(String name, String email, String oldPassword, String newPassword) {
        JSONObject result = new JSONObject();

        try {
            JSONObject json = new JSONObject();
            json.put("name", name);
            json.put("email", email);
            json.put("oldPassword", oldPassword);
            json.put("newPassword", newPassword);
            json.put("token", accessToken);

            String response = sendPostRequest("updateInfo.php", json.toString());
            //System.out.print(response);

            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");

            result.put("success", success);
            result.put("message", message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.put("success", false);
            result.put("message", e.getMessage());
        }
        return result;
    }

    public static JSONObject forgotPassword(String username, String password, String keysecret) {
        JSONObject result = new JSONObject();

        try {
            JSONObject json = new JSONObject();

            json.put("username", username);
            json.put("password", password);
            json.put("keysecret", keysecret);

            String response = sendPostRequest("forgotPassword.php", json.toString());
            //System.out.print(response);
            JSONObject jsonResponse = new JSONObject(response);
            boolean success = jsonResponse.getBoolean("success");
            String message = jsonResponse.getString("message");

            result.put("success", success);
            result.put("message", message);

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            result.put("success", false);
            result.put("message", e.getMessage());

        }
        return result;
    }
}
