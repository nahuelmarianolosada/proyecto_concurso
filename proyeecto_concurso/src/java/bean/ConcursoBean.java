/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.UnidadDeOrganizacion;
import dominio.Cargo;
import dominio.Establecimiento;
import dominio.Expediente;
import dominio.Institucion;
import dominio.Localidad;
import dominio.Persona;
import dominio.Profesion;
import dominio.Resolucion;
import dominio.Tribunal;
import dominio.TribunalJurado;
import hibernate.HibernateUtil;
import hibernate.dao.UnidadDeOrganizacionDao;
import hibernate.dao.CargoDao;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.ExpedienteDao;
import hibernate.dao.InstitucionDao;
import hibernate.dao.LocalidadDao;
import hibernate.dao.ProfesionDao;
import hibernate.dao.ResolucionDao;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.UnidadDeOrganizacionDaoImpl;
import hibernate.dao.impl.CargoDaoImpl;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.ExpedienteDaoImpl;
import hibernate.dao.impl.InstitucionDaoImpl;
import hibernate.dao.impl.LocalidadDaoImpl;
import hibernate.dao.impl.ProfesionDaoImpl;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.impl.TribunalJuradoDaoImpl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Nahuel Losada
 */
@ManagedBean(name = "beanConcurso")
@ViewScoped
public class ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

        
        
//    @ManagedProperty("#{beanResolucion}")
//    private ResolucionBean beanResolucion;
//
//    @ManagedProperty("#{beanCargo}")
//    private CargoBean beanCargo;
//
//    @ManagedProperty("#{beanTribunal}")
//    private TribunalBean beanTribunal;
//    private List<Profesion> listaProfesiones;
//
//    private List<Establecimiento> listaEstablecimientos;
//
//    private List<Institucion> listaInstituciones;
//
//    private boolean banderaInstitucion;
//    private boolean banderaEstablecimiento;
//
//    private List<Localidad> listaLocalidades;
    /**
     * Creates a new instance of ConcursoBean
     */
    public ConcursoBean() {
        //init();

        //refreshListas();
    }

    

//    public TribunalBean getBeanTribunal() {
//        return beanTribunal;
//    }
//
//    public void setBeanTribunal(TribunalBean beanTribunal) {
//        this.beanTribunal = beanTribunal;
//    }
//
//    public CargoBean getBeanCargo() {
//        return beanCargo;
//    }
//
//    public void setBeanCargo(CargoBean beanCargo) {
//        this.beanCargo = beanCargo;
//    }
//
//    public ResolucionBean getBeanResolucion() {
//        return beanResolucion;
//    }
//
//    public void setBeanResolucion(ResolucionBean beanResolucion) {
//        this.beanResolucion = beanResolucion;
//    }
//    public List<Profesion> getListaProfesiones() {
//        return listaProfesiones;
//    }
//
//    public void setListaProfesiones(List<Profesion> listaProfesiones) {
//        this.listaProfesiones = listaProfesiones;
//    }
//
//    public List<Establecimiento> getListaEstablecimientos() {
//        return listaEstablecimientos;
//    }
//
//    public void setListaEstablecimientos(List<Establecimiento> listaEstablecimientos) {
//        this.listaEstablecimientos = listaEstablecimientos;
//    }
//
//    public List<Institucion> getListaInstituciones() {
//        return listaInstituciones;
//    }
//
//    public void setListaInstituciones(List<Institucion> listaInstituciones) {
//        this.listaInstituciones = listaInstituciones;
//    }
//
//    public boolean isBanderaInstitucion() {
//        return banderaInstitucion;
//    }
//
//    public void setBanderaInstitucion(boolean banderaInstitucion) {
//        this.banderaInstitucion = banderaInstitucion;
//    }
//
//    public boolean isBanderaEstablecimiento() {
//        return banderaEstablecimiento;
//    }
//
//    public void setBanderaEstablecimiento(boolean banderaEstablecimiento) {
//        this.banderaEstablecimiento = banderaEstablecimiento;
//    }
//
//    public List<Localidad> getListaLocalidades() {
//        return listaLocalidades;
//    }
//
//    public void setListaLocalidades(List<Localidad> listaLocalidades) {
//        this.listaLocalidades = listaLocalidades;
//    }
    /**
     *
     * M E T O D O S
     *
     */
