/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Cargo;
import dominio.Postulante;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface PostulanteDao {
    public List<Postulante> getAll();
    public Postulante getPostulante(int idPostulante);
    public Postulante getPostulanteAcreditados(Cargo cargo);
    public int generarIdNuevoPostulante();
    public void insertar(Postulante postulante);
    public void eliminar(Postulante postulante);
    public void modificar(Postulante postulante);
}
