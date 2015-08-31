package dominio;
// Generated 26/08/2015 11:54:39 by Hibernate Tools 3.6.0

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Cargo generated by hbm2java
 */
public class Cargo implements java.io.Serializable {

    private int idCargo;
    private Resolucion resolucion;
    private Establecimiento establecimiento;
    private Profesion profesion;
    private String especialidad;
    private String categoria;
    private Integer adicional;
    private String funcion;
    private String areaDeDesempenio;
    private String modalidad;
    private Date fechaActaFormulacionPerfil;
    private String enunciacion;
    private boolean esDesierto;
    private Integer cantidad;
    private Set postulantes = new HashSet(0);

    public Cargo() {
    }

    public Cargo(boolean esDesierto) {
        this.esDesierto = esDesierto;
    }
    
    

    public Cargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public Cargo(int idCargo, Profesion profesion) {
        this.idCargo = idCargo;
        this.profesion = profesion;
    }

    public Cargo(int idCargo, Resolucion resolucion, Establecimiento establecimiento, Profesion profesion) {
        this.idCargo = idCargo;
        this.resolucion = resolucion;
        this.establecimiento = establecimiento;
        this.profesion = profesion;
    }
    
    

    public Cargo(Profesion profesion) {
        this.profesion = profesion;
    }

    public Cargo(Establecimiento establecimiento, Profesion profesion) {
        this.establecimiento = establecimiento;
        this.profesion = profesion;
    }

    public Cargo(Resolucion resolucion, Establecimiento establecimiento, Profesion profesion) {
        this.resolucion = resolucion;
        this.establecimiento = establecimiento;
        this.profesion = profesion;
    }

    public Cargo(int idCargo, Establecimiento establecimiento, Profesion profesion) {
        this.idCargo = idCargo;
        this.establecimiento = establecimiento;
        this.profesion = profesion;
    }

    public Cargo(int idCargo, Resolucion resolucion, Establecimiento establecimiento, Profesion profesion, String especialidad, String categoria, Integer adicional, String funcion, String areaDeDesempenio, String modalidad, Date fechaActaFormulacionPerfil, String enunciacion) {
        this.idCargo = idCargo;
        this.resolucion = resolucion;
        this.establecimiento = establecimiento;
        this.profesion = profesion;
        this.especialidad = especialidad;
        this.categoria = categoria;
        this.adicional = adicional;
        this.funcion = funcion;
        this.areaDeDesempenio = areaDeDesempenio;
        this.modalidad = modalidad;
        this.fechaActaFormulacionPerfil = fechaActaFormulacionPerfil;
        this.enunciacion = enunciacion;
    }

    public Cargo(int idCargo, Resolucion resolucion, Establecimiento establecimiento, Profesion profesion, String especialidad, String categoria, Integer adicional, String funcion, String areaDeDesempenio, String modalidad, Date fechaActaFormulacionPerfil, String enunciacion, Set postulantes) {
        this.idCargo = idCargo;
        this.resolucion = resolucion;
        this.establecimiento = establecimiento;
        this.profesion = profesion;
        this.especialidad = especialidad;
        this.categoria = categoria;
        this.adicional = adicional;
        this.funcion = funcion;
        this.areaDeDesempenio = areaDeDesempenio;
        this.modalidad = modalidad;
        this.fechaActaFormulacionPerfil = fechaActaFormulacionPerfil;
        this.enunciacion = enunciacion;
        this.postulantes = postulantes;
    }

