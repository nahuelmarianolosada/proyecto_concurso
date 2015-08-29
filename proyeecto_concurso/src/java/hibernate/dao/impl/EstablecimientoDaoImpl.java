/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Establecimiento;
import hibernate.HibernateUtil;
import hibernate.dao.EstablecimientoDao;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class EstablecimientoDaoImpl extends HibernateUtil implements EstablecimientoDao {

    @Override
    public List<Establecimiento> getAll() {
        Criteria criteria = getSession().createCriteria(Establecimiento.class);
        criteria.addOrder(Order.asc("idEstablecimiento"));
        List<Establecimiento> lista = criteria.list();
        return lista;
    }

    @Override
    public List<Establecimiento> getEstablecimiento(String nombreEstablecimiento) {

        System.out.println("EstablecimientoDaoImpl.getEstablecimiento(" + nombreEstablecimiento + ")");
        List<Establecimiento> lista = new ArrayList<Establecimiento>();
        Criteria criteria = getSession().createCriteria(Establecimiento.class);

        criteria.add(Restrictions.ilike("nombre", "%" + nombreEstablecimiento + "%"));
        criteria.addOrder(Order.asc("nombre"));
        return lista = (List<Establecimiento>) criteria.list();

    }

    @Override
    public Establecimiento getEstablecimientoByCodigoSiisa(long codigoSiisa) {
        Criteria criteria = getSession().createCriteria(Establecimiento.class);
        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("codigoSiisa", codigoSiisa));
        if (criteria.list().size() > 0) {
            return (Establecimiento) criteria.list().get(0);
        } else {
            return null;
        }
    }

    @Override
    public Establecimiento getEstablecimientoById(int idEstablecimiento) {
        Criteria criteria = getSession().createCriteria(Establecimiento.class);
        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("idEstablecimiento", idEstablecimiento));
        if (criteria.list().size() > 0) {
            return (Establecimiento) criteria.list().get(0);
        } else {
            return null;
        }
    }

    @Override
    public void insertar(Establecimiento establecimiento) {
        try {
            getSession().beginTransaction();
            getSession().save(establecimiento);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Establecimiento establecimiento) {
        try {
            getSession().beginTransaction();
            getSession().delete(establecimiento);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Establecimiento establecimiento) {
        try {
            getSession().beginTransaction();
            getSession().update(establecimiento);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
