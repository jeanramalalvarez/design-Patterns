package ar.edu.utn.d2s.controllers;

import java.io.Serializable;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;
import javax.servlet.http.HttpSession;

import ar.edu.utn.d2s.me.Usuario;

@ManagedBean(name="usuarioController")
@SessionScoped

public class UsuarioController implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Usuario usuario;
	
	public String logout() {
	    HttpSession session = (HttpSession) FacesContext.getCurrentInstance().getExternalContext().getSession(false);
	    session.invalidate();
	  //Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
	  	flash.setKeepMessages(true);
	  	flash.setRedirect(true);
	  	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Logout Successfuly!", ""));
	    return "login?faces-redirect=true";
	}
	
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	

}
