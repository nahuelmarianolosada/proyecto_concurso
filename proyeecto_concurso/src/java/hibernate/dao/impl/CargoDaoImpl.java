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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

/**
 *
 * @author SIISAJUJUY
 */
public class CargoDaoImpl extends HibernateUtil implements CargoDao {

    @Override
    public List<Cargo> getAll() {
        Criteria criteria = getSession().createCriteria(Cargo.class);
        criteria.addOrder(Order.asc("idCargo"));
        List<Cargo> lista = criteria.list();
        return lista;
    }

    @Override
    public Cargo getCargo(int idCargo) {
        return (Cargo) getSession().get(Cargo.class, idCargo);
    }

    @Override
    public List<Cargo> getCargos(Resolucion resolucion) {
        Criteria criteria = getSession().createCriteria(Cargo.class);
        criteria.add(Restrictions.eq("resolucion", resolucion));
        List<Cargo> lista = criteria.list();
        return lista;
    }

    /**
     * Genera un id nuevo buscando en la BD el ultimo cargo guardado y sumandole
     * +1 al id
     *
     * @return Si es el primer registro retorna 0, caso contrario el siguiente
     * del valor del id del ultimo registro
     */
    @Override
    public int generarNuevoIdCargo() {

        Criteria criteria = getSession().createCriteria(Cargo.class);
        criteria.addOrder(Order.desc("idCargo"));
        criteria.setMaxResults(1);
        if (criteria.list().size() > 0) {
            Cargo ultimoCargo = (Cargo) criteria.list().get(0);
            return ultimoCargo.getIdCargo() + 1;
        } else {
            return 1;
        }

    }

    @Override
    public void insertar(Cargo cargo) throws SQLException {
        System.out.println("\033[32CargoDaoImpl.insertar() => Guardando " + cargo.toString());

//        String driver = "org.postgresql.Driver";
//        String connectString = "jdbc:postgresql://localhost:5432/concursosDB";
//        String user = "nmlosada";
//        String password = "siisa1234";
//
//        Connection dbConnection = null;
//        PreparedStatement preparedStatement = null;
//        String insertTableSQL = "INSERT INTO cargo"
//                + "(id_profesion, especialidad, categoria, adicional,funcion,area_de_desempenio,codigo_establecimiento,modalidad,fecha_acta_formulacion_perfil,enunciacion,id_cargo,resolucion_id,es_desierto) VALUES"
//                + "(?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            getSession().beginTransaction();
            getSession().save(cargo);
            getSession().getTransaction().commit();

//            Class.forName(driver);
//
//            dbConnection = DriverManager.getConnection(connectString, user, password);
//            preparedStatement = dbConnection.prepareStatement(insertTableSQL);
//
//            preparedStatement.setInt(1, cargo.getProfesion().getIdProfesion());
//            preparedStatement.setString(2, cargo.getEspecialidad());
//            preparedStatement.setString(3, cargo.getCategoria());
//            preparedStatement.setInt(4, cargo.getAdicional());
//            preparedStatement.setString(5, cargo.getFuncion());
//            preparedStatement.setString(6, cargo.getAreaDeDesempenio());
//            preparedStatement.setLong(7, cargo.getEstablecimiento().getCodigoSiisa());
//            preparedStatement.setString(8, cargo.getModalidad());
//
//            java.sql.Date sqlDate = new java.sql.Date(cargo.getFechaActaFormulacionPerfil().getTime());
//
//            preparedStatement.setDate(9, sqlDate);
//            preparedStatement.setString(10, cargo.getEnunciacion());
//            preparedStatement.setInt(11, cargo.getIdCargo());
//            preparedStatement.setInt(12, cargo.getResolucion().getIdResolucion());
//            preparedStatement.setBoolean(13, cargo.isEsDesierto());
//
//            // execute insert SQL stetement
//            preparedStatement.executeUpdate();
        } catch (org.hibernate.HibernateException exHibernate) {
            exHibernate.printStackTrace();
            getSession().getTransaction().rollback();
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        } finally {

//            if (preparedStatement != null) {
//                preparedStatement.close();
//            }
//
//            if (dbConnection != null) {
//                dbConnection.close();
//            }
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
        System.out.println("\033[32CargoDaoImpl.modificar() => Modificando a " + cargo.toString());
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

}
