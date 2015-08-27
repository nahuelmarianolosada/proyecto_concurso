/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Expediente;
import dominio.UnidadDeOrganizacion;
import hibernate.dao.ExpedienteDao;
import hibernate.dao.UnidadDeOrganizacionDao;
import hibernate.dao.impl.ExpedienteDaoImpl;
import hibernate.dao.impl.UnidadDeOrganizacionDaoImpl;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.ViewScoped;
import org.hibernate.NonUniqueObjectException;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanExpediente")
@ViewScoped
public class ExpedienteBean extends ConcursoBean implements Serializable {

    private static final long serialVersionUID = 1L;

    //ATRIBUTOS
    private Expediente expedienteNuevo;//Expediente que se va a guardar
    private List<UnidadDeOrganizacion> listaUnidadDeOrganizacions; //lista que se utiliza para cargar el combo con las areas
    private boolean datosValidos;//Bandera que se referencia a la vista para habilitar la pestaña siguiente
    private List<Expediente> listaExpedientes;

    @ManagedProperty("#{beanResolucion}")
    private ResolucionBean beanResolucion;

    //GETTERS & SETTERS
    public Expediente getExpedienteNuevo() {
        return expedienteNuevo;
    }

    public void setExpedienteNuevo(Expediente expedienteNuevo) {
        this.expedienteNuevo = expedienteNuevo;
    }

    public List<UnidadDeOrganizacion> getListaUnidadDeOrganizacions() {
        return listaUnidadDeOrganizacions;
    }

    public void setListaUnidadDeOrganizacions(List<UnidadDeOrganizacion> listaUnidadDeOrganizacions) {
        this.listaUnidadDeOrganizacions = listaUnidadDeOrganizacions;
    }

    public boolean isDatosValidos() {
        return datosValidos;
    }

    public void setDatosValidos(boolean datosValidos) {
        this.datosValidos = datosValidos;
    }

    public List<Expediente> getListaExpedientes() {
        return listaExpedientes;
    }

    public void setListaExpedientes(List<Expediente> listaExpedientes) {
        this.listaExpedientes = listaExpedientes;
    }

    public ResolucionBean getBeanResolucion() {
        return beanResolucion;
    }

    public void setBeanResolucion(ResolucionBean beanResolucion) {
        this.beanResolucion = beanResolucion;
    }

    public List<Expediente> getAllExpedientes() {
        ExpedienteDao expedienteDao = new ExpedienteDaoImpl();
        return expedienteDao.getAll();
    }

    /**
     * CONSTRUCTOR VACIO Creates a new instance of ExpedienteBean
     */
    public ExpedienteBean() {
        listaUnidadDeOrganizacions = new ArrayList<UnidadDeOrganizacion>();
        ExpedienteDao expedienteDao = new ExpedienteDaoImpl();
        expedienteNuevo = new Expediente(expedienteDao.generarNuevoIdExpediente(),new UnidadDeOrganizacion(), 0, "", "", 0, "");
        System.out.println("ExpedienteBean.ExpedienteBean() => " + expedienteNuevo.toString());
        refreshListas();
        datosValidos = false;

        //listaExpedientes = expedienteDao.getAll();
    }

    //METODOS
    public void refreshListas() {
        System.out.println("ExpedienteBean.refreshListas() => Obteniendo las unidades de Organizacion");
        UnidadDeOrganizacionDao unidadDao = new UnidadDeOrganizacionDaoImpl();
        listaUnidadDeOrganizacions = unidadDao.getAll();
    }

    public void onUnidadSeleccionada() {
        for (UnidadDeOrganizacion unidad : listaUnidadDeOrganizacions) {
            if (unidad.getCodigoUnidadDeOrganizacion() == expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion()) {
                expedienteNuevo.setUnidadDeOrganizacion(unidad);
                System.out.println("ExpedienteBean.onUnidadSeleccionada() => Se a cargado el expediente nuevo con la UDO");
                break;
            }
        }
    }

