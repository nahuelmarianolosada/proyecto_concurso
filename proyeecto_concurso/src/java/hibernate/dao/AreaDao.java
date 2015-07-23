/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Area;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface AreaDao {
    public List<Area> getAll();
    public Area getArea(int idArea);
    public void insertar(Area area);
    public void eliminar(Area area);
    public void modificar(Area area);
}
