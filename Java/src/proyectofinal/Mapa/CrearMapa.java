/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Mapa;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Desktop;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import javax.swing.event.MouseInputListener;
import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.OSMTileFactoryInfo;
import org.jxmapviewer.input.PanMouseInputListener;
import org.jxmapviewer.input.ZoomMouseWheelListenerCenter;
import org.jxmapviewer.viewer.DefaultTileFactory;
import org.jxmapviewer.viewer.GeoPosition;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.SwingUtilities;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingWorker;
import org.json.JSONArray;
import org.json.JSONObject;
import org.jxmapviewer.VirtualEarthTileFactoryInfo;
import org.jxmapviewer.input.ZoomMouseWheelListenerCursor;
import org.jxmapviewer.viewer.TileFactory;
import org.jxmapviewer.viewer.TileFactoryInfo;
import org.jxmapviewer.viewer.WaypointPainter;
import proyectofinal.Entidades.DataPA;
import proyectofinal.Entidades.PuntoDeAbastecimiento;
import proyectofinal.Entidades.PuntoDeAbastecimiento;
import proyectofinal.Entidades.PuntoDeAbastecimientoML;
import proyectofinal.Entidades.SetPuntoDeAbastecimiento;
import proyectofinal.Entidades.UserClient;
//import proyectofinal.Pantallas.TRash;
import proyectofinal.Pantallas.PantallaPrincipal;
//import proyectofinal.Pantallas.wqqw;
import proyectofinal.panel.PanelBorder;
import proyectofinal.panel.textfield.TextField;

public class CrearMapa extends JXMapViewer {

    private static Set<PuntoDeAbastecimiento> waypoints = new HashSet<>();
    private static WaypointPainter<PuntoDeAbastecimiento> waypointPainter = new SetPuntoDeAbastecimiento();
    private static javax.swing.Timer timer;
    private TextField textField;
    private boolean buscando = false;
    private JLabel resetButton;
    private JLabel manualButton;
    private JLabel closeLabel;
    private JLabel minimizeLabel;

    public CrearMapa() {

    }

