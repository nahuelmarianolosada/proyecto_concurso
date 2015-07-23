/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.TipoUsuario;
import dominio.Usuario;
import hibernate.dao.TipoUsuarioDao;
import hibernate.dao.UsuarioDao;
import hibernate.dao.impl.TipoUsuarioDaoImpl;
import hibernate.dao.impl.UsuarioDaoImpl;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.hibernate.HibernateException;
import org.primefaces.context.RequestContext;
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.ToggleEvent;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanUsuarios")
@ViewScoped
public class UsuariosBean implements Serializable {
    
    private static final long serialVersionUID = 1L;
    private List<Usuario> listaUsuarios;
    private List<TipoUsuario> listaTiposUsuario;
    private Usuario usuarioSeleccionado;
    private int tipoUsuarioSeleccionado;
    private Usuario usuarioNuevo;

    /**
     * Creates a new instance of UsuariosBean
     */
    public UsuariosBean() {
        refreshListas();
        usuarioNuevo = new Usuario(new UsuarioDaoImpl().getNuevoId());
        tipoUsuarioSeleccionado = 0;
        usuarioSeleccionado = new Usuario();
    }
    
    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    public List<TipoUsuario> getListaTiposUsuario() {
        return listaTiposUsuario;
    }
    
    public void setListaTiposUsuario(List<TipoUsuario> listaTiposUsuario) {
        this.listaTiposUsuario = listaTiposUsuario;
    }
    
    public Usuario getUsuarioSeleccionado() {
        return usuarioSeleccionado;
    }
    
    public void setUsuarioSeleccionado(Usuario usuarioSeleccionado) {
        this.usuarioSeleccionado = usuarioSeleccionado;
    }
    
    public int getTipoUsuarioSeleccionado() {
        return tipoUsuarioSeleccionado;
    }
    
    public void setTipoUsuarioSeleccionado(int tipoUsuarioSeleccionado) {
        this.tipoUsuarioSeleccionado = tipoUsuarioSeleccionado;
    }
    
    public Usuario getUsuarioNuevo() {
        return usuarioNuevo;
    }
    
    public void setUsuarioNuevo(Usuario usuarioNuevo) {
        this.usuarioNuevo = usuarioNuevo;
    }
    
    public void refreshListas() {
        UsuarioDao usDao = new UsuarioDaoImpl();
        listaUsuarios = usDao.getAll();
        
        TipoUsuarioDao tipoUsDao = new TipoUsuarioDaoImpl();
        listaTiposUsuario = tipoUsDao.getAll();
    }
    
    public void abrirDialogoGestion() {
        RequestContext.getCurrentInstance().openDialog("dlgGestionUsuarios");
    }
    
    public void onUsuarioEdit(RowEditEvent event) {
        Usuario usModificado = (Usuario) event.getObject();
        TipoUsuario tpUsuarioSeleccionado = null;
        if (validarUsername(usModificado)) {
            
            TipoUsuarioDao tipoDao = new TipoUsuarioDaoImpl();
            tpUsuarioSeleccionado = tipoDao.getTipoUsuario(usModificado.getTipoUsuario().getIdTipoUsuario());
            
            usModificado.setTipoUsuario(tpUsuarioSeleccionado);
            
            System.out.println("beanUsuario.onUsuarioEdit(): id_usuario => " + usModificado.getIdUsuario());
            System.out.println("beanUsuario.onUsuarioEdit(): nombre => " + usModificado.getNombre());
            System.out.println("beanUsuario.onUsuarioEdit(): tipo => " + usModificado.getTipoUsuario().getIdTipoUsuario() + " " + usModificado.getTipoUsuario().getDescripcion());
            try {

//                UsuarioDao usDAO = new UsuarioDaoImpl();
//                usDAO.modificar(usModificado);
                guardarUsuarioEditado(usModificado);
                refreshListas();
                nuevoMensajeInfo("Registro de Concursos de Salud", "Usuario " + usModificado.getIdUsuario() + " modificado.");
            } catch (HibernateException ex1) {
                System.out.println("Error! " + ex1.getCause() + "\n" + ex1.getMessage());
            } catch (Exception exGeneral) {
                nuevoMensajeAlerta("Error!" + exGeneral.getClass(), exGeneral.getMessage());
            }
        } else {
            onUsuarioEditCancel();
            refreshListas();
            System.out.println("beanUsuario.onUsuarioEdit(): Username " + usModificado.getUserName() + " se encuentra repetido");
            
        }
    }
    
    public void guardarUsuarioEditado(Usuario usuario) throws SQLException {
        System.out.println("beanUsuario.guardarUsuarioEditado() => Guardando el usuario");
        String driver = "org.postgresql.Driver";
        String connectString = "jdbc:postgresql://localhost/concursosDB";
        String user = "nmlosada";
        String password = "siisa1234";
        Connection con = null;
        Statement stmt = null;
        
        try {
            Class.forName(driver);
            con = DriverManager.getConnection(connectString, user, password);
            stmt = con.createStatement();

            //UTILIZANDO UNA CONSULTA NORMAL
            String consultaSQL = "UPDATE usuario SET nombre='" + usuario.getNombre() + "', apellido='" + usuario.getApellido() + "',fecha_nacimiento='" + usuario.getFechaNacimiento() + "', dni=" + usuario.getDni() + ", user_name='" + usuario.getUserName() + "',pass='" + usuario.getPass() + "', activo='" + usuario.getActivo() + "', tipo_usuario_id_tipo_usuario=" + usuario.getTipoUsuario().getIdTipoUsuario() + " WHERE id_usuario=" + usuario.getIdUsuario() + ";";
            stmt.executeUpdate(consultaSQL);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            e.printStackTrace();
        } finally {
            stmt.close();
            con.close();
        }
    }
    
