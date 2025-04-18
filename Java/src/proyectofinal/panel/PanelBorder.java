package proyectofinal.panel;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;
import java.awt.geom.RoundRectangle2D;
import java.beans.Beans;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import javax.swing.JPanel;

public class PanelBorder extends JPanel {

    private boolean drawBorderOnly;
    private PropertyChangeSupport support;

    public PanelBorder() {
        initComponents();
        setOpaque(false);
        drawBorderOnly = false;
        if (!Beans.isDesignTime()) {
            support = new PropertyChangeSupport(this);
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        Area mainArea = new Area(new RoundRectangle2D.Double(0, 0, getWidth(), getHeight(), 20, 20));

        if (drawBorderOnly) {
            g2.setColor(getBackground());
            g2.fill(mainArea);
        } else {
            g2.setColor(getBackground());
            g2.fill(mainArea);

            mainArea.subtract(new Area(new Rectangle2D.Double(0, 0, getWidth(), getHeight() - 5)));
            g2.setPaint(new GradientPaint(0, 0, Color.decode("#5CB5E0"), getWidth(), 0, Color.decode("#007046")));
            g2.fill(mainArea);
        }

        g2.dispose();
        super.paintComponent(grphcs);
    }

    public boolean isDrawBorderOnly() {
        return drawBorderOnly;
    }

    public void setDrawBorderOnly(boolean drawBorderOnly) {
        boolean oldDrawBorderOnly = this.drawBorderOnly;
        this.drawBorderOnly = drawBorderOnly;
        if (support != null) {
            support.firePropertyChange("drawBorderOnly", oldDrawBorderOnly, drawBorderOnly);
        }
        repaint();
    }

    public void addPropertyChangeListener(PropertyChangeListener listener) {
        if (support != null) {
            support.addPropertyChangeListener(listener);
        }
    }

    public void removePropertyChangeListener(PropertyChangeListener listener) {
        if (support != null) {
            support.removePropertyChangeListener(listener);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 361, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 197, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
