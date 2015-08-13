/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Persona;
import dominio.TribunalJurado;
import dominio.Tribunal;
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
import com.sun.javafx.scene.control.skin.VirtualFlow;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;

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
    private Persona tribunalSeleccionado;

    /**
     * Creates a new instance of TribunalBean
     */
    public TribunalBean() {

        TribunalJuradoDao tribJuraDao = new TribunalJuradoDaoImpl();
        TribunalDao tribunalDao = new TribunalDaoImpl();
        listaJurados = tribJuraDao.getAll();
        listaPersonas = new ArrayList<Persona>();
        personaBuscada = new Persona();
        listaResultadoBusquedaPersona = new ArrayList<Persona>();
        datosValidos = false;
        tribunalNuevo = new Tribunal(tribunalDao.generarNuevoIdTribunal());
        juradoNuevo = new TribunalJurado(tribJuraDao.generarNuevoIdJurado());
        banderaBtn=false;


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

    public Persona getTribunalSeleccionado() {
        return tribunalSeleccionado;
    }

    public void setTribunalSeleccionado(Persona tribunalSeleccionado) {
        this.tribunalSeleccionado = tribunalSeleccionado;
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
    
    
    public void guardarJurado() {

        try {

        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

//         try {
//             for (UnidadDeOrganizacion unidad : listaUnidadDeOrganizacions) {
//                 if (unidad.getCodigoUnidadDeOrganizacion() == expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion()) {
//                     expedienteNuevo.setUnidadDeOrganizacion(unidad);
//                     break;
//                 }
//             }
//             expedienteNuevo.setNumeroExpediente(expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion() + "-" + expedienteNuevo.getNumero() + "/" + expedienteNuevo.getAnio());
//
//             if (expedienteNuevo.esValido()) {
//                 beanResolucion.getResolucionNueva().setExpediente(expedienteNuevo);
//                 ResolucionDao resDao = new ResolucionDaoImpl();
//                 beanResolucion.setListaResoluciones(resDao.getResoluciones(expedienteNuevo));
//                 datosValidos = true;
//                 pasarVistaDePestania();
//                 nuevoMensajeInfo("Registro de Concursos de Salud - EXPEDIENTE", "Número: " + expedienteNuevo.getNumeroExpediente() + "\nRégimen: " + expedienteNuevo.getRegimen() + "\nSituación: " + expedienteNuevo.getSituacion());
//             }
//             System.out.println("\033[32mExpedienteBean.guardarExpediente() => " + expedienteNuevo.toString());
//
//         } catch (Exception ex1) {
//             ex1.printStackTrace();
//         }
    }

}
