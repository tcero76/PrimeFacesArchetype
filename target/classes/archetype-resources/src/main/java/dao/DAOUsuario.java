/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ${groupId}.${artifactId}.dao;

import ${groupId}.${artifactId}.interfaces.ICrud;
import java.util.List;
import ${groupId}.${artifactId}.modelo.Usuario;
import org.hibernate.Query;
import org.hibernate.Session;

/**
 *
 * @author TCERO
 */
public class DAOUsuario extends ICrud {
    
    @Override
    public List<Usuario> listar(Session sesion){
        String hql = "select distinct u from Usuario u left join fetch u.rol r left join fetch  r.rolmenus rm left join fetch rm.menu";
        return sesion.createQuery(hql).list();
    }
    
    public Integer login(String usuariolog, String clavelog, Session session) {
        Query sql = session.createSQLQuery("SELECT verificarLog('" + usuariolog + "','" + clavelog + "');");
        return (Integer)sql.uniqueResult();
    }
    
    public Usuario findbyid(int id, Session session) {
        String hql = "select distinct u from Usuario u left join fetch u.rol r left join fetch "
                + "r.rolmenus rm left join fetch rm.menu where u.idusuario = :id";
        return (Usuario)session.createQuery(hql).setParameter("id", id).uniqueResult();
    }
    
    public List<Usuario> listar2(Session sesion) {
        return (List<Usuario>)sesion.createCriteria(Usuario.class).list();
    }
    
}
