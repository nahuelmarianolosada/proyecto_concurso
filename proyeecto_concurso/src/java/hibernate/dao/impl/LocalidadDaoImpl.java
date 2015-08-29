/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Localidad;
import hibernate.HibernateUtil;
import hibernate.dao.LocalidadDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class LocalidadDaoImpl extends HibernateUtil implements LocalidadDao {

    @Override
    public List<Localidad> getAll() {
        Criteria criteria = getSession().createCriteria(Localidad.class);
        criteria.addOrder(Order.asc("idLocalidad"));
        List<Localidad> lista = criteria.list();
        return lista;
    }

    @Override
    public Localidad getLocalidad(int idLocalidad) {
        return (Localidad) getSession().get(Localidad.class, idLocalidad);
    }

    @Override
    public Localidad getLocalidadPorCodigo(long codigo_localidad) {

        Localidad lo = new Localidad();

        Criteria criteria = getSession().createCriteria(Localidad.class);
        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("codigoLocalidad", codigo_localidad));
        if (!criteria.list().isEmpty()) {
            lo = (Localidad) criteria.list().get(0);
        }
        return lo;

    }

    @Override
    public List<Localidad> getLocalidad(String nombreLocalidad) {
        System.out.println("LocalidadDaoImpl.getLocalidad(" + nombreLocalidad + ")");
        List<Localidad> lista = new ArrayList<Localidad>();
        Criteria criteria = getSession().createCriteria(Localidad.class);

        criteria.add(Restrictions.ilike("nombreLocalidad", "%" + nombreLocalidad + "%"));
        criteria.addOrder(Order.asc("nombreLocalidad"));
        return lista = (List<Localidad>) criteria.list();

    }

    @Override
    public void insertar(Localidad localidad) {
        try {
            getSession().beginTransaction();
            getSession().save(localidad);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Localidad localidad) {
        try {
            getSession().beginTransaction();
            getSession().delete(localidad);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Localidad localidad) {
        try {
            getSession().beginTransaction();
            getSession().update(localidad);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
