package ar.edu.utn.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;


import ar.edu.utn.model.prototype.Color;
import ar.edu.utn.model.prototype.Notebook;

@ManagedBean
@ViewScoped
public class PrototypeNotebookMacMB {
	private Notebook instanciaProtipicaMac;
	private String colorSelected;
	private Notebook macClonado;
	
	@PostConstruct
    public void init() {
		instanciaProtipicaMac = new Notebook();
		instanciaProtipicaMac.setColor(new Color());
		instanciaProtipicaMac.getColor().setColor("");;
		
    }
	
	
	public void make(){
    	macClonado = this.getInstanciaProtipicaMac().clone();
    	macClonado.getColor().setColor(colorSelected);
		//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));    	
    }


	public Notebook getInstanciaProtipicaMac() {
		return instanciaProtipicaMac;
	}


	public void setInstanciaProtipicaMac(Notebook instanciaProtipicaMac) {
		this.instanciaProtipicaMac = instanciaProtipicaMac;
	}


	public String getColorSelected() {
		return colorSelected;
	}


	public void setColorSelected(String colorSelected) {
		this.colorSelected = colorSelected;
	}


	/**
	 * @return the macClonado
	 */
	public Notebook getMacClonado() {
		return macClonado;
	}


	/**
	 * @param macClonado the macClonado to set
	 */
	public void setMacClonado(Notebook macClonado) {
		this.macClonado = macClonado;
	}
    
	

}
