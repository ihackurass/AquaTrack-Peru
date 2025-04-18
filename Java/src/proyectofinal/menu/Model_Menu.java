package proyectofinal.menu;

import javax.swing.Icon;
import javax.swing.ImageIcon;

public class Model_Menu extends BaseMenu {

    public Model_Menu(String icon, String name, MenuType type) {
        this(icon, name, type, true);
    }

    public Model_Menu(String icon, String name, MenuType type, boolean isEnabled) {
        super(icon, name, type, isEnabled);
    }

    public Model_Menu() {
        super(null, null, null, true);
    }

    public Icon toIcon() {
        return new ImageIcon(getClass().getResource("/imagen/menu/" + getIcon() + ".png"));
    }
}

