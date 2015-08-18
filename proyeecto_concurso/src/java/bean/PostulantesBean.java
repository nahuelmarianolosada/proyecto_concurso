/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Cargo;
import dominio.Postulante;
import dominio.Persona;
import hibernate.dao.PersonaDao;
import hibernate.dao.impl.PersonaDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanPostulante")
@ViewScoped
public class PostulantesBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private Postulante nuevoPostulante;
    private List<Postulante> listaPostulantes;
    private List<Persona> listaResultado;
    private Persona postulanteSeleccionado;
    private boolean datosValidos, banderaBtn, banderaGanador;
    private String buscado, criterio;

    @ManagedProperty("#{beanCargo}")
    private CargoBean beanCargo;

    /**
     * Creates a new instance of PostulantesBean
     */
    public PostulantesBean() {
        nuevoPostulante = new Postulante(new Persona());
        nuevoPostulante.setCargo(new Cargo());
        banderaGanador = false;
        listaPostulantes = new ArrayList<>();
    }

    /*
     GETTERS && SETTERS
     */
    public Postulante getNuevoPostulante() {
        return nuevoPostulante;
    }

    public void setNuevoPostulante(Postulante nuevoPostulante) {
        this.nuevoPostulante = nuevoPostulante;
    }

    public List<Postulante> getListaPostulantes() {
        return listaPostulantes;
    }

    public void setListaPostulantes(List<Postulante> listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
    }

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public String getCriterio() {
        return criterio;
    }

    public void setCriterio(String criterio) {
        this.criterio = criterio;
    }

    public boolean getBanderaBtn() {
        return banderaBtn;
    }

    public void setBanderaBtn(boolean banderaBtn) {
        this.banderaBtn = banderaBtn;
    }

    public List<Persona> getListaResultado() {
        return listaResultado;
    }

    public void setListaResultado(List<Persona> listaResultado) {
        this.listaResultado = listaResultado;
    }

    public Persona getPostulanteSeleccionado() {
        return postulanteSeleccionado;
    }

    public void setPostulanteSeleccionado(Persona postulanteSeleccionado) {
        this.postulanteSeleccionado = postulanteSeleccionado;
    }

    public CargoBean getBeanCargo() {
        return beanCargo;
    }

    public void setBuscado(String buscado) {
        this.buscado = buscado;
    }

    public void setBeanCargo(CargoBean beanCargo) {
        this.beanCargo = beanCargo;
    }

    public String getBuscado() {
        return buscado;
    }

    public boolean isBanderaGanador() {
        return banderaGanador;
    }

    public void setBanderaGanador(boolean banderaGanador) {
        this.banderaGanador = banderaGanador;
    }

    /*
     METODOS
     */
    public void buscarPorCriterio() {
        PersonaDao personaDao = new PersonaDaoImpl();
        Persona personaEncontrada = new Persona();
        RequestContext context = RequestContext.getCurrentInstance();

        try {
            switch (criterio) {
                case "dni": {
                    personaEncontrada = personaDao.buscarPorDni(Integer.parseInt(buscado));
                    if (personaEncontrada != null) {
                        nuevoPostulante.setPersona(personaEncontrada);
                    } else {
                        nuevoPostulante.setPersona(personaEncontrada);
                        nuevoMensajeAlerta("Registro de Concursos de Salud - POSTULANTE", "No se a encontrado la persona con el DNI = " + buscado);
                    }
                    break;
                }
                case "nombre": {
                    listaResultado = personaDao.buscarPorNombre(buscado);

                    if (listaResultado != null) {
                        context.execute("PF('dlgPersonaResultado').show();");
                        context.update("dlgPersonaResultado");
                    } else {
                        nuevoPostulante.setPersona(personaEncontrada);
                        nuevoMensajeAlerta("Registro de Concursos de Salud - POSTULANTE", "No se a encontrado la persona con el Nombre = " + buscado);
                    }
                    break;
                }
                case "apellido": {
                    listaResultado = personaDao.buscarPorApellido(buscado);

                    if (listaResultado != null) {
                        context.execute("PF('dlgPersonaResultado').show();");
                        context.update("dlgPersonaResultado");
                    } else {
                        nuevoPostulante.setPersona(personaEncontrada);
                        nuevoMensajeAlerta("Registro de Concursos de Salud - POSTULANTE", "No se a encontrado la persona con el Apellido = " + buscado);
                    }
                    break;
                }
            }
        } catch (NumberFormatException exFormato) {
            nuevoMensajeAlerta("Registro de Concursos de Salud - Error", "Ingrese numeros para buscar por DNI");
        }
    }

    public void validarBuscador() {
        if (buscado.isEmpty()) {
            banderaBtn = false;
        } else {
            banderaBtn = true;
        }
    }

    public void seleccionarPersona(SelectEvent event) {
        nuevoPostulante.setPersona((Persona) event.getObject());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgPersonaResultado').hide();");
        //FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deseleccionarPersona(UnselectEvent event) {
        nuevoPostulante.setPersona(new Persona());
//        FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void guardarNuevoPostulante() {
        for (Cargo cargo : beanCargo.getListaCargos()) {
            if (cargo.getIdCargo() == nuevoPostulante.getCargo().getIdCargo()) {
                nuevoPostulante.setCargo(cargo);
                break;
            }
        }

        listaPostulantes.add(nuevoPostulante);
        nuevoPostulante = new Postulante(new Persona());
        nuevoPostulante.setCargo(new Cargo());

        System.out.println("\033[32mPostulantesBean.guardarNuevoPostulante() => " + nuevoPostulante.toString());
        nuevoMensajeInfo("Registro Provincial de Concursos", "Postulante " + nuevoPostulante.getIdInscripcion() + " guardado");
    }

    public void cambiarEstadoGanador() {
        if (banderaGanador) {
            banderaGanador = false;
        } else {
            banderaGanador = true;
        }
    }

}
