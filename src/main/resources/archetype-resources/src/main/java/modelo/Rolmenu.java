package ${groupId}.modelo;
// Generated 21-01-2018 05:32:20 PM by Hibernate Tools 4.3.1



/**
 * Rolmenu generated by hbm2java
 */
public class Rolmenu  implements java.io.Serializable {


     private Integer idrolmenu;
     private Menu menu;
     private Rol rol;

    public Rolmenu() {
    }

    public Rolmenu(Menu menu, Rol rol) {
       this.menu = menu;
       this.rol = rol;
    }
   
    public Integer getIdrolmenu() {
        return this.idrolmenu;
    }
    
    public void setIdrolmenu(Integer idrolmenu) {
        this.idrolmenu = idrolmenu;
    }
    public Menu getMenu() {
        return this.menu;
    }
    
    public void setMenu(Menu menu) {
        this.menu = menu;
    }
    public Rol getRol() {
        return this.rol;
    }
    
    public void setRol(Rol rol) {
        this.rol = rol;
    }




}


