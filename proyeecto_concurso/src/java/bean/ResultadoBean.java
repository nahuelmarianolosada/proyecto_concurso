/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Concurso;
import dominio.Postulante;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.CellEditEvent;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanResultado")
@ViewScoped
public class ResultadoBean {

    @ManagedProperty("#{beanPostulante}")
    private PostulantesBean beanPostulantes;
    
   
    
    private Concurso nuevoConcurso;

    public PostulantesBean getBeanPostulantes() {
        return beanPostulantes;
    }

    public void setBeanPostulantes(PostulantesBean beanPostulantes) {
        this.beanPostulantes = beanPostulantes;
    }

    public Concurso getNuevoConcurso() {
        return nuevoConcurso;
    }

    public void setNuevoConcurso(Concurso nuevoConcurso) {
        this.nuevoConcurso = nuevoConcurso;
    }
    
    
    public ResultadoBean() {
        
    }

    
    
    
    public void onCellEdit(CellEditEvent event) {
        Object oldValue = event.getOldValue();
        Object newValue = event.getNewValue();

        if (newValue != null && !newValue.equals(oldValue)) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_INFO, "Cell Changed", "Old: " + oldValue + ", New:" + newValue);
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

}