    /**
     *
     * Método que se ejecuta cuando se apreta el boton de guardar en la pestaña
     * de expedientes. Setea el expediente asignandole una unidad de
     * organizacion y un numero de expediente formateado
     *
     */
    public void validarExpedienteTab() {

        try {
            for (UnidadDeOrganizacion unidad : listaUnidadDeOrganizacions) {
                if (unidad.getCodigoUnidadDeOrganizacion() == expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion()) {
                    expedienteNuevo.setUnidadDeOrganizacion(unidad);
                    break;
                }
            }

            expedienteNuevo.setNumeroExpediente(expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion() + "-" + expedienteNuevo.getNumero() + "/" + expedienteNuevo.getAnio());
            nuevoMensajeInfo("Expediente " + expedienteNuevo.getIdExpediente(), "Numero de Expediente: " + expedienteNuevo.getNumeroExpediente() + "\nSituación: " + expedienteNuevo.getSituacion() + "\nRégimen: " + expedienteNuevo.getRegimen() + "\nEstablecimiento: " + expedienteNuevo.getUnidadDeOrganizacion().getNombreUnidad());

        } catch (NullPointerException ex1) {
            nuevoMensajeAlerta("\033[31mError! " + ex1.getMessage(), ex1.getLocalizedMessage());
        }

    }

    public void guardarExpediente() {
        ExpedienteDao expedienteDao = new ExpedienteDaoImpl();
        try {

//            for (UnidadDeOrganizacion unidad : listaUnidadDeOrganizacions) {
//                if (unidad.getCodigoUnidadDeOrganizacion() == expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion()) {
//                    expedienteNuevo.setUnidadDeOrganizacion(unidad);
//                    break;
//                }
//            }
            String numExpedienteSinFormato = expedienteNuevo.getUnidadDeOrganizacion().getCodigoUnidadDeOrganizacion() + "-" + expedienteNuevo.getNumero() + "/" + expedienteNuevo.getAnio();
            expedienteNuevo.setNumeroExpediente(formatearExpediente(numExpedienteSinFormato));

            //Validamos que los datos guardados en el expediente sean validos y
            //que aparte no exista en la BD
            if (expedienteDao.getExpediente(expedienteNuevo.getNumeroExpediente()) == null) {
                beanResolucion.getResolucionNueva().setExpediente(expedienteNuevo);
                
                //Seteamos el Expediente Final
                super.setExpedienteFinalCargado(expedienteNuevo);

                expedienteDao.insertar(expedienteNuevo);
                System.out.println("\033[32mExpedienteBean.guardarExpediente() => " + expedienteNuevo.toString());

                datosValidos = true;
                pasarVistaDePestania();
                nuevoMensajeInfo("Registro de Concursos de Salud - EXPEDIENTE", "Número: " + expedienteNuevo.getNumeroExpediente() + "\nRégimen: " + expedienteNuevo.getRegimen() + "\nSituación: " + expedienteNuevo.getSituacion());
                expedienteNuevo = new Expediente(expedienteDao.generarNuevoIdExpediente(), new UnidadDeOrganizacion());
            } else {
                nuevoMensajeAlerta("Registro de Concursos de Salud", "Número de Expediente Inválido");
            }

        } catch (NonUniqueObjectException exUnico) {
            nuevoMensajeAlerta("Registro de Concursos de Salud", "Expediente ya existente");
            exUnico.printStackTrace();
        } catch (Exception ex1) {
            ex1.printStackTrace();
        }

    }

    /**
     * Funcion que realiza un formato esstructurado para el numero del
     * expediente. Solamente formatea los numeros de expedientes que tengan
     * menos de 14 caracteres
     *
     * @param numeroExpediente El numero del expediente completo que se desea
     * formatear
     * @return El numero del expediente formateado
     */
    public static String formatearExpediente(String numeroExpediente) {
        String numeroExpedienteFormateado = "";
        System.out.print("Formateando Expediente recibido: [" + numeroExpediente + "]");
        char[] cadenaCaracteres = numeroExpediente.toCharArray();

        if (numeroExpediente.length() != 14) {

            int indiceGuion = numeroExpediente.indexOf(45);
            int indiceBarra = numeroExpediente.indexOf(47);

            String udo = numeroExpediente.substring(0, 3);
            String numero = numeroExpediente.substring(indiceGuion + 1, indiceBarra);
            String anio = numeroExpediente.substring(indiceBarra + 1, numeroExpediente.length());

            char[] numeroFormateado = new char[5];

            //vamos llenando el numero formateado de atras para adelante
            //con el numero
            int i = numeroFormateado.length - 1;
            while (i > 0) {
                for (int j = numero.length() - 1; j >= 0; j--) {
                    numeroFormateado[i] = numero.charAt(j);
                    i--;
                }
                while (i >= 0) {
                    numeroFormateado[i] = '0';
                    i--;
                }
            }

            numeroExpediente = new String(numeroFormateado);
            System.out.println("\nUDO = " + udo + "\nNumero Final = " + numeroExpediente + "\nAño = " + anio);
            numeroExpedienteFormateado = udo + "-" + numeroExpediente + "/" + anio;
        }
        return numeroExpedienteFormateado;
    }

}
