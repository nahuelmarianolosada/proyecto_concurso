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
import hibernate.dao.PostulanteDao;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.InstitucionDaoImpl;
import hibernate.dao.impl.PostulanteDaoImpl;

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
    private List<TribunalJurado> listaJuradoNuevos;
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
        //listaJurados = tribJuraDao.getAll();
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

        listaJuradoNuevos = new ArrayList<>();

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
            ConexionRefeps conexionRefeps = new ConexionRefeps();
            listaPersonas = conexionRefeps.buscarProfesionalRefepsNombreCompleto(buscado);

        } catch (NullPointerException exNulo) {
            System.out.println("Nombre buscado: " + buscado);
            System.out.println(exNulo.getLocalizedMessage() + "\n" + exNulo.getMessage());

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
        Persona persAux = personaDao.buscarPorDni(personaSelec.getDni());
        if (!listaJuradoNuevos.isEmpty()) {
            if (persAux == null) {
                personaSelec.setIdPersona(personaDao.generarIdNuevoPersona());
                System.out.println("TribunalBean.seleccionarPersona() => " + personaSelec.toString());
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
        juradoNuevo.setPersona(personaSelec);
        System.out.println("TribunalBean.seleccionarPersona() => Se a seleccionado la " + juradoNuevo.getPersona().toString());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgProfesionalesResultado').hide();");
        context.update("formJuradoNuevo");
    }

    public void seleccionarInstitucion() {
        InstitucionDao institucionDao = new InstitucionDaoImpl();
        if (juradoNuevo.getInstitucion().getIdInstitucion() != 0 || juradoNuevo.getEstablecimiento().getIdEstablecimiento() != 0) {
            juradoNuevo.setInstitucion(institucionDao.getInstitucion(juradoNuevo.getInstitucion().getIdInstitucion()));
            System.out.println("TribunalBean.seleccionarInstitucion(" + juradoNuevo.getInstitucion().getIdInstitucion() + ") => Se a seleccionado el Instituto (" + juradoNuevo.getInstitucion().getIdInstitucion() + ") " + juradoNuevo.getInstitucion().getNombreInstitucion());
        } else {
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Seleccione una institucion a la que representa");
        }
    }

    public void seleccionarEstablecimiento() {
        EstablecimientoDao establecDao = new EstablecimientoDaoImpl();
        if (juradoNuevo.getInstitucion().getIdInstitucion() != 0 || juradoNuevo.getEstablecimiento().getIdEstablecimiento() != 0) {
            juradoNuevo.setEstablecimiento(establecDao.getEstablecimientoById(juradoNuevo.getEstablecimiento().getIdEstablecimiento()));
            System.out.println("TribunalBean.seleccionarEstablecimiento(" + juradoNuevo.getEstablecimiento().getIdEstablecimiento() + ") => Se a seleccionado el Establecimiento (" + juradoNuevo.getEstablecimiento().getIdEstablecimiento() + ") " + juradoNuevo.getEstablecimiento().getNombre());
        } else {
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Seleccione un establecimiento al que representa");
        }
    }

    public void guardarJuradoNuevo() {

        try {
            PersonaDao persDao = new PersonaDaoImpl();

            for (Resolucion resolucion : getListaFinalResoluciones()) {
                if (resolucion.getIdResolucion() == resolucionSeleccionada.getIdResolucion()) {
                    resolucionSeleccionada = resolucion;
                    break;
                }
            }

            //Controla por el Dni si existe la persona cargada en la bd concurso.
            if (!persDao.existeDniPersona(juradoNuevo.getPersona())) {

                //en caso de que no exista lo seteamos y guardamos
                Persona personaNueva = juradoNuevo.getPersona();
                personaNueva.setIdPersona(persDao.generarIdNuevoPersona());
                personaNueva.setApellido(juradoNuevo.getPersona().getApellido().toUpperCase());
                personaNueva.setNombres(juradoNuevo.getPersona().getNombres().toUpperCase());
                personaNueva.setDireccion(juradoNuevo.getPersona().getDireccion().toUpperCase());
                persDao.insertar(personaNueva);
            }

            juradoNuevo.setTribunal(resolucionSeleccionada.getTribunal());

            //Agrega el jurado nuevo a la lista de jurados.
            if (!listaJuradoNuevos.contains(juradoNuevo)) {

                //Seteamos el establecimiento e institucion
                EstablecimientoDao establecimientoDao = new EstablecimientoDaoImpl();
                juradoNuevo.setEstablecimiento(establecimientoDao.getEstablecimientoByCodigoSiisa(juradoNuevo.getEstablecimiento().getCodigoSiisa()));

                InstitucionDao institucionDao = new InstitucionDaoImpl();
                juradoNuevo.setInstitucion(institucionDao.getInstitucion(juradoNuevo.getInstitucion().getIdInstitucion()));

                //Guardamos
                System.out.println("TribunalBean.guardarJuradoNuevo() => Guardando " + juradoNuevo.toString());
                listaJuradoNuevos.add(juradoNuevo);
            }

            //Inicializa el jurado Nuevo y el Seleccionado
            juradoSeleccionado = new TribunalJurado();
            juradoNuevo = new TribunalJurado(juradoNuevo.getIdTribunalJurado() + 1, new Institucion(), new Persona(persDao.generarIdNuevoPersona(), "M"), new Establecimiento(), resolucionSeleccionada.getTribunal(), "", false, "");

            buscado = "";
        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! al guardar el jurado", ex1.getMessage());
            ex1.printStackTrace();
        }

    }

    public void guardarTribunalNuevo() {

        PostulanteDao postulanteDao = new PostulanteDaoImpl();
        try {
            //Seteamos la cantidad de jurados para el nuevoTribunal

            for (Resolucion resolucion : getListaFinalResoluciones()) {
                if (resolucionSeleccionada.getIdResolucion() == resolucion.getIdResolucion()) {
                    tribunalNuevo = resolucion.getTribunal();
                    tribunalNuevo.setCantidadMiembros(Short.parseShort(String.valueOf(getListaJuradoNuevos().size())));
                    break;
                }
            }

            //seteamos el tribunal en la resolucion Seleccionada
            //Tengo que cambiar la forma de recorrer la lista en memoria de resolucion
            resolucionSeleccionada.setTribunal(tribunalNuevo);
            for (Resolucion resolucion : getListaFinalResoluciones()) {
                if (resolucionSeleccionada.getNumeroResolucion().equalsIgnoreCase(resolucion.getNumeroResolucion())) {
                    //Primero obtenemos el elemento resolucion de la lista y despues
                    //le seteamos el tribunal
                    getListaFinalResoluciones().get(getListaFinalResoluciones().indexOf(resolucion)).setTribunal(tribunalNuevo);

                    break;
                }
            }

            //getListaFinalResoluciones().get(resolucionSeleccionada.getIdResolucion()).setTribunal(tribunalNuevo);
            //Guardamos el tribunal en la lista final de tribunales
            getListaFinalTribunales().add(tribunalNuevo);

            for (TribunalJurado jurado : listaJuradoNuevos) {
                jurado.setTribunal(tribunalNuevo);
                if (getListaFinalJurados().isEmpty()) {
                    jurado.setIdTribunalJurado(postulanteDao.generarIdNuevoPostulante());
                } else {
                    jurado.setIdTribunalJurado(getListaFinalJurados().get(getListaFinalJurados().size() - 1).getIdTribunalJurado() + 1);
                }
                getListaFinalJurados().add(jurado);
            }

            datosValidos = true;
            nuevoMensajeInfo("Registro Provincial de Concursos de Salud", "Se a guardado el tribunal con los postulantes seleccionados.");
            pasarVistaDePestania();
        } catch (Exception exGeneral) {
            nuevoMensajeAlerta("Error! al guardar el tribunal nuevo", exGeneral.getMessage());
            exGeneral.printStackTrace();
        }

    }

}
