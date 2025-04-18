/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package proyectofinal.Pantallas;

import java.awt.Color;
import javax.swing.JOptionPane;
import org.json.JSONObject;
import proyectofinal.Entidades.UserClient;

/**
 *
 * @author Home
 */
public class PantallaEditar extends javax.swing.JPanel {

    /**
     * Creates new form PantallaEditar
     */
    public PantallaEditar() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panelBorder1 = new proyectofinal.panel.PanelBorder();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel1 = new javax.swing.JLabel();
        txtname = new proyectofinal.panel.textfield.TextField();
        jLabel2 = new javax.swing.JLabel();
        txtcorreo = new proyectofinal.panel.textfield.TextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        btnupd = new javax.swing.JButton();
        pwdnew = new proyectofinal.panel.textfield.PasswordField();
        pwdold = new proyectofinal.panel.textfield.PasswordField();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(881, 539));

        panelBorder1.setBackground(new java.awt.Color(220, 220, 220));

        jSeparator1.setBackground(new java.awt.Color(0, 153, 153));

        jLabel1.setFont(new java.awt.Font("Microsoft PhagsPa", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 117, 131));
        jLabel1.setText("EDITAR PERFIL");

        txtname.setActivateShadow(true);
        txtname.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        txtname.setShadowColor(new java.awt.Color(0, 153, 153));

        jLabel2.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(102, 102, 102));
        jLabel2.setText("NOMBRE COMPLETO");

        txtcorreo.setActivateShadow(true);
        txtcorreo.setFont(new java.awt.Font("SansSerif", 1, 11)); // NOI18N
        txtcorreo.setShadowColor(new java.awt.Color(0, 153, 153));

        jLabel3.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(102, 102, 102));
        jLabel3.setText("CORREO");

        jLabel4.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(102, 102, 102));
        jLabel4.setText("ANTERIOR CONTRASEÑA");

        jLabel5.setFont(new java.awt.Font("Dialog", 1, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(102, 102, 102));
        jLabel5.setText("NUEVA CONTRASEÑA");

        btnupd.setBackground(new java.awt.Color(255, 255, 255));
        btnupd.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        btnupd.setForeground(new java.awt.Color(0, 204, 204));
        btnupd.setText("ACTUALIZAR");
        btnupd.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btnupd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnupdActionPerformed(evt);
            }
        });

        pwdnew.setBackground(new java.awt.Color(204, 204, 204));
        pwdnew.setEnabled(false);
        pwdnew.setShadowColor(new java.awt.Color(0, 153, 153));

        pwdold.setShadowColor(new java.awt.Color(0, 153, 153));
        pwdold.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                pwdoldKeyReleased(evt);
            }
        });

        javax.swing.GroupLayout panelBorder1Layout = new javax.swing.GroupLayout(panelBorder1);
        panelBorder1.setLayout(panelBorder1Layout);
        panelBorder1Layout.setHorizontalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelBorder1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtname, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 326, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addComponent(jLabel5)
                    .addComponent(pwdnew, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pwdold, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(22, 22, 22)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addGap(331, 331, 331)
                        .addComponent(btnupd, javax.swing.GroupLayout.PREFERRED_SIZE, 178, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(348, Short.MAX_VALUE))
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jSeparator1)
                .addContainerGap())
        );
        panelBorder1Layout.setVerticalGroup(
            panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelBorder1Layout.createSequentialGroup()
                .addGap(13, 13, 13)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(11, 11, 11)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(38, 38, 38)
                .addGroup(panelBorder1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtname, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtcorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelBorder1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pwdold, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pwdnew, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(60, 60, 60)
                .addComponent(btnupd, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(66, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelBorder1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(55, 55, 55)
                .addComponent(panelBorder1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(76, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnupdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnupdActionPerformed
        String name = txtname.getText().trim();
        String email = txtcorreo.getText().trim();
        String oldPassword = new String(pwdold.getPassword()).trim();
        String newPassword = new String(pwdnew.getPassword()).trim();

        //System.out.print(oldPassword);
        //System.out.print(newPassword);
        if (isInputValid(name, email, oldPassword, newPassword)) {
            if (name.length() > 5 || email.length() > 5 || newPassword.length() > 5) {

                JSONObject response = UserClient.UpdateInfo(name, email, oldPassword, newPassword);
                boolean result = response.getBoolean("success");
                String message = response.getString("message");
                if (result) {
                    JOptionPane.showMessageDialog(this, message, "Exitoso", JOptionPane.INFORMATION_MESSAGE);

                } else {
                    JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);

                }
            } else {
                JOptionPane.showMessageDialog(this, "El Usuario , Password , Correo o Clave deben tener más de 5 caracteres", "Error", JOptionPane.ERROR_MESSAGE);
            }

        } else {
            JOptionPane.showMessageDialog(this, "Es Necesario Llenar Algunos Campos", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnupdActionPerformed

    public static boolean isInputValid(String name, String email, String oldPassword, String newPassword) {
        // Validar si se proporciona la contraseña antigua, también debe proporcionarse la nueva
        if ((!oldPassword.isEmpty() && newPassword.isEmpty()) || (oldPassword.isEmpty() && !newPassword.isEmpty())) {
            return false;
        }
        // Validar que al menos uno de los campos esté presente
        if (name.isEmpty() && email.isEmpty() && oldPassword.isEmpty() && newPassword.isEmpty()) {
            return false;
        }
        // Validar que si se proporciona el nombre o el correo, están correctos
        if (!name.isEmpty() || !email.isEmpty()) {
            return true;
        }
        // Validar que si se proporcionan las contraseñas antigua y nueva, están correctas
        if (!oldPassword.isEmpty() && !newPassword.isEmpty()) {
            return true;
        }
        return false;
    }

    private void pwdoldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pwdoldKeyReleased

        if (pwdold.getPassword().length > 5) {
            pwdnew.setEnabled(true);
            pwdnew.setBackground(Color.WHITE);
        } else {
            pwdnew.setText("");
            pwdnew.setEnabled(false);
            pwdnew.setBackground(Color.LIGHT_GRAY);
        }    }//GEN-LAST:event_pwdoldKeyReleased


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnupd;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private proyectofinal.panel.PanelBorder panelBorder1;
    private proyectofinal.panel.textfield.PasswordField pwdnew;
    private proyectofinal.panel.textfield.PasswordField pwdold;
    private proyectofinal.panel.textfield.TextField txtcorreo;
    private proyectofinal.panel.textfield.TextField txtname;
    // End of variables declaration//GEN-END:variables
}
