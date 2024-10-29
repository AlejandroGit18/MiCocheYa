
package modelo;

// Clase auxiliar para representar un elemento de menú
public class MenuItem {
    private int idMenu;
    private String descripcion;
    private String href;
    private boolean esSubMenu;
    private int idMenuPadre;
    private String Icon;

    public MenuItem(int idMenu, String descripcion, String href, boolean esSubMenu, int idMenuPadre, String Icon) {
        this.idMenu = idMenu;
        this.descripcion = descripcion;
        this.href = href;
        this.esSubMenu = esSubMenu;
        this.idMenuPadre = idMenuPadre;
        this.Icon = Icon;
    }


    

    public int getIdMenu() { return idMenu; }
    public String getDescripcion() { return descripcion; }
    public String getHref() { return href; }
    public boolean isEsSubMenu() { return esSubMenu; }
    public int getIdMenuPadre() { return idMenuPadre; }
        public String getIcon() { return Icon; }
        
        

    public void setIdMenu(int idMenu) {
        this.idMenu = idMenu;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setHref(String href) {
        this.href = href;
    }

    public void setEsSubMenu(boolean esSubMenu) {
        this.esSubMenu = esSubMenu;
    }

    public void setIdMenuPadre(int idMenuPadre) {
        this.idMenuPadre = idMenuPadre;
    }

    public void setIcon(String Icon) {
        this.Icon = Icon;
    }
}