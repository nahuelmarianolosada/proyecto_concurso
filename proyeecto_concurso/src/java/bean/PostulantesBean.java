/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Cargo;
import dominio.Postulante;
import dominio.Persona;
import hibernate.dao.CargoDao;
import hibernate.dao.PersonaDao;
import hibernate.dao.PostulanteDao;
import hibernate.dao.impl.CargoDaoImpl;
import hibernate.dao.impl.PersonaDaoImpl;
import hibernate.dao.impl.PostulanteDaoImpl;
import java.io.Serializable;
import java.sql.SQLException;
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
        System.out.println("PostulantesBean.PostulantesBean() => Se ah creado el bean Postulantes");

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
    public void buscarPorCriterio() throws SQLException {
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

        Persona personaSelec = (Persona) event.getObject();
        PersonaDao personaDao = new PersonaDaoImpl();
        if (!listaPostulantes.isEmpty()) {
            personaSelec.setIdPersona(listaPostulantes.get(listaPostulantes.size() - 1).getPersona().getIdPersona() + 1);
            System.out.println("PostulantesBean.seleccionarPersona() => " + personaSelec.toString());
        } else {
            personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
        }
        nuevoPostulante.setPersona(personaSelec);
        System.out.println("Postulante.seleccionarPersona() => Se a seleccionado la " + nuevoPostulante.getPersona().toString());

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

    public void limpiar_inicializar() {
        nuevoPostulante = new Postulante(new Persona());
        nuevoPostulante.setCargo(new Cargo());
        banderaGanador = false;
        buscado = "";
        banderaBtn = false;
    }

    public void guardarNuevoPostulante() {
        PostulanteDao postulanteDao = new PostulanteDaoImpl();

        if (validarPostulante(nuevoPostulante)) {

            //Si es el primer postulante generamos un id nuevo de la BD
            if (listaPostulantes.isEmpty()) {
                nuevoPostulante.setIdPostulante(postulanteDao.generarIdNuevoPostulante());
            } else {
                //En caso de que ya existan registros en la lista obtenemos el 
                //ultimo cargado y le sumamos 1 al ID
                nuevoPostulante.setIdPostulante(listaPostulantes.get(listaPostulantes.size() - 1).getIdPostulante());
            }

            //Seteamos si es que el postulante es el ganador de un cargo
            if (banderaGanador) {
                for (Cargo cargo : getListaFinalCargos()) {
                    if (cargo.getIdCargo() == nuevoPostulante.getCargo().getIdCargo()) {
                        nuevoPostulante.setCargo(cargo);
                        nuevoPostulante.getCargo().setEsDesierto(false);
                        break;
                    }
                }
//                Cargo cargoAsignado = cargoDao.getCargo(nuevoPostulante.getCargo().getIdCargo());
//                nuevoPostulante.setCargo(cargoAsignado);
//                nuevoPostulante.getCargo().setEsDesierto(false);
            }else{
                nuevoPostulante.setCargo(new Cargo(true));
            }

            listaPostulantes.add(nuevoPostulante);

            System.out.println("\033[32mPostulantesBean.guardarNuevoPostulante() => " + nuevoPostulante.toString());

            nuevoMensajeInfo("Registro Provincial de Concursos", "Postulante cargado");
        }
    }

    public void guardarListaPostulantes() {
        super.setListaFinalPostulantes(listaPostulantes);
        PostulanteDao postulanteDao = new PostulanteDaoImpl();
        PersonaDao personaDao = new PersonaDaoImpl();

        for (Postulante postulante : listaPostulantes) {
            Persona personaExistente = personaDao.buscarPorDni(postulante.getPersona().getDni());
            System.out.println("Existe la persona en la BD? " + personaExistente);
            if (personaExistente == null) {
                personaDao.insertar(personaExistente);
            }
            if (!getListaFinalPostulantes().contains(listaPostulantes)) {
                getListaFinalPostulantes().addAll(listaPostulantes);
                break;
                //getListaFinalPostulantes().add(postulante);
                //postulanteDao.insertar(postulante);
            } else {
                nuevoMensajeInfo("Registro Provincial de Concursos", "Postulante repetido");
            }
        }
        nuevoMensajeInfo("Registro Provincial de Concursos", "Se a guardado la lista de postulantes");
//        pasarVistaDePestania();
    }

    /**
     *
     * Valida los campos propios del postulante como el puntaje, fojas,
     * oposicion, antecedentes y si son todos mayor de 0 devuelve true.
     *
     * @param postulante Postulante al que se desea validar
     * @return devuelve true en caso de que todos los valores sean superiores a
     * 0
     */
    public boolean validarPostulante(Postulante postulante) {
        boolean esValido = false;
        if (postulante.getPuntaje() >= 0) {
            if (postulante.getFojas() >= 0) {
                if (postulante.getOposicion() >= 0) {
                    if (postulante.getAntecedentes() >= 0) {
                        esValido = true;
                    } else {
                        nuevoMensajeAlerta("Registro Provincial de Concursos de Salud", "El puntaje de Antecedentes no puede ser menor a 0");
                    }
                } else {
                    nuevoMensajeAlerta("Registro Provincial de Concursos de Salud", "El puntaje de Oposicion no puede ser menor a 0");
                }
            } else {
                nuevoMensajeAlerta("Registro Provincial de Concursos de Salud", "La cantidad de fojas no puede ser menor a 0");
            }
        } else {
            nuevoMensajeAlerta("Registro Provincial de Concursos de Salud", "El puntaje no puede ser menor a 0");
        }
        return esValido;
    }

    public boolean validarPersonaExistente(Persona persona) {
        boolean esValido = false;
        PersonaDao personaDao = new PersonaDaoImpl();
        esValido = personaDao.existeDniPersona(persona);
        return esValido;
    }

    public void cambiarEstadoGanador() {
        if (banderaGanador) {
            banderaGanador = false;
        } else {
            banderaGanador = true;
        }
    }

}
