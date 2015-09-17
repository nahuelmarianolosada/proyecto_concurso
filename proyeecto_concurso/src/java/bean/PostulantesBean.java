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
import hibernate.dao.PostulanteDao;
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
    private Cargo cargoSeleccionado;
    
    @ManagedProperty("#{beanCargo}")
    private CargoBean beanCargo;

    /**
     * Creates a new instance of PostulantesBean
     */
    public PostulantesBean() {
        
        nuevoPostulante = new Postulante(new Persona());
        banderaGanador = false;
        listaPostulantes = new ArrayList<>();
        System.out.println("PostulantesBean.PostulantesBean() => Se ah creado el bean Postulantes");
        
        cargoSeleccionado = new Cargo();
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
    
    public Cargo getCargoSeleccionado() {
        return cargoSeleccionado;
    }
    
    public void setCargoSeleccionado(Cargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    /*
     METODOS
     */
    
    
    public void quitarPostulante(Postulante postulante){
        System.out.println("PostulantesBean.quitarPostulante() => " + postulante.toString());
         for (Postulante p : listaPostulantes) {
            if (p.getIdPostulante() == postulante.getIdPostulante()) {
                listaPostulantes.remove(postulante);
                break;
            }
        }
        if (listaPostulantes.isEmpty()) {
            datosValidos = false;
        }
    }
    
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
        Persona persAux = personaDao.buscarPorDni(personaSelec.getDni());
        if (!listaPostulantes.isEmpty()) {
            if (persAux == null) {
                personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
                System.out.println("PostulantesBean.seleccionarPersona() => " + personaSelec.toString());
            } else {
                personaSelec = persAux;
            }
        } else {
            //En caso de que sea el primer registro de la lista verificamos su
            //existencia en la BD
            if (persAux == null) {
                personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
            } else {
                personaSelec = persAux;
            }
            
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
                nuevoPostulante.setIdPostulante(listaPostulantes.get(listaPostulantes.size() - 1).getIdPostulante() + 1);
            }

            //Seteamos si es que el postulante es el ganador de un cargo
            if (banderaGanador) {
                for (Cargo cargo : getListaFinalCargos()) {
                    if (cargo.getIdCargo() == cargoSeleccionado.getIdCargo()) {
                        nuevoPostulante.setCargo(cargo);
                        nuevoPostulante.getCargo().setEsDesierto(false);
                        getListaFinalCargos().set(getListaFinalCargos().indexOf(cargo), nuevoPostulante.getCargo());
                        
                    } else {
                        getListaFinalCargos().get(getListaFinalCargos().indexOf(cargo)).setEsDesierto(true);
                    }
                }
            } else {
                nuevoPostulante.setCargo(null);
            }
            
            listaPostulantes.add(nuevoPostulante);
            
            System.out.println("\033[32mPostulantesBean.guardarNuevoPostulante() => " + nuevoPostulante.toString());
            
            nuevoPostulante = new Postulante(nuevoPostulante.getIdPostulante() + 1);
            nuevoPostulante.setPersona(new Persona());
            cargoSeleccionado = new Cargo();
            banderaGanador = false;
            buscado = "";
            
            nuevoMensajeInfo("Registro Provincial de Concursos", "Postulante cargado");
            
        }
    }
    
    public void guardarListaPostulantes() {
        PersonaDao personaDao = new PersonaDaoImpl();
        PostulanteDao postulanteDao = new PostulanteDaoImpl();
        
        for (Postulante postulante : listaPostulantes) {
            //Controla por el Dni si existe la persona cargada en la bd concurso.
            if (!personaDao.existeDniPersona(postulante.getPersona())) {
                //en caso de que no exista lo seteamos y guardamos
                Persona personaNueva = postulante.getPersona();
                personaNueva.setIdPersona(personaDao.generarIdNuevoPersona());
                personaNueva.setApellido(postulante.getPersona().getApellido().toUpperCase());
                personaNueva.setNombres(postulante.getPersona().getNombres().toUpperCase());
                personaNueva.setDireccion(postulante.getPersona().getDireccion().toUpperCase());
                personaDao.insertar(personaNueva);
            }

//            for (Cargo cargo : getListaFinalCargos()) {
//                if (postulante.getCargo() != null) {
//                    if (cargo.getIdCargo() == postulante.getCargo().getIdCargo()) {
//                        postulante.setCargo(cargo);
//                        break;
//                    }
//                }
//            }
            if (!getListaFinalPostulantes().contains(postulante)) {
                //Preguntamos si es el primer postulante de la lista
                if (listaPostulantes.indexOf(postulante) == 0) {
                    postulante.setIdPostulante(postulanteDao.generarIdNuevoPostulante());
                } else {
                    //En caso de que no sea el primero le asignamos el valor del
                    //ultimo registro en la lista final +1
                    postulante.setIdPostulante(listaPostulantes.get(getListaFinalPostulantes().size() - 1).getIdPostulante() + 1);
                }
                
                getListaFinalPostulantes().add(postulante);
            }
        }
        pasarVistaDePestania();
        datosValidos = true;
        nuevoMensajeInfo("Registro Provincial de Concursos", "Se a guardado la lista de postulantes");
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
