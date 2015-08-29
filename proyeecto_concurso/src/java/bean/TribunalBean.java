/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Persona;
import dominio.TribunalJurado;
import dominio.Tribunal;
import dominio.Institucion;
import dominio.Establecimiento;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import bd.ConexionRefeps;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import hibernate.dao.TribunalDao;
import hibernate.dao.impl.TribunalDaoImpl;
import hibernate.dao.PersonaDao;
import hibernate.dao.impl.PersonaDaoImpl;
import javax.faces.bean.ManagedProperty;
import dominio.Resolucion;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.InstitucionDao;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.InstitucionDaoImpl;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanTribunal")
@ViewScoped
public class TribunalBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TribunalJurado juradoNuevo, juradoSeleccionado;
    private Tribunal tribunalNuevo;
    private String categoriaJurado, buscado;
    private Persona personaBuscada;
    private boolean datosValidos, banderaBtn;
    private List<Persona> listaResultadoBusquedaPersona, listaPersonas;
    private List<TribunalJurado> listaJurados, listaJuradoNuevos;
    private List<Tribunal> listaTribunalesNuevos;
    private Resolucion resolucionSeleccionada;

    @ManagedProperty("#{beanResolucion}")
    private ResolucionBean beanResolucion;

    /**
     * Creates a new instance of TribunalBean
     */
    public TribunalBean() {

        TribunalJuradoDao tribJuraDao = new TribunalJuradoDaoImpl();
        TribunalDao tribunalDao = new TribunalDaoImpl();
        PersonaDao personaDao = new PersonaDaoImpl();
        listaJurados = tribJuraDao.getAll();
        listaPersonas = new ArrayList<Persona>();
        personaBuscada = new Persona();
        listaResultadoBusquedaPersona = new ArrayList<Persona>();
        datosValidos = false;
        //idJuradoGenerado = tribJuraDao.generarNuevoIdJurado();
        //idTribunalGenerado = tribunalDao.generarNuevoIdTribunal();
        //idPersonaGenerado = personaDao.generarIdNuevoPersona();
        //juradoSeleccionado = new Persona(idPersonaGenerado);
        tribunalNuevo = new Tribunal();
        juradoNuevo = new TribunalJurado(new Institucion(), new Persona(), new Establecimiento(), tribunalNuevo, "", true, "");
        banderaBtn = false;
        listaJuradoNuevos = new ArrayList<TribunalJurado>();
        listaTribunalesNuevos = new ArrayList<Tribunal>();
        resolucionSeleccionada = new Resolucion();
    }

    //GETTERS && SETTERS
    public TribunalJurado getJuradoNuevo() {
        return juradoNuevo;
    }

    public Tribunal getTribunalNuevo() {
        return tribunalNuevo;
    }

    public void setTribunalNuevo(Tribunal tribunalNuevo) {
        this.tribunalNuevo = tribunalNuevo;
    }

    public void setJuradoNuevo(TribunalJurado juradoNuevo) {
        this.juradoNuevo = juradoNuevo;
    }

    public String getCategoriaJurado() {
        return categoriaJurado;
    }

    public void setCategoriaJurado(String categoriaJurado) {
        this.categoriaJurado = categoriaJurado;
    }

    public List<TribunalJurado> getListaJurados() {
        return listaJurados;
    }

    public void setListaJurados(List<TribunalJurado> listaJurados) {
        this.listaJurados = listaJurados;
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

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public String getBuscado() {
        return buscado;
    }

    public void setBuscado(String buscado) {
        this.buscado = buscado;
    }

    public boolean isBanderaBtn() {
        return banderaBtn;
    }

    public void setBanderaBtn(boolean banderaBtn) {
        this.banderaBtn = banderaBtn;
    }

    public TribunalJurado getJuradoSeleccionado() {
        return juradoSeleccionado;
    }

    public void setJuradoSeleccionado(TribunalJurado juradoSeleccionado) {
        this.juradoSeleccionado = juradoSeleccionado;
    }

    public List<TribunalJurado> getListaJuradoNuevos() {
        return listaJuradoNuevos;
    }

    public void setListaJuradoNuevos(List<TribunalJurado> listaJuradoNuevos) {
        this.listaJuradoNuevos = listaJuradoNuevos;
    }

    public ResolucionBean getBeanResolucion() {
        return beanResolucion;
    }

    public void setBeanResolucion(ResolucionBean beanResolucion) {
        this.beanResolucion = beanResolucion;
    }

    public Resolucion getResolucionSeleccionada() {
        return resolucionSeleccionada;
    }

    public void setResolucionSeleccionada(Resolucion resolucionSeleccionada) {
        this.resolucionSeleccionada = resolucionSeleccionada;
    }

    //METODOS
    public void onResolucionSelect() {
        listaJuradoNuevos.clear();
        for (Resolucion resolucion : getListaFinalResoluciones()) {
            if (resolucion.getIdResolucion() == resolucionSeleccionada.getIdResolucion()) {
                resolucionSeleccionada = resolucion;
                for (TribunalJurado jurado : getListaFinalJurados()) {
                    if (jurado.getTribunal() == resolucionSeleccionada.getTribunal()) {
                        listaJuradoNuevos.add(jurado);
                    }
                }
                break;
            }
        }
    }

    public void buscarPersonaREFEPS() {

        try {
            PersonaDao personaDao = new PersonaDaoImpl();

            if (!personaDao.buscarPorNombre(buscado).isEmpty()) {
                listaPersonas = personaDao.buscarPorNombre(buscado);
            } else {
                ConexionRefeps conexionRefeps = new ConexionRefeps();
                listaPersonas = conexionRefeps.buscarProfesionalRefepsNombreCompleto(buscado);
            }
        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
            nuevoMensajeAlerta("Error al intentar buscar una persona" + buscado, exGeneral.getMessage());
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
        if (!listaJuradoNuevos.isEmpty()) {
            personaSelec.setIdPersona(listaJuradoNuevos.get(0).getPersona().getIdPersona()+1);
        } else {
            personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
        }
        juradoNuevo.setPersona(personaSelec);
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgProfesionalesResultado').hide();");
        context.update("formJuradoNuevo");
    }

    public void deseleccionarPersona(UnselectEvent event) {
        juradoNuevo.setPersona(new Persona());
    }

    public void guardarJuradoNuevo() {

        try {
            PersonaDao persDao = new PersonaDaoImpl();
            EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
            InstitucionDaoImpl institucionDao = new InstitucionDaoImpl();

            juradoNuevo.setEstablecimiento(establecimientoDao.getEstablecimientoById(juradoNuevo.getEstablecimiento().getIdEstablecimiento()));
            Institucion instEncontrada = institucionDao.getInstitucion(juradoNuevo.getInstitucion().getIdInstitucion());
            juradoNuevo.setInstitucion(instEncontrada);

            
            for (Resolucion resolucion : getListaFinalResoluciones()) {
                if (resolucion.getIdResolucion() == resolucionSeleccionada.getIdResolucion()) {
                    resolucionSeleccionada = resolucion;
                    break;
                }
            }

            //Controla por el Dni si existe la persona cargada en la bd concurso.
            if (!persDao.existeDniPersona(juradoNuevo.getPersona())) {
//                Persona personaNueva = new Persona(persDao.generarIdNuevoPersona());
//                personaNueva.setApellido(juradoNuevo.getPersona().getApellido());
//                personaNueva.setNombres(juradoNuevo.getPersona().getNombres());
//                personaNueva.setSexo(juradoNuevo.getPersona().getSexo());
//                personaNueva.setDireccion(juradoNuevo.getPersona().getDireccion());
//                personaNueva.setCuil(juradoNuevo.getPersona().getCuil());
//                personaNueva.setFechaDeNacimiento(juradoNuevo.getPersona().getFechaDeNacimiento());
//                personaNueva.setTelefono(juradoNuevo.getPersona().getTelefono());
//                personaNueva.setDni(juradoNuevo.getPersona().getDni());
//                personaNueva.setEmail(juradoNuevo.getPersona().getEmail());
//                if (juradoNuevo.getPersona().getLocalidadByIdLocalidadDireccion() != null) {
//                    personaNueva.setLocalidadByIdLocalidadDireccion(juradoNuevo.getPersona().getLocalidadByIdLocalidadDireccion());
//                }
//                if (juradoNuevo.getPersona().getLocalidadByLocalidadNacimiento() != null) {
//                    personaNueva.setLocalidadByLocalidadNacimiento(juradoNuevo.getPersona().getLocalidadByLocalidadNacimiento());
//                }

                //en caso de que no exista lo seteamos y guardamos
                Persona personaNueva = juradoNuevo.getPersona();
                persDao.insertar(personaNueva);
            }

            juradoNuevo.setTribunal(resolucionSeleccionada.getTribunal());

            //Agrega el jurado nuevo a la lista de jurados.
            if (!listaJuradoNuevos.contains(juradoNuevo)) {
                System.out.println("TribunalBean.guardarJuradoNuevo() => Guardando " + juradoNuevo.toString());
                listaJuradoNuevos.add(juradoNuevo);
            }

            if (!getListaFinalJurados().contains(juradoNuevo)) {
                getListaFinalJurados().add(juradoNuevo);
            }

            //Inicializa el jurado Nuevo y el Seleccionado
            juradoSeleccionado = new TribunalJurado();
            juradoNuevo = new TribunalJurado(juradoNuevo.getIdTribunalJurado() + 1, getListaInstituciones().get(0), new Persona(), getListaEstablecimientos().get(0), resolucionSeleccionada.getTribunal(), "", false, "");

        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! al guardar el jurado", ex1.getMessage());
            ex1.printStackTrace();
        }

    }

    public void guardarTribunalNuevo() {

        try {
            //Seteamos la cantidad de jurados para el nuevoTribunal
            TribunalDao tribunalDao = new TribunalDaoImpl();

            for (Resolucion resolucion : getListaFinalResoluciones()) {
                if (resolucionSeleccionada.getIdResolucion() == resolucion.getIdResolucion()) {
                    tribunalNuevo = resolucion.getTribunal();
                    tribunalNuevo.setCantidadMiembros(Short.parseShort(String.valueOf(getListaJuradoNuevos().size())));
                    break;
                }
            }

            //seteamos el tribunal en la resolucion Seleccionada
            resolucionSeleccionada.setTribunal(tribunalNuevo);
            getListaFinalResoluciones().get(resolucionSeleccionada.getIdResolucion()).setTribunal(tribunalNuevo);

            //Guardamos el tribunal en la lista final de tribunales
            getListaFinalTribunales().add(tribunalNuevo);

            for (TribunalJurado jurado : listaJuradoNuevos) {
                jurado.setTribunal(tribunalNuevo);
                getListaFinalJurados().add(jurado);
            }

            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Se a guardado el tribunal con los postulantes seleccionados.");
            pasarVistaDePestania();
        } catch (Exception exGeneral) {
            nuevoMensajeAlerta("Error! al guardar el tribunal nuevo", exGeneral.getMessage());
        }

    }

}
