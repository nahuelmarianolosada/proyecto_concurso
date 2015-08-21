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
import hibernate.HibernateUtil;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import hibernate.dao.impl.TribunalDaoImpl;
import hibernate.dao.TribunalDao;
import java.io.Serializable;
import java.sql.SQLException;
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

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanTribunal")
@ViewScoped
public class TribunalBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TribunalJurado juradoNuevo;
    private Tribunal tribunalNuevo;
    private String categoriaJurado;
    private List<TribunalJurado> listaJurados;
    private List<Persona> listaPersonas;
    private Persona personaBuscada;
    private List<Persona> listaResultadoBusquedaPersona;
    private boolean datosValidos;
    private String buscado;
    private boolean banderaBtn;
    private Persona juradoSeleccionado;
    private List<TribunalJurado> listaJuradoNuevos;
    private List<Tribunal> listaTribunalesNuevos;
    private int idTribunalGenerado;
    private int idJuradoGenerado;
    private int idPersonaGenerado;
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
        idJuradoGenerado = tribJuraDao.generarNuevoIdJurado();
        idTribunalGenerado = tribunalDao.generarNuevoIdTribunal();
        idPersonaGenerado = personaDao.generarIdNuevoPersona();
        juradoSeleccionado = new Persona(idPersonaGenerado);
        tribunalNuevo = new Tribunal(idTribunalGenerado);
        juradoNuevo = new TribunalJurado(idJuradoGenerado, new Institucion(), juradoSeleccionado, new Establecimiento(), tribunalNuevo, "", true, "");
        banderaBtn = false;
        listaJuradoNuevos = new ArrayList<TribunalJurado>();
        listaTribunalesNuevos = new ArrayList<Tribunal>();

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

    public Persona getJuradoSeleccionado() {
        return juradoSeleccionado;
    }

    public void setJuradoSeleccionado(Persona juradoSeleccionado) {
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

    //METODOS
    public void buscarPersonaREFEPS() {

        ConexionRefeps conexionRefeps = new ConexionRefeps();
        listaPersonas = conexionRefeps.buscarProfesionalRefepsNombreCompleto(buscado);

    }

    public void validarBuscador() {
        if (buscado.isEmpty()) {
            banderaBtn = false;

        } else {
            banderaBtn = true;
        }
    }

    public void seleccionarPersona(SelectEvent event) {
        juradoNuevo.setPersona((Persona) event.getObject());
        RequestContext context = RequestContext.getCurrentInstance();
        context.execute("PF('dlgProfesionalesResultado').hide();");
        //FacesMessage msg = new FacesMessage("Car Selected", ((Car) event.getObject()).getId());
        //FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void deseleccionarPersona(UnselectEvent event) {
        juradoNuevo.setPersona(new Persona());
//        FacesMessage msg = new FacesMessage("Car Unselected", ((Car) event.getObject()).getId());
//        FacesContext.getCurrentInstance().addMessage(null, msg);
    }

    public void guardarJuradoNuevo() {

        try {
            PersonaDao persDao = new PersonaDaoImpl();
            TribunalJuradoDao juradoDao = new TribunalJuradoDaoImpl();
            for (Institucion insti : getListaInstituciones()) {
                if (juradoNuevo.getInstitucion().getIdInstitucion() == insti.getIdInstitucion()) {
                    juradoNuevo.setInstitucion(insti);
                }
            }
            for (Establecimiento establecimiento : getListaEstablecimientos()) {
                if (juradoNuevo.getEstablecimiento().getIdEstablecimiento() == establecimiento.getIdEstablecimiento()) {
                    juradoNuevo.setEstablecimiento(establecimiento);
                }
            }

            //Controla por el Dni si existe la persona cargada en la bd concurso.
            if (juradoSeleccionado.getDni() != null) {
                if (!persDao.existeDniPersona(juradoSeleccionado)) {

                    //  persDao.insertar(juradoSeleccionado);
                } else {
                    // persDao.insertar(juradoSeleccionado);
                }
            }

            //Agrega el jurado nuevo a la lista de jurados.
            juradoNuevo.setIdTribunalJurado(idJuradoGenerado);
            juradoNuevo.setTribunal(tribunalNuevo);
            listaJuradoNuevos.add(juradoNuevo);
            idJuradoGenerado++;

            //GUarda el jurado
            //    juradoDao.insertar(juradoNuevo);
            //Setea la persona y el jurado .
            juradoSeleccionado = new Persona(persDao.generarIdNuevoPersona());
            juradoNuevo = new TribunalJurado(juradoDao.generarNuevoIdJurado(), new Institucion(), juradoSeleccionado, new Establecimiento(), tribunalNuevo, "", true, "");

        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

    }

    public void guardarTribunalNuevo() {
        int canjurado=0;
        TribunalDao tribunalDao = new TribunalDaoImpl();
        //Setea el tirbunal nuevo.
        

        //tribunalNuevo = new Tribunal(idTribunalGenerado, (short) listaJuradoNuevos.size());
      
        beanResolucion.getListaResoluciones();
        beanResolucion.getResolucionNueva();
        for (TribunalJurado jurados : listaJuradoNuevos) {
            if (jurados.getTribunal().getIdTribunal()==idTribunalGenerado) {
                 canjurado++;
            }
       
        for (TribunalJurado jurad : listaJuradoNuevos) {
            if (jurad.getTribunal().equals(null)) {
                jurad.setTribunal(tribunalNuevo);
                canjurado++;
            }
 for (Resolucion resolucionCargadas : beanResolucion.getListaResoluciones()) {
            if (beanResolucion.getResolucionNueva().getNumeroResolucion() == resolucionCargadas.getNumeroResolucion()) {
                resolucionCargadas.setTribunal(tribunalNuevo);
            }
        }
            System.out.println("id jurado :" + jurados.getIdTribunalJurado() + "tribunal: " + jurados.getTribunal().getIdTribunal() + " nombre: " + jurados.getPersona().getNombres() + "apellido :" + jurados.getPersona().getApellido());

        }

        //Agrega el tribunal nuevo a la lista de tribunales.
        listaTribunalesNuevos.add(tribunalNuevo);

        idTribunalGenerado++;
        tribunalNuevo.setIdTribunal(idTribunalGenerado);

        setListaFinalJurados(listaJuradoNuevos);
        setListaFinalTribunales(listaTribunalesNuevos);

        // tribunalDao.insertar(tribunalNuevo);
    }

    }

    public List<Tribunal> getListaTribunalesNuevos() {
        return listaTribunalesNuevos;
    }

    public void setListaTribunalesNuevos(List<Tribunal> listaTribunalesNuevos) {
        this.listaTribunalesNuevos = listaTribunalesNuevos;
    }

    public int getIdTribunalGenerado() {
        return idTribunalGenerado;
    }

    public void setIdTribunalGenerado(int idTribunalGenerado) {
        this.idTribunalGenerado = idTribunalGenerado;
    }

    public int getIdJuradoGenerado() {
        return idJuradoGenerado;
    }

    public void setIdJuradoGenerado(int idJuradoGenerado) {
        this.idJuradoGenerado = idJuradoGenerado;
    }

    public int getIdPersonaGenerado() {
        return idPersonaGenerado;
    }

    public void setIdPersonaGenerado(int idPersonaGenerado) {
        this.idPersonaGenerado = idPersonaGenerado;
    }

}
