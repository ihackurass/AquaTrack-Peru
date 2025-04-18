/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyectofinal.Pantallas.LoginyRegistro;

import javax.swing.JOptionPane;
import org.json.JSONObject;
import proyectofinal.Entidades.UserClient;

/**
 *
 * @author Home
 */
public class PantallaRecuperarClave extends javax.swing.JFrame {

    /**
     * Creates new form RecuperarContraseña
     */
    public PantallaRecuperarClave() {

        initComponents();
        //txtkeysecret.setVisible(false);
        //txtnewpassword.setVisible(false);
        setLocationRelativeTo(null);
        txtusernameolv.setOpaque(false);
        txtusernameolv.setBackground(new java.awt.Color(0, 0, 0, 1));

        txtkeysecret.setOpaque(false);
        txtkeysecret.setBackground(new java.awt.Color(0, 0, 0, 1));

        txtnewpassword.setOpaque(false);
        txtnewpassword.setBackground(new java.awt.Color(0, 0, 0, 1));
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
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnchangepass = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel16 = new javax.swing.JLabel();
        txtnewpassword = new javax.swing.JPasswordField();
        jLabel17 = new javax.swing.JLabel();
        txtusernameolv = new javax.swing.JTextField();
        jLabel18 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        txtkeysecret = new javax.swing.JTextField();
        jLabel26 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowOpened(java.awt.event.WindowEvent evt) {
                formWindowOpened(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/imagen/logo(1).jpg"))); // NOI18N
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 450, 360));

        jPanel2.setBackground(new java.awt.Color(25, 118, 211));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Eras Medium ITC", 0, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("X");
        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(378, 0, 40, 29));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 32)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Recuperar Contraseña");
        jLabel3.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);
        jPanel2.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 30, 420, 41));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("Bienvenido A La Plataforma ");
        jPanel2.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 420, -1));

        btnchangepass.setBackground(new java.awt.Color(255, 255, 255));
        btnchangepass.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnchangepass.setForeground(new java.awt.Color(25, 118, 211));
        btnchangepass.setText("CAMBIAR CLAVE");
        btnchangepass.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnchangepass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnchangepassActionPerformed(evt);
            }
        });
        jPanel2.add(btnchangepass, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 330, 341, 40));

        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Click Aqui");
        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jPanel2.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 380, 122, -1));

        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(199, 226, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        jLabel14.setText("Ya Tiene Una Cuenta?");
        jPanel2.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 380, 200, -1));

        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("_________________________________________");
        jPanel2.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 210, 290, 40));

        txtnewpassword.setFont(txtnewpassword.getFont().deriveFont(txtnewpassword.getFont().getSize()+2f));
        txtnewpassword.setForeground(new java.awt.Color(255, 255, 255));
        txtnewpassword.setBorder(null);
        txtnewpassword.setCaretColor(new java.awt.Color(255, 255, 255));
        txtnewpassword.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtnewpasswordActionPerformed(evt);
            }
        });
        jPanel2.add(txtnewpassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 260, 240, 30));

        jLabel17.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(199, 226, 255));
        jLabel17.setText("Usuario");
        jPanel2.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 110, 341, -1));

        txtusernameolv.setFont(txtusernameolv.getFont().deriveFont(txtusernameolv.getFont().getSize()+2f));
        txtusernameolv.setForeground(new java.awt.Color(255, 255, 255));
        txtusernameolv.setBorder(null);
        txtusernameolv.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtusernameolvActionPerformed(evt);
            }
        });
        jPanel2.add(txtusernameolv, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 130, 240, 30));

        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("_________________________________________");
        jPanel2.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 140, 290, 39));

        jLabel21.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(199, 226, 255));
        jLabel21.setText("Clave Secreta");
        jPanel2.add(jLabel21, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 180, 341, -1));

        jLabel22.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel22.setForeground(new java.awt.Color(199, 226, 255));
        jPanel2.add(jLabel22, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 341, -1));

        jLabel25.setFont(new java.awt.Font("Segoe UI", 0, 13)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(199, 226, 255));
        jLabel25.setText("Nueva Contraseña");
        jPanel2.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 240, 341, -1));

        txtkeysecret.setFont(txtkeysecret.getFont().deriveFont(txtkeysecret.getFont().getSize()+2f));
        txtkeysecret.setForeground(new java.awt.Color(255, 255, 255));
        txtkeysecret.setBorder(null);
        txtkeysecret.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtkeysecretActionPerformed(evt);
            }
        });
        jPanel2.add(txtkeysecret, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 240, 30));

        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("_________________________________________");
        jPanel2.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 270, 290, 39));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 500, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        setBounds(0, 0, 920, 440);
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void btnchangepassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnchangepassActionPerformed
        String username = txtusernameolv.getText();
        String password = new String(txtnewpassword.getPassword());
        String keysecret = txtkeysecret.getText();

        if (username.isEmpty() || password.isEmpty() || keysecret.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Faltan Campos A Completar", "Error", JOptionPane.ERROR_MESSAGE);
        } else if (username.length() < 5 || password.length() < 5 || keysecret.length() < 5) {
            JOptionPane.showMessageDialog(this, "El Usuario , Password y Clave Secreta deben tener más de 5 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            recoverPassword(username,password,keysecret);
        }
    }//GEN-LAST:event_btnchangepassActionPerformed

        private void recoverPassword(String username, String password, String keysecret) {
        try {
            JSONObject RegisterResult = UserClient.forgotPassword(username, password,keysecret);
            boolean success = RegisterResult.getBoolean("success");
            String message = RegisterResult.getString("message");
            if (success) {
                JOptionPane.showMessageDialog(this, message, "Exitoso", JOptionPane.INFORMATION_MESSAGE);
                Pantallalogin login = new Pantallalogin();
                login.setVisible(true);
                this.dispose();
            } else {
                JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error: " + e.getMessage());
        }
    }
    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked

        Pantallalogin LoginFrame = new Pantallalogin();
        LoginFrame.setVisible(true);
        //LoginFrame.pack();
        //LoginFrame.setLocationRelativeTo(null);
        this.dispose();
    }//GEN-LAST:event_jLabel13MouseClicked

    private void txtnewpasswordActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtnewpasswordActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtnewpasswordActionPerformed

    private void txtusernameolvActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtusernameolvActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtusernameolvActionPerformed

    private void txtkeysecretActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtkeysecretActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtkeysecretActionPerformed

    private void formWindowOpened(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowOpened
        for (double i = 0.0; i <= 1.0; i = i + 0.1) {
            String val = i + "";
            float f = Float.valueOf(val);
            this.setOpacity(f);
            try {
                Thread.sleep(50);
            } catch (Exception e) {

            }
        }    }//GEN-LAST:event_formWindowOpened

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
            java.util.logging.Logger.getLogger(PantallaRecuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PantallaRecuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PantallaRecuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PantallaRecuperarClave.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PantallaRecuperarClave().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnchangepass;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JTextField txtkeysecret;
    private javax.swing.JPasswordField txtnewpassword;
    private javax.swing.JTextField txtusernameolv;
    // End of variables declaration//GEN-END:variables
}
