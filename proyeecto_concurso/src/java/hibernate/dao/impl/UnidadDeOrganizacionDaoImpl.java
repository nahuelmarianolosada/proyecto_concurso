/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;

import dominio.UnidadDeOrganizacion;
import hibernate.HibernateUtil;
import static hibernate.HibernateUtil.getSession;
import hibernate.dao.UnidadDeOrganizacionDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author SIISAJUJUY
 */
public class UnidadDeOrganizacionDaoImpl extends HibernateUtil implements UnidadDeOrganizacionDao{
    @Override
    public List<UnidadDeOrganizacion> getAll() {
        Criteria criteria = getSession().createCriteria(UnidadDeOrganizacion.class);
        criteria.addOrder(Order.asc("codigoUnidadDeOrganizacion"));
        List<UnidadDeOrganizacion> lista = criteria.list();
        return lista;
    }

    @Override
    public UnidadDeOrganizacion getUnidadDeOrganizacion(int codigoUnidadDeOrganizacion) {
        return (UnidadDeOrganizacion)getSession().get(UnidadDeOrganizacion.class, codigoUnidadDeOrganizacion);
    }

//    @Override
//    public void insertar(UnidadDeOrganizacion unidadDeOrganizacion) {
//        try {
//            getSession().beginTransaction();
//            getSession().save(unidadDeOrganizacion);
//            getSession().getTransaction().commit();
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            getSession().getTransaction().rollback();
//        }
//    }

    @Override
    public void eliminar(UnidadDeOrganizacion unidadDeOrganizacion) {
        try {
            getSession().beginTransaction();
            getSession().delete(unidadDeOrganizacion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(UnidadDeOrganizacion unidadDeOrganizacion) {
        try {
            getSession().beginTransaction();
            getSession().update(unidadDeOrganizacion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
