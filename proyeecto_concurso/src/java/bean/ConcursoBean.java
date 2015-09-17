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
import hibernate.dao.impl.ExpedienteDaoImpl;
import hibernate.dao.impl.PostulanteDaoImpl;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.impl.TribunalDaoImpl;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
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

    private Expediente expedienteSeleccionado;

    private List<Resolucion> resolucionesPorExpedienteSeleccionado;
    
    private List<Cargo> cargosPorResolucion;
    
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

        expedienteSeleccionado = new Expediente();
        resolucionesPorExpedienteSeleccionado = new ArrayList<>();
        cargosPorResolucion = new ArrayList<>();

        inicializar();

        //recargarDeDatosFinales();
    }

    public List<Cargo> getCargosPorResolucion() {
        return cargosPorResolucion;
    }

    public void setCargosPorResolucion(List<Cargo> cargosPorResolucion) {
        this.cargosPorResolucion = cargosPorResolucion;
    }
    

    public Expediente getExpedienteSeleccionado() {
        return expedienteSeleccionado;
    }

    public void setExpedienteSeleccionado(Expediente expedienteSeleccionado) {
        this.expedienteSeleccionado = expedienteSeleccionado;
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

    public List<Resolucion> getResolucionesPorExpedienteSeleccionado() {
        return resolucionesPorExpedienteSeleccionado;
    }

    public void setResolucionesPorExpedienteSeleccionado(List<Resolucion> resolucionesPorExpedienteSeleccionado) {
        this.resolucionesPorExpedienteSeleccionado = resolucionesPorExpedienteSeleccionado;
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

    
    
    public void eliminarExpediente(){
        System.out.println("Aqui se implementa el delete en cascada :P");
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
        
        expedienteFinalCargado = new Expediente();
        listaFinalResoluciones = new ArrayList<>();
        

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

    public void buscarExpediente(Expediente expediente) {
        ResolucionDao resolucionDao = new ResolucionDaoImpl();
        CargoDao cargoDao = new CargoDaoImpl();
        expedienteSeleccionado = expediente;
        resolucionesPorExpedienteSeleccionado = resolucionDao.getResoluciones(expedienteSeleccionado);
        for (Resolucion resolucion : resolucionesPorExpedienteSeleccionado) {
            for (Cargo cargo : cargoDao.getCargos(resolucion)) {
                //Verificamos que no exista el cargo en la lista
                if(cargosPorResolucion.indexOf(cargo) == -1){
                    cargosPorResolucion.add(cargo);
                }
            }
            
        }
    }

    public void recargarDeDatosFinales() {
        ExpedienteDao expDao = new ExpedienteDaoImpl();
        expedienteFinalCargado = expDao.getExpediente("714-00024/1977");

        ResolucionDao resDao = new ResolucionDaoImpl();
        setListaFinalResoluciones(resDao.getResoluciones(expedienteFinalCargado));

        CargoDao cargoDao = new CargoDaoImpl();
        setListaFinalCargos(cargoDao.getListaCargosDeResolucion(listaFinalResoluciones.get(0)));

        TribunalJuradoDao juradoDao = new TribunalJuradoDaoImpl();
        setListaFinalJurados(juradoDao.getJuradosDelTribunal(listaFinalResoluciones.get(0).getTribunal()));

        PostulanteDao postulanteDao = new PostulanteDaoImpl();
        for (Cargo cargo : getListaFinalCargos()) {
            if (postulanteDao.getPostulanteAcreditados(cargo) != null) {
                listaFinalPostulantes.add(postulanteDao.getPostulanteAcreditados(cargo));
            }
        }
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
                System.out.println("ConcursoBean.guardarExpedienteFinal() => GUARDANDO " + cargo.toString());
                cargoDao.insertar(cargo);
            }
            System.out.println("----------------------Se a guardado la lista de Cargos");

            TribunalJuradoDao juradoDao = new TribunalJuradoDaoImpl();
            for (TribunalJurado jurado : getListaFinalJurados()) {
                System.out.println("ConcursoBean.guardarExpedienteFinal() => Guardando " + jurado.toString());
                juradoDao.insertar(jurado);
            }
            System.out.println("----------------------Se a guardado la lista de Jurados");

            for (Postulante postulante : listaFinalPostulantes) {
                System.out.println("ConcursoBean.guardarExpedienteFinal() => Guardando " + postulante.toString());
                postulanteDao.insertar(postulante);
            }
            System.out.println("----------------------Se a guardado la lista de Postulantes");
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Expediente Correctamente Guardado");
            inicializar();
            setNumeroDePestania(0);
            context.update("tabuladorPestañero");
            context.update("form:panel");

        } catch (Exception exGeneral) {
            nuevoMensajeAlerta("Error! " + exGeneral.getCause(), exGeneral.getMessage());
            exGeneral.printStackTrace();
        }

    }

}
