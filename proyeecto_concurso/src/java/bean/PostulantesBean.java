/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Convocatoria;
import dominio.Persona;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanPostulante")
@ViewScoped
public class PostulantesBean {

    private Convocatoria nuevoPostulante;
    private List<Convocatoria> listaPostulantes;
    private boolean datosValidos, banderaBtn;
    private String buscado, criterio;

    /**
     * Creates a new instance of PostulantesBean
     */
    public PostulantesBean() {
        nuevoPostulante = new Convocatoria(new Persona());
        
    }

    /*
     GETTERS && SETTERS
     */
    public Convocatoria getNuevoPostulante() {
        return nuevoPostulante;
    }

    public void setNuevoPostulante(Convocatoria nuevoPostulante) {
        this.nuevoPostulante = nuevoPostulante;
    }

    public List<Convocatoria> getListaPostulantes() {
        return listaPostulantes;
    }

    public void setListaPostulantes(List<Convocatoria> listaPostulantes) {
        this.listaPostulantes = listaPostulantes;
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
    
    

    /*
     METODOS
     */
    public void buscarPorCriterio(){
        switch(criterio){
            case "nombre":{
                
                break;
            }
        }
    }
    
    public void validarBuscador(){
        if(buscado.isEmpty()){
            banderaBtn = false;
        }else{
            banderaBtn = true;
        }
    }
    
}
