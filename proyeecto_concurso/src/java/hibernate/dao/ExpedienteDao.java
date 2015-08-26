/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package hibernate.dao;

import dominio.Expediente;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface ExpedienteDao {
    public List<Expediente> getAll();
    public Expediente getExpediente(int idExpediente);
    public Expediente getExpediente(String numeroExpediente);
    public int generarNuevoIdExpediente();
    public void insertar(Expediente expediente);
    public void eliminar(Expediente expediente);
    public void modificar(Expediente expediente);
}
