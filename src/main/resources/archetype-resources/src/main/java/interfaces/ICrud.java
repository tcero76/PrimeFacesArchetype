/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;

import org.hibernate.Session;

/**
 *
 * @author TCERO
 */
public abstract class ICrud<T> {
    public abstract T listar(Session sesion) ;
    public void borrar(Session sesion, T borrar) {
        sesion.delete(borrar);
    };
    public void editar(Session sesion, T editar) {
        sesion.update(editar);
    };
    public void guardar(Session sesion, T guardar){
        sesion.save(guardar);
    };
}
