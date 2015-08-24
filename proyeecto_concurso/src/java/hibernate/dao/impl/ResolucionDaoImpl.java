/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Expediente;
import dominio.Resolucion;
import hibernate.HibernateUtil;
import hibernate.dao.ResolucionDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nahuel
 */
public class ResolucionDaoImpl extends HibernateUtil implements ResolucionDao {

    @Override
    public List<Resolucion> getAll() {
        Criteria criteria = getSession().createCriteria(Resolucion.class);
        criteria.addOrder(Order.asc("idResolucion"));
        List<Resolucion> lista = criteria.list();
        return lista;
    }

    @Override
    public Resolucion getResolucion(int idResolucion) {
        return (Resolucion) getSession().get(Resolucion.class, idResolucion);
    }

    @Override
    public List<Resolucion> getResoluciones(Expediente expediente) {
        Criteria criteria = getSession().createCriteria(Resolucion.class);
        criteria.add(Restrictions.eq("expediente", expediente));
        List<Resolucion> lista = criteria.list();
        return lista;
    }

//    @Override
//    public int getNuevoId() {
//        Criteria criteria = getSession().createCriteria(Resolucion.class);
//        criteria.addOrder(Order.desc("idResolucion"));
//        Resolucion u = (Resolucion) criteria.list().get(0);
//        return (int) (u.getIdResolucion() + 1);
//    }
    @Override
    public void insertar(Resolucion resolucion) {
        try {
            getSession().beginTransaction();
            getSession().save(resolucion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Resolucion resolucion) {
        try {
            getSession().beginTransaction();
            getSession().delete(resolucion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

//    @Override
//    public void eliminarById(int id_resolucion) {
//        try {
//            Criteria criteria = getSession().createCriteria(Resolucion.class);
//            criteria.setMaxResults(1);
//            criteria.add(Restrictions.eq("idResolucion", id_resolucion));
//            if (criteria.list().size() > 0) {
//                Resolucion usEliminar = (Resolucion) criteria.list().get(0);
//                getSession().beginTransaction();
//                getSession().delete(usEliminar);
//                getSession().getTransaction().commit();
//            } else {
//                System.out.println("ResolucionDaoImpl.eliminarById(): No se encontró el id " + id_resolucion + " en la BD");
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//            getSession().getTransaction().rollback();
//        }
//    }
    @Override
    public void modificar(Resolucion resolucion) {
        try {
            getSession().beginTransaction();
            getSession().update(resolucion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public Resolucion obtenerUltimaResolucion() {
        Criteria criteria = getSession().createCriteria(Resolucion.class);
        criteria.addOrder(Order.desc("idResolucion"));
        return (Resolucion) criteria.list().get(0);

    }
}
