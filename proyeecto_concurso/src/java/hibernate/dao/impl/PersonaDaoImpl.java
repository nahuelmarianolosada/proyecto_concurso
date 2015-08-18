/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import hibernate.HibernateUtil;
import hibernate.dao.PersonaDao;
import dominio.Persona;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author favio
 */
public class PersonaDaoImpl extends HibernateUtil implements PersonaDao {

    @Override
    public List<Persona> getAllPersona() {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.addOrder(Order.asc("idPersona"));
        List<Persona> lista = criteria.list();
        return lista;
    }

    @Override
    public Persona getPersona(int idPersona) {
        return (Persona) getSession().get(Persona.class, idPersona);
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

    @Override
    public Persona buscarPorDni(Integer dni) {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.setMaxResults(1);
        criteria.add(Restrictions.eq("dni", dni));
        if (criteria.list().size() > 0) {
            return (Persona) criteria.list().get(0);
        } else {
            return null;
        }
    }

    @Override
    public List<Persona> buscarPorNombre(String nombre) {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.add(Restrictions.ilike("nombres", "%" + nombre + "%"));
        System.out.println("PersonaDaoImpl.buscarPorNombre(" + nombre +") => Cantidad de registros coincidentes: " + criteria.list().size());
        if (criteria.list().size() > 0) {
            return criteria.list();
        } else {
            return null;
        }
    }

    @Override
    public List<Persona> buscarPorApellido(String apellido) {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.add(Restrictions.ilike("apellido", "%" + apellido + "%"));
        System.out.println("PersonaDaoImpl.buscarPorApellido(" + apellido +") => Cantidad de registros coincidentes: " + criteria.list().size());
        if (criteria.list().size() > 0) {
            return criteria.list();
        } else {
            return null;
        }
    }
    @Override
    public int generarIdNuevoPersona() {
          
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.addOrder(Order.desc("idPersona"));
        Persona ultimaPersona = (Persona) criteria.list().get(0);
        return ultimaPersona.getIdPersona()+ 1;
        
       } 
    
   @Override
   public boolean existeDniPersona(Persona persona){
       Criteria criteria = getSession().createCriteria(Persona.class);
       criteria.add(Restrictions.eq("dni",persona.getDni()));
        if (criteria.list().size() > 0) {
            return true;
        } else {
            return false;
        }
   
   }
    
}
