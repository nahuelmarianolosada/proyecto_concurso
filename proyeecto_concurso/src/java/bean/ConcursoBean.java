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
import dominio.Institucion;

/**
 *
 * @author Nahuel Mariano
 *
 */
import java.util.List;

@ManagedBean(name = "beanConcurso")
@ViewScoped
public class ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private static int numeroDePestania = 0;//Indice en la pestaña "tabuladorPestañero"
    private boolean banderaInstitucion;
    private boolean banderaEstablecimiento;

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
    private List<Establecimiento> listaEstablecimientos;
    private List<Institucion> listaInstituciones;
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

        refreshListas();

    }

    public int getNumeroDePestania() {
        return numeroDePestania;
    }

    public void setNumeroDePestania(int numeroDePestania) {
        this.numeroDePestania = numeroDePestania;
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
    public List<Establecimiento> getListaEstablecimientos() {
        return listaEstablecimientos;
    }

    public void setListaEstablecimientos(List<Establecimiento> listaEstablecimientos) {
        this.listaEstablecimientos = listaEstablecimientos;
    }
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
    public static void pasarVistaDePestania() {
        numeroDePestania += 1;
        System.out.println("ConcursoBean.pasarVistaDePagina(): La pestaña ahora es " + numeroDePestania);
    }
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

    public void refreshListas() {

        //ProfesionDao profDao = new ProfesionDaoImpl();
        //listaProfesiones = profDao.getAll();
        //CargoDao cargoDao = new CargoDaoImpl();
        //beanCargo.setListaCargos(cargoDao.getAll());
        //InstitucionDao instDao = new InstitucionDaoImpl();
        //listaInstituciones = instDao.getAll();
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
        }
    }
}
