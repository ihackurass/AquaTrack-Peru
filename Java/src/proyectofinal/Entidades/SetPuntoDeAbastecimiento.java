/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.Entidades;

import org.jxmapviewer.JXMapViewer;
import org.jxmapviewer.viewer.WaypointPainter;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Point2D;
public class SetPuntoDeAbastecimiento extends WaypointPainter<PuntoDeAbastecimiento>{
    @Override
    protected void doPaint(Graphics2D g, JXMapViewer jxMapViewer, int width, int height) {
        for (PuntoDeAbastecimiento swingWaypoint : getWaypoints()) {
            Point2D point = jxMapViewer.getTileFactory().geoToPixel(
                    swingWaypoint.getPosition(), jxMapViewer.getZoom());
            Rectangle rectangle = jxMapViewer.getViewportBounds();
            int labelX = (int)(point.getX() - rectangle.getX());
            int labelY = (int)(point.getY() - rectangle.getY());
            JLabel label = swingWaypoint.getLabel();
            label.setLocation(labelX - label.getWidth() / 2, labelY - label.getHeight() / 2);
        }
    }
    
}
