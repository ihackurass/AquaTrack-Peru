/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Entidades;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import proyectofinal.Pantallas.PantallaInfoPA;
public class PuntoDeAbastecimientoML implements MouseListener{
    
    private JLabel label;
    private String texto;
    private DataPA data;
    public PuntoDeAbastecimientoML(DataPA data, String texto, JLabel label){
        this.texto = texto;
        this.label = label;
        this.data = data;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point clickPoint = e.getLocationOnScreen();
        
        PantallaInfoPA pInfo = new PantallaInfoPA(data,texto);
        int x = clickPoint.x + label.getWidth();
        int y = clickPoint.y;
        pInfo.setLocation(x, y);
        pInfo.setVisible(true);

    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }
    
}
