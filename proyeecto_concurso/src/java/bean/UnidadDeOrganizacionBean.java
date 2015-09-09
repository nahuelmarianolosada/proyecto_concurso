/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import java.io.Serializable;
import hibernate.dao.UnidadDeOrganizacionDao;
import hibernate.dao.impl.UnidadDeOrganizacionDaoImpl;
import dominio.UnidadDeOrganizacion;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.primefaces.event.RowEditEvent;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;

/**
 *
 * @author favio
 */
@ManagedBean(name = "beanUnidadDeOrganizacion")
@ViewScoped
public class UnidadDeOrganizacionBean implements Serializable {

    private List<UnidadDeOrganizacion> listaUnidadOrganizacion;
    private UnidadDeOrganizacion udoSeleccionada;
    private UnidadDeOrganizacion udoNueva;
     @ManagedProperty("#{beanExpediente}")
    private ExpedienteBean beanExpediente;

    /**
     * Creates a new instance of UnidadDeOrganizacionBeanBean
     */
    public UnidadDeOrganizacionBean() {

        refreshLista();
        // udoNueva = new UnidadDeOrganizacion(new UnidadDeOrganizacionDaoImpl().generarNuevoIdUdo());
        udoNueva = new UnidadDeOrganizacion();
        udoSeleccionada = new UnidadDeOrganizacion();
    }

    public List<UnidadDeOrganizacion> getListaUnidadOrganizacion() {
        return listaUnidadOrganizacion;
    }

    public void setListaUnidadOrganizacion(List<UnidadDeOrganizacion> listaUnidadOrganizacion) {
        this.listaUnidadOrganizacion = listaUnidadOrganizacion;
    }

    public UnidadDeOrganizacion getUdoSeleccionada() {
        return udoSeleccionada;
    }

    public void setUdoSeleccionada(UnidadDeOrganizacion udoSeleccionada) {
        this.udoSeleccionada = udoSeleccionada;
    }

    public UnidadDeOrganizacion getUdoNueva() {
        return udoNueva;
    }

    public void setUdoNueva(UnidadDeOrganizacion udoNueva) {
        this.udoNueva = udoNueva;
    }

    /**
     * METODOS
     */
    public void refreshLista() {
        System.out.println((char) 27 + "[36mBeanUnidadDeOrganización.refreshlista()");
        UnidadDeOrganizacionDao udoDao = new UnidadDeOrganizacionDaoImpl();
        listaUnidadOrganizacion = udoDao.getAll();
        RequestContext context = RequestContext.getCurrentInstance();
                context.update(":formGestionarUdo:tblUdoRegistradas");

    }

    public void edicionCancelada() {
        nuevoMensajeInfo("Registro de Concursos de Salud", "Se a cancelado la edicion de la Unidad de Organización.");
    }

