/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectofinal.Pantallas;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import org.json.JSONArray;
import org.json.JSONObject;
import proyectofinal.Entidades.UserClient;
import proyectofinal.panel.DropDown.ListItem;
import proyectofinal.panel.DropDown.ToggleListAnimation;
import proyectofinal.panel.InfoPanel;
import proyectofinal.scrollbar.ScrollBar;

/**
 *
 * @author Home
 */
public class PantallaFiltro extends javax.swing.JPanel {

    /**
     * Creates new form PantallaFiltro
     */
    JPanel forma;
    JPanel forma2;

    public PantallaFiltro() {
        initComponents();
        setLayout(new BorderLayout());

        JSONObject puntos = UserClient.filtrarDistritos();
        if (puntos.getBoolean("success")) {
            JSONObject datos = puntos.getJSONObject("datos");
            ToggleListAnimation cuadro = new ToggleListAnimation();

            for (String distrito : datos.keySet()) {
                forma = new JPanel();
                forma2 = new JPanel();

                ListItem listItem = new ListItem(datos, distrito);
                cuadro.add(listItem);
                forma.add(cuadro);

                JScrollPane scroll = createScroll();
                scroll.setViewportView(forma);
                scroll.getViewport().setOpaque(false);
                scroll.setViewportBorder(null);
                forma2.add(scroll);
            }
            JScrollPane scroll = createScroll();
            scroll.setViewportView(forma2);
            scroll.getViewport().setOpaque(false);
            scroll.setViewportBorder(null);
            this.add(scroll, BorderLayout.CENTER);

        } else {
            System.out.println("Error: " + puntos.getString("message"));
            JOptionPane.showMessageDialog(null, "Error: " +puntos.getString("message"), "Error", JOptionPane.ERROR_MESSAGE);

        }
    }

    private JScrollPane createScroll() {
        JScrollPane scroll = new JScrollPane();
        scroll.setBorder(null);
        scroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scroll.setVerticalScrollBar(new ScrollBar());
        return scroll;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(881, 539));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 881, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 539, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
