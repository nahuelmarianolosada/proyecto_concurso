/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.TipoUsuario;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface TipoUsuarioDao {
    public List<TipoUsuario> getAll();
    public TipoUsuario getTipoUsuario(int idTipoUsuario);
    public void insertar(TipoUsuario tipoUsuario);
    public void eliminar(TipoUsuario tipoUsuario);
    public void modificar(TipoUsuario tipoUsuario);
}
