/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.panel.DropDown;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Path2D;
import java.util.ArrayList;
import java.util.List;
import org.jdesktop.animation.timing.Animator;
import org.jdesktop.animation.timing.TimingTargetAdapter;
import proyectofinal.panel.DropDown.ToggleListener;

public class DropDownArrow extends JComponent {

    public boolean isSelected() {
        //System.out.print(selected);

        return selected;
    }

    public void setSelected(boolean selected) {
        if (this.selected != selected) {
            this.selected = selected;
            if (selected) {
                animate = 1f;
            } else {
                animate = 0;
            }
            repaint();
        }
    }

    public void setSelected(boolean selected, boolean animated) {
        if (this.selected != selected) {
            this.selected = selected;
            runEventSelected();
            if (animated) {
                startAnimation();
                runEventSelected();
            } else {
                if (selected) {
                    animate = 1f;
                } else {
                    animate = 0;
                }
                repaint();
            }
        }
    }

    private boolean selected;

    private Animator animator;
    private float animate;
    private float temp;

    private boolean mousePress;
    private boolean mouseHover;
    private List<ToggleListener> listeners = new ArrayList<>();

    public DropDownArrow() {
        setPreferredSize(new Dimension(120, 40));
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setForeground(Color.BLACK);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                mouseHover = true;
            }

            @Override
            public void mouseExited(MouseEvent e) {
                mouseHover = false;
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    mousePress = true;
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (mousePress && mouseHover) {
                        setSelected(!isSelected(), true);
                    }
                    mousePress = false;
                }
            }

        });

        animator = new Animator(350, new TimingTargetAdapter() {
            @Override
            public void timingEvent(float fraction) {
                if (isSelected()) {
                    animate = 180 * fraction;
                    temp = fraction;

                } else {
                    animate = 180 * (1 - fraction);
                    temp = 1f - fraction;

                }
                repaint();
                runEventAnimated(temp);
            }

            @Override
            public void end() {
                animate = selected ? 180 : 0;
                repaint();
            }
        });
        animator.setResolution(5);
    }

    public void addEventToggleSelected(ToggleListener listener) {
        this.listeners.add(listener);
    }

    private void runEventSelected() {
        for (ToggleListener listener : listeners) {
            listener.onSelected(selected);
        }
    }

    private void runEventAnimated(float temp) {
        for (ToggleListener listener : listeners) {
            listener.onAnimated(temp);
        }
    }

    private void startAnimation() {
        if (animator.isRunning()) {
            float f = animator.getTimingFraction();
            animator.stop();
            animator.setStartFraction(1f - f);
        } else {
            animator.setStartFraction(0);
        }
        this.selected = selected;
        animator.start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();
        g2.setColor(getForeground());

        double scaleFactor = 2.3;

        int x = width / 2;
        int y = height / 2;

        Path2D p2 = new Path2D.Double();
        p2.moveTo(x - 4 * scaleFactor, y - 2 * scaleFactor);
        p2.lineTo(x, y + 2 * scaleFactor);
        p2.lineTo(x + 4 * scaleFactor, y - 2 * scaleFactor);

        AffineTransform at = AffineTransform.getRotateInstance(Math.toRadians(animate), x, y);
        g2.setStroke(new BasicStroke(4f));
        g2.draw(at.createTransformedShape(p2));

        g2.dispose();
    }
}
