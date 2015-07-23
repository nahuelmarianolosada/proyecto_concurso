/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;

import dominio.Area;
import hibernate.HibernateUtil;
import hibernate.dao.AreaDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author SIISAJUJUY
 */
public class AreaDaoImpl extends HibernateUtil implements AreaDao{
    @Override
    public List<Area> getAll() {
        Criteria criteria = getSession().createCriteria(Area.class);
        criteria.addOrder(Order.asc("idArea"));
        List<Area> lista = criteria.list();
        return lista;
    }

    @Override
    public Area getArea(int idArea) {
        return (Area)getSession().get(Area.class, idArea);
    }

    @Override
    public void insertar(Area area) {
        try {
            getSession().beginTransaction();
            getSession().save(area);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Area area) {
        try {
            getSession().beginTransaction();
            getSession().delete(area);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Area area) {
        try {
            getSession().beginTransaction();
            getSession().update(area);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
