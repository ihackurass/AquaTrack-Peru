/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinal.Pantallas;

import java.awt.Color;
import javax.swing.JLabel;
import proyectofinal.Entidades.DataPA;
import javax.swing.*;
import java.awt.*;
import org.json.JSONObject;
import proyectofinal.Entidades.MoveJFrame;
import proyectofinal.Entidades.UserClient;

/**
 *
 * @author marco
 */
public class PantallaInfoPA extends MoveJFrame {

    //PantallaPrincipal ga = new PantallaPrincipal();
    public PantallaInfoPA() {

        initComponents();
        //initIcons();

    }

    public PantallaInfoPA(DataPA data, String texto) {
        initComponents();
        initIcons();

        setData(data, texto);
        if (UserClient.isLoggedIn()) {
            boolean isFavorite = UserClient.isFavorite(lbCodigo.getText());
            starFilled.setVisible(isFavorite);
        }

        //this.setLocation(ga.getxBtnFav() + 212, ga.getyBtnFav() + 156);
    }

    public void checkFavorite() {
    }

    public void initIcons() {
        if (UserClient.isLoggedIn()) {

            starFilled.setVisible(false);
            starEmpty.setVisible(true);
        } else {
            starFilled.setVisible(false);
            starEmpty.setVisible(false);
        }
    }

    private void setData(DataPA data, String texto) {
        lbDistrito.setText(data.getDistrito());
        lbEstructura.setText(data.getEstructura());
        lbCodigo.setText(data.getCodigo());
        lbDireccion.setText("<html>" + data.getDireccion());
        lblpunto.setText(texto);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        btnCerrar = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        starFilled = new javax.swing.JLabel();
        starEmpty = new javax.swing.JLabel();
        lblpunto = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        lbEstructura = new javax.swing.JLabel();
        lbCodigo = new javax.swing.JLabel();
        lbDireccion = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        lbDistrito = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(36, 36, 51));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        btnCerrar.setBackground(new java.awt.Color(36, 36, 51));
        btnCerrar.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnCerrar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnCerrarMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                btnCerrarMouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                btnCerrarMouseExited(evt);
            }
        });
        btnCerrar.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Segoe UI Emoji", 1, 14)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("x");
        btnCerrar.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 20, 20, -1));

        jLabel3.setFont(new java.awt.Font("Segoe UI Historic", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("X");
        btnCerrar.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 6, 10, 20));

        jPanel2.add(btnCerrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 0, 30, 30));

        starFilled.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        starFilled.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/star(1).png"))); // NOI18N
        starFilled.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                starFilledMouseClicked(evt);
            }
        });
        jPanel2.add(starFilled, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        starEmpty.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        starEmpty.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/star.png"))); // NOI18N
        starEmpty.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                starEmptyMouseClicked(evt);
            }
        });
        jPanel2.add(starEmpty, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 30, 30));

        lblpunto.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(lblpunto, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 10, -1, -1));

        jPanel1.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 320, 30));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        jLabel1.setText("DISTRITO:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 40, 70, -1));

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 300, 20));

        jLabel4.setText("Estructura:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 70, -1));

        lbEstructura.setText("-");
        jPanel1.add(lbEstructura, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 80, 160, -1));

        lbCodigo.setText("-");
        jPanel1.add(lbCodigo, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 110, 80, -1));

        lbDireccion.setText("-");
        lbDireccion.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbDireccion.setVerticalTextPosition(javax.swing.SwingConstants.TOP);
        jPanel1.add(lbDireccion, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 150, 160, 60));

        jLabel9.setText("Código:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 50, -1));

        lbDistrito.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        lbDistrito.setText("-");
        jPanel1.add(lbDistrito, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 40, 140, -1));

        jLabel7.setText("Dirección:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 224, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCerrarMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseEntered
        btnCerrar.setBackground(new Color(36, 36, 51));
    }//GEN-LAST:event_btnCerrarMouseEntered

    private void btnCerrarMouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseExited
        btnCerrar.setBackground(new Color(36, 36, 51));
    }//GEN-LAST:event_btnCerrarMouseExited

    private void btnCerrarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnCerrarMouseClicked
        this.dispose();
    }//GEN-LAST:event_btnCerrarMouseClicked

    private void starFilledMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_starFilledMouseClicked
        starFilled.setVisible(false);
        starFilled.setEnabled(false);

        starEmpty.setVisible(true);
        starEmpty.setEnabled(true);

        JSONObject FavResult = UserClient.PointFav(lbCodigo.getText(), "d");
        String message = FavResult.getString("message");

        boolean success = FavResult.getBoolean("success");
        if (success) {
            JOptionPane.showMessageDialog(this, message, "Exitoso", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_starFilledMouseClicked

    private void starEmptyMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_starEmptyMouseClicked
        starFilled.setVisible(true);
        starFilled.setEnabled(true);

        starEmpty.setVisible(false);
        starEmpty.setEnabled(false);
        JSONObject FavResult = UserClient.PointFav(lbCodigo.getText(), "a");
        String message = FavResult.getString("message");

        boolean success = FavResult.getBoolean("success");
        if (success) {
            JOptionPane.showMessageDialog(this, message, "Exitoso", JOptionPane.INFORMATION_MESSAGE);

        } else {
            JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

        }
    }//GEN-LAST:event_starEmptyMouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(PantallaInfoPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaInfoPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaInfoPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaInfoPA.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {

            public void run() {

                new PantallaInfoPA().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel btnCerrar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel lbCodigo;
    private javax.swing.JLabel lbDireccion;
    private javax.swing.JLabel lbDistrito;
    private javax.swing.JLabel lbEstructura;
    private javax.swing.JLabel lblpunto;
    private javax.swing.JLabel starEmpty;
    private javax.swing.JLabel starFilled;
    // End of variables declaration//GEN-END:variables
}