    public void nuevoMensajeInfo(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public void nuevoMensajeAlerta(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }

    public boolean validadUdo(UnidadDeOrganizacion unidad) {
        boolean resultado = true;
        for (UnidadDeOrganizacion udo : listaUnidadOrganizacion) {
            if (udo.getNombreUnidad().toLowerCase().equals(unidad.getNombreUnidad().toLowerCase()) && udo.getIdUnidadOrganizacion() != unidad.getIdUnidadOrganizacion()) {
                nuevoMensajeAlerta("Registro de Concursos de Salud", "El nombre de la organización " + unidad.getNombreUnidad() + " se encuentra registrada. Por favor ingrese uno diferente.");
                resultado = false;
                break;
            }
            if (udo.getCodigoUnidadDeOrganizacion() == unidad.getCodigoUnidadDeOrganizacion() && udo.getIdUnidadOrganizacion() != unidad.getIdUnidadOrganizacion()) {
                nuevoMensajeAlerta("Registro de Concursos de Salud", "El código de la organización " + unidad.getCodigoUnidadDeOrganizacion() + " se encuentra registrado. Por favor ingrese uno diferente.");
                resultado = false;
                break;
            }

        }

        return resultado;

    }

    public void edicionDeUnidadDeOrganizacion(RowEditEvent event) {

        UnidadDeOrganizacion udoModificada = (UnidadDeOrganizacion) event.getObject();
        if (validadUdo(udoModificada)) {
           
//            System.out.println("beanUnidadDeOrganizacion.edicionDeUnidadOrganizacion():id_unidad => " + udoModificada.getIdUnidadOrganizacion());
//            System.out.println("beanUnidadDeOrganizacion.edicionDeUnidadOrganizacion():nombre_unidad => " + udoModificada.getNombreUnidad());
//            System.out.println("beanUnidadDeOrganizacion.edicionDeUnidadOrganizacion():codigo_unidad_de_organizacion => " + udoModificada.getCodigoUnidadDeOrganizacion());
            try {
                guardarUdoEditada(udoModificada);
                refreshLista();
                beanExpediente.setListaUnidadDeOrganizacions(listaUnidadOrganizacion);
                nuevoMensajeInfo("Registro de Concursos de Salud", "UDO " + udoModificada.getNombreUnidad() + " modificado.");
            } catch (HibernateException ex1) {
                System.out.println("Error! " + ex1.getCause() + "\n" + ex1.getMessage());
            } catch (Exception exGeneral) {
                nuevoMensajeAlerta("Error!" + exGeneral.getClass(), exGeneral.getMessage());
            }
        } else {
            edicionCancelada();
            refreshLista();
            System.out.println("beanUnidadDeOrganizacion.edicionCancalada(): Nombre de la organización " + udoModificada.getNombreUnidad() + " 0 el codigo " + udoModificada.getCodigoUnidadDeOrganizacion() + " se encuentran repetido");
        }
    }

    public ExpedienteBean getBeanExpediente() {
        return beanExpediente;
    }

    public void setBeanExpediente(ExpedienteBean beanExpediente) {
        this.beanExpediente = beanExpediente;
    }

    public void guardarUdoEditada(UnidadDeOrganizacion unidad) throws SQLException {
        System.out.println("beanUnidadDeOrganizacion.guardarUDOEditada() => Guardando la UDO " + unidad.getIdUnidadOrganizacion());
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost/concursosDB";
        String user = "nmlosada";
        String password = "siisa1234";
        Connection con = null;
        Statement stmt = null;

        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stmt = con.createStatement();

            //UTILIZANDO UNA CONSULTA NORMAL
            String consultaSQL = "UPDATE unidad_de_organizacion SET nombre_unidad='" + unidad.getNombreUnidad() + "',codigo_unidad_de_organizacion=" + unidad.getCodigoUnidadDeOrganizacion() + " WHERE id_unidad_organizacion=" + unidad.getIdUnidadOrganizacion() + ";";
            stmt.executeUpdate(consultaSQL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            stmt.close();
            con.close();
        }

    }

    public void eliminar() {
        System.out.println("BeanUnidadDeOrganizacion.eliminar();");
        System.out.println("Udo que se va a eliminar: " + udoSeleccionada.getIdUnidadOrganizacion() + " nombre :" + udoSeleccionada.getNombreUnidad() + "codigo : " + udoSeleccionada.getCodigoUnidadDeOrganizacion());
        try {

            UnidadDeOrganizacionDao unidadDao = new UnidadDeOrganizacionDaoImpl();
            unidadDao.eliminarById(udoSeleccionada.getIdUnidadOrganizacion());
            refreshLista();
           // udoNueva.setIdUnidadOrganizacion(unidadDao.generarNuevoIdUdo());
            nuevoMensajeInfo("Registro de concursos de Salud", "Se a eliminado el usuario " + udoSeleccionada.getIdUnidadOrganizacion());
        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! " + ex1.getClass(), ex1.getMessage());
        }
    }
}
