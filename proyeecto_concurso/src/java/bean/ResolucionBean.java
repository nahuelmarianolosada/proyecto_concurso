/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Resolucion;
import dominio.Tribunal;
import hibernate.dao.ResolucionDao;
import hibernate.dao.impl.ResolucionDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
    private boolean datosValidos;//Bandera que se referencia a la vista para habilitar la pestaña siguiente
    private int anioNumeroResolucion;

    @ManagedProperty("#{beanExpediente}")
    private ExpedienteBean beanExpediente;

    @ManagedProperty("#{beanCargo}")
    private CargoBean beanCargo;

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

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public int getAnioNumeroResolucion() {
        return anioNumeroResolucion;
    }

    public void setAnioNumeroResolucion(int anioNumeroResolucion) {
        this.anioNumeroResolucion = anioNumeroResolucion;
    }

    public CargoBean getBeanCargo() {
        return beanCargo;
    }

    public void setBeanCargo(CargoBean beanCargo) {
        this.beanCargo = beanCargo;
    }

    /**
     * Creates a new instance of ResolucionBean
     */
    public ResolucionBean() {
        banderaModificacionParcial = false;
        banderaProrroga = false;
        resolucionNueva = new Resolucion();
        listaResoluciones = new ArrayList<Resolucion>();
        datosValidos = false;
        
        ResolucionDao resolucionDao = new ResolucionDaoImpl();
        listaResoluciones = resolucionDao.getAll();
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

    public void onDateSelect(SelectEvent event) {
        
    }

    public void guardarResolucion() {
//        Object objeto = context.getExternalContext().getSessionMap().get("nombreDelBean");
//        nombreDelBean objetoBean = null;
//        if (objeto != null) {
//            objetoBean = (nombreDelBean) objeto;
//        }

        try {
            resolucionNueva.setExpediente(beanExpediente.getExpedienteNuevo());
            String numeroResolucion = resolucionNueva.getNumeroResolucion();
            resolucionNueva.setNumeroResolucion(numeroResolucion + "-" + dependenciaNumeroResolucion + "/" + anioNumeroResolucion);
            resolucionNueva.setModificacion(banderaModificacionParcial);
            resolucionNueva.setProrroga(banderaProrroga);
            resolucionNueva.setTribunal(new Tribunal());
            listaResoluciones.add(resolucionNueva);

            beanCargo.getCargoNuevo().setResolucion(resolucionNueva);

//            RequestContext context = RequestContext.getCurrentInstance();
//            context.update("tabuladorPestañero:formCargos:tblCargos");
            pasarVistaDePestania();
            System.err.println("ResolucionBean.guardarResolucion() => " + resolucionNueva.toString());

            nuevoMensajeInfo("Registro de Concursos de Salud - RESOLUCIÓN", "NºResolucion: " + resolucionNueva.getNumeroResolucion()
                    + " guardada éxitosamente");
            datosValidos = true;
        } catch (Exception ex1) {
            ex1.printStackTrace();
            nuevoMensajeAlerta("Error al guardar la resolucion", " " + ex1.toString());
        }
    }

    
    
}
