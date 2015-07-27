/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Tribunal;
import java.util.List;

/**
 *
 * @author nahuel
 */
public interface TribunalDao {
    public List<Tribunal> getAll();
    public Tribunal getTribunal(int idTribunal);
    public void insertar(Tribunal tribunal);
    public void eliminar(Tribunal tribunal);
    public void modificar(Tribunal tribunal);
}
