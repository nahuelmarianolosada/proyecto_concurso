/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bd;

import hibernate.dao.impl.TribunalJuradoDaoImpl;
import hibernate.dao.impl.LocalidadDaoImpl;
import hibernate.dao.LocalidadDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import dominio.Persona;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author favio
 */
public class ConexionRefeps {

    public List<Persona> buscarProfesionalRefepsNombreCompleto(String nombreCompleto) {

       LocalidadDao daoLocalidad = new LocalidadDaoImpl();
        List<Persona> listaPersona = new ArrayList();

        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost:5432/siisaDB";
        String user = "nmlosada";
        String password = "siisa1234";

        try {
            Class.forName(driver);
            Connection con = DriverManager.getConnection(connectString, user, password);
            Statement stmt = con.createStatement();

            String consultaSQL = "SELECT * FROM \"vw_profesionalDatosPersonales\" WHERE nombreCompleto like upper('%"+nombreCompleto+"%');";
            ResultSet rs = stmt.executeQuery(consultaSQL);

            int contadorDeRegistros = 0;

            while (rs.next()) {
                //  System.out.println(contadorDeRegistros + " - CODIGO: " + rs.getLong("codigo_de_profesional") + " - Nombre Completo: " + rs.getString("nombrecompleto") + " - dNI: " + rs.getString("numero_documento"));
                contadorDeRegistros += 1;
                Persona persona = new Persona();
                
                persona.setDni(Integer.parseInt(rs.getString("numero_documento")));
                persona.setApellido(rs.getString("apellido"));
                persona.setNombres(rs.getString("nombre"));
                persona.setCuil(rs.getLong("cuil"));
                persona.setSexo(rs.getString("sexo"));
                persona.setFechaDeNacimiento(rs.getDate("fecha_de_nacimiento"));
                persona.setDireccion(rs.getString("direccion"));
                persona.setTelefono(rs.getString("tel1"));
                persona.setEmail(rs.getString("email"));
                persona.setLocalidadByLocalidadNacimiento(daoLocalidad.getLocalidadPorCodigo(rs.getLong("localidad_nac")));

                listaPersona.add(persona);

            }

            stmt.close();
            con.close();

            System.out.println("Nombres    Apellido    Localidad Nacimiento");
            for (Persona person : listaPersona) {
                System.out.println(person.getNombres() + " " + person.getApellido() + " " + person.getLocalidadByLocalidadNacimiento().getCodigoLocalidad());

            }
            

        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        }

        return listaPersona;

    }

}
