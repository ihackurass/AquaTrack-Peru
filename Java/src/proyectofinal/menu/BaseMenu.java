/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectofinal.menu;

public class BaseMenu {
    private String icon;
    private String name;
    private MenuType type;
    private boolean isEnabled;

    public BaseMenu(String icon, String name, MenuType type, boolean isEnabled) {
        this.icon = icon;
        this.name = name;
        this.type = type;
        this.isEnabled = isEnabled;
    }

    // Getters y setters
    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public MenuType getType() {
        return type;
    }

    public void setType(MenuType type) {
        this.type = type;
    }

    public boolean isEnabled() {
        return isEnabled;
    }

    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    public static enum MenuType {
        TITLE, MENU, EMPTY
    }
}
