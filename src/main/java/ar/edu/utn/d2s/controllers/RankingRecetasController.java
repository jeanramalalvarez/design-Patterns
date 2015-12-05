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

import ar.edu.ut.d2s.exceptions.ParametrosInvalidosException;
import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import ar.edu.utn.d2s.hibernate.HibernateUtil;
import ar.edu.utn.d2s.me.Calificacion;
import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioRecetas;
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class RankingRecetasController {
	
	private Grupo grupoSeleccionado;
	private Set<Grupo> gruposDisponibles;
	private List<Receta> recetasRankeadas;

	@ManagedProperty(value="#{usuarioController}")
	private UsuarioController usuarioController;
	
    @PostConstruct
    public void init() {
    	gruposDisponibles = obtenerGruposDisponiblesBD();

    }
    
    public String obtenerRanking(){
    	//Operation
    	try {
			recetasRankeadas = usuarioController.getUsuario().getRankingRecetas(grupoSeleccionado);
		} catch (UsuarioInvalidoException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));	    	
	    	e.printStackTrace();
	    	return null;
		}

    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Se obtuvo el ranking correctamente", ""));
    	
    	return null;
    }

	public Grupo getGrupoSeleccionado() {
		return grupoSeleccionado;
	}

	public void setGrupoSeleccionado(Grupo grupoSeleccionado) {
		this.grupoSeleccionado = grupoSeleccionado;
	}

	public UsuarioController getUsuarioController() {
		return usuarioController;
	}

	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
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

	/**
	 * @return the gruposDisponibles
	 */
	public Set<Grupo> getGruposDisponibles() {
		return gruposDisponibles;
	}

	/**
	 * @param gruposDisponibles the gruposDisponibles to set
	 */
	public void setGruposDisponibles(Set<Grupo> gruposDisponibles) {
		this.gruposDisponibles = gruposDisponibles;
	}

	public List<Receta> getRecetasRankeadas() {
		return recetasRankeadas;
	}

	public void setRecetasRankeadas(List<Receta> recetasRankeadas) {
		this.recetasRankeadas = recetasRankeadas;
	}

}
