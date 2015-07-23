/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Usuario;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface UsuarioDao {
    public List<Usuario> getAll();
    public Usuario getUsuario(int idUsuario);
    public Usuario getUsuario(String user, String pass);
    public int getNuevoId();
    public void insertar(Usuario usuario);
    public void eliminar(Usuario usuario);
    public void eliminarById(int id_usuario);
    public void modificar(Usuario usuario);
}
