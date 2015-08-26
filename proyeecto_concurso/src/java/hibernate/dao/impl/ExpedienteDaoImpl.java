/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Expediente;
import hibernate.HibernateUtil;
import hibernate.dao.ExpedienteDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class ExpedienteDaoImpl extends HibernateUtil implements ExpedienteDao {

    @Override
    public List<Expediente> getAll() {
        Criteria criteria = getSession().createCriteria(Expediente.class);
        criteria.addOrder(Order.asc("idExpediente"));
        List<Expediente> lista = criteria.list();
        return lista;
    }

    @Override
    public Expediente getExpediente(int idExpediente) {
        return (Expediente) getSession().get(Expediente.class, idExpediente);
    }

    @Override
    public Expediente getExpediente(String numeroExpediente) {
        Expediente expedienteEncontrado = null;
        Criteria criteria = getSession().createCriteria(Expediente.class);
        criteria.add(Restrictions.eq("numeroExpediente", numeroExpediente));
        criteria.addOrder(Order.asc("numeroExpediente"));
        if (criteria.list().size() > 0) {
            expedienteEncontrado = (Expediente) criteria.list().get(0);
        }
        return expedienteEncontrado;
    }

    @Override
    public int generarNuevoIdExpediente() {
        Criteria criteria = getSession().createCriteria(Expediente.class);
        criteria.addOrder(Order.desc("idExpediente"));
        if (criteria.list().size() > 0) {
            Expediente ultimoExpediente = (Expediente) criteria.list().get(0);
            return ultimoExpediente.getIdExpediente() + 1;
        } else {
            return 0;
        }

    }

    @Override
    public void insertar(Expediente expediente) {
        System.out.println("ExpedienteDaoImpl.insertar() => Guardando " + expediente.toString());
        try {
            getSession().beginTransaction();
            getSession().save(expediente);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Expediente expediente) {
        try {
            getSession().beginTransaction();
            getSession().delete(expediente);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Expediente expediente) {
        try {
            getSession().beginTransaction();
            getSession().update(expediente);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
