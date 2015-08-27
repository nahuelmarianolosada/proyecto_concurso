/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Cargo;
import dominio.Establecimiento;
import dominio.Profesion;
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
    private boolean finalizoCarga;

    /**
     * Creates a new instance of CargoBean
     */
    public CargoBean() {
//        cargoNuevo = new Cargo(getListaEstablecimientos().get(0), getListaProfesiones().get(0));
        ProfesionDao profDao = new ProfesionDaoImpl();
        listaProfesiones = profDao.getAll();
        cargoNuevo = new Cargo(listaProfesiones.get(0));
        cargoNuevo.setEstablecimiento(super.getListaEstablecimientos().get(0));

        cargoSeleccionado = new Cargo();
        datosValidos = false;

        listaCargos = new ArrayList<Cargo>();

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

    public boolean isFinalizoCarga() {
        return finalizoCarga;
    }

    public void setFinalizoCarga(boolean finalizoCarga) {
        this.finalizoCarga = finalizoCarga;
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
        if (cargoNuevo.getCantidad() > 0) {
            try {
                ProfesionDao profDao = new ProfesionDaoImpl();
                Profesion profEncontrada = profDao.getProfesion(cargoNuevo.getProfesion().getIdProfesion());
                cargoNuevo.setProfesion(profEncontrada);
                obtenerEstablecimiento(cargoNuevo);
                System.out.println("CargoBean.guardarNuevoCargo() => Cantidad de Cargos: " + cargoNuevo.getCantidad());

                for (Resolucion resol : getListaFinalResoluciones()) {
                    if (resol.getNumeroResolucion().equalsIgnoreCase(cargoNuevo.getResolucion().getNumeroResolucion())) {
                        cargoNuevo.setResolucion(resol);
                        break;
                    }
                }

                listaCargos.add(cargoNuevo);
                cargoNuevo.setIdCargo(cargoNuevo.getIdCargo() + 1);

                System.out.println("\033[32mCargoBean.guardarNuevoCargo() => Cargo Nuevo: " + cargoNuevo.toString());
                finalizoCarga = true;

                //Obtenemos la resolucion para asignarsela al siguiente cargo que se cargue
                Resolucion res = cargoNuevo.getResolucion();
                cargoNuevo = new Cargo(listaProfesiones.get(0));
                cargoNuevo.setEstablecimiento(getListaEstablecimientos().get(0));
                cargoNuevo.setResolucion(res);

            } catch (Exception exGeneral) {
                exGeneral.printStackTrace();
            }
        } else {
            nuevoMensajeAlerta("Registro Provincial de Concursos de Salud", "La cantidad de cargos debe ser mayor a 0");
            throw new NullPointerException("Cantidad igual a 0!");
        }
    }

    /**
     *
     * Metodo que guarda todos los cargos precargados en la lista
     */
    public void guardarCargos() {
        int sumatoria = 0;
        CargoDao cargoDao = new CargoDaoImpl();
        if (listaCargos.size() > 0) {
            datosValidos = true;
        }

        for (Cargo cargo : listaCargos) {
            for (int i = 0; i < cargo.getCantidad(); i++) {
                //cargo.setIdCargo(cargoDao.generarNuevoIdCargo());
                super.getListaFinalCargos().add(cargo);
                cargoDao.insertar(cargo);
                sumatoria++;
            }
        }
        nuevoMensajeInfo("Registro de concursos de Salud", sumatoria + " cargos fueron cargados");
        pasarVistaDePestania();
    }

    /**
     *
     * Metodo que setea, si existe, el establecimiento en el cargo
     *
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
