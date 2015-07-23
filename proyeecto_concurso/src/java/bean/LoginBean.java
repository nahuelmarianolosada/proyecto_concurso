/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bean;

import dominio.Usuario;
import hibernate.dao.UsuarioDao;
import hibernate.dao.impl.UsuarioDaoImpl;
import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import javax.servlet.http.HttpSession;

/**
 *
 * @author SIISAJUJUY
 */
@ManagedBean(name = "beanLogin")
@SessionScoped
public class LoginBean implements Serializable {

    private static final long serialVersionUID = 1L;

    private String username;
    private String pass;
    private Usuario usuarioLogeado;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Usuario getUsuarioLogeado() {
        return usuarioLogeado;
    }

    public void setUsuarioLogeado(Usuario usuarioLogeado) {
        this.usuarioLogeado = usuarioLogeado;
    }

    /**
     * CONSTRUCTOR Creates a new instance of LoginBean
     */
    public LoginBean() {
        UsuarioDao usDao = new UsuarioDaoImpl();
        usuarioLogeado = usDao.getUsuario("admin", "admin");
    }

    public String login() {

        String resp = "login";
        boolean usuarioEncontrado = false;
        //RequestContext context = RequestContext.getCurrentInstance();

        try {
            UsuarioDao usuarioDao = new UsuarioDaoImpl();
            usuarioLogeado = usuarioDao.getUsuario(username, pass);
            if (usuarioLogeado != null) {
                if (usuarioLogeado.getActivo()) {
                    usuarioEncontrado = true;

                    resp = "index";

                    System.out.println("---------------------------------------------------------------------------------------");
                    System.out.println("Usuario encontrado! " + usuarioLogeado.getNombre() + " " + usuarioLogeado.getApellido() + "\nUsername: " + usuarioLogeado.getUserName() + "\nActivo: " + usuarioLogeado.getActivo());
                    System.out.println("---------------------------------------------------------------------------------------");
                } else {
                    nuevoMensajeAlerta("Registro Provincial de Concursos", "El usuario ingresado no está activo.");
                }
            } else {
                nuevoMensajeAlerta("Datos ingresados invalidos", "Contraseña y/o username invalidos");
            }

        } catch (Exception ex1) {
            nuevoMensajeAlerta("Error! " + ex1.getClass(), ex1.getMessage());
        } finally {
            return resp;
        }
    }

    public String logout() {
        usuarioLogeado = new Usuario();
        return "login";
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

    public boolean esAdmin() {
        boolean esAdministrador = false;
        if (usuarioLogeado.getTipoUsuario().getDescripcion().toLowerCase().equals("administrador")) {
            esAdministrador = true;
        } else {
            esAdministrador = false;
        }
        return esAdministrador;
    }

    public void actualizarMiUsuario() {
        System.out.println("-----------------------------------------------------------------------------------------------------------");
        System.out.println("Datos del Usuario actualizado: " + usuarioLogeado.getApellido() + " " + usuarioLogeado.getNombre()
                + "\nDni " + usuarioLogeado.getDni()
                + "\nUsername " + usuarioLogeado.getUserName()
                + "\nContraseña " + usuarioLogeado.getPass()
                + "\nFecha " + usuarioLogeado.getFechaNacimiento()
                + "\nActivo " + usuarioLogeado.getActivo());
        System.out.println("-----------------------------------------------------------------------------------------------------------");

        UsuarioDao usuarioDao = new UsuarioDaoImpl();
        usuarioDao.modificar(usuarioLogeado);
        nuevoMensajeInfo("Registro de Concursos de Salud", "Usuario " + usuarioLogeado.getIdUsuario() + " actualizado.");

    }
}
