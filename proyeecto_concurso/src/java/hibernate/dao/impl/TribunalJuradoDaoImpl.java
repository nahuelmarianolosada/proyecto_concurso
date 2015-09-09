/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao.impl;

import dominio.Tribunal;
import dominio.TribunalJurado;
import hibernate.HibernateUtil;
import static hibernate.HibernateUtil.getSession;
import hibernate.dao.TribunalJuradoDao;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.exception.DataException;

/**
 *
 * @author SIISAJUJUY
 */
public class TribunalJuradoDaoImpl extends HibernateUtil implements TribunalJuradoDao {

    @Override
    public List<TribunalJurado> getAll() {
        Criteria criteria = getSession().createCriteria(TribunalJurado.class);
        criteria.addOrder(Order.asc("idTribunalJurado"));
        List<TribunalJurado> lista = criteria.list();
        return lista;
    }

    @Override
    public TribunalJurado getTribunalJurado(int idTribunalJurado) {
        return (TribunalJurado) getSession().get(TribunalJurado.class, idTribunalJurado);
    }

    @Override
    public void insertar(TribunalJurado tribunalJurado) throws SQLException{
        System.out.println("\033[32TribunalJuradoDaoImpl.insertar() => Guardando " + tribunalJurado.toString());
        try {
            getSession().beginTransaction();
            getSession().save(tribunalJurado);
            getSession().getTransaction().commit();

        } catch (DataException exData) {

            String driver = "org.postgresql.Driver";
            String connectString = "jdbc:postgresql://localhost:5432/concursosDB";
            String user = "nmlosada";
            String password = "siisa1234";
            Connection con = null;
            PreparedStatement stmt = null;

            try {
                Class.forName(driver);
                con = DriverManager.getConnection(connectString, user, password);

                String insertTableSQL = "INSERT INTO tribunal_jurado"
                        + "(id_persona, tribunal_id_tribunal, estado, presencia,condicion,institucion_id_institucion,establecimiento_codigo_siisa,id_tribunal_jurado) VALUES"
                        + "(?,?,?,?,?,?,?,?)";

                stmt = con.prepareStatement(insertTableSQL);

                stmt.setInt(1, tribunalJurado.getPersona().getIdPersona());
                stmt.setInt(2, tribunalJurado.getTribunal().getIdTribunal());
                stmt.setString(3, tribunalJurado.getEstado());
                stmt.setBoolean(4, tribunalJurado.getPresencia());
                stmt.setString(5, tribunalJurado.getCondicion());
                stmt.setInt(6, tribunalJurado.getInstitucion().getIdInstitucion());
                stmt.setLong(7, tribunalJurado.getEstablecimiento().getCodigoSiisa());
                stmt.setInt(8, tribunalJurado.getIdTribunalJurado());

                // execute insert SQL stetement
                stmt.executeUpdate();
                
            } catch (Exception ex) {
                ex.printStackTrace();
            } finally {
                stmt.close();
                con.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void eliminar(TribunalJurado tribunalJurado) {
        try {
            getSession().beginTransaction();
            getSession().delete(tribunalJurado);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public void modificar(TribunalJurado tribunalJurado
    ) {
        try {
            getSession().beginTransaction();
            getSession().update(tribunalJurado);
            getSession().getTransaction().commit();

        } catch (Exception e) {
            e.printStackTrace();
            getSession().getTransaction().rollback();
        }
    }

    @Override
    public int generarNuevoIdJurado() {

        Criteria criteria = getSession().createCriteria(TribunalJurado.class);
        criteria.addOrder(Order.desc("idTribunalJurado"));
        if (!criteria.list().isEmpty()) {
            TribunalJurado ultimoTribunalJurado = (TribunalJurado) criteria.list().get(0);
            return ultimoTribunalJurado.getIdTribunalJurado() + 1;
        } else {
            return 0;
        }
    }

    @Override
    public List<TribunalJurado> getJuradosDelTribunal(Tribunal tribunal
    ) {

        Criteria criteria = getSession().createCriteria(TribunalJurado.class);
        criteria.add(Restrictions.eq("tribunal", tribunal));
        List<TribunalJurado> lista = criteria.list();
        return lista;

    }

}
