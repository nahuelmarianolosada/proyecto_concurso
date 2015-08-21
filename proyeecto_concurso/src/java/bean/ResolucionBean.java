/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Expediente;
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
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;

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


    /**
     * Creates a new instance of ResolucionBean
     */
    public ResolucionBean() {
        banderaModificacionParcial = false;
        banderaProrroga = false;
        resolucionNueva = new Resolucion();
        listaResoluciones = new ArrayList<Resolucion>();
        datosValidos = false;
        ResolucionDao resDao = new ResolucionDaoImpl();
        listaResoluciones = resDao.getAll();
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

    public void inicializarResolucionNueva() {
        banderaModificacionParcial = false;
        banderaProrroga = false;

        //En caso de que se guarden mas de una resolucion, seteamos
        //que hace referencia al expediente de la resolucion
        Expediente expedienteAuxiliar = resolucionNueva.getExpediente();

        resolucionNueva = new Resolucion();
        resolucionNueva.setExpediente(expedienteAuxiliar);
//resolucionNueva.setExpediente(getExpedienteFinalCargado());
        resolucionNueva.setTribunal(new Tribunal());

        RequestContext context = RequestContext.getCurrentInstance();
        context.update("dlgNuevaResolucion");
    }

    public void guardarResolucion() {

        try {
            String numeroResolucion = resolucionNueva.getNumeroResolucion();
            resolucionNueva.setNumeroResolucion(numeroResolucion + "-" + dependenciaNumeroResolucion + "/" + anioNumeroResolucion);
            if (banderaModificacionParcial) {
                resolucionNueva.setModificacion(banderaModificacionParcial);
            }
            if (banderaProrroga) {
                resolucionNueva.setProrroga(banderaProrroga);
            }
            resolucionNueva.setTribunal(new Tribunal());
            listaResoluciones.add(resolucionNueva);

           // pasarVistaDePestania();
            System.err.println("\033[32mResolucionBean.guardarResolucion() => " + resolucionNueva.toString());

            nuevoMensajeInfo("Registro de Concursos de Salud - RESOLUCIÓN", "NºResolucion: " + resolucionNueva.getNumeroResolucion()
                    + " guardada éxitosamente");

            inicializarResolucionNueva();

            
        }
        catch (Exception ex1) {
            ex1.printStackTrace();
            nuevoMensajeAlerta("Error al guardar la resolucion", " " + ex1.toString());
        }
    }

    public void guardarListaResoluciones() {
        setListaFinalResoluciones(listaResoluciones);
        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Se a guardado la lista de resoluciones");
        pasarVistaDePestania();
        datosValidos = true;
    }

}