    public void onUsuarioEditCancel() {
        nuevoMensajeInfo("Registro de Concursos de Salud", "Se a cancelado la edicion del usuario.");
    }
    
    public void nuevoMensajeInfo(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,
                summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public void nuevoMensajeAlerta(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_WARN,
                summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
    
    public String getFechaActual() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaActual = new Date();
        return sdf.format(fechaActual);
    }
    
    public boolean validarUsername() {
        boolean resultado = true;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUserName().toLowerCase().equals(usuarioNuevo.getUserName().toLowerCase())) {
                nuevoMensajeAlerta("Registro de Concursos de Salud", "El username " + usuarioNuevo.getUserName() + " se encuentra repetido. Por favor ingrese uno diferente.");
                resultado = false;
                break;
            }
        }
        if (resultado) {
            System.out.println("beanUsuario.validarUsername: username '" + usuarioNuevo.getUserName() + "' válido.");
        }
        return resultado;
    }
    
    public boolean validarUsername(Usuario user) {
        boolean resultado = true;
        for (Usuario usuario : listaUsuarios) {
            if (usuario.getUserName().toLowerCase().equals(user.getUserName().toLowerCase()) && usuario.getIdUsuario() != user.getIdUsuario()) {
                nuevoMensajeAlerta("Registro de Concursos de Salud", "El username " + user.getUserName() + " se encuentra repetido. Por favor ingrese uno diferente.");
                resultado = false;
                break;
            }
        }
        if (resultado) {
            System.out.println("beanUsuario.validarUsername: username '" + user.getUserName() + "' válido.");
        }
        return resultado;
    }
    
    public void habilitarCampos(ToggleEvent event) {
        
        RequestContext context = RequestContext.getCurrentInstance();
        System.out.println(event.getComponent().getId() + " toggled. Status:" + event.getVisibility().name());
        if (event.getVisibility().name().equals("VISIBLE")) {
            
            context.execute("PF('bui').hide();");
        } else {
            context.execute("PF('bui').show();");
        }
        
    }
    
    public void guardarNuevoUsuario() {
        try {
            if (validarUsername(usuarioNuevo)) {
                UsuarioDao usDao = new UsuarioDaoImpl();
                TipoUsuarioDao tipoDao = new TipoUsuarioDaoImpl();
                
                TipoUsuario tu = tipoDao.getTipoUsuario(tipoUsuarioSeleccionado);
                usuarioNuevo.setTipoUsuario(tu);
                usuarioNuevo.setFechaNacimiento(usuarioNuevo.getFechaNacimiento());
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(usuarioNuevo.getFechaNacimiento()); // Configuramos la fecha que se recibe
                calendar.add(Calendar.DAY_OF_YEAR, 1);  // numero de días a añadir, o restar en caso de días<0
                usuarioNuevo.setFechaNacimiento(calendar.getTime());
                usuarioNuevo.setUltimaActualizacion(new Date());
                usDao.insertar(usuarioNuevo);
                usuarioNuevo = new Usuario(usuarioNuevo.getIdUsuario() + 1);
                System.out.println("beanUsuarios.guardarNuevoUsuario(): Se a guardado correctamente el usuario " + usuarioNuevo.getIdUsuario()
                        + "\nApellido y Nombre: " + usuarioNuevo.getApellido() + ", " + usuarioNuevo.getNombre()
                        + "\nFecha de Nacimiento: " + usuarioNuevo.getFechaNacimiento());
                limpiarCampos();
                refreshListas();
                nuevoMensajeInfo("Registro de concursos de Salud", "Se a guardado correctamente el usuario " + usuarioNuevo.getIdUsuario());
                
            } else {
                nuevoMensajeInfo("Registro de concursos de Salud", "Username " + usuarioNuevo.getUserName() + " se encuentra repetido. Por favor ingrese otro diferente.");
            }
        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! " + ex1.getClass(), ex1.getMessage());
        }
    }
    
    public void eliminar() {
        try {
            System.out.println("beanUsuarios.eliminar(): Se va a eliminar el usuario id=" + usuarioSeleccionado.getIdUsuario());
            UsuarioDao usDao = new UsuarioDaoImpl();
            usDao.eliminarById(usuarioSeleccionado.getIdUsuario());
            refreshListas();
            usuarioNuevo.setIdUsuario(usDao.getNuevoId());
            nuevoMensajeInfo("Registro de concursos de Salud", "Se a eliminado el usuario " + usuarioSeleccionado.getIdUsuario());
        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! " + ex1.getClass(), ex1.getMessage());
        }
    }
    
    public void limpiarCampos() {
//        UsuarioDao usDao = new UsuarioDaoImpl();
//        usuarioNuevo = new Usuario(usDao.getNuevoId());
        usuarioNuevo.setUserName("");
        usuarioNuevo.setPass("");
        usuarioNuevo.setNombre("");
        usuarioNuevo.setApellido("");
        usuarioNuevo.setDni(null);
        usuarioNuevo.setActivo(false);
        usuarioNuevo.setTipoUsuario(new TipoUsuario());
        usuarioNuevo.setFechaNacimiento(null);
        
    }
}
