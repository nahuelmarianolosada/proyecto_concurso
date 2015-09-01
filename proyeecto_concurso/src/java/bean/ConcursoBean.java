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
import hibernate.dao.ExpedienteDao;
import hibernate.dao.PostulanteDao;
import hibernate.dao.ResolucionDao;
import hibernate.dao.TribunalDao;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.UnidadDeOrganizacionDao;
import hibernate.dao.impl.ExpedienteDaoImpl;
import hibernate.dao.impl.PostulanteDaoImpl;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.impl.TribunalDaoImpl;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import hibernate.dao.impl.UnidadDeOrganizacionDaoImpl;
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
    private static List<Tribunal> listaFinalTribunales;
    
    private Cargo cargoFinalSeleccionado;
    private Postulante postulanteFinalSeleccionado;
    
    public static List<Expediente> listaTab_expedientes;
    
    public static List<Resolucion> listaTab_resoluciones;

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
        listaFinalTribunales = new ArrayList<>();
        listaFinalJurados = new ArrayList<>();
        
        inicializar();
    }
    
    public static List<Expediente> getListaTab_expedientes() {
        return listaTab_expedientes;
    }
    
    public static void setListaTab_expedientes(List<Expediente> listaTab_expedientes) {
        ConcursoBean.listaTab_expedientes = listaTab_expedientes;
    }
    
    public static List<Resolucion> getListaTab_resoluciones() {
        return listaTab_resoluciones;
    }
    
    public static void setListaTab_resoluciones(List<Resolucion> listaTab_resoluciones) {
        ConcursoBean.listaTab_resoluciones = listaTab_resoluciones;
    }
    
    public static List<Tribunal> getListaFinalTribunales() {
        return listaFinalTribunales;
    }
    
    public static void setListaFinalTribunales(List<Tribunal> listaFinalTribunales) {
        ConcursoBean.listaFinalTribunales = listaFinalTribunales;
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
    
    public Cargo getCargoFinalSeleccionado() {
        return cargoFinalSeleccionado;
    }
    
    public void setCargoFinalSeleccionado(Cargo cargoSeleccionado) {
        this.cargoFinalSeleccionado = cargoSeleccionado;
    }
    
    public Postulante getPostulanteFinalSeleccionado() {
        return postulanteFinalSeleccionado;
    }
    
    public void setPostulanteFinalSeleccionado(Postulante postulanteSeleccionado) {
        this.postulanteFinalSeleccionado = postulanteSeleccionado;
    }

    /**
     *
     * M E T O D O S
     *
     */
    public static void pasarVistaDePestania() {
        
        if (!expedienteFinalCargado.getNumeroExpediente().equals("")) {
            numeroDePestania += 1;
        }
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
        System.out.println("ConcursoBean.validarPestania() => mostrando [" + event.getTab().getTitle() + "]");
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
                RequestContext context = RequestContext.getCurrentInstance();
                context.update("tabuladorPestañero:formTribunal:tblTribunal:lblTribunales");
                break;
            }
            case "Postulantes": {
                setNumeroDePestania(4);
                break;
            }
            case "Resultado": {
                setNumeroDePestania(5);
                RequestContext context = RequestContext.getCurrentInstance();
                context.update("tabuladorPestañero:formResultadoConcurso");
                break;
            }
        }
    }
    
    public void inicializar() {
        
        RequestContext context = RequestContext.getCurrentInstance();
        
        ExpedienteDao expedienteDao = new ExpedienteDaoImpl();
        listaTab_expedientes = expedienteDao.getAll();
        
        ResolucionDao resolucionDao = new ResolucionDaoImpl();
        listaTab_resoluciones = resolucionDao.getAll();

//        expedienteFinalCargado = expDao.getExpediente("711-00001/1951");
//
//        ResolucionDao resDao = new ResolucionDaoImpl();
//        setListaFinalResoluciones(resDao.getResoluciones(expedienteFinalCargado));
//
//        CargoDao cargoDao = new CargoDaoImpl();
//        setListaFinalCargos(cargoDao.getListaCargosDeResolucion(listaFinalResoluciones.get(0)));
//
//        TribunalJuradoDao juradoDao = new TribunalJuradoDaoImpl();
//        setListaFinalJurados(juradoDao.getJuradosDelTribunal(listaFinalResoluciones.get(0).getTribunal()));
//
//        PostulanteDao postulanteDao = new PostulanteDaoImpl();
//        for (Cargo cargo : getListaFinalCargos()) {
//            if (postulanteDao.getPostulanteAcreditados(cargo) != null) {
//                listaFinalPostulantes.add(postulanteDao.getPostulanteAcreditados(cargo));
//            }
//        }
        context.update("formMostrar:menuAccordion");
    }
    
    public void guardarExpedienteFinal() {
        PostulanteDao postulanteDao = new PostulanteDaoImpl();
        try {
            RequestContext context = RequestContext.getCurrentInstance();
            ExpedienteDao expDao = new ExpedienteDaoImpl();
            System.out.println("ConcursoBean.guardarExpedienteFinal() => GUARDANDO " + expedienteFinalCargado.toString());
            expDao.insertar(expedienteFinalCargado);
            System.out.println("----------------------Se a guardado el expediente");
            
            TribunalDao tribunalDao = new TribunalDaoImpl();
            for (Tribunal tribunal : listaFinalTribunales) {
                System.out.println("ConcursoBean.guardarExpedienteFinal() => GUARDANDO " + tribunal.toString());
                tribunalDao.insertar(tribunal);
            }
            System.out.println("----------------------Se a guardado la lista de Tribunales");
            
            ResolucionDao resolucionDao = new ResolucionDaoImpl();
            for (Resolucion resolucion : listaFinalResoluciones) {
                System.out.println("ConcursoBean.guardarExpedienteFinal() => GUARDANDO " + resolucion.toString());
                resolucionDao.insertar(resolucion);
            }
            System.out.println("----------------------Se a guardado la lista de Resoluciones");
            
            CargoDao cargoDao = new CargoDaoImpl();
            for (Cargo cargo : listaFinalCargos) {
                cargo.setIdCargo(cargoDao.generarNuevoIdCargo());
                System.out.println("ConcursoBean.guardarExpedienteFinal() => GUARDANDO " + cargo.toString());
                for (int i = 0; i < getListaFinalPostulantes().size(); i++) {
                    if (getListaFinalPostulantes().get(i).getCargo().getIdCargo() == cargo.getIdCargo()) {
                        cargo.setEsDesierto(true);
                        break;
                    }

                //if (postulanteDao.getPostulanteAcreditados(cargo) != null) {
                    cargoDao.insertar(cargo);
                }
            }
            System.out.println("----------------------Se a guardado la lista de Cargos");
            
            for (Postulante postulante : listaFinalPostulantes) {
                postulanteDao.insertar(postulante);
            }
            System.out.println("----------------------Se a guardado la lista de Postulantes");
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Expediente Correctamente Guardado");
            inicializar();
            
            context.update("form:panel");
            
        } catch (Exception exGeneral) {
            nuevoMensajeAlerta("Error! " + exGeneral.getCause(), exGeneral.getMessage());
            exGeneral.printStackTrace();
        }
        
        
    }
    
}