    public int getIdCargo() {
        return this.idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public Resolucion getResolucion() {
        return this.resolucion;
    }

    public void setResolucion(Resolucion resolucion) {
        this.resolucion = resolucion;
    }

    public Establecimiento getEstablecimiento() {
        return this.establecimiento;
    }

    public void setEstablecimiento(Establecimiento establecimiento) {
        this.establecimiento = establecimiento;
    }

    public Profesion getProfesion() {
        return this.profesion;
    }

    public void setProfesion(Profesion profesion) {
        this.profesion = profesion;
    }

    public String getEspecialidad() {
        return this.especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public String getCategoria() {
        return this.categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public Integer getAdicional() {
        return this.adicional;
    }

    public void setAdicional(Integer adicional) {
        this.adicional = adicional;
    }

    public String getFuncion() {
        return this.funcion;
    }

    public void setFuncion(String funcion) {
        this.funcion = funcion;
    }

    public String getAreaDeDesempenio() {
        return this.areaDeDesempenio;
    }

    public void setAreaDeDesempenio(String areaDeDesempenio) {
        this.areaDeDesempenio = areaDeDesempenio;
    }

    public String getModalidad() {
        return this.modalidad;
    }

    public void setModalidad(String modalidad) {
        this.modalidad = modalidad;
    }

    public Date getFechaActaFormulacionPerfil() {
        return this.fechaActaFormulacionPerfil;
    }

    public void setFechaActaFormulacionPerfil(Date fechaActaFormulacionPerfil) {
        this.fechaActaFormulacionPerfil = fechaActaFormulacionPerfil;
    }

    public String getEnunciacion() {
        return this.enunciacion;
    }

    public void setEnunciacion(String enunciacion) {
        this.enunciacion = enunciacion;
    }

    public Set getPostulantes() {
        return this.postulantes;
    }

    public void setPostulantes(Set postulantes) {
        this.postulantes = postulantes;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public boolean isEsDesierto() {
        return esDesierto;
    }

    public void setEsDesierto(boolean esDesierto) {
        this.esDesierto = esDesierto;
    }

    

    @Override
    public String toString() {
        if (cantidad != null) {
            return "Cargo " + idCargo + "{resolucion=(" + resolucion.getIdResolucion() + ") " + resolucion.getNumeroResolucion() + ", establecimiento=(" + establecimiento.getCodigoSiisa() + ") " + establecimiento.getNombre() + ", profesion=(id: " + profesion.getIdProfesion() + ") " + profesion.getNombreProfesion() + ", especialidad=" + especialidad + ", categoria=" + categoria + ", adicional=" + adicional + ", funcion=" + funcion + ", areaDeDesempenio=" + areaDeDesempenio + ", modalidad=" + modalidad + ", fechaActaFormulacionPerfil=" + fechaActaFormulacionPerfil + ", enunciacion=" + enunciacion + ", es Desierto=" + esDesierto + ", cantidad=" + cantidad + '}';
        }else{
            return "Cargo " + idCargo + "{resolucion=(" + resolucion.getIdResolucion() + ") " + resolucion.getNumeroResolucion() + ", establecimiento=(" + establecimiento.getCodigoSiisa() + ") " + establecimiento.getNombre() + ", profesion=(id: " + profesion.getIdProfesion() + ") " + profesion.getNombreProfesion() + ", especialidad=" + especialidad + ", categoria=" + categoria + ", adicional=" + adicional + ", funcion=" + funcion + ", areaDeDesempenio=" + areaDeDesempenio + ", modalidad=" + modalidad + ", fechaActaFormulacionPerfil=" + fechaActaFormulacionPerfil + ", enunciacion=" + enunciacion  + ", es Desierto=" + esDesierto + '}';
        }
    }
    
    public String getMessage(){
        if (esDesierto) {
            return "DESIERTO";
        }else{
            return "Cargo " + idCargo /*+ "{resolucion=(" + resolucion.getIdResolucion() + ") " + resolucion.getNumeroResolucion() + ", establecimiento=(" + establecimiento.getCodigoSiisa() + ") " + establecimiento.getNombre() + ", profesion=(" + profesion.getProfesionRefencia() + ") " + profesion.getNombreProfesion() + ", especialidad=" + especialidad + ", categoria=" + categoria + ", adicional=" + adicional + ", funcion=" + funcion + ", areaDeDesempenio=" + areaDeDesempenio + ", modalidad=" + modalidad + ", fechaActaFormulacionPerfil=" + fechaActaFormulacionPerfil + ", enunciacion=" + enunciacion  + '}'*/;
        }
    }

}
