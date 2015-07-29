/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Persona;
import dominio.TribunalJurado;
import hibernate.HibernateUtil;
import hibernate.dao.TribunalJuradoDao;
import hibernate.dao.impl.TribunalJuradoDaoImpl;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanTribunal")
@ViewScoped
public class TribunalBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;
    private TribunalJurado juradoNuevo;
    private String categoriaJurado;
    private List<TribunalJurado> listaJurados;
    private List<Persona> listaPersonas;
    private Persona personaBuscada;
    private List<Persona> listaResultadoBusquedaPersona;

    /**
     * Creates a new instance of TribunalBean
     */
    public TribunalBean() {

        TribunalJuradoDao tribJura = new TribunalJuradoDaoImpl();
        listaJurados = tribJura.getAll();
        personaBuscada = new Persona();
        listaResultadoBusquedaPersona = new ArrayList<Persona>();

//        try {
//            juradoNuevo = new TribunalJurado(getListaInstituciones().get(0), getListaEstablecimientos().get(0), new Persona(Integer.getInteger(""), Long.getLong("")));
//        } catch (Exception exGeneral) {
//            exGeneral.printStackTrace();
//        }
    }

    //GETTERS && SETTERS
    public TribunalJurado getJuradoNuevo() {
        return juradoNuevo;
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
    
    
    //METODOS
    
    public void buscarPersonaREFEPS() {
        try {
            listaResultadoBusquedaPersona = HibernateUtil.buscarPersonas(personaBuscada.getDni());
            if (listaResultadoBusquedaPersona.size() == 0) {
                nuevoMensajeInfo("Registro Provincial de Concursos de Saludo", "Sin resultados coincidentes con el DNI " + personaBuscada.getDni());
            }

        } catch (SQLException exSQL) {
            exSQL.printStackTrace();
        }
    }

}
