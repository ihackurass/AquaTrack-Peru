package proyectofinal.panel.DropDown;

import java.awt.Component;
import proyectofinal.panel.DropDown.DropDownArrow;
import java.util.List;
import java.util.Random;
import org.json.JSONArray;
import org.json.JSONObject;
import proyectofinal.panel.DropDown.SubListItem;

public class ListItem extends Item {

    private final Component[] components;

    public ListItem(JSONObject jsonList, String distrito) {
        initComponents();
        int count = 0;
        //JSONObject jsonObject = new JSONObject(jsonList);
        JSONArray items = jsonList.getJSONArray(distrito);
        components = new Component[items.length()];

        for (int i = 0; i < items.length(); i++) {
            JSONObject item = items.getJSONObject(i);
            count += 1;
            components[i] = new SubListItem(item.toString());
        }
        lblDistrito.setText(distrito + " (" + count + ")");

    }

    @Override
    public Component[] getSubItem() {
        return components;
    }

    @Override
    public DropDownArrow getDropDownArrow() {
        return dropDownArrow1;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        listHeader1 = new proyectofinal.panel.ListHeader();
        dropDownArrow1 = new proyectofinal.panel.DropDown.DropDownArrow();
        lblDistrito = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dropDownArrow1.setForeground(new java.awt.Color(51, 51, 51));
        listHeader1.add(dropDownArrow1);
        dropDownArrow1.setBounds(580, 0, 50, 50);

        lblDistrito.setBackground(new java.awt.Color(102, 102, 102));
        lblDistrito.setFont(new java.awt.Font("SansSerif", 1, 14)); // NOI18N
        listHeader1.add(lblDistrito);
        lblDistrito.setBounds(10, 4, 390, 40);

        add(listHeader1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 630, -1));
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private proyectofinal.panel.DropDown.DropDownArrow dropDownArrow1;
    private javax.swing.JLabel lblDistrito;
    private proyectofinal.panel.ListHeader listHeader1;
    // End of variables declaration//GEN-END:variables
}
