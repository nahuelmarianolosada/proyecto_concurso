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
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.ordering.antlr.Factory;

/**
 *
 * @author SIISAJUJUY
 */
public class UnidadDeOrganizacionDaoImpl extends HibernateUtil implements UnidadDeOrganizacionDao {

    @Override
    public List<UnidadDeOrganizacion> getAll() {

        Criteria criteria = getSession().createCriteria(UnidadDeOrganizacion.class);
        criteria.addOrder(Order.asc("idUnidadOrganizacion"));
        List<UnidadDeOrganizacion> lista = criteria.list();
        for (UnidadDeOrganizacion u : lista) {
            System.out.println(u.getIdUnidadOrganizacion() + " " + u.getNombreUnidad() + " " + u.getCodigoUnidadDeOrganizacion());
        }

        return lista;
    }

    @Override
    public UnidadDeOrganizacion getUnidadDeOrganizacion(int codigoUnidadDeOrganizacion) {
        return (UnidadDeOrganizacion) getSession().get(UnidadDeOrganizacion.class, codigoUnidadDeOrganizacion);
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

    @Override
    public int generarNuevoIdUdo() {
        Criteria criteria = getSession().createCriteria(UnidadDeOrganizacion.class);
        criteria.addOrder(Order.desc("idUnidadDeOrganizacion"));
        if (!criteria.list().isEmpty()) {
            UnidadDeOrganizacion u = (UnidadDeOrganizacion) criteria.list().get(0);
            return (int) (u.getIdUnidadOrganizacion() + 1);
        } else {
            return 0;
        }
    }

    @Override
    public void eliminarById(int id_unidad_organizacion) {
        try {
            Criteria criteria = getSession().createCriteria(UnidadDeOrganizacion.class);
            criteria.setMaxResults(1);
            criteria.add(Restrictions.eq("idUnidadOrganizacion", id_unidad_organizacion));
            if (criteria.list().size() > 0) {
                UnidadDeOrganizacion udoEliminar = (UnidadDeOrganizacion) criteria.list().get(0);
                getSession().beginTransaction();
                getSession().delete(udoEliminar);
                getSession().getTransaction().commit();
            } else {
                System.out.println("UNidadDeOrganizacionDaoImpl.eliminarById(): No se encontró el id " + id_unidad_organizacion + " en la BD");
            }

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }

    }

    @Override
    public List<UnidadDeOrganizacion> actualizaListaUdo() {

        System.out.println("BeanUNidadDaoimpl=> actualizarSession");


        Criteria criteria = getSession().createCriteria(UnidadDeOrganizacion.class);
        criteria.addOrder(Order.asc("idUnidadOrganizacion"));
        List<UnidadDeOrganizacion> lista = criteria.list();
        for (UnidadDeOrganizacion u : lista) {
            getSession().beginTransaction();
            getSession().refresh(u);
            getSession().getTransaction().commit();
            System.out.println(u.getIdUnidadOrganizacion() + " " + u.getNombreUnidad() + " " + u.getCodigoUnidadDeOrganizacion());
        }

        return lista;
    }

}
