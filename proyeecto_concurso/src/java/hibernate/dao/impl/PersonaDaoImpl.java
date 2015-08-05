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
        criteria.addOrder(Order.asc("idProfesion"));
        List<Persona> lista = criteria.list();
        return lista;
    }
    
    
    
    
    
    
}
