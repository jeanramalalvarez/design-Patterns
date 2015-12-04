package ar.edu.utn.d2s.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.primefaces.context.RequestContext;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;

import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import ar.edu.utn.d2s.hibernate.HibernateUtil;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioRecetas;
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class LoginController {
    
	private String mail;
	private String password;
	
	@ManagedProperty(value="#{usuarioController}")
	private UsuarioController usuarioController;
	
    @PostConstruct
    public void init() {
     
    }
 
	public String login () {
    	//Validate user
		Usuario usuario = buscarUsuario();
		if (usuario == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Usuario o password incorrectos",""));
			return null;
		}
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario " + usuario.getNombre() + " Logueado!", ""));
       
    	//Setter user in backing bean
    	usuario.setRepositorioRecetas(getRepositorioRecetas());
    	this.usuarioController.setUsuario(usuario);
		return "index?faces-redirect=true";
    }	


    
    private Usuario buscarUsuario() {
		// TODO Auto-generated method stub
    	Session session = HibernateUtil.getSessionFactory().openSession();
		//Consulto
		Criteria usuarioCriteria = session.createCriteria(Usuario.class);
		usuarioCriteria.add(Restrictions.eq("mail", mail));
		usuarioCriteria.add(Restrictions.eq("password", password));
		Usuario usuario = (Usuario) usuarioCriteria.uniqueResult();
		session.close();
		return usuario;
	}





	public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


 
	private RepositorioRecetas getRepositorioRecetas() {
		RepositorioRecetas repositorioRecetas = new RepositorioRecetas();
		// TODO Auto-generated method stub
		//Levantar las recetas de la base datos que sean precargadas
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Consulto
		Criteria recetaCriteria = session.createCriteria(Receta.class);
		recetaCriteria.add(Restrictions.eq("precargada", true));
		repositorioRecetas.setRecetas(new HashSet<Receta>(recetaCriteria.list()));
		session.close();
		return repositorioRecetas;
	}





	public String getMail() {
		return mail;
	}





	public void setMail(String mail) {
		this.mail = mail;
	}





	public String getPassword() {
		return password;
	}





	public void setPassword(String password) {
		this.password = password;
	}

	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}


	private RepositorioUsuarios getRepositorioUsuarios() {
		RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
		// TODO Auto-generated method stub
		//Levantar todos los usuarios de la base datos 
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Consulto
		Criteria usuarioCriteria = session.createCriteria(Usuario.class);
		repositorioUsuarios.setUsuarios(new HashSet<Usuario>(usuarioCriteria.list()));
		session.close();
		return repositorioUsuarios;
	}
	
}