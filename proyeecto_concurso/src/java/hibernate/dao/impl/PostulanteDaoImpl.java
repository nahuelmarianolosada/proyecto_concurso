/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;

import dominio.Postulante;
import hibernate.HibernateUtil;
import hibernate.dao.PostulanteDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author SIISAJUJUY
 */
public class PostulanteDaoImpl extends HibernateUtil implements PostulanteDao{
    @Override
    public List<Postulante> getAll() {
        Criteria criteria = getSession().createCriteria(Postulante.class);
        criteria.addOrder(Order.asc("idInscripcion"));
        List<Postulante> lista = criteria.list();
        return lista;
    }

    @Override
    public Postulante getPostulante(int codigoPostulante) {
        return (Postulante)getSession().get(Postulante.class, codigoPostulante);
    }
    
//    @Override
//    public int generarIdNuevoPostulante() {
//          
//        Criteria criteria = getSession().createCriteria(Postulante.class);
//        criteria.addOrder(Order.desc("idInscripcion"));
//        Postulante ultimoPostulante = (Postulante) criteria.list().get(0);
//        return ultimoPostulante.getIdInscripcion() + 1;
//        
//       } 
    

    @Override
    public void insertar(Postulante postulante) {
        try {
            getSession().beginTransaction();
            getSession().save(postulante);
            getSession().getTransaction().commit();

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
