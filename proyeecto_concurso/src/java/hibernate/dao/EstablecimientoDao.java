/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate.dao;

import dominio.Establecimiento;
import java.util.List;

/**
 *
 * @author SIISAJUJUY
 */
public interface EstablecimientoDao {

    public List<Establecimiento> getAll();

    public List<Establecimiento> getEstablecimiento(String nombreEstablecimiento);

    public Establecimiento getEstablecimiento(int idEstablecimiento);

    public Establecimiento getEstablecimiento(long codigoSiisa);

    public void insertar(Establecimiento establecimiento);

    public void eliminar(Establecimiento establecimiento);

    public void modificar(Establecimiento establecimiento);
}
