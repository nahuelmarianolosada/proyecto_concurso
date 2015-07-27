/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;

import dominio.Tribunal;
import dominio.TribunalJurado;
import hibernate.HibernateUtil;
import hibernate.dao.TribunalDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author nahuel
 */
public class TribunalDaoImpl extends HibernateUtil implements TribunalDao{

    @Override
    public List<Tribunal> getAll() {
        Criteria criteria = getSession().createCriteria(Tribunal.class);
        criteria.addOrder(Order.asc("idTribunal"));
        List<Tribunal> lista = criteria.list();
        return lista;
    }

    @Override
    public Tribunal getTribunal(int idTribunal) {
        return (Tribunal)getSession().get(Tribunal.class, idTribunal);
    }
    
    

    @Override
    public void insertar(Tribunal tribunal) {
        try {
            getSession().beginTransaction();
            getSession().save(tribunal);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Tribunal tribunal) {
        try {
            getSession().beginTransaction();
            getSession().delete(tribunal);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Tribunal tribunal) {
        try {
            getSession().beginTransaction();
            getSession().update(tribunal);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
    
}
