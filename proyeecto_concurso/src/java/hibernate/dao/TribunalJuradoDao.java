/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.TribunalJurado;
import dominio.Resolucion;
import dominio.Tribunal;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface TribunalJuradoDao {
    public List<TribunalJurado> getAll();
    public TribunalJurado getTribunalJurado(int idTribunalJurado);
    public void insertar(TribunalJurado tribunalJurado);
    public void eliminar(TribunalJurado tribunalJurado);
    public void modificar(TribunalJurado tribunalJurado);
    public int generarNuevoIdJurado();
    public List <TribunalJurado> getJuradosDelTribunal(Tribunal tribunal);
}
