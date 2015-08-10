/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;

import hibernate.HibernateUtil;
import hibernate.dao.PersonaDao;
import dominio.Persona;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
/**
 *
 * @author favio
 */
public class PersonaDaoImpl extends HibernateUtil implements PersonaDao{
    
     @Override
    public List<Persona> getAllPersona() {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.addOrder(Order.asc("idPersona"));
        List<Persona> lista = criteria.list();
        return lista;
    }
    
     @Override
    public Persona getPersona(int idPersona) {
        return (Persona)getSession().get(Persona.class, idPersona);
    }

    @Override
    public void insertar(Persona persona) {
        try {
            getSession().beginTransaction();
            getSession().save(persona);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Persona persona) {
        try {
            getSession().beginTransaction();
            getSession().delete(persona);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Persona persona) {
        try {
            getSession().beginTransaction();
            getSession().update(persona);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    
    
}
