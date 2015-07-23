/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Profesion;

import java.util.List;


/**
 *
 * @author SIISAJUJUY
 */
public interface ProfesionDao {
    public List<Profesion> getAll();
    public Profesion getProfesion(int idProfesion);
    public void insertar(Profesion profesion);
    public void eliminar(Profesion profesion);
    public void modificar(Profesion profesion);
}
