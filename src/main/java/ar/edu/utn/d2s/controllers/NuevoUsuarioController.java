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
import ar.edu.utn.d2s.me.RepositorioUsuarios;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;
 
@ManagedBean
@ViewScoped
public class NuevoUsuarioController {
    
	private Integer dia;
	private List<Integer> dias;
	
	private Integer anio;
	private List<Integer> anios;
	
	private Integer mes;
	private Map<String, Integer> mapMeses;
	
	private List<String> restriccionesSelected;
	private List<String> restricciones;
	private Map<String, String> mapRestricciones;
	
	private String preferencia;
	private Set<String> preferencias;
	
	private Usuario usuario;

	private RepositorioUsuarios repositorioUsuarios;
    @PostConstruct
    public void init() {
        
       //Populate dias
        dias = new ArrayList<Integer>();
        for (int i = 1; i <= 31; i++) {
			dias.add(i);
		}
        
      //Populate años
        anios = new ArrayList<Integer>();
        int anioActual = Calendar.getInstance().get(Calendar.YEAR);
        for (int i = anioActual - 100; i < anioActual; i++) {
			anios.add(i);
		}
        
        //Populate meses
        mapMeses = new LinkedHashMap<String, Integer>();
        mapMeses.put("Enero", 1);
        mapMeses.put("Febrero", 2);
        mapMeses.put("Marzo", 3);
        mapMeses.put("Abril", 4);
        mapMeses.put("Mayo", 5);
        mapMeses.put("Junio", 6);
        mapMeses.put("Julio", 7);
        mapMeses.put("Agosto", 8);
        mapMeses.put("Septiembre", 9);
        mapMeses.put("Octubre", 10);
        mapMeses.put("Nobiembre", 11);
        mapMeses.put("Diciembre", 12);
        
        
        //Generate Map de restricciones
        mapRestricciones = new LinkedHashMap<String, String>();
        mapRestricciones.put("Diabetico", "Azucar");
        mapRestricciones.put("Celiaco", "Gluten");
        mapRestricciones.put("Vegetariano", "Carne");
        mapRestricciones.keySet();

        //Populate restricciones
        restricciones = new ArrayList<String>(mapRestricciones.keySet());
              
        //Instatiate preferencias
        preferencias = new HashSet<String>();
        
        //Create usuario temporal
        usuario = new Usuario();
        
        //Load users repository for validations
        repositorioUsuarios = getRepositorioUsuarios();
       
    }
 
   
     
  

	public String crear () {
    	//Terminate to create usuario
    	usuario.setFechaNacimiento(new LocalDate(anio, mes, dia));
    	for (String restriccion : restriccionesSelected) {
			usuario.agregarRestriccion(new Restriccion(restriccion, mapRestricciones.get(restriccion)));
		}
    	usuario.setPreferencias(preferencias);
    	
    	//Validate user with model
    	try {
			repositorioUsuarios.agregarUsuario(usuario);
		} catch (UsuarioInvalidoException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR,"", e.getMessage()));
	    	e.printStackTrace();
	    	return "nuevoUsuario";
		}
    	
    	
    	//Persist user with DAO
    	Session session = HibernateUtil.getSessionFactory().openSession();
    	Transaction tx = session.beginTransaction();
		session.save(usuario);
		tx.commit();
		session.close();
      	
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
		Flash flash = facesContext.getExternalContext().getFlash();
    	flash.setKeepMessages(true);
    	flash.setRedirect(true);
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Usuario creardo con exito!", ""));
        return "login?faces-redirect=true";
    }


    public String agregarPreferencia () {
        if (preferencia != "") {
        	preferencias.add(preferencia);			
		}
        return null;
    }
     
    public void addMessage(String summary, String detail) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }



	public Integer getDia() {
		return dia;
	}



	public void setDia(Integer dia) {
		this.dia = dia;
	}



	public List<Integer> getDias() {
		return dias;
	}



	public void setDias(List<Integer> dias) {
		this.dias = dias;
	}



	public Integer getAnio() {
		return anio;
	}



	public void setAnio(Integer anio) {
		this.anio = anio;
	}



	public List<Integer> getAnios() {
		return anios;
	}



	public void setAnios(List<Integer> anios) {
		this.anios = anios;
	}



	public Integer getMes() {
		return mes;
	}



	public void setMes(Integer mes) {
		this.mes = mes;
	}



	public Map<String, Integer> getMapMeses() {
		return mapMeses;
	}



	public void setMapMeses(Map<String, Integer> mapMeses) {
		this.mapMeses = mapMeses;
	}



	public List<String> getRestriccionesSelected() {
		return restriccionesSelected;
	}



	public void setRestriccionesSelected(List<String> restriccionesSelected) {
		this.restriccionesSelected = restriccionesSelected;
	}



	public List<String> getRestricciones() {
		return restricciones;
	}



	public void setRestricciones(List<String> restricciones) {
		this.restricciones = restricciones;
	}



	public Map<String, String> getMapRestricciones() {
		return mapRestricciones;
	}



	public void setMapRestricciones(Map<String, String> mapRestricciones) {
		this.mapRestricciones = mapRestricciones;
	}



	public String getPreferencia() {
		return preferencia;
	}



	public void setPreferencia(String preferencia) {
		this.preferencia = preferencia;
	}



	public Set<String> getPreferencias() {

		return preferencias;
	}



	public void setPreferencias(HashSet<String> preferencias) {
		this.preferencias = preferencias;
	}



	public Usuario getUsuario() {
		return usuario;
	}



	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
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