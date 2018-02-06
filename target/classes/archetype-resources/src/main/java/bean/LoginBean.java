/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${groupId}.bean;

import ${groupId}.dao.DAOUsuario;
import ${groupId}.utiles.HibernateUtil;
import ${groupId}.modelo.Usuario;
import java.awt.event.ActionEvent;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import ${groupId}.modelo.Menu;
import ${groupId}.modelo.Rol;
import ${groupId}.modelo.Rolmenu;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.primefaces.context.RequestContext;

/**
 *
 * @author TCERO
 */
@ManagedBean(name = "loginBean")
@SessionScoped
public class LoginBean implements Serializable {
    
    private Session session;
    private Transaction transaction;
    private Rol rol;
    private List<Rolmenu> rolmenu;
    private List<Menu> menu;
    private Usuario usuario;
    private String usuariolog, clavelog;
    
    @PostConstruct
    public void myPostConstruct() {
        String renderKitId = FacesContext.getCurrentInstance().getViewRoot().getRenderKitId();
        if (renderKitId.equalsIgnoreCase("PRIMEFACES_MOBILE")) {
            try {
                FacesContext.getCurrentInstance().getExternalContext().redirect("faces/login_m.xhtml");
            } catch (IOException ex) {
                Logger.getLogger(LoginBean.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void login(ActionEvent event) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage message = null;
        Integer id = 0;
        menu = new ArrayList<Menu>();
        boolean loggedIn;
        String ruta = "";
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            DAOUsuario daousuario = new DAOUsuario();
            id = (Integer) daousuario.login(usuariolog, clavelog, session);
            transaction.commit();
        } catch (Exception e) {
            FacesContext.getCurrentInstance().addMessage("growl", new FacesMessage(FacesMessage.SEVERITY_INFO, e.getMessage(), ""));
            if (this.transaction != null) {
                transaction.rollback();
            }
            
        } finally {
            if (session != null) {
                session.close();
            }
        }
        if (id != 0) {
            loggedIn = true;
            try {
                session = HibernateUtil.getSessionFactory().openSession();
                transaction = session.beginTransaction();
                DAOUsuario daousuario = new DAOUsuario();
                usuario = daousuario.findbyid(id, session);
                transaction.commit();
            } catch (Exception e) {
                if (this.transaction != null) {
                    transaction.rollback();
                }
            } finally {
                if (session != null) {
                    session.close();
                }
            }
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("usuario", this.usuario.getIdusuario());
            message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Bienvenido", this.usuario.getNombre());
            String renderKitId = FacesContext.getCurrentInstance().getViewRoot().getRenderKitId();
            ruta = "/${artifactId}/faces/Plantilla.xhtml";
        } else {
            loggedIn = false;
            message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Loggin Error", "Usuario Incorrecto");
        }
        Map mapaSesion = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
        if (mapaSesion.containsKey("paginaAnterior")) {
            ruta = "${artifactId}/faces" + mapaSesion.get("paginaAnterior").toString();
            mapaSesion.remove("paginaAnterior");
        }
        FacesContext.getCurrentInstance().addMessage(null, message);
        context.addCallbackParam("loggedIn", loggedIn);
        context.addCallbackParam("ruta", ruta);
    }
    
    public void salir() {
        String ruta = "./../../";
        RequestContext context = RequestContext.getCurrentInstance();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession sesion = (HttpSession) facesContext.getExternalContext().getSession(false);
        sesion.invalidate();
        context.addCallbackParam("loggetOut", true);
        context.addCallbackParam("ruta", ruta);
    }
    
    public Menu renderizado(int idMenu) {
        Menu retorno = null;
        
        for (Object menu : usuario.getRol().getRolmenus()) {
            Rolmenu menuaux = (Rolmenu) menu;
            if (idMenu == menuaux.getMenu().getIdmenu()) {
                retorno = menuaux.getMenu();
            }
        }
        if (retorno == null) {
            retorno = new Menu();
            retorno.setActivo(Boolean.FALSE);
        }
        return retorno;
    }
    
    public String Baseurl() {
        return "./faces/Vistas/";
    }
    
    public String getUsuariolog() {
        return usuariolog;
    }
    
    public String getClavelog() {
        return clavelog;
    }
    
    public LoginBean() {
    }
    
    public void setUsuariolog(String usuariolog) {
        this.usuariolog = usuariolog;
    }
    
    public void setClavelog(String clavelog) {
        this.clavelog = clavelog;
    }
    
    public Usuario getUsuario() {
        return usuario;
    }
    
    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }
    
    public Boolean esMenu(Integer id) {
        Boolean encontrado = false;
        List<Rolmenu> rolmenu = new ArrayList<Rolmenu>(usuario.getRol().getRolmenus());
        for (Rolmenu r : rolmenu) {
            if (r.getMenu().getIdmenu().equals(id)) {
                encontrado = true;
            }
        }
        return encontrado;
    }
    
}
