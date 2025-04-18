package proyectofinal.panel.textfield;

import java.awt.Color;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.geom.Area;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicTextFieldUI;

public class TextField extends JTextField {

    private int round = 10;
    private Color shadowColor = new Color(170, 170, 170);
    private BufferedImage imageShadow;
    private final Insets shadowSize = new Insets(2, 5, 8, 5);
    private boolean drawShadow = false;
    private boolean activateShadow = false;

    private String placeholder;
    private Color placeholderColor;
    private boolean usePlaceholder;
    private Font regularFont;
    private Font placeholderFont;

    public TextField() {
        setUI(new TextUI());
        setOpaque(false);
        setForeground(new Color(80, 80, 80));
        setSelectedTextColor(new Color(255, 255, 255));
        setSelectionColor(new Color(133, 209, 255));
        setBorder(new EmptyBorder(10, 12, 15, 12));
        setBackground(new Color(255, 255, 255));
        addFocusListener(new FocusAdapter() {
            @Override
            public void focusGained(FocusEvent e) {
                drawShadow = true;
                repaint();
            }

            @Override
            public void focusLost(FocusEvent e) {
                drawShadow = false;
                repaint();
            }
        });
    }

    public TextField(String placeholder, boolean usePlaceholder) {
        this();
        this.placeholder = placeholder;
        this.placeholderColor = Color.GRAY;
        this.usePlaceholder = usePlaceholder;

        regularFont = new Font("Arial", Font.PLAIN, 13);
        placeholderFont = new Font("Arial", Font.ITALIC, 13);

        if (usePlaceholder) {
            this.addFocusListener(new FocusAdapter() {
                @Override
                public void focusGained(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText("");
                        setForeground(Color.BLACK);
                        setFont(regularFont);
                    }
                }

                @Override
                public void focusLost(FocusEvent e) {
                    if (getText().isEmpty()) {
                        setText(placeholder);
                        setForeground(placeholderColor);
                        setFont(placeholderFont);
                    }
                }
            });

            // Establecer el placeholder inicial
            if (getText().isEmpty()) {
                setText(placeholder);
                setForeground(placeholderColor);
                setFont(placeholderFont);
            }
        }
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
        createImageShadow();
        repaint();
    }

    public Color getShadowColor() {
        return shadowColor;
    }

    public void setShadowColor(Color shadowColor) {
        this.shadowColor = shadowColor;
        createImageShadow();
        repaint();
    }

    public boolean isActivateShadow() {
        return activateShadow;
    }

    public void setActivateShadow(boolean activateShadow) {
        this.activateShadow = activateShadow;
        repaint();
    }

    @Override
    public String getText() {
        String text = super.getText();
        return text.equals(placeholder) ? "" : text;
    }

    public void setPlaceholder(String placeholder) {
        this.placeholder = placeholder;
        if (usePlaceholder && getText().isEmpty()) {
            setText(placeholder);
            setForeground(placeholderColor);
        }
    }

    public String getPlaceholder() {
        return placeholder;
    }

    public void setUsePlaceholder(boolean usePlaceholder) {
        this.usePlaceholder = usePlaceholder;
        if (usePlaceholder) {
            if (getText().isEmpty()) {
                setText(placeholder);
                setForeground(placeholderColor);
            }
        } else {
            if (getText().equals(placeholder)) {
                setText("");
                setForeground(Color.BLACK);
            }
        }
    }

    public boolean isUsePlaceholder() {
        return usePlaceholder;
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        Graphics2D g2 = (Graphics2D) grphcs.create();
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        double width = getWidth() - (shadowSize.left + shadowSize.right);
        double height = getHeight() - (shadowSize.top + shadowSize.bottom);
        double x = shadowSize.left;
        double y = shadowSize.top;

        // Crear imagen de sombra
        if (activateShadow) {
            if (drawShadow) {
                g2.setPaint(new GradientPaint(0, 0, Color.decode("#28698f"), getWidth(), getHeight(), Color.decode("#5CB5E0")));
                g2.fillRoundRect(0, 0, getWidth() - 4, getHeight() - 4, round, round);
            } else {
                g2.drawImage(imageShadow, 0, 0, null);
            }
        } else {
            g2.drawImage(imageShadow, 0, 0, null);
        }

        g2.setColor(getBackground());
        Area area = new Area(new RoundRectangle2D.Double(x, y, width, height, round, round));
        g2.fill(area);

        g2.dispose();
        super.paintComponent(grphcs);
    }

    @Override
    protected void paintBorder(Graphics g) {
    }

    @Override
    public void setBounds(int x, int y, int width, int height) {
        super.setBounds(x, y, width, height);
        createImageShadow();
    }

    private void createImageShadow() {
        int height = getHeight();
        int width = getWidth();
        if (width > 0 && height > 0) {
            imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = imageShadow.createGraphics();
            BufferedImage img = createShadow();
            if (img != null) {
                g2.drawImage(createShadow(), 0, 0, null);
            }
            g2.dispose();
        }
    }

    private BufferedImage createShadow() {
        int width = getWidth() - (shadowSize.left + shadowSize.right);
        int height = getHeight() - (shadowSize.top + shadowSize.bottom);
        if (width > 0 && height > 0) {
            BufferedImage img = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
            Graphics2D g2 = img.createGraphics();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.fill(new RoundRectangle2D.Double(0, 0, width, height, round, round));
            g2.dispose();
            return new ShadowRenderer(5, 0.5f, shadowColor).createShadow(img); // Aumentar el tamaño y opacidad de la sombra
        } else {
            return null;
        }
    }

    private class TextUI extends BasicTextFieldUI {

        @Override
        protected void paintBackground(Graphics grphcs) {
        }
    }
}
