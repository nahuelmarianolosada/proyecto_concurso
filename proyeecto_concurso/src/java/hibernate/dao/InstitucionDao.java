/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import dominio.Institucion;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface InstitucionDao {

    public List<Institucion> getAll();

    public Institucion getInstitucion(int idInstitucion);

    public List<Institucion> getInstitucion(String nombreInstitucion);

    public void insertar(Institucion institucion);

    public void eliminar(Institucion institucion);

    public void modificar(Institucion institucion);
}
