/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.UnidadDeOrganizacion;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface UnidadDeOrganizacionDao {
    public List<UnidadDeOrganizacion> getAll();
    public UnidadDeOrganizacion getUnidadDeOrganizacion(int idUnidadDeOrganizacion);
//    public void insertar(UnidadDeOrganizacion unidadDeOrganizacion);
    public void eliminar(UnidadDeOrganizacion unidadDeOrganizacion);
    public void modificar(UnidadDeOrganizacion unidadDeOrganizacion);
}
