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
import org.hibernate.criterion.Restrictions;
import dominio.Resolucion;
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
     @Override
    //Obtiene la lista de cargos de la resolución que se esta cargando.
    public List<Cargo> getListaCargosDeResolucion(Resolucion resolucion) {

        Criteria criteria = getSession().createCriteria(Cargo.class);
        criteria.add(Restrictions.eq("resolucion", resolucion));
        List<Cargo> lista = criteria.list();
        return lista;

    }
    //Genera el nuevo Id del cargo que se guardara.
    @Override
    public int generarNuevoIdCargo() {

        Criteria criteria = getSession().createCriteria(Cargo.class);
        criteria.addOrder(Order.desc("idCargo"));
        Cargo ultimoCargo = (Cargo) criteria.list().get(0);
        int nuevoIdCargo = ultimoCargo.getIdCargo() + 1;
        return nuevoIdCargo;
        
    }
}
