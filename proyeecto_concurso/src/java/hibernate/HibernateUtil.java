/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import dominio.Localidad;
import dominio.Persona;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author SIISAJUJUY
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    private static Session session;

    static {
        try {
            System.out.println("----------------------------------------------------------\nHibernateUtil: Se ha abierto una nueva sesion con la BD");
            sessionFactory = new Configuration().configure("/hibernate/hibernate.cfg.xml").buildSessionFactory();
            session = sessionFactory.openSession();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static Session getSession() {
        System.out.println("-----------------------------------------------------------------\nHibernateUtil: Se esta accediendo a la sesion de la BD");
        return session;
    }

    public static List<Persona> buscarPersonas(int dni) throws SQLException{

        List<Persona> listaResultado = new ArrayList<Persona>();
        String driver = "org.postgresql.Driver";
        //String connectString = "jdbc:postgresql://192.168.137.175:5432/concurso";
        String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
        String user = "nmlosada";
        String password = "siisa1234";
        Statement stmt = null;
        Connection con = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stmt = con.createStatement();

            //probarHibernate();
            //UTILIZANDO UNA CONSULTA NORMAL
            String consultaSQL = "SELECT * FROM \"vw_profesionalCompleto\" WHERE numero_documento = '" + dni + "';";
            ResultSet rs = stmt.executeQuery(consultaSQL);

            int contadorDeRegistros = 1;
            while (rs.next()) {
                
                Persona personaEncontrada = new Persona();
                personaEncontrada.setApellido(rs.getString("apellido"));
                personaEncontrada.setNombres(rs.getString("nombre"));
                personaEncontrada.setDni(Integer.parseInt(rs.getString("numero_documento")));
                personaEncontrada.setSexo(rs.getString("sexo"));
                personaEncontrada.setLocalidadByLocalidadNacimiento(new Localidad(rs.getLong("local_id")));
                personaEncontrada.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
                personaEncontrada.setCuil(rs.getLong("cuil"));
                personaEncontrada.setTelefono(rs.getString("tel1"));
                personaEncontrada.setEmail(rs.getString("email"));
                
                listaResultado.add(personaEncontrada);
                contadorDeRegistros += 1;
            }
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            stmt.close();
            con.close();
            return listaResultado;
        }

    }
}
