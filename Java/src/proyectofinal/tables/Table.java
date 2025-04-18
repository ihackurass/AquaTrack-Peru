package proyectofinal.tables;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.Icon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import proyectofinal.scrollbar.ScrollBar;
import proyectofinal.tables.shadow.ShadowRenderer;

public class Table extends JTable {

    private BufferedImage imageShadow;

    public Table() {
        setRowHeight(50);
        setFont(new Font("Arial", Font.PLAIN, 14));
        getTableHeader().setReorderingAllowed(false);
        getTableHeader().setDefaultRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable jtable, Object o, boolean bln, boolean bln1, int i, int i1) {
                TableHeader h = new TableHeader(o + "");
                h.setHorizontalAlignment(JLabel.CENTER);
                h.setFont(new Font("Arial", Font.BOLD, 15));
                return h;
            }
        });
        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent ce) {
                createShadow();
            }
        });
    }

    private void createShadow() {
        int width = getWidth();
        int height = rowHeight;
        int space = 12;
        int margin = space / 2;
        imageShadow = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics2D g2 = imageShadow.createGraphics();
        g2.fillRect(0, 0, width - margin, height - margin);
        g2.drawImage(new ShadowRenderer(6, 0.3f, new Color(160, 160, 160)).createShadow(imageShadow), -4, -4, null);
        g2.fillRect(margin, margin, width - space, height - space);
        g2.setComposite(AlphaComposite.Clear);
        g2.fillRect(margin, margin, width - space, height - space);
        g2.dispose();
    }

    @Override
    public Component prepareRenderer(TableCellRenderer tcr, int i, int i1) {
        if (i1 == 3) {
            TableCell_Status cell = new TableCell_Status(getValueAt(i, 3).toString());
            return cell;
        } else {
            String values = "";
            if (getValueAt(i, i1) != null) {
                values = getValueAt(i, i1).toString();
            }
            TableCell cell = new TableCell(values);
            cell.setFont(new Font("Arial", Font.PLAIN, 13));
            if (i1 == 0) {
                cell.setHorizontalAlignment(JLabel.CENTER);
            } else if (i1 == 1) {
                cell.setHorizontalAlignment(JLabel.CENTER);
            } else if (i1 == 2) {
                cell.setHorizontalAlignment(JLabel.CENTER);
            }
            return cell;
        }
    }

    @Override
    protected void paintComponent(Graphics grphcs) {
        super.paintComponent(grphcs);
        Graphics2D g2 = (Graphics2D) grphcs;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        g2.setColor(getBackground());
        g2.fill(new RoundRectangle2D.Float(0, 0, getWidth(), getHeight(), 10, 10));

        super.paintComponent(g2);
        int space = 12;
        int margin = space / 2;
        for (int i = 0; i < getRowCount(); i++) {
            int row = i;
            Rectangle r = getCellRect(row, 0, true);
            if (isRowSelected(i)) {
                g2.setColor(new Color(17, 164, 232));
                g2.drawRect(margin, r.getLocation().y + margin, getWidth() - margin * 2, rowHeight - space);
            }
            g2.drawImage(imageShadow, 0, r.getLocation().y, null);
            g2.setColor(new Color(109, 206, 255));

            g2.fillRect(margin, r.getLocation().y + margin, 3, rowHeight - space);
        }
        g2.dispose();
    }

    public void fixTable(JScrollPane scroll) {
        scroll.setBorder(null);
        scroll.getViewport().setBackground(new Color(250, 250, 250));
        ScrollBar sb = new ScrollBar();
        sb.setBackground(new Color(250, 250, 250));
        scroll.setVerticalScrollBar(sb);
        JPanel p = new JPanel();
        p.setBackground(new Color(250, 250, 250));
        scroll.setCorner(JScrollPane.UPPER_RIGHT_CORNER, p);
    }
}
