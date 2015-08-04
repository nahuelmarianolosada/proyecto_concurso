/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Tribunal;
import dominio.TribunalJurado;
import hibernate.HibernateUtil;
import static hibernate.HibernateUtil.getSession;
import hibernate.dao.TribunalJuradoDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class TribunalJuradoDaoImpl extends HibernateUtil implements TribunalJuradoDao {

    @Override
    public List<TribunalJurado> getAll() {
        Criteria criteria = getSession().createCriteria(TribunalJurado.class);
        criteria.addOrder(Order.asc("idTribunalJurado"));
        List<TribunalJurado> lista = criteria.list();
        return lista;
    }


    @Override
    public TribunalJurado getTribunalJurado(int idTribunalJurado) {
        return (TribunalJurado) getSession().get(TribunalJurado.class, idTribunalJurado);
    }

    @Override
    public void insertar(TribunalJurado tribunalJurado) {
        try {
            getSession().beginTransaction();
            getSession().save(tribunalJurado);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(TribunalJurado tribunalJurado) {
        try {
            getSession().beginTransaction();
            getSession().delete(tribunalJurado);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(TribunalJurado tribunalJurado) {
        try {
            getSession().beginTransaction();
            getSession().update(tribunalJurado);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
    
      public int generarNuevoIdJurado() {

        Criteria criteria = getSession().createCriteria(TribunalJurado.class);
        criteria.addOrder(Order.desc("idTribunalJurado"));
        TribunalJurado ultimoTribunalJurado = (TribunalJurado) criteria.list().get(0);
        return ultimoTribunalJurado.getIdTribunalJurado()+ 1;
        

    }
}
