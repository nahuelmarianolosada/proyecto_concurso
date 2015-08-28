/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import dominio.Cargo;
import java.util.List;
import dominio.Resolucion;
import java.sql.SQLException;

/**
 *
 * @author SIISAJUJUY
 */
public interface CargoDao {

    public List<Cargo> getAll();

    public Cargo getCargo(int idCargo);

    public List<Cargo> getCargos(Resolucion resolucion);

    public void insertar(Cargo cargo) throws SQLException;

    public void eliminar(Cargo cargo);

    public void modificar(Cargo cargo);

    public List<Cargo> getListaCargosDeResolucion(Resolucion resolucion);

    public int generarNuevoIdCargo();

}
