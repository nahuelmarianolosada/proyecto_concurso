/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;

import dominio.TipoUsuario;
import hibernate.HibernateUtil;
import hibernate.dao.TipoUsuarioDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author SIISAJUJUY
 */
public class TipoUsuarioDaoImpl extends HibernateUtil implements TipoUsuarioDao {

    @Override
    public List<TipoUsuario> getAll() {
        Criteria criteria = getSession().createCriteria(TipoUsuario.class);
        criteria.addOrder(Order.asc("idTipoUsuario"));
        List<TipoUsuario> lista = criteria.list();
        return lista;
    }

    @Override
    public TipoUsuario getTipoUsuario(int idTipoUsuario) {
        return (TipoUsuario)getSession().get(TipoUsuario.class, idTipoUsuario);
    }

    @Override
    public void insertar(TipoUsuario tipoUsuario) {
        try {
            getSession().beginTransaction();
            getSession().save(tipoUsuario);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(TipoUsuario tipoUsuario) {
        try {
            getSession().beginTransaction();
            getSession().delete(tipoUsuario);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(TipoUsuario tipoUsuario) {
        try {
            getSession().beginTransaction();
            getSession().update(tipoUsuario);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
    
}
