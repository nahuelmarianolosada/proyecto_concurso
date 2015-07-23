/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Area;
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
import hibernate.dao.AreaDao;
import hibernate.dao.CargoDao;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.InstitucionDao;
import hibernate.dao.LocalidadDao;
import hibernate.dao.ProfesionDao;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.AreaDaoImpl;
import hibernate.dao.impl.CargoDaoImpl;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.InstitucionDaoImpl;
import hibernate.dao.impl.LocalidadDaoImpl;
import hibernate.dao.impl.ProfesionDaoImpl;
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
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.TransferEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.DualListModel;

/**
 *
 * @author Nahuel Losada laksndlaskdnklas
 */
@ManagedBean(name = "beanConcurso")
@ViewScoped
public class ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private boolean banderaModificacionParcial = false;
    private boolean banderaProrroga = false;
    private List<Cargo> listaCargosVacantes;
    private Cargo cargoSeleccionado;
    private List<Area> listaAreas;
    private Expediente expedienteNuevo;
    private Resolucion resolucionNueva;
    private Cargo cargoNuevo;
    private List<Profesion> listaProfesiones;
    private List<Cargo> listaCargos;
    private List<Establecimiento> listaEstablecimientos;
    private TribunalJurado juradoNuevo;
    private List<Institucion> listaInstituciones;
    private String categoriaJurado;
    private boolean banderaInstitucion;
    private boolean banderaEstablecimiento;
    private List<TribunalJurado> listaJurados;
    private List<Localidad> listaLocalidades;
    private List<Persona> listaPersonas;
    private Persona personaBuscada;
    private List<Persona> listaResultadoBusquedaPersona;
    

    /**
     * Creates a new instance of ConcursoBean
     */
    public ConcursoBean() {
        //init();
        banderaModificacionParcial = false;
        banderaProrroga = false;

        listaCargosVacantes = new ArrayList<Cargo>();
        listaCargosVacantes.add(new Cargo());
        cargoSeleccionado = new Cargo();

        listaAreas = new ArrayList<Area>();
        refreshListas();

        expedienteNuevo = new Expediente("", new Area(), 0, Integer.getInteger(""), "", "", Integer.getInteger(""));
        resolucionNueva = new Resolucion();
        cargoNuevo = new Cargo(listaEstablecimientos.get(0), listaProfesiones.get(0));
        try {
            juradoNuevo = new TribunalJurado(listaInstituciones.get(0), listaEstablecimientos.get(0), new Persona());
        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
        }
        System.out.println("ConcursoBean:ConcursoBean() => " + expedienteNuevo.toString());

        TribunalJuradoDao tribJura = new TribunalJuradoDaoImpl();
        listaJurados = tribJura.getAll();

        personaBuscada = new Persona();
        listaResultadoBusquedaPersona = new ArrayList<Persona>();
    }

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

    public List<Cargo> getListaCargosVacantes() {
        return listaCargosVacantes;
    }

    public void setListaCargosVacantes(List<Cargo> listaCargosVacantes) {
        this.listaCargosVacantes = listaCargosVacantes;
    }

    public Cargo getCargoSeleccionado() {
        return cargoSeleccionado;
    }

    public void setCargoSeleccionado(Cargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    public List<Area> getListaAreas() {
        return listaAreas;
    }

    public void setListaAreas(List<Area> listaAreas) {
        this.listaAreas = listaAreas;
    }

    public Expediente getExpedienteNuevo() {
        return expedienteNuevo;
    }

    public void setExpedienteNuevo(Expediente expedienteNuevo) {
        this.expedienteNuevo = expedienteNuevo;
    }

    public Resolucion getResolucionNueva() {
        return resolucionNueva;
    }

    public void setResolucionNueva(Resolucion resolucionNueva) {
        this.resolucionNueva = resolucionNueva;
    }

    public Cargo getCargoNuevo() {
        return cargoNuevo;
    }

    public void setCargoNuevo(Cargo cargoNuevo) {
        this.cargoNuevo = cargoNuevo;
    }

    public List<Profesion> getListaProfesiones() {
        return listaProfesiones;
    }

    public void setListaProfesiones(List<Profesion> listaProfesiones) {
        this.listaProfesiones = listaProfesiones;
    }

    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public List<Establecimiento> getListaEstablecimientos() {
        return listaEstablecimientos;
    }

    public void setListaEstablecimientos(List<Establecimiento> listaEstablecimientos) {
        this.listaEstablecimientos = listaEstablecimientos;
    }

    public TribunalJurado getJuradoNuevo() {
        return juradoNuevo;
    }

    public void setJuradoNuevo(TribunalJurado juradoNuevo) {
        this.juradoNuevo = juradoNuevo;
    }

    public List<Institucion> getListaInstituciones() {
        return listaInstituciones;
    }

    public void setListaInstituciones(List<Institucion> listaInstituciones) {
        this.listaInstituciones = listaInstituciones;
    }

    public String getCategoriaJurado() {
        return categoriaJurado;
    }

    public void setCategoriaJurado(String categoriaJurado) {
        this.categoriaJurado = categoriaJurado;
    }

    public boolean isBanderaInstitucion() {
        return banderaInstitucion;
    }

    public void setBanderaInstitucion(boolean banderaInstitucion) {
        this.banderaInstitucion = banderaInstitucion;
    }

    public boolean isBanderaEstablecimiento() {
        return banderaEstablecimiento;
    }

    public void setBanderaEstablecimiento(boolean banderaEstablecimiento) {
        this.banderaEstablecimiento = banderaEstablecimiento;
    }

    public List<TribunalJurado> getListaJurados() {
        return listaJurados;
    }

    public void setListaJurados(List<TribunalJurado> listaJurados) {
        this.listaJurados = listaJurados;
    }

    public List<Localidad> getListaLocalidades() {
        return listaLocalidades;
    }

    public void setListaLocalidades(List<Localidad> listaLocalidades) {
        this.listaLocalidades = listaLocalidades;
    }

    public List<Persona> getListaPersonas() {
        return listaPersonas;
    }

    public void setListaPersonas(List<Persona> listaPersonas) {
        this.listaPersonas = listaPersonas;
    }

    public Persona getPersonaBuscada() {
        return personaBuscada;
    }

    public void setPersonaBuscada(Persona personaBuscada) {
        this.personaBuscada = personaBuscada;
    }

    public List<Persona> getListaResultadoBusquedaPersona() {
        return listaResultadoBusquedaPersona;
    }

    public void setListaResultadoBusquedaPersona(List<Persona> listaResultadoBusquedaPersona) {
        this.listaResultadoBusquedaPersona = listaResultadoBusquedaPersona;
    }
    
    
    

    /**
     *
     * M E T O D O S
     *
     */
    
    public void buscarPersonaREFEPS(){
        try{
            listaResultadoBusquedaPersona = HibernateUtil.buscarPersonas(personaBuscada.getDni());
            if(listaResultadoBusquedaPersona.size()!=0){
                nuevoMensajeInfo("Registro Provincial de Concursos de Saludo", "Sin resultados coincidentes con el DNI " + personaBuscada.getDni());
            }
            
        }catch(SQLException exSQL){
            exSQL.printStackTrace();
        }
    }
    
    public List<String> buscarInstitucion(String nombreInstitucion) {
        InstitucionDao instDao = new InstitucionDaoImpl();
        List<String> results = new ArrayList<String>();

        for (Institucion inst : instDao.getInstitucion(nombreInstitucion)) {
            results.add(inst.getNombreInstitucion());
        }

        return results;
    }

    public void onInstitucionSeleccionada(SelectEvent event) {
        for (Institucion inst : listaInstituciones) {
            if (inst.getIdInstitucion() == juradoNuevo.getInstitucion().getIdInstitucion()) {
                juradoNuevo.setInstitucion(inst);
                break;
            }
        }
        //nuevoMensajeInfo("Registro Provincial de Concursos de Salud", event.getObject().toString());
        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", juradoNuevo.getInstitucion().getIdInstitucion() + " " + juradoNuevo.getInstitucion().getNombreInstitucion());
    }

    public List<String> buscarEstablecimiento(String nombreEstablecimiento) {
        EstablecimientoDao instDao = new EstablecimientoDaoImpl();
        List<String> results = new ArrayList<String>();

        for (Establecimiento estab : instDao.getEstablecimiento(nombreEstablecimiento)) {
            results.add(estab.getNombre());
        }

        return results;
    }

    public void onEstablecimientoSeleccionado(SelectEvent event) {
        for (Establecimiento estab : listaEstablecimientos) {
            if (estab.getNombre() == juradoNuevo.getEstablecimiento().getNombre()) {
                juradoNuevo.setEstablecimiento(estab);
                break;
            }
        }
        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", juradoNuevo.getEstablecimiento().getCodigoSiisa() + " " + juradoNuevo.getEstablecimiento().getNombre());
    }

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

    public void onPostulanteAdjSeleccionado() {

//        System.out.println("ConcursoBean.onPostulanteAdjSeleccionado: Matricula del Postulante: " + postulanteAdjudicado.getNumero_de_matricula() + ". " + postulanteAdjudicado.getApellido());
    }

//    public void onChangePostulanteAdjudicado() {
//
//        for (Postulante postulante : listaPostulantes) {
//            if (postulante.getNumero_de_matricula().equals(postulanteAdjudicado.getNumero_de_matricula())) {
//                postulanteAdjudicado = postulante;
//                break;
//            }
//        }
//        nuevoMensaje("Ingreso al onChangePostulanteAdjudicado", postulanteAdjudicado.getNumero_de_matricula() + " - " + postulanteAdjudicado.getApellido() + " " + postulanteAdjudicado.getNombre());
//        System.out.println("ConcursoBean.onChangePostulanteAdjudicado: Datos del Postulante seleccionado: " + postulanteAdjudicado.getApellido() + " - " + postulanteAdjudicado.getNumero_de_matricula());
//
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

    /**
     *
     * PickList Tribunal
     *
     */
//    private DualListModel<String> tribunal;
//
//    @PostConstruct
//    public void init() {
//
//        List<String> tribunalSource = new ArrayList<String>();
//        List<String> tribunalTarget = new ArrayList<String>();
//
//        tribunalSource.add("Ministerio de Salud");
//        tribunalSource.add("Colegio");
//        tribunalSource.add("Consejo");
//        tribunalSource.add("Establecimiento");
//        tribunalSource.add("Institución");
//
//        tribunal = new DualListModel<String>(tribunalSource, tribunalTarget);
//
//    }
//
//    public DualListModel<String> getTribunal() {
//        return tribunal;
//    }
//
//    public void setTribunal(DualListModel<String> tribunal) {
//        this.tribunal = tribunal;
//    }
//
//    public void onTransfer(TransferEvent event) {
//
//        if (event.getItems().size() == 1 && event.isAdd()) {
//            StringBuilder builder = new StringBuilder();
//            for (Object item : event.getItems()) {
//                builder.append(item.toString());
//            }
//
//            if (builder.toString().toLowerCase().equals("institución")) {
//                InstitucionDao instDao = new InstitucionDaoImpl();
//                listaInstituciones = instDao.getAll();
//                
//                RequestContext context = RequestContext.getCurrentInstance();
//                context.execute("PF('dlgNuevoMiembroTribunalInstitucion').show();");
//            }
//
//            nuevoMensajeInfo("Jurado Transferido", builder.toString());
//
//        } else if (!event.isRemove()) {
//            nuevoMensajeAlerta("Error", "Seleccione solamente un tribunal");
//        }
//
//        //mostrarDialogoNuevoMiembro();
//    }
//
//    public void onSelect(SelectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jurado Seleccionado", event.getObject().toString()));
//
//    }
//
//    public void onUnselect(UnselectEvent event) {
//        FacesContext context = FacesContext.getCurrentInstance();
//        context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Jurado Deseleccionado", event.getObject().toString()));
//    }
//
//    public void mostrarDialogoNuevoMiembro() {
//        RequestContext context = RequestContext.getCurrentInstance();
//        context.execute("PF('dlgNuevoMiembroTribunal').show();");
//    }
    public void refreshListas() {
        AreaDao areaDao = new AreaDaoImpl();
        listaAreas = areaDao.getAll();

        ProfesionDao profDao = new ProfesionDaoImpl();
        listaProfesiones = profDao.getAll();

        CargoDao cargoDao = new CargoDaoImpl();
        listaCargos = cargoDao.getAll();

        EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
        listaEstablecimientos = establecimientoDao.getAll();

        InstitucionDao instDao = new InstitucionDaoImpl();
        listaInstituciones = instDao.getAll();
    }

//    public List<String> buscarLocalidad(String nombreLocalidad) {
//        LocalidadDao localDao = new LocalidadDaoImpl();
//        List<String> results = new ArrayList<String>();
//
//        for (Localidad localidad : localDao.getLocalidad(nombreLocalidad)) {
//            results.add(localidad.getNombreDeLocalidad());
//        }
//
//        return results;
//    }
//    public void onLocalidadSeleccionada(SelectEvent event) {
//        for (Localidad local : listaLocalidades) {
//            if (juradoNuevo.getPersona().getLocalidadNacimiento() == local.) {
//                juradoNuevo.setEstablecimiento(estab);
//                break;
//            }
//        }
//        nuevoMensajeInfo("Registro Provincial de Concursos de Salud", juradoNuevo.getEstablecimiento().getCodigoSiisa() + " " + juradoNuevo.getEstablecimiento().getNombre());
//    }
    public void validarExpedienteTab() {
        try {
            for (Area area : listaAreas) {
                if (area.getPrefijoExpediente() == expedienteNuevo.getArea().getPrefijoExpediente()) {
                    expedienteNuevo.setArea(area);
                    break;
                }
            }
            expedienteNuevo.setNumeroExpediente(expedienteNuevo.getArea().getPrefijoExpediente() + "-" + expedienteNuevo.getNumero() + "/" + expedienteNuevo.getAnio());
            nuevoMensajeInfo("Expediente " + expedienteNuevo.getIdExpediente(), "Numero de Expediente: " + expedienteNuevo.getNumeroExpediente() + "-" + expedienteNuevo.getRegimen() + "- " + expedienteNuevo.getSituacion());
        } catch (NullPointerException ex1) {
            nuevoMensajeAlerta("Error! " + ex1.getMessage(), ex1.getLocalizedMessage());
        }
    }

    public void obtenerEstablecimiento() {
        try {
            System.out.println("ConcursoBean:obtenerEstablecimiento():\nBuscando el codigo del Establecimiento seleccionado: \nCodigoSiisa => " + cargoNuevo.getEstablecimiento().getCodigoSiisa() + "\tNombre => " + cargoNuevo.getEstablecimiento().getNombre());
            EstablecimientoDao establDao = new EstablecimientoDaoImpl();
            Establecimiento estEncontrado = establDao.getEstablecimiento(cargoNuevo.getEstablecimiento().getCodigoSiisa());
            if (estEncontrado != null) {
                cargoNuevo.setEstablecimiento(estEncontrado);
            } else {
                System.out.println("No se encontro el establecimiento con el codigo ");
            }
        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
        }
    }

}
