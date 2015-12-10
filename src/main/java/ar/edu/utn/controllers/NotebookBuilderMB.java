package ar.edu.utn.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ar.edu.utn.builder.LenovoG475Builder;
import ar.edu.utn.builder.MacUltraBookPro7Builder;
import ar.edu.utn.builder.Notebook;
import ar.edu.utn.builder.NotebookBuilder;
import ar.edu.utn.builder.NotebookDirector;
import ar.edu.utn.model.abstractFactory.Color;
import ar.edu.utn.model.abstractFactory.FabricaLcdAzul;
import ar.edu.utn.model.abstractFactory.FabricaLcdNegro;
import ar.edu.utn.model.abstractFactory.FabricaLedRojo;
import ar.edu.utn.model.abstractFactory.FabricaPlasmaNegro;
import ar.edu.utn.model.abstractFactory.FabricaTv;
import ar.edu.utn.model.abstractFactory.Tv;


@ManagedBean
@ViewScoped
public class NotebookBuilderMB {
	private Notebook notebook;
	private NotebookBuilder notebookBuilder;
	private NotebookDirector notebookDirector;
	private String builderSelected;

    @PostConstruct
    public void init() {
    }
	
    public void make(){
    	try {
			notebookBuilder =  this.obtenerBuilderSeleccionado();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e1.getMessage(), ""));
	    	return;
		}

    	notebookDirector = new NotebookDirector(notebookBuilder);
    	notebookDirector.construir();
    	this.setNotebook(notebookBuilder.getNotebook());
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));    	
    }
    

    
    
    
	
	private NotebookBuilder obtenerBuilderSeleccionado() throws Exception {
		// TODO Auto-generated method stub
		switch (builderSelected) {
		case "Lenovo_G475":
			return new LenovoG475Builder();
		case "Mac_UltrabookPro7":
			return new MacUltraBookPro7Builder();
		default:
			throw new  Exception("Error: Builder not exist");
		}
	}

	
	public Notebook getNotebook() {
		return notebook;
	}

	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}

	public NotebookBuilder getNotebookBuilder() {
		return notebookBuilder;
	}

	public void setNotebookBuilder(NotebookBuilder notebookBuilder) {
		this.notebookBuilder = notebookBuilder;
	}

	public String getBuilderSelected() {
		return builderSelected;
	}

	public void setBuilderSelected(String builderSelected) {
		this.builderSelected = builderSelected;
	}

	/**
	 * @return the notebookDirector
	 */
	public NotebookDirector getNotebookDirector() {
		return notebookDirector;
	}

	/**
	 * @param notebookDirector the notebookDirector to set
	 */
	public void setNotebookDirector(NotebookDirector notebookDirector) {
		this.notebookDirector = notebookDirector;
	}
	
}
