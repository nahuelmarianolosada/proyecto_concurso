/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Cargo;
import dominio.Establecimiento;
import dominio.Profesion;
import dominio.TribunalJurado;
import dominio.Tribunal;
import java.io.Serializable;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import hibernate.dao.ResolucionDao;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.CargoDao;
import hibernate.dao.impl.CargoDaoImpl;
import dominio.Resolucion;
import hibernate.dao.EstablecimientoDao;
import hibernate.dao.ProfesionDao;
import hibernate.dao.impl.EstablecimientoDaoImpl;
import hibernate.dao.impl.ProfesionDaoImpl;
import java.util.ArrayList;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanCargo")
@ViewScoped
public class CargoBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //ATRIBUTOS
    private List<Cargo> listaCargos;
    private Cargo cargoNuevo;
    private Cargo ultimoCargo;
    private Cargo cargoSeleccionado;
    private Resolucion ultimaResolucion;
    private boolean datosValidos;//Bandera que se referencia a la vista para habilitar la pestaña siguiente
    private List<Profesion> listaProfesiones;
    private Integer cantidad;
    private List<TribunalJurado> listaJurados;
    private TribunalJurado juradoNuevo;
    private Tribunal tribunalNuevo;
    /**
     * Creates a new instance of CargoBean
     */
    public CargoBean() {
//        cargoNuevo = new Cargo(getListaEstablecimientos().get(0), getListaProfesiones().get(0));
        ProfesionDao profDao = new ProfesionDaoImpl();
        listaProfesiones = profDao.getAll();
        cargoNuevo = new Cargo(generarIdNuevoCargo(), listaProfesiones.get(0));
        cargoNuevo.setEstablecimiento(super.getListaEstablecimientos().get(0));

        cargoSeleccionado = new Cargo();
        datosValidos = false;
            
        listaCargos = new ArrayList<Cargo>();
        listaJurados =new ArrayList<TribunalJurado>();
        juradoNuevo= new TribunalJurado();
        tribunalNuevo= new Tribunal();
        
    }

    //GETTERS & SETTERS
    public List<Cargo> getListaCargos() {
        return listaCargos;
    }

    public Cargo getUltimoCargo() {
        return ultimoCargo;
    }

    public void setUltimoCargo(Cargo ultimoCargo) {
        this.ultimoCargo = ultimoCargo;
    }

    public Resolucion getUltimaResolucion() {
        return ultimaResolucion;
    }

    public void setUltimaResolucion(Resolucion ultimaresolucion) {
        this.ultimaResolucion = ultimaresolucion;
    }

    public void setListaCargos(List<Cargo> listaCargos) {
        this.listaCargos = listaCargos;
    }

    public Cargo getCargoNuevo() {
        return cargoNuevo;
    }

    public void setCargoNuevo(Cargo cargoNuevo) {
        this.cargoNuevo = cargoNuevo;
    }

    public Cargo getCargoSeleccionado() {
        return cargoSeleccionado;
    }

    public void setCargoSeleccionado(Cargo cargoSeleccionado) {
        this.cargoSeleccionado = cargoSeleccionado;
    }

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public List<Profesion> getListaProfesiones() {
        return listaProfesiones;
    }

    public void setListaProfesiones(List<Profesion> listaProfesiones) {
        this.listaProfesiones = listaProfesiones;
    }

    public Integer getCantidad() {
        return cantidad;
    }

    public void setCantidad(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public List<TribunalJurado> getListaJurados() {
        return listaJurados;
    }

    public void setListaJurados(List<TribunalJurado> listaJurados) {
        this.listaJurados = listaJurados;
    }

    public TribunalJurado getJuradoNuevo() {
        return juradoNuevo;
    }

    public void setJuradoNuevo(TribunalJurado juradoNuevo) {
        this.juradoNuevo = juradoNuevo;
    }

    public Tribunal getTribunalNuevo() {
        return tribunalNuevo;
    }

    public void setTribunalNuevo(Tribunal tribunalNuevo) {
        this.tribunalNuevo = tribunalNuevo;
    }
    
    

    //METODOS
    /**
     *
     * Obtiene la ultima resolución cargada.
     *
     * @return la ultima resolucion cargada
     */
    public Resolucion obtenerUltimaResolucion() {
        ResolucionDao resDao = new ResolucionDaoImpl();
        Resolucion resolucionEntidad = resDao.obtenerUltimaResolucion();
        return resolucionEntidad;

    }

    //Genera el Id del Nuevo Cargo a grabar
    public int generarIdNuevoCargo() {
        CargoDao resCarg = new CargoDaoImpl();
        return (resCarg.generarNuevoIdCargo());

    }

    public void guardarNuevoCargo() {
        try {
            ProfesionDao profDao = new ProfesionDaoImpl();
            Profesion profEncontrada = profDao.getProfesion(cargoNuevo.getProfesion().getIdProfesion());
            cargoNuevo.setProfesion(profEncontrada);
            obtenerEstablecimiento(cargoNuevo);
            System.out.println("\033[32mCargoBean.guardarNuevoCargo() => Cargo Nuevo: " + cargoNuevo.toString());
            
            //Obtenemos la resolucion para asignarsela al siguiente cargo que se cargue
            Resolucion res = cargoNuevo.getResolucion();
            
            listaCargos.add(cargoNuevo);
            cargoNuevo = new Cargo(generarIdNuevoCargo(), listaProfesiones.get(0));
            cargoNuevo.setEstablecimiento(getListaEstablecimientos().get(0));
            cargoNuevo.setResolucion(res);
            if (listaCargos.size() > 0) {
                datosValidos = true;
            }
            
        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
        }
    }
    
    /**
     * 
     * Metodo que guarda todos los cargos precargados en la lista
     */
    public void guardarCargos(){
        nuevoMensajeInfo("Registro de concursos de Salud", listaCargos.size() + " cargos fueron cargados");
        pasarVistaDePestania();
    }

    /**
     *
     * Metodo que setea, si existe, el establecimiento en el cargo
     * @param cargo cargo al que se desea establecer el establecimiento
     */
    public void obtenerEstablecimiento(Cargo cargo) {
        try {

            EstablecimientoDao establDao = new EstablecimientoDaoImpl();
            Establecimiento estEncontrado = establDao.getEstablecimiento(cargo.getEstablecimiento().getCodigoSiisa());
            if (estEncontrado != null) {
                cargo.setEstablecimiento(estEncontrado);
            } else {
                System.out.println("\033[31mCargoBean.obtenerEstablecimiento() => No se encontro el establecimiento con el codigo ");
            }
        } catch (Exception exGeneral) {
            exGeneral.printStackTrace();
        }
    }

}
