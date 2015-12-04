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

import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import ar.edu.utn.d2s.hibernate.HibernateUtil;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class NuevaRecetaController {
	private Set<String> horarios;
	private Set<String> temporadas;
	private String ingrediente;
	private Set<String> ingredientes;
	
	private Receta receta;
	
	@ManagedProperty(value="#{usuarioController}")
	private UsuarioController usuarioController;
	
    @PostConstruct
    public void init() {
        
      
        //Populate horarios
        horarios = new TreeSet<String>();
        horarios.add("Desayuno");
        horarios.add("Almuerzo");
        horarios.add("Merienda");
        horarios.add("Cena");

        //Populate temporadas
        temporadas = new TreeSet<String>();
        temporadas.add("Otoño");
        temporadas.add("Invierno");
        temporadas.add("Primavera");
        temporadas.add("Verano");
              
        //Instatiate preferencias
        ingredientes = new HashSet<String>();
        
        //Create receta temporal
        receta = new Receta();
     
    }
 
   
     
  

	public String crear () {
		//Terminate to create receta
		receta.setIngredientes(ingredientes);
		receta.setAutor(usuarioController.getUsuario());
		

    	//Validate recipe with model
		try {
			usuarioController.getUsuario().agregarReceta(receta);
		} catch (RecetaInvalidaException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, e.getMessage(), ""));
	    	e.printStackTrace();
	    	return null;
		}

    	//Persist user with DAO
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
		session.save(receta);
		tx.commit();
		session.close();
      	
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Receta crearda con exito!", ""));
        return "index?faces-redirect=true";
    }


    public String agregarIngrediente () {
        if (ingrediente != "") {
        	ingredientes.add(ingrediente);			
		}
        return null;
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }




	public Set<String> getHorarios() {
		return horarios;
	}



	public void setHorarios(Set<String> horarios) {
		this.horarios = horarios;
	}


	public Receta getReceta() {
		return receta;
	}



	public void setReceta(Receta receta) {
		this.receta = receta;
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





	public Set<String> getTemporadas() {
		return temporadas;
	}





	public void setTemporadas(Set<String> temporadas) {
		this.temporadas = temporadas;
	}





	public String getIngrediente() {
		return "";
	}





	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}





	public Set<String> getIngredientes() {
		return ingredientes;
	}





	public void setIngredientes(Set<String> ingredientes) {
		this.ingredientes = ingredientes;
	}





	public UsuarioController getUsuarioController() {
		return usuarioController;
	}





	public void setUsuarioController(UsuarioController usuarioController) {
		this.usuarioController = usuarioController;
	}


}