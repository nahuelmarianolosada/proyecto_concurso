package dominio;
// Generated 22/07/2015 11:18:12 by Hibernate Tools 3.6.0


import java.util.HashSet;
import java.util.Set;

/**
 * Area generated by hbm2java
 */
public class Area  implements java.io.Serializable {


     private int prefijoExpediente;
     private int idArea;
     private String nombreArea;
     private Set expedientes = new HashSet(0);

    public Area() {
    }

	
    public Area(int prefijoExpediente, int idArea, String nombreArea) {
        this.prefijoExpediente = prefijoExpediente;
        this.idArea = idArea;
        this.nombreArea = nombreArea;
    }
    public Area(int prefijoExpediente, int idArea, String nombreArea, Set expedientes) {
       this.prefijoExpediente = prefijoExpediente;
       this.idArea = idArea;
       this.nombreArea = nombreArea;
       this.expedientes = expedientes;
    }
   
    public int getPrefijoExpediente() {
        return this.prefijoExpediente;
    }
    
    public void setPrefijoExpediente(int prefijoExpediente) {
        this.prefijoExpediente = prefijoExpediente;
    }
    public int getIdArea() {
        return this.idArea;
    }
    
    public void setIdArea(int idArea) {
        this.idArea = idArea;
    }
    public String getNombreArea() {
        return this.nombreArea;
    }
    
    public void setNombreArea(String nombreArea) {
        this.nombreArea = nombreArea;
    }
    public Set getExpedientes() {
        return this.expedientes;
    }
    
    public void setExpedientes(Set expedientes) {
        this.expedientes = expedientes;
    }




}

