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
import ar.edu.ut.d2s.exceptions.ParametrosInvalidosException;
import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import ar.edu.utn.d2s.hibernate.HibernateUtil;
import ar.edu.utn.d2s.me.Calificacion;
import ar.edu.utn.d2s.me.Comida;
import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Planificador;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class CalificarRecetaController {
	
	
	@ManagedProperty(value="#{usuarioController}")
	private UsuarioController usuarioController;
	
	
	private Grupo grupoSeleccionado;
	private Receta recetaSeleccionada;
	private int valorCalificacion;
	
	private Comida comida;
	
	private Date fecha;
	
	private Set<Grupo> gruposDisponibles;
	private Set<Receta> recetasCompartidasGrupos;
	
	
    @PostConstruct
    public void init() {
        
       
    	gruposDisponibles = getGruposBD();
    	recetasCompartidasGrupos = getRecetasCompartidas(gruposDisponibles);   	
     
    }
 



	public String calificar () {
		//Terminate to create 
		Calificacion calificacion = new Calificacion();
		calificacion.setGrupo(grupoSeleccionado);
		calificacion.setUsuario(usuarioController.getUsuario());
		calificacion.setValor(valorCalificacion);
		
		//Operation
		try {
			usuarioController.getUsuario().calificar(recetaSeleccionada, calificacion);
		} catch (UsuarioInvalidoException | RecetaInvalidaException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
	    	e.printStackTrace();
	    	return null;
		}

    	//Persist 
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
    	session.save(calificacion);
    	session.update(recetaSeleccionada);
		tx.commit();
		session.close();
      	
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Receta calificada con exito!", ""));
        return "index?faces-redirect=true";
    }

	public String modificar() throws RecetaInvalidaException{
		
		//Operation
		try {
			usuarioController.getUsuario().modificarCalificacion(recetaSeleccionada, grupoSeleccionado, valorCalificacion);
		} catch (UsuarioInvalidoException | ParametrosInvalidosException | RecetaInvalidaException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
	    	e.printStackTrace();
	    	return null;	
		}

		//Persist 
		Calificacion calificacion = recetaSeleccionada.getCalificacionObjecto(usuarioController.getUsuario(), grupoSeleccionado);
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
    	session.update(calificacion);
		tx.commit();
		session.close();		

		//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Receta modificada con exito!", ""));
        return "index?faces-redirect=true";
	}
   
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }


    
	  

	private Set<Grupo> getGruposBD() {
		Set<Grupo> gruposTMP = new HashSet<Grupo>();
		// TODO Auto-generated method stub
		Session session = HibernateUtil.getSessionFactory().openSession();
		//Consulto
		Criteria unaCriteria = session.createCriteria(Grupo.class);
		gruposTMP = new HashSet<Grupo>(unaCriteria.list());
		session.close();
		return gruposTMP;
	}



	public UsuarioController getUsuarioController() {
		return usuarioController;
	}





	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}





	public Comida getComida() {
		return comida;
	}





	public void setComida(Comida comida) {
		this.comida = comida;
	}





	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}





	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}





	public Receta getRecetaSeleccionada() {
		return recetaSeleccionada;
	}





	public void setRecetaSeleccionada(Receta recetaSeleccionada) {
		this.recetaSeleccionada = recetaSeleccionada;
	}



	public Set<Grupo> getGruposDisponibles() {
		return gruposDisponibles;
	}



	public void setGruposDisponibles(Set<Grupo> gruposDisponibles) {
		this.gruposDisponibles = gruposDisponibles;
	}



	public Set<Receta> getRecetasCompartidasGrupos() {
		return recetasCompartidasGrupos;
	}



	public void setRecetasCompartidasGrupos(Set<Receta> recetasCompartidasGrupos) {
		this.recetasCompartidasGrupos = recetasCompartidasGrupos;
	}


	   

	private Set<Receta> getRecetasCompartidas(Set<Grupo> gruposDisponibles) {
		// TODO Auto-generated method stub
		Set<Receta> recetasCompartidasGruposTMP = new HashSet<Receta>();
		for (Grupo grupo : gruposDisponibles) {
			recetasCompartidasGruposTMP.addAll(grupo.getRecetasCompartidas());
		}
		return recetasCompartidasGruposTMP;
	}




	public Integer getValorCalificacion() {
		return valorCalificacion;
	}




	public void setValorCalificacion(Integer valorCalificacion) {
		this.valorCalificacion = valorCalificacion;
	}




	public void setValorCalificacion(int valorCalificacion) {
		this.valorCalificacion = valorCalificacion;
	}



	
}