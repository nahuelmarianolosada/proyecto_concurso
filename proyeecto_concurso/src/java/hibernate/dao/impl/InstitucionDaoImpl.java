/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Institucion;
import hibernate.HibernateUtil;
import hibernate.dao.InstitucionDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class InstitucionDaoImpl extends HibernateUtil implements InstitucionDao {

    @Override
    public List<Institucion> getAll() {
        Criteria criteria = getSession().createCriteria(Institucion.class);
        criteria.addOrder(Order.asc("idInstitucion"));
        List<Institucion> lista = criteria.list();
        return lista;
    }

    @Override
    public Institucion getInstitucion(int idInstitucion) {
        return (Institucion) getSession().get(Institucion.class, idInstitucion);
    }

    @Override
    public List<Institucion> getInstitucion(String nombreInstitucion) {
        System.out.println("InstitucionDaoImpl.getInstitucion(" + nombreInstitucion + ")");
        List<Institucion> lista = new ArrayList<Institucion>();
        Criteria criteria = getSession().createCriteria(Institucion.class);

        criteria.add(Restrictions.ilike("nombreInstitucion", "%" + nombreInstitucion + "%"));
        criteria.addOrder(Order.asc("nombreInstitucion"));
        return lista = (List<Institucion>) criteria.list();
        
    }

    @Override
    public void insertar(Institucion institucion) {
        try {
            getSession().beginTransaction();
            getSession().save(institucion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Institucion institucion) {
        try {
            getSession().beginTransaction();
            getSession().delete(institucion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Institucion institucion) {
        try {
            getSession().beginTransaction();
            getSession().update(institucion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
