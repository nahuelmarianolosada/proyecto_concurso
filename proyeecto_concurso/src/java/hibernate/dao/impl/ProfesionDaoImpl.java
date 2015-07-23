/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;


import dominio.Profesion;
import hibernate.HibernateUtil;
import hibernate.dao.ProfesionDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;


/**
 *
 * @author SIISAJUJUY
 */
public class ProfesionDaoImpl extends HibernateUtil implements ProfesionDao {
    @Override
    public List<Profesion> getAll() {
        Criteria criteria = getSession().createCriteria(Profesion.class);
        criteria.addOrder(Order.asc("idProfesion"));
        List<Profesion> lista = criteria.list();
        return lista;
    }

    @Override
    public Profesion getProfesion(int idProfesion) {
        return (Profesion)getSession().get(Profesion.class, idProfesion);
    }

    @Override
    public void insertar(Profesion profesion) {
        try {
            getSession().beginTransaction();
            getSession().save(profesion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Profesion profesion) {
        try {
            getSession().beginTransaction();
            getSession().delete(profesion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Profesion profesion) {
        try {
            getSession().beginTransaction();
            getSession().update(profesion);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
