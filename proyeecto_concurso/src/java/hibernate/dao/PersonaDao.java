/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import java.util.List;
import dominio.Persona;

/**
 *
 * @author favio
 */
public interface PersonaDao {

    public List<Persona> getAllPersona();

    public Persona getPersona(int idPersona);

    public void insertar(Persona persona);

    public void eliminar(Persona persona);

    public void modificar(Persona persona);
    

    
}
