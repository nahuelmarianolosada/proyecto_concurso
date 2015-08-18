/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import hibernate.dao.CargoDao;
import dominio.Cargo;
import hibernate.dao.impl.CargoDaoImpl;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.InstitucionDao;
import hibernate.dao.impl.InstitucionDaoImpl;
import java.io.Serializable;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.TabChangeEvent;
import dominio.Establecimiento;
import dominio.Expediente;
import dominio.Institucion;
import dominio.Postulante;
import dominio.Resolucion;
import dominio.Tribunal;
import dominio.TribunalJurado;
import java.util.ArrayList;

/**
 *
 * @author Nahuel Mariano
 *
 */
import java.util.List;
import org.primefaces.context.RequestContext;

@ManagedBean(name = "beanConcurso")
@ViewScoped
public class ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int numeroDePestania = 0;//Indice en la pestaña "tabuladorPestañero"
    private boolean banderaInstitucion;
    private boolean banderaEstablecimiento;
    private List<Establecimiento> listaEstablecimientos;
    private List<Institucion> listaInstituciones;

    private static Expediente expedienteFinalCargado;
    private static List<Resolucion> listaFinalResoluciones;
    private static List<Cargo> listaFinalCargos;
    private static List<TribunalJurado> listaFinalJurados;
    private static List<Postulante> listaFinalPostulantes;

    /**
     * Creates a new instance of ConcursoBean
     */
    public ConcursoBean() {
        //init();
        refreshListas();
        expedienteFinalCargado = new Expediente();
        listaFinalResoluciones = new ArrayList<>();
        listaFinalCargos = new ArrayList<>();
        listaFinalPostulantes = new ArrayList<>();
    }

    public int getNumeroDePestania() {
        return numeroDePestania;
    }

    public void setNumeroDePestania(int numeroDePestania) {
        this.numeroDePestania = numeroDePestania;
    }

    public List<Establecimiento> getListaEstablecimientos() {
        return listaEstablecimientos;
    }

    public void setListaEstablecimientos(List<Establecimiento> listaEstablecimientos) {
        this.listaEstablecimientos = listaEstablecimientos;
    }

    public List<Institucion> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<Institucion> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public Expediente getExpedienteFinalCargado() {
        return expedienteFinalCargado;
    }

    public void setExpedienteFinalCargado(Expediente expedienteFinalCargado) {
        this.expedienteFinalCargado = expedienteFinalCargado;
    }

    public List<Resolucion> getListaFinalResoluciones() {
        return listaFinalResoluciones;
    }

    public void setListaFinalResoluciones(List<Resolucion> listaFinalResoluciones) {
        this.listaFinalResoluciones = listaFinalResoluciones;
    }

    public List<Cargo> getListaFinalCargos() {
        return listaFinalCargos;
    }

    public void setListaFinalCargos(List<Cargo> listaFinalCargos) {
        this.listaFinalCargos = listaFinalCargos;
    }

    public List<TribunalJurado> getListaFinalJurados() {
        return listaFinalJurados;
    }

    public void setListaFinalJurados(List<TribunalJurado> listaFinalJurados) {
        this.listaFinalJurados = listaFinalJurados;
    }

    public List<Postulante> getListaFinalPostulantes() {
        return listaFinalPostulantes;
    }

    public void setListaFinalPostulantes(List<Postulante> listaFinalPostulantes) {
        this.listaFinalPostulantes = listaFinalPostulantes;
    }

    /**
     *
     * M E T O D O S
     *
     */
    public static void pasarVistaDePestania() {
        numeroDePestania += 1;
        System.out.println("ConcursoBean.pasarVistaDePagina(): La pestaña ahora es " + numeroDePestania);
    }

    public void habilitarCmbInstitucion() {
        if (banderaInstitucion) {
            banderaInstitucion = false;
        } else {
            if (listaInstituciones == null) {
                InstitucionDao instDao = new InstitucionDaoImpl();
                listaInstituciones = instDao.getAll();
            }
            banderaInstitucion = true;
        }
    }

    public void habilitarCmbEstablecimiento() {
        if (banderaEstablecimiento) {
            banderaEstablecimiento = false;
        } else {
            if (listaEstablecimientos == null) {
                EstablecimientoDao estDao = new EstablecimientoDaoImpl();
                listaEstablecimientos = estDao.getAll();
            }
            banderaEstablecimiento = true;
        }
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

    public void refreshListas() {

        //ProfesionDao profDao = new ProfesionDaoImpl();
        //listaProfesiones = profDao.getAll();
        //CargoDao cargoDao = new CargoDaoImpl();
//    beanCargo.setListaCargos(cargoDao.getAll());
        InstitucionDao instDao = new InstitucionDaoImpl();
        listaInstituciones = instDao.getAll();
        EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
        listaEstablecimientos = establecimientoDao.getAll();
    }

    /**
     *
     * Método que se llama cada vez que se pasa de pestaña, mostrando con un
     * mensaje informativo el nombre de la pestaña que se va a mostrar
     *
     * @param event Evento que recibe
     */
    public void validarPestania(TabChangeEvent event) {
        System.out.println("ConcursoBean.validarPestania() => mostrando [" + event.getTab().getTitle() + "}");
        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Pestaña Activa: " + event.getTab().getTitle());
        switch (event.getTab().getTitle()) {
            case "Expediente": {
                setNumeroDePestania(0);
                break;
            }
            case "Resoluciones": {
                setNumeroDePestania(1);
                break;
            }
            case "Cargos": {
                setNumeroDePestania(2);
                break;
            }
            case "Tribunal": {
                setNumeroDePestania(3);
                break;
            }
            case "Postulantes": {
                setNumeroDePestania(4);
                break;
            }
            case "Resultado": {
                setNumeroDePestania(5);
                getExpedienteFinalCargado();
                getListaFinalResoluciones();
                Expediente exp = getExpedienteFinalCargado();
                getListaFinalCargos();
                getListaFinalPostulantes();
                RequestContext context = RequestContext.getCurrentInstance();
                context.update("tabuladorPestañero:formResultadoConcurso");
                break;
            }
        }
    }

}
