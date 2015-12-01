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
import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import ar.edu.utn.d2s.hibernate.HibernateUtil;
import ar.edu.utn.d2s.me.Comida;
import ar.edu.utn.d2s.me.Planificador;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class ReplanificarComidaController {
	
	
	@ManagedProperty(value="#{usuarioController}")
	private UsuarioController usuarioController;

	private Receta nuevaReceta;
	private Set<Receta> recetasDisponibles;
	private Comida comidaSeleccionada;
	private Set<Comida> comidasPlanificadas;
	
	
    @PostConstruct
    public void init() {

    	comidasPlanificadas= usuarioController.getUsuario().getComidasReplanificables();
    	recetasDisponibles= usuarioController.getUsuario().listarRecetasDisponibles();
    }
 
   
     
  
    public void buscarRecetasReplanificables(){

    	//definir la estrategia de busqueda para las recetas planificadas
//    	String estrategiaBusqueda;
//    	if (horario == null && fecha == null) {
//    		recetasReplanificables = usuarioController.getUsuario().getRecetasReplanificables();
//		}
//    	
//    	if (horario == null && fecha != null) {
//    		recetasReplanificables = usuarioController.getUsuario().getRecetasReplanificablesPorFecha(fecha);
//		}
//    	
//    	if (horario != null && fecha == null) {
//    		recetasReplanificables = usuarioController.getUsuario().getRecetasReplanificablesPorHorario(horario);
//		}
//    	
//    	if (horario != null && fecha != null) {
//    		recetasReplanificables = usuarioController.getUsuario().getRecetasReplanificablesPorFechaHorario();
//		}
   	
    }
    
	public String  replanificar() {
		if (comidaSeleccionada == null) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"Debe seleccionar una comida!", ""));
	        return "replanificarComida";
		}

		Planificador planificadorComidas = new Planificador();
		String resutl;
		try {
			resutl = planificadorComidas.replanificar(comidaSeleccionada, nuevaReceta, usuarioController.getUsuario());
		} catch (ComidaInvalidaInvaliException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,e.getMessage(), ""));
	    	e.printStackTrace();
	        return "replanificarComida";	
		}
		

		//Update DB
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = session.beginTransaction();
		if (resutl.equals("actualizada")) {
			session.update(usuarioController.getUsuario());			
		}
		if (resutl.equals("removida")) {
			session.delete(comidaSeleccionada);			
		}
		
		tx.commit();
		session.close();
		
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Comida replanificada con exito!", ""));
        return "index?faces-redirect=true";
    }


   



	public UsuarioController getUsuarioController() {
		return usuarioController;
	}





	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}


	public Receta getNuevaReceta() {
		return nuevaReceta;
	}




	public void setNuevaReceta(Receta nuevaReceta) {
		this.nuevaReceta = nuevaReceta;
	}




	public Comida getComidaSeleccionada() {
		return comidaSeleccionada;
	}




	public void setComidaSeleccionada(Comida comidaSeleccionada) {
		this.comidaSeleccionada = comidaSeleccionada;
	}




	public Set<Comida> getComidasPlanificadas() {
		return comidasPlanificadas;
	}




	public void setComidasPlanificadas(Set<Comida> comidasPlanificadas) {
		this.comidasPlanificadas = comidasPlanificadas;
	}




	public Set<Receta> getRecetasDisponibles() {
		return recetasDisponibles;
	}




	public void setRecetasDisponibles(Set<Receta> recetasDisponibles) {
		this.recetasDisponibles = recetasDisponibles;
	}


}