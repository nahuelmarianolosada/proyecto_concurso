/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Cargo;
import dominio.Postulante;
import hibernate.HibernateUtil;
import hibernate.dao.PostulanteDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author SIISAJUJUY
 */
public class PostulanteDaoImpl extends HibernateUtil implements PostulanteDao {

    @Override
    public List<Postulante> getAll() {
        Criteria criteria = getSession().createCriteria(Postulante.class);
        criteria.addOrder(Order.asc("idPostulante"));
        List<Postulante> lista = criteria.list();
        return lista;
    }

    @Override
    public Postulante getPostulante(int idPostulante) {
        return (Postulante) getSession().get(Postulante.class, idPostulante);
    }

    @Override
    public Postulante getPostulanteAcreditados(Cargo cargo) {
        Criteria criteria = getSession().createCriteria(Postulante.class);
        criteria.addOrder(Order.asc("cargo"));
        criteria.add(Restrictions.eq("cargo", cargo));
        if (!criteria.list().isEmpty()) {
            Postulante ultimoPostulante = (Postulante) criteria.list().get(0);
            return ultimoPostulante;
        } else {
            return null;
        }
    }

    @Override
    public int generarIdNuevoPostulante() {

        Criteria criteria = getSession().createCriteria(Postulante.class);
        criteria.addOrder(Order.desc("idPostulante"));
        if (!criteria.list().isEmpty()) {
            Postulante ultimoPostulante = (Postulante) criteria.list().get(0);
            return ultimoPostulante.getIdPostulante() + 1;
        } else {
            return 0;
        }
    }

    @Override
    public void insertar(Postulante postulante) {
        System.out.println("\033[32PostulanteDaoImpl.insertar() => Guardando " + postulante.toString());
        try {
            getSession().beginTransaction();
            getSession().save(postulante);
          //  getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Postulante postulante) {
        try {
            getSession().beginTransaction();
            getSession().delete(postulante);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Postulante postulante) {
        try {
            getSession().beginTransaction();
            getSession().update(postulante);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
}
