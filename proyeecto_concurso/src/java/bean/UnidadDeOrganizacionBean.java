/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import hibernate.dao.UnidadDeOrganizacionDao;
import hibernate.dao.impl.UnidadDeOrganizacionDaoImpl;
import dominio.UnidadDeOrganizacion;
import java.util.List;
/**
 *
 * @author favio
 */
@ManagedBean(name = "beanUnidadDeOrganizacion")
@ViewScoped
public class UnidadDeOrganizacionBean implements Serializable{
   
private List<UnidadDeOrganizacion> listaUnidadOrganizacion;
private UnidadDeOrganizacion udoSelececcionada;
private UnidadDeOrganizacion udoNueva;

    /**
     * Creates a new instance of UnidadDeOrganizacionBeanBean
     */
    public List<UnidadDeOrganizacion> getListaUnidadOrganizacion() {
        return listaUnidadOrganizacion;
    }

    public void setListaUnidadOrganizacion(List<UnidadDeOrganizacion> listaUnidadOrganizacion) {
        this.listaUnidadOrganizacion = listaUnidadOrganizacion;
    }

    public UnidadDeOrganizacion getUdoSelececcionada() {
        return udoSelececcionada;
    }

    public void setUdoSelececcionada(UnidadDeOrganizacion udoSelececcionada) {
        this.udoSelececcionada = udoSelececcionada;
    }

    public UnidadDeOrganizacion getUdoNueva() {
        return udoNueva;
    }

    public void setUdoNueva(UnidadDeOrganizacion udoNueva) {
        this.udoNueva = udoNueva;
    }

     
    
    
    
    
    
    
}
