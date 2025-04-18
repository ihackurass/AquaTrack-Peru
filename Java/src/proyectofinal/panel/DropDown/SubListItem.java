package proyectofinal.panel.DropDown;

import org.json.JSONObject;

public class SubListItem extends Item.SubItem {

    public SubListItem(String jsonValue) {
        initComponents();
        JSONObject jsonObject = new JSONObject(jsonValue);
        lblCodigo.setText( jsonObject.getString("estructura") + " --- [" +jsonObject.getString("codigo_id") + "]");
        lblEstructura.setText( jsonObject.getString("direccion"));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblCodigo = new javax.swing.JLabel();
        lblEstructura = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setPreferredSize(new java.awt.Dimension(383, 50));
        setRequestFocusEnabled(false);

        lblCodigo.setFont(new java.awt.Font("sansserif", 1, 12)); // NOI18N
        lblCodigo.setForeground(new java.awt.Color(99, 98, 98));

        lblEstructura.setFont(new java.awt.Font("Dialog", 0, 8)); // NOI18N
        lblEstructura.setForeground(new java.awt.Color(99, 98, 98));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(lblEstructura, javax.swing.GroupLayout.DEFAULT_SIZE, 349, Short.MAX_VALUE)
                    .addComponent(lblCodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lblCodigo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblEstructura)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lblCodigo;
    private javax.swing.JLabel lblEstructura;
    // End of variables declaration//GEN-END:variables
}
