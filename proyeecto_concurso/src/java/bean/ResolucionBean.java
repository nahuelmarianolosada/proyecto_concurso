/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Resolucion;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanResolucion")
@ViewScoped
public class ResolucionBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //ATRIBUTOS
    private boolean banderaModificacionParcial = false;
    private boolean banderaProrroga = false;
    private Resolucion resolucionNueva;
    private List<Resolucion> listaResoluciones;
    private String dependenciaNumeroResolucion;

    @ManagedProperty("#{beanExpediente}")
    private ExpedienteBean beanExpediente;

    //GETTERS & SETTERS
    public boolean isBanderaModificacionParcial() {
        return banderaModificacionParcial;
    }

    public void setBanderaModificacionParcial(boolean banderaModificacionParcial) {
        this.banderaModificacionParcial = banderaModificacionParcial;
    }

    public boolean isBanderaProrroga() {
        return banderaProrroga;
    }

    public void setBanderaProrroga(boolean banderaProrroga) {
        this.banderaProrroga = banderaProrroga;
    }

    public Resolucion getResolucionNueva() {
        return resolucionNueva;
    }

    public void setResolucionNueva(Resolucion resolucionNueva) {
        this.resolucionNueva = resolucionNueva;
    }

    public List<Resolucion> getListaResoluciones() {
        return listaResoluciones;
    }

    public void setListaResoluciones(List<Resolucion> listaReoluciones) {
        this.listaResoluciones = listaReoluciones;
    }

    public String getDependenciaNumeroResolucion() {
        return dependenciaNumeroResolucion;
    }

    public void setDependenciaNumeroResolucion(String dependenciaNumeroResolucion) {
        this.dependenciaNumeroResolucion = dependenciaNumeroResolucion;
    }

    public ExpedienteBean getBeanExpediente() {
        return beanExpediente;
    }

    public void setBeanExpediente(ExpedienteBean beanExpediente) {
        this.beanExpediente = beanExpediente;
    }

    /**
     * Creates a new instance of ResolucionBean
     */
    public ResolucionBean() {
        banderaModificacionParcial = false;
        banderaProrroga = false;
        resolucionNueva = new Resolucion();
        listaResoluciones = new ArrayList<Resolucion>();
    }

    //METODOS
    public void cambiarEstadoModificacion() {
        if (banderaModificacionParcial) {
            banderaModificacionParcial = false;
        } else {
            banderaModificacionParcial = true;
        }
    }

    public void cambiarEstadoProrroga() {
        if (banderaProrroga) {
            banderaProrroga = false;
        } else {
            banderaProrroga = true;
        }
    }

    public void guardarResolucion() {
//        Object objeto = context.getExternalContext().getSessionMap().get("nombreDelBean");
//        nombreDelBean objetoBean = null;
//        if (objeto != null) {
//            objetoBean = (nombreDelBean) objeto;
//        }

        try {
            resolucionNueva.setExpediente(beanExpediente.getExpedienteNuevo());

            resolucionNueva.setModificacion(banderaModificacionParcial);
            resolucionNueva.setProrroga(banderaProrroga);
            listaResoluciones.add(resolucionNueva);

            nuevoMensajeInfo("Registro de Concursos de Salud - RESOLUCIÓN", "NºResolucion: " + resolucionNueva.getNumeroResolucion()
                    + " guardada éxitosamente");
        } catch (Exception ex1) {
            ex1.printStackTrace();
            nuevoMensajeAlerta("Registro de Concursos de Salud ERROR!", "Error al guardar la resolucion. Detalles: " + ex1.getCause());
        }
    }

}
