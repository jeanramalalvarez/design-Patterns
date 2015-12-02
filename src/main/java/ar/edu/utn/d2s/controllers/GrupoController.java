package ar.edu.utn.d2s.controllers;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

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

import ar.edu.ut.d2s.exceptions.ComidaInvalidaInvaliException;
import ar.edu.ut.d2s.exceptions.FechaFueraFueraDeRangoException;
import ar.edu.ut.d2s.exceptions.GrupoInvalidoException;
import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioExistenteException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import ar.edu.utn.d2s.hibernate.HibernateUtil;
import ar.edu.utn.d2s.me.Comida;
import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Planificador;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioRecetas;
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class GrupoController {
	
	
	@ManagedProperty(value="#{usuarioController}")
	private UsuarioController usuarioController;

	private Grupo grupoSeleccionado;
	private Set<Grupo> gruposDisponibles;
	
	
    @PostConstruct
    public void init() {
    	gruposDisponibles = obtenerGruposDisponiblesBD();
    }
 
   
    
	public String  unirse() {
		
		if (grupoSeleccionado == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar un grupo!", ""));
	    	return "unirseGrupo";
		}
		
		try {
			grupoSeleccionado.agregarMiembro(usuarioController.getUsuario());
		} catch (UsuarioExistenteException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), ""));
			e.printStackTrace();
	    	return "unirseGrupo";
			
		}

		//Update DB
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(usuarioController.getUsuario());
		session.update(grupoSeleccionado);
		tx.commit();
		session.close();
		
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Se ha unido correctamente al grupo!", ""));
        return "index?faces-redirect=true";
    }

	
	public String salir() throws GrupoInvalidoException{

		if (grupoSeleccionado == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar un grupo!", ""));
	    	return "salirGrupo";
		}
		
		try {
			grupoSeleccionado.removerMiembro(usuarioController.getUsuario());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), ""));
	    	e.printStackTrace();
	    	return "salirGrupo";
		}

		//Update BD
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		session.update(usuarioController.getUsuario());
		session.update(grupoSeleccionado);
		tx.commit();
		session.close();
		
		//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Ha salido correctamente del grupo!", ""));
        return "index?faces-redirect=true";
	}

   



	public UsuarioController getUsuarioController() {
		return usuarioController;
	}





	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}


	

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}




	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}




	public Set<Grupo> getGruposDisponibles() {
		return gruposDisponibles;
	}




	public void setGruposDisponibles(Set<Grupo> gruposDisponibles) {
		this.gruposDisponibles = gruposDisponibles;
	}

    private Set<Grupo> obtenerGruposDisponiblesBD() {
		// TODO Auto-generated method stub
    	Set<Grupo> gruposDisponibles = new HashSet<Grupo>();
		// TODO Auto-generated method stub
		//Levantar todos los grupos
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Consulto
		Criteria grupoCriteria = session.createCriteria(Grupo.class);
		gruposDisponibles = new HashSet<Grupo>(grupoCriteria.list());
		session.close();
		return gruposDisponibles;
	}


}