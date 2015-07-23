/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao.impl;


import dominio.Cargo;
import hibernate.HibernateUtil;
import hibernate.dao.CargoDao;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;

/**
 *
 * @author SIISAJUJUY
 */
public class CargoDaoImpl extends HibernateUtil implements CargoDao{

    @Override
    public List<Cargo> getAll() {
        Criteria criteria = getSession().createCriteria(Cargo.class);
        criteria.addOrder(Order.asc("idCargo"));
        List<Cargo> lista = criteria.list();
        return lista;
    }

    @Override
    public Cargo getCargo(int idCargo) {
        return (Cargo)getSession().get(Cargo.class, idCargo);
    }

    @Override
    public void insertar(Cargo cargo) {
        try {
            getSession().beginTransaction();
            getSession().save(cargo);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(Cargo cargo) {
        try {
            getSession().beginTransaction();
            getSession().delete(cargo);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(Cargo cargo) {
        try {
            getSession().beginTransaction();
            getSession().update(cargo);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }
    
}
