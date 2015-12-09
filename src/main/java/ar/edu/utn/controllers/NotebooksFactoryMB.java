package ar.edu.utn.controllers;



import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.context.Flash;

import ar.edu.utn.exceptions.NotebookModelInvalidException;
import ar.edu.utn.model.*;
import ar.edu.utn.model.factoryMethod.FactoryNotebook;
import ar.edu.utn.model.factoryMethod.LenovoFactoryNotebook;
import ar.edu.utn.model.factoryMethod.MacFactoryNotebook;
import ar.edu.utn.model.factoryMethod.Notebook;


 
@ManagedBean
@ViewScoped
public class NotebooksFactoryMB {
	String lenovoModelNotebook;
	String macModelNotebook;
	Notebook notebook;
	FactoryNotebook notebookFactory;
	
    @PostConstruct
    public void init() {

     
    }
 
	public String makeLenovo() {
		this.notebookFactory = new LenovoFactoryNotebook();
		try {
			this.notebook = notebookFactory.create(this.getLenovoModelNotebook());
		} catch (NotebookModelInvalidException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e.getMessage(), ""));
	    	e.printStackTrace();
	        return null;
		}
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));
        return null;
    }
	
	public String makeMac() {
		
		this.notebookFactory = new MacFactoryNotebook();
		try {
			this.notebook = notebookFactory.create(this.getMacModelNotebook());
		} catch (NotebookModelInvalidException e) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e.getMessage(), ""));
	    	e.printStackTrace();
	        return null;
		}
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));
        return null;
    }



	public String getLenovoModelNotebook() {
		return lenovoModelNotebook;
	}



	public void setLenovoModelNotebook(String lenovoModelNotebook) {
		this.lenovoModelNotebook = lenovoModelNotebook;
	}



	public String getMacModelNotebook() {
		return macModelNotebook;
	}



	public void setMacModelNotebook(String macModelNotebook) {
		this.macModelNotebook = macModelNotebook;
	}



	public Notebook getNotebook() {
		return notebook;
	}



	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}



	public FactoryNotebook getNotebookFactory() {
		return notebookFactory;
	}



	public void setNotebookFactory(FactoryNotebook notebookFactory) {
		this.notebookFactory = notebookFactory;
	}
}
