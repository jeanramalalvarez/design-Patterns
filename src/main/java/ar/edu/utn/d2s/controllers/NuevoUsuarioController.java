package ar.edu.utn.d2s.controllers;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;

import org.joda.time.LocalDate;
import org.primefaces.model.menu.DefaultMenuItem;
import org.primefaces.model.menu.DefaultMenuModel;
import org.primefaces.model.menu.DefaultSubMenu;
import org.primefaces.model.menu.MenuModel;
 
@ManagedBean
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
    
//	LocalDate date = new LocalDate;
 
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
              
        
   
        
    }
 
   
     
    public void crear () {
        addMessage("Success", "Data saved");
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




}