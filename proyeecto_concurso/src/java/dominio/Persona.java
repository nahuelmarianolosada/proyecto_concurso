package dominio;
// Generated 24/07/2015 09:37:24 by Hibernate Tools 3.6.0


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Persona generated by hbm2java
 */
public class Persona  implements java.io.Serializable {


     private int idPersona;
     private Localidad localidadByLocalidadNacimiento;
     private Localidad localidadByIdLocalidadDireccion;
     private Integer dni;
     private String nombres;
     private String apellido;
     private String sexo;
     private String nacionalidad;
     private Date fechaDeNacimiento;
     private String direccion;
     private String telefono;
     private String email;
     private Long cuil;
     private Integer edad;
     private Set convocatorias = new HashSet(0);
     private Set tribunalJurados = new HashSet(0);
     private Set personals = new HashSet(0);

    public Persona() {
    }

	
    public Persona(int idPersona) {
        this.idPersona = idPersona;
    }

    public Persona(Integer dni, Long cuil) {
        this.dni = dni;
        this.cuil = cuil;
    }
    
    
    
    public Persona(int idPersona, Localidad localidadByLocalidadNacimiento, Localidad localidadByIdLocalidadDireccion, Integer dni, String nombres, String apellido, String sexo, String nacionalidad, Date fechaDeNacimiento, String direccion, String telefono, String email, Long cuil, Integer edad, Set convocatorias, Set tribunalJurados, Set personals) {
       this.idPersona = idPersona;
       this.localidadByLocalidadNacimiento = localidadByLocalidadNacimiento;
       this.localidadByIdLocalidadDireccion = localidadByIdLocalidadDireccion;
       this.dni = dni;
       this.nombres = nombres;
       this.apellido = apellido;
       this.sexo = sexo;
       this.nacionalidad = nacionalidad;
       this.fechaDeNacimiento = fechaDeNacimiento;
       this.direccion = direccion;
       this.telefono = telefono;
       this.email = email;
       this.cuil = cuil;
       this.edad = edad;
       this.convocatorias = convocatorias;
       this.tribunalJurados = tribunalJurados;
       this.personals = personals;
    }
   
    public int getIdPersona() {
        return this.idPersona;
    }
    
    public void setIdPersona(int idPersona) {
        this.idPersona = idPersona;
    }
    public Localidad getLocalidadByLocalidadNacimiento() {
        return this.localidadByLocalidadNacimiento;
    }
    
    public void setLocalidadByLocalidadNacimiento(Localidad localidadByLocalidadNacimiento) {
        this.localidadByLocalidadNacimiento = localidadByLocalidadNacimiento;
    }
    public Localidad getLocalidadByIdLocalidadDireccion() {
        return this.localidadByIdLocalidadDireccion;
    }
    
    public void setLocalidadByIdLocalidadDireccion(Localidad localidadByIdLocalidadDireccion) {
        this.localidadByIdLocalidadDireccion = localidadByIdLocalidadDireccion;
    }
    public Integer getDni() {
        return this.dni;
    }
    
    public void setDni(Integer dni) {
        this.dni = dni;
    }
    public String getNombres() {
        return this.nombres;
    }
    
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }
    public String getApellido() {
        return this.apellido;
    }
    
    public void setApellido(String apellido) {
        this.apellido = apellido;
    }
    public String getSexo() {
        return this.sexo;
    }
    
    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
    public String getNacionalidad() {
        return this.nacionalidad;
    }
    
    public void setNacionalidad(String nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
    public Date getFechaDeNacimiento() {
        return this.fechaDeNacimiento;
    }
    
    public void setFechaDeNacimiento(Date fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }
    public String getDireccion() {
        return this.direccion;
    }
    
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
    public String getTelefono() {
        return this.telefono;
    }
    
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public Long getCuil() {
        return this.cuil;
    }
    
    public void setCuil(Long cuil) {
        this.cuil = cuil;
    }
    public Integer getEdad() {
        return this.edad;
    }
    
    public void setEdad(Integer edad) {
        this.edad = edad;
    }
    public Set getConvocatorias() {
        return this.convocatorias;
    }
    
    public void setConvocatorias(Set convocatorias) {
        this.convocatorias = convocatorias;
    }
    public Set getTribunalJurados() {
        return this.tribunalJurados;
    }
    
    public void setTribunalJurados(Set tribunalJurados) {
        this.tribunalJurados = tribunalJurados;
    }
    public Set getPersonals() {
        return this.personals;
    }
    
    public void setPersonals(Set personals) {
        this.personals = personals;
    }




}


