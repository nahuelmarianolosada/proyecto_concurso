/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Usuario;
import hibernate.HibernateUtil;
import hibernate.dao.UsuarioDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class UsuarioDaoImpl extends HibernateUtil implements UsuarioDao {

    @Override
    public List<Usuario> getAll() {
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.addOrder(Order.asc("idUsuario"));
        List<Usuario> lista = criteria.list();
        return lista;
    }

    @Override
    public Usuario getUsuario(int idUsuario) {
        return (Usuario) getSession().get(Usuario.class, idUsuario);
    }

    @Override
    public Usuario getUsuario(String user, String pass) {
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("userName", user)).add(Restrictions.eq("pass", pass));
        if (criteria.list().size() > 0) {
            return (Usuario) criteria.list().get(0);
        } else {
            return null;
        }
    }

    @Override
    public int getNuevoId() {
        Criteria criteria = getSession().createCriteria(Usuario.class);
        criteria.addOrder(Order.desc("idUsuario"));
        Usuario u = (Usuario) criteria.list().get(0);
        return (int) (u.getIdUsuario() + 1);
    }

    @Override
    public void insertar(Usuario usuario) {
        try {
            getSession().beginTransaction();
            getSession().save(usuario);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Usuario usuario) {
        try {
            getSession().beginTransaction();
            getSession().delete(usuario);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminarById(int id_usuario) {
        try {
            Criteria criteria = getSession().createCriteria(Usuario.class);
            criteria.setMaxResults(1);
            criteria.add(Restrictions.eq("idUsuario", id_usuario));
            if (criteria.list().size() > 0) {
                Usuario usEliminar = (Usuario) criteria.list().get(0);
                getSession().beginTransaction();
                getSession().delete(usEliminar);
                getSession().getTransaction().commit();
            } else {
                System.out.println("UsuarioDaoImpl.eliminarById(): No se encontró el id " + id_usuario + " en la BD");
            }

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Usuario usuario) {
        try {
            getSession().beginTransaction();
            getSession().update(usuario);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

}
