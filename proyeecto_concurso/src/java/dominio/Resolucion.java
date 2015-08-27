package dominio;
// Generated 26/08/2015 11:54:39 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Resolucion generated by hbm2java
 */
public class Resolucion  implements java.io.Serializable {


     private int idResolucion;
     private Expediente expediente;
     private Tribunal tribunal;
     private String estado;
     private Boolean modificacion;
     private Boolean prorroga;
     private Boolean antecedente;
     private Boolean oposicion;
     private String clase;
     private String agrupamiento;
     private Date fechaApertura;
     private Date fechaCierre;
     private Date fechaEjecucion;
     private Date fechaPublicacion;
     private String documento;
     private String numeroResolucion;
     private String modificaResolucion;
     private String prorrogaResolucion;
     private Set concursos = new HashSet(0);
     private Set cargos = new HashSet(0);

    public Resolucion() {
    }

    public Resolucion(int idResolucion) {
        this.idResolucion = idResolucion;
    }

    
    public Resolucion(int idResolucion, String numeroResolucion) {
        this.idResolucion = idResolucion;
        this.numeroResolucion = numeroResolucion;
    }
    public Resolucion(int idResolucion, Expediente expediente, Tribunal tribunal, String estado, Boolean modificacion, Boolean prorroga, Boolean antecedente, Boolean oposicion, String clase, String agrupamiento, Date fechaApertura, Date fechaCierre, Date fechaEjecucion, Date fechaPublicacion, String documento, String numeroResolucion, String modificaResolucion, String prorrogaResolucion, Set concursos, Set cargos) {
       this.idResolucion = idResolucion;
       this.expediente = expediente;
       this.tribunal = tribunal;
       this.estado = estado;
       this.modificacion = modificacion;
       this.prorroga = prorroga;
       this.antecedente = antecedente;
       this.oposicion = oposicion;
       this.clase = clase;
       this.agrupamiento = agrupamiento;
       this.fechaApertura = fechaApertura;
       this.fechaCierre = fechaCierre;
       this.fechaEjecucion = fechaEjecucion;
       this.fechaPublicacion = fechaPublicacion;
       this.documento = documento;
       this.numeroResolucion = numeroResolucion;
       this.modificaResolucion = modificaResolucion;
       this.prorrogaResolucion = prorrogaResolucion;
       this.concursos = concursos;
       this.cargos = cargos;
    }
   
    public int getIdResolucion() {
        return this.idResolucion;
    }
    
    public void setIdResolucion(int idResolucion) {
        this.idResolucion = idResolucion;
    }
    public Expediente getExpediente() {
        return this.expediente;
    }
    
    public void setExpediente(Expediente expediente) {
        this.expediente = expediente;
    }
    public Tribunal getTribunal() {
        return this.tribunal;
    }
    
    public void setTribunal(Tribunal tribunal) {
        this.tribunal = tribunal;
    }
    public String getEstado() {
        return this.estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    public Boolean getModificacion() {
        return this.modificacion;
    }
    
    public void setModificacion(Boolean modificacion) {
        this.modificacion = modificacion;
    }
    public Boolean getProrroga() {
        return this.prorroga;
    }
    
    public void setProrroga(Boolean prorroga) {
        this.prorroga = prorroga;
    }
    public Boolean getAntecedente() {
        return this.antecedente;
    }
    
    public void setAntecedente(Boolean antecedente) {
        this.antecedente = antecedente;
    }
    public Boolean getOposicion() {
        return this.oposicion;
    }
    
    public void setOposicion(Boolean oposicion) {
        this.oposicion = oposicion;
    }
    public String getClase() {
        return this.clase;
    }
    
    public void setClase(String clase) {
        this.clase = clase;
    }
    public String getAgrupamiento() {
        return this.agrupamiento;
    }
    
    public void setAgrupamiento(String agrupamiento) {
        this.agrupamiento = agrupamiento;
    }
    public Date getFechaApertura() {
        return this.fechaApertura;
    }
    
    public void setFechaApertura(Date fechaApertura) {
        this.fechaApertura = fechaApertura;
    }
    public Date getFechaCierre() {
        return this.fechaCierre;
    }
    
    public void setFechaCierre(Date fechaCierre) {
        this.fechaCierre = fechaCierre;
    }
    public Date getFechaEjecucion() {
        return this.fechaEjecucion;
    }
    
    public void setFechaEjecucion(Date fechaEjecucion) {
        this.fechaEjecucion = fechaEjecucion;
    }
    public Date getFechaPublicacion() {
        return this.fechaPublicacion;
    }
    
    public void setFechaPublicacion(Date fechaPublicacion) {
        this.fechaPublicacion = fechaPublicacion;
    }
    public String getDocumento() {
        return this.documento;
    }
    
    public void setDocumento(String documento) {
        this.documento = documento;
    }
    public String getNumeroResolucion() {
        return this.numeroResolucion;
    }
    
    public void setNumeroResolucion(String numeroResolucion) {
        this.numeroResolucion = numeroResolucion;
    }
    public String getModificaResolucion() {
        return this.modificaResolucion;
    }
    
    public void setModificaResolucion(String modificaResolucion) {
        this.modificaResolucion = modificaResolucion;
    }
    public String getProrrogaResolucion() {
        return this.prorrogaResolucion;
    }
    
    public void setProrrogaResolucion(String prorrogaResolucion) {
        this.prorrogaResolucion = prorrogaResolucion;
    }
    public Set getConcursos() {
        return this.concursos;
    }
    
    public void setConcursos(Set concursos) {
        this.concursos = concursos;
    }
    public Set getCargos() {
        return this.cargos;
    }
    
    public void setCargos(Set cargos) {
        this.cargos = cargos;
    }

    @Override
    public String toString() {
        return "Resolucion " + idResolucion + " {expediente=" + expediente.getNumeroExpediente() + ", tribunal=" + tribunal.getIdTribunal() + ", estado=" + estado + ", modificacion=" + modificacion + ", prorroga=" + prorroga + ", antecedente=" + antecedente + ", oposicion=" + oposicion + ", clase=" + clase + ", agrupamiento=" + agrupamiento + ", fechaApertura=" + fechaApertura + ", fechaCierre=" + fechaCierre + ", fechaEjecucion=" + fechaEjecucion + ", fechaPublicacion=" + fechaPublicacion + ", documento=" + documento + ", numeroResolucion=" + numeroResolucion + ", modificaResolucion=" + modificaResolucion + ", prorrogaResolucion=" + prorrogaResolucion + '}';
    }


    


}