    public void iniciarMapa() {
        //this.removeAll();
        List<TileFactory> estilosMapa = new ArrayList<>();

        estilosMapa(estilosMapa);
        TileFactory tilePrincipal = estilosMapa.get(0);
        setTileFactory(tilePrincipal);
        GeoPosition lima = new GeoPosition(-12.0089248, -76.9715627);

        textField = new TextField("Buscar Informacion", true);
        textField.setBounds(20, 20, 200, 40);
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String texto = textField.getText();
                if (!texto.isEmpty() && texto.length() > 4) {
                    buscando = true;
                    SearchPuntosByKey(texto);
                } else {
                    //SearchPuntosByKey(null);
                    JOptionPane.showMessageDialog(CrearMapa.this, "La Búsqueda No Puede Ser Vacía o Menos de 4 caracteres");
                    //Notification noti = new Notification(PantallaPrincipal.getJFram(),Notification.Type.WARNING,Notification.Location.TOP_CENTER,"La Búsqueda No Puede Ser Vacía o Menos de 4 caracteres");
                    //noti.showNotification();
                }
            }
        });
        resetButton = new JLabel(new ImageIcon(getClass().getResource("/imagen/refresh.png")));
        resetButton.setBounds(210, 22, 50, 30);
        resetButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                buscando = false;
                textField.setText("");
                SearchPuntosByKey(null);
            }
        });

        closeLabel = new JLabel(new ImageIcon(getClass().getResource("/imagen/rojo.png")));
        closeLabel.setBounds(830, 5, 50, 30);
        closeLabel.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
        closeLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.exit(0);
            }
        });

        manualButton = new JLabel(new ImageIcon(getClass().getResource("/imagen/manual.png")));
        manualButton.setBounds(15, 480, 50, 30);
        manualButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                openPDFInBrowser("src/files/ManualDeUsuario.pdf");

            }
        });
        this.add(closeLabel);

        this.add(manualButton);

        this.add(resetButton);

        this.add(textField);

        setZoom(8);
        interaccion();
        setAddressLocation(lima);

        waypointPainter.setWaypoints(waypoints);
        this.setOverlayPainter(waypointPainter);

        UpdateMap();
    }

    public static void openPDFInBrowser(String filePath) {
        if (Desktop.isDesktopSupported()) {
            try {
                File pdfFile = new File(filePath);
                if (pdfFile.exists()) {
                    URI uri = pdfFile.toURI();
                    Desktop.getDesktop().browse(uri);
                } else {
                    JOptionPane.showMessageDialog(null, "El Archivo No Existe", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        } else {
            JOptionPane.showMessageDialog(null, "Error Al Abrir El Archivo", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void UpdateMap() {
        if (timer != null && timer.isRunning()) {
            timer.stop();
        }

        timer = new javax.swing.Timer(5000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!buscando) {
                    new SwingWorker<JSONObject, Void>() {
                        @Override
                        protected JSONObject doInBackground() throws Exception {
                            return UserClient.getPuntos();
                        }

                        @Override
                        protected void done() {
                            try {
                                JSONObject response = get();
                                if (response != null && response.getBoolean("success")) {
                                    updateWaypoints(response);
                                } else {
                                    JOptionPane.showMessageDialog(CrearMapa.this, "Error: " + response.getString("message"));
                                    //System.out.print("Error: " + response.getString("message"));
                                }
                            } catch (Exception ex) {
                                ex.printStackTrace();
                            }
                        }
                    }.execute();
                }
            }
        });

        timer.start();
    }

    private void SearchPuntosByKey(String valor) {
        new SwingWorker<JSONObject, Void>() {
            @Override
            protected JSONObject doInBackground() throws Exception {
                if (valor == null) {
                    return UserClient.getPuntos();
                } else {
                    return UserClient.getPuntosByKey(valor);
                }
            }

            @Override
            protected void done() {
                try {
                    JSONObject response = get();
                    if (response != null && response.getBoolean("success")) {
                        updateWaypoints(response);
                    } else {
                        JOptionPane.showMessageDialog(CrearMapa.this, "Error: " + response.getString("message"));
                        //System.out.print("Error: " + response.getString("message"));
                    }
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        }.execute();
    }

    public void updateWaypoints(JSONObject listaJson) {
        try {
            JSONArray data = listaJson.getJSONArray("datos");

            Set<PuntoDeAbastecimiento> newWaypoints = new HashSet<>();
            for (int i = 0; i < data.length(); i++) {
                JSONObject jsonObject = data.getJSONObject(i);
                double lat = jsonObject.getDouble("lat");
                double lon = jsonObject.getDouble("lon");
                String distrito = jsonObject.getString("distrito");
                String estructura = jsonObject.getString("estructura");
                String direccion = jsonObject.getString("direccion");
                String codigo = jsonObject.getString("codigo");

                GeoPosition position = new GeoPosition(lat, lon);
                DataPA dataPA = new DataPA(distrito, estructura, direccion, codigo);
                PuntoDeAbastecimiento punto = new PuntoDeAbastecimiento(codigo, position, dataPA);
                newWaypoints.add(punto);
            }
            for (PuntoDeAbastecimiento antiguo : waypoints) {
                this.remove(antiguo.getLabel());
            }
            waypoints.clear();
            waypoints.addAll(newWaypoints);

            waypointPainter.setWaypoints(waypoints);

            this.repaint();

            //this.removeAll();
            for (PuntoDeAbastecimiento w : waypoints) {
                this.add(w.getLabel());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void estilosMapa(List<TileFactory> estilosMapa) {
        TileFactoryInfo tile1 = new OSMTileFactoryInfo();
        TileFactoryInfo tile2 = new VirtualEarthTileFactoryInfo(VirtualEarthTileFactoryInfo.MAP);
        // Añadir los estilos a la lista
        estilosMapa.add(new DefaultTileFactory(tile1));
        estilosMapa.add(new DefaultTileFactory(tile2));

    }

    public void interaccion() {
        MouseInputListener mia = new PanMouseInputListener(this);
        addMouseListener(mia);
        addMouseMotionListener(mia);
        addMouseWheelListener(new ZoomMouseWheelListenerCursor(this));
    }

}
