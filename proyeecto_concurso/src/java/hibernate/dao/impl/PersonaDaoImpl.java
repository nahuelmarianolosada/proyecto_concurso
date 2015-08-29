/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import hibernate.HibernateUtil;
import hibernate.dao.PersonaDao;
import dominio.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
        System.out.println("PersonaDaoImpl.insertar() => Guardando " + persona.toString());
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
    public List<Persona> buscarPorNombre(String nombre) throws SQLException {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.add(Restrictions.ilike("nombres", "%" + nombre + "%"));
        System.out.println("PersonaDaoImpl.buscarPorNombre(" + nombre + ") => Cantidad de registros coincidentes: " + criteria.list().size());
        if (criteria.list().size() > 0) {
            return criteria.list();
        } else {
            String driver = "org.postgresql.Driver";
            String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
            String user = "nmlosada";
            String password = "siisa1234";
            List<Persona> listaPersonas = new ArrayList<>();
            System.out.print("PersonaDaoImpl.buscarPorNombre(" + nombre + ") => Buscando en la BD Siisa.");
            Connection con = null;
            Statement stmt = null;
            try {

                Class.forName(driver);
                con = DriverManager.getConnection(connectString, user, password);
                stmt = con.createStatement();

                String consultaSQL = "SELECT * FROM \"vw_profesionalDatosPersonales\" WHERE nombre like upper('%" + nombre + "%');";
                ResultSet rs = stmt.executeQuery(consultaSQL);


                while (rs.next()) {
                    
                    Persona persona = new Persona();
                    persona.setApellido(rs.getString("apellido"));
                    persona.setNombres(rs.getString("nombre"));
                    persona.setCuil(rs.getLong("cuil"));
                    persona.setDireccion(rs.getString("direccion"));
                    persona.setDni(rs.getInt("numero_documento"));
                    persona.setSexo(rs.getString("sexo"));
                    persona.setEmail(rs.getString("email"));
                    persona.setTelefono(rs.getString("tel1"));
                    persona.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
                    listaPersonas.add(persona);
                }

                System.out.println(" Cantidad de registros encontrados: " + listaPersonas.size());

            } catch (Exception exGeneral) {
                exGeneral.printStackTrace();
            } finally {
                stmt.close();
                con.close();
                if (!listaPersonas.isEmpty()) {
                    return listaPersonas;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public List<Persona> buscarPorApellido(String apellido) throws SQLException {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.add(Restrictions.ilike("apellido", "%" + apellido + "%"));
        System.out.println("PersonaDaoImpl.buscarPorApellido(" + apellido + ") => Cantidad de registros coincidentes: " + criteria.list().size());
        if (criteria.list().size() > 0) {
            return criteria.list();
        } else {
            String driver = "org.postgresql.Driver";
            String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
            String user = "nmlosada";
            String password = "siisa1234";
            List<Persona> listaPersonas = new ArrayList<>();
            System.out.print("PersonaDaoImpl.buscarPorNombre(" + apellido + ") => Buscando en la BD Siisa");
            Connection con = null;
            Statement stmt = null;
            try {

                Class.forName(driver);
                con = DriverManager.getConnection(connectString, user, password);
                stmt = con.createStatement();

                String consultaSQL = "SELECT * FROM \"vw_profesionalDatosPersonales\" WHERE apellido like upper('%" + apellido + "%');";
                ResultSet rs = stmt.executeQuery(consultaSQL);

               

                while (rs.next()) {
                    
                    Persona persona = new Persona();
                    persona.setApellido(rs.getString("apellido"));
                    persona.setNombres(rs.getString("nombre"));
                    persona.setCuil(rs.getLong("cuil"));
                    persona.setDireccion(rs.getString("direccion"));
                    persona.setDni(rs.getInt("numero_documento"));
                    persona.setSexo(rs.getString("sexo"));
                    persona.setEmail(rs.getString("email"));
                    persona.setTelefono(rs.getString("tel1"));
                    persona.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
                    listaPersonas.add(persona);
                }

                System.out.println(" cantidad de registros encontrados: " + listaPersonas.size());

            } catch (Exception exGeneral) {
                exGeneral.printStackTrace();
            } finally {
                stmt.close();
                con.close();
                if (!listaPersonas.isEmpty()) {
                    return listaPersonas;
                } else {
                    return null;
                }
            }
        }
    }

    @Override
    public int generarIdNuevoPersona() {

        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.addOrder(Order.desc("idPersona"));
        if (!criteria.list().isEmpty()) {
            Persona ultimaPersona = (Persona) criteria.list().get(0);
            return ultimaPersona.getIdPersona() + 1;
        } else {
            return 0;
        }

    }

    @Override
    public boolean existeDniPersona(Persona persona) {
        Criteria criteria = getSession().createCriteria(Persona.class);
        criteria.add(Restrictions.eq("dni", persona.getDni()));
        if (!criteria.list().isEmpty()) {
            return true;
        } else {
            return false;
        }

    }

}
