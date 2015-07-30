/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Cargo;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import org.primefaces.context.RequestContext;
import hibernate.dao.ResolucionDao;
import hibernate.dao.impl.ResolucionDaoImpl;
import hibernate.dao.CargoDao;
import hibernate.dao.impl.CargoDaoImpl;
import dominio.Resolucion;

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
    private boolean datosValidos;//Bandera que se referencia a la vista para habilitar la pestaña siguiente

    /**
     * Creates a new instance of CargoBean
     */
    public CargoBean() {
//        cargoNuevo = new Cargo(getListaEstablecimientos().get(0), getListaProfesiones().get(0));
        CargoDao cargoDao = new CargoDaoImpl();
        cargoNuevo = new Cargo();
        listaCargos = cargoDao.getListaCargosDeResolucion(cargoNuevo.getResolucion());
        inicializarCargo();
        cargoSeleccionado = new Cargo(generarIdNuevoCargo());
        datosValidos = false;
    }

    //GETTERS & SETTERS
    public List<Cargo> getListaCargos() {
        return listaCargos;
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

    /**
     *
     * Método que setea el cargo Nuevo asignandole el nuevo Id de cargo y
     * obtiene la última resolución.
     *
     */
    public void inicializarCargo() {

//        CargoDao cargoDao = new CargoDaoImpl();
//        ResolucionDao resDao = new ResolucionDaoImpl();
//        ultimaResolucion = resDao.obtenerUltimaResolucion();
//        listaCargos = cargoDao.getListaCargosDeResolucion(ultimaResolucion);
        
//        System.out.println("Lista de cargos");
//        for (Cargo cargo : listaCargos) {
//            System.out.println(cargo.getIdCargo());
//        }

    }

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

}
