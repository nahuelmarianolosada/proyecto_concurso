/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Localidad;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface LocalidadDao {
    public List<Localidad> getAll();

    public Localidad getLocalidad(int idLocalidad);

    public List<Localidad> getLocalidad(String nombreLocalidad);

    public void insertar(Localidad localidad);

    public void eliminar(Localidad localidad);

    public void modificar(Localidad localidad);
}