//    public List<String> buscarInstitucion(String nombreInstitucion) {
//        InstitucionDao instDao = new InstitucionDaoImpl();
//        List<String> results = new ArrayList<String>();
//
//        for (Institucion inst : instDao.getInstitucion(nombreInstitucion)) {
//            results.add(inst.getNombreInstitucion());
//        }
//
//        return results;
//    }
//    public void onInstitucionSeleccionada(SelectEvent event) {
//        for (Institucion inst : listaInstituciones) {
//            if (inst.getIdInstitucion() == beanTribunal.getJuradoNuevo().getInstitucion().getIdInstitucion()) {
//                beanTribunal.getJuradoNuevo().setInstitucion(inst);
//                break;
//            }
//        }
//        //nuevoMensajeInfo("Registro Provincial de Concursos de Salud", event.getObject().toString());
//        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", beanTribunal.getJuradoNuevo().getInstitucion().getIdInstitucion() + " " + beanTribunal.getJuradoNuevo().getInstitucion().getNombreInstitucion());
//    }
//    public List<String> buscarEstablecimiento(String nombreEstablecimiento) {
//        EstablecimientoDao instDao = new EstablecimientoDaoImpl();
//        List<String> results = new ArrayList<String>();
//
//        for (Establecimiento estab : instDao.getEstablecimiento(nombreEstablecimiento)) {
//            results.add(estab.getNombre());
//        }
//
//        return results;
//    }
//    public void onEstablecimientoSeleccionado(SelectEvent event) {
//        for (Establecimiento estab : listaEstablecimientos) {
//            if (estab.getNombre() == beanTribunal.getJuradoNuevo().getEstablecimiento().getNombre()) {
//                beanTribunal.getJuradoNuevo().setEstablecimiento(estab);
//                break;
//            }
//        }
//        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", beanTribunal.getJuradoNuevo().getEstablecimiento().getCodigoSiisa() + " " + beanTribunal.getJuradoNuevo().getEstablecimiento().getNombre());
//    }
//    public void habilitarCmbInstitucion() {
//        if (banderaInstitucion) {
//            banderaInstitucion = false;
//        } else {
//            if (listaInstituciones == null) {
//                InstitucionDao instDao = new InstitucionDaoImpl();
//                listaInstituciones = instDao.getAll();
//            }
//            banderaInstitucion = true;
//        }
//    }
//    public void habilitarCmbEstablecimiento() {
//        if (banderaEstablecimiento) {
//            banderaEstablecimiento = false;
//        } else {
//            if (listaEstablecimientos == null) {
//                EstablecimientoDao estDao = new EstablecimientoDaoImpl();
//                listaEstablecimientos = estDao.getAll();
//            }
//            banderaEstablecimiento = true;
//        }
//    }
//    public void onPostulanteAdjSeleccionado() {
//
////        System.out.println("ConcursoBean.onPostulanteAdjSeleccionado: Matricula del Postulante: " + postulanteAdjudicado.getNumero_de_matricula() + ". " + postulanteAdjudicado.getApellido());
//    }
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

//    public void refreshListas() {
//
//        ProfesionDao profDao = new ProfesionDaoImpl();
//        listaProfesiones = profDao.getAll();
//
//        CargoDao cargoDao = new CargoDaoImpl();
////        beanCargo.setListaCargos(cargoDao.getAll());
//
//        EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
//        listaEstablecimientos = establecimientoDao.getAll();
//
//        InstitucionDao instDao = new InstitucionDaoImpl();
//        listaInstituciones = instDao.getAll();
//    }
    
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
            case "Resoluciones": {
//                ResolucionDao resDao = new ResolucionDaoImpl();
//                ExpedienteDao expDao = new ExpedienteDaoImpl();
                break;
            }
        }
    }
    

//    public void obtenerEstablecimiento(TabChangeEvent event) {
//        try {
//            nuevoMensajeInfo("Registro provincial de concursos de Salud", "Pestaña activa: " + event.getTab().getTitle());
//            //System.out.println("ConcursoBean:obtenerEstablecimiento():\nBuscando el codigo del Establecimiento seleccionado: \nCodigoSiisa => " + beanCargo.getCargoNuevo().getEstablecimiento().getCodigoSiisa() + "\tNombre => " + beanCargo.getCargoNuevo().getEstablecimiento().getNombre());
//            EstablecimientoDao establDao = new EstablecimientoDaoImpl();
//            //Establecimiento estEncontrado = establDao.getEstablecimiento(beanCargo.getCargoNuevo().getEstablecimiento().getCodigoSiisa());
////            if (estEncontrado != null) {
////                beanCargo.getCargoNuevo().setEstablecimiento(estEncontrado);
////            } else {
////                System.out.println("No se encontro el establecimiento con el codigo ");
////            }
//        } catch (Exception exGeneral) {
//            exGeneral.printStackTrace();
//        }
//    }
}
