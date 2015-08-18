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

    /**
     * Creates a new instance of TribunalBean
     */
    public TribunalBean() {

        TribunalJuradoDao tribJuraDao = new TribunalJuradoDaoImpl();
        TribunalDao tribunalDao = new TribunalDaoImpl();
        listaJurados = tribJuraDao.getAll();
        listaPersonas = new ArrayList<Persona>();
        personaBuscada = new Persona();
        juradoSeleccionado = new Persona();
        listaResultadoBusquedaPersona = new ArrayList<Persona>();
        datosValidos = false;
        tribunalNuevo = new Tribunal(tribunalDao.generarNuevoIdTribunal());
        juradoNuevo= new TribunalJurado(0,new Institucion(),juradoSeleccionado, new Establecimiento(), tribunalNuevo,"", true, "" );
        banderaBtn = false;
        listaJuradoNuevos = new ArrayList<TribunalJurado>();
        
       

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

    //METODOS
    public void buscarPersonaREFEPS() {

        ConexionRefeps conexionRefeps = new ConexionRefeps();
        listaPersonas=conexionRefeps.buscarProfesionalRefepsNombreCompleto(buscado);
        
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

            TribunalJuradoDao juradoDao = new TribunalJuradoDaoImpl();
            PersonaDao persDao = new PersonaDaoImpl();
          
            TribunalJurado  jurado = new TribunalJurado(juradoDao.generarNuevoIdJurado(),juradoNuevo,tribunalNuevo);
            //Setea el jurado nuevo a cargar

            //Agrega el jurado nuevo a la lista de jurados.
           
         
            listaJuradoNuevos.add(jurado);
            //GUarda el jurado
        //    juradoDao.insertar(juradoNuevo);
          System.out.println("nombres : " + jurado.getPersona().getNombres()+"apellido: "+jurado.getPersona().getApellido());
        //Controla por el Dni si existe la persona cargada en la bd concurso.
       //   if(!persDao.existeDniPersona(juradoSeleccionado)){
            
        //  persDao.insertar(juradoSeleccionado);
         
            
       //     }
           
            for (TribunalJurado tribunalJurado : listaJuradoNuevos) {
                 System.out.println("Id jurado: "+tribunalJurado.getIdTribunalJurado()+" nombres : " + tribunalJurado.getPersona().getNombres()+" apellido: "+tribunalJurado.getPersona().getApellido());
            }
            
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

    }

    public void guardarTribunalNuevo() {

        TribunalDao tribunalDao = new TribunalDaoImpl();
        tribunalNuevo.setCantidadMiembros((short)listaJuradoNuevos.size());
        tribunalDao.insertar(tribunalNuevo);
        listaJuradoNuevos.clear();
  
    }

}
