/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import dominio.Cargo;
import dominio.Establecimiento;
import dominio.TribunalJurado;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public class ConexionRemota {

    
    public static void main(String[] args) {
        try {
            //consultarVista();
            //probarHibernate();
            
            
            
        } catch (Exception exGeneral) {
            System.out.println("Error!\n" + exGeneral.getMessage());
        }
//        String driver = "org.postgresql.Driver";
//        //String connectString = "jdbc:postgresql://192.168.137.175:5432/concurso";
//        String connectString = "jdbc:postgresql://localhost:5432/concursosDB";
//        String user = "nmlosada";
//        String password = "siisa1234";
//
//        try {
//            Class.forName(driver);
//            Connection con = DriverManager.getConnection(connectString, user, password);
//            Statement stmt = con.createStatement();
//
//            //probarHibernate();
//            //UTILIZANDO UNA CONSULTA NORMAL
//            String consultaSQL = "SELECT * FROM vw_usuarios";
//            ResultSet rs = stmt.executeQuery(consultaSQL);
        //UTILIZANDO UNA CONSULTA ALMACENADA
        //CallableStatement cs= con.prepareCall("{call sp_listardepartamentos()}");
        //ResultSet rs = cs.executeQuery();
//            int contadorDeRegistros = 1;
//            while (rs.next()) {
//                System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getString("codigo_de_profesional") + " NOMBRE: " + rs.getString("nombres"));
//                contadorDeRegistros += 1;
//            }

//            String consultaSQL = "SELECT * FROM area;";
//            ResultSet rs = stmt.executeQuery(consultaSQL);
//            int contadorDeRegistros = 1;
//            while (rs.next()) {
//                System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getString("id_area") + " AREA: " + rs.getString("nombre_area"));
//                contadorDeRegistros += 1;
//            }
//            stmt.close();
//            con.close();
//
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//            e.printStackTrace();
//        }
    }

    public static void consultarVista() throws SQLException {


        String driver = "org.postgresql.Driver";
                String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
        String user = "nmlosada";
        String password = "siisa1234";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            Statement stmt = con.createStatement();

          //UTILIZANDO UNA CONSULTA NORMAL
            String consultaSQL = "SELECT * FROM \"vw_profesionalCompleto\" WHERE numero_documento = '" + 23053786 + "';";
            ResultSet rs = stmt.executeQuery(consultaSQL);

            int contadorDeRegistros = 1;
            
            while (rs.next()) {
                System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getLong("codigo_de_profesional") + " - Nombre Completo: " + rs.getString("nombrecompleto")+" -Profesión: "+rs.getString("profmat"));
                contadorDeRegistros += 1;
            }
            stmt.close();
            con.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

    }



public static void probarHibernate() {
//        AreaDao areaDao = new AreaDaoImpl();
//        for (Area area : areaDao.getAll()) {
//            System.out.println("ID: " + area.getIdArea() + " - Nombre: " + area.getNombreArea() + " - Prefijo: " + area.getCodigoExpediente());
//        }

//        EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
//        for (Establecimiento establecimiento : establecimientoDao.getAll()) {
//            System.out.println("Id: " + establecimiento.getIdEstablecimiento() + " - Nombre: " + establecimiento.getNombre());
//        }
        
//        InstitucionDao institucionDao = new InstitucionDaoImpl();
//        for (Institucion inst : institucionDao.getAll()) {
//            System.out.println("Id Institucion: " + inst.getIdInstitucion() + " - Nombre: " + inst.getNombreInstitucion());
//        }
//        EstablecimientoDao estDao = new EstablecimientoDaoImpl();
//        System.out.println("Lista de Establecimientos: ");
//        for (Establecimiento esta : estDao.getAll()) {
//            System.out.println(esta.toString());
//        }
        
        TribunalJuradoDao tribDao = new TribunalJuradoDaoImpl();
        for (TribunalJurado jurado : tribDao.getAll()) {
            System.out.println("Persona: " + jurado.getPersona().getNombres());
        }
        
        
        
        
        
    }
}
