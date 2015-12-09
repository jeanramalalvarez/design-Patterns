package ar.edu.utn.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ar.edu.utn.model.abstractFactory.Color;
import ar.edu.utn.model.abstractFactory.FabricaLcdAzul;
import ar.edu.utn.model.abstractFactory.FabricaPlasmaNegro;
import ar.edu.utn.model.abstractFactory.FabricaTv;
import ar.edu.utn.model.abstractFactory.Tv;


@ManagedBean
@ViewScoped
public class TvAbstractFactoryMB {
	private Tv tv;
	private Color color;
	private FabricaTv fabricaTv;
	

    @PostConstruct
    public void init() {
    }
	
    public void makeLcdAzul(){
    	fabricaTv = new FabricaLcdAzul();
    	tv = fabricaTv.createTv();
    	color = fabricaTv.createColor();
    	
    	try {
    		color.colorear(tv);			
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e.getMessage(), ""));
	    	return;
		
		}
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));    	
    }
    
    public void makePlasmaNegro(){
    	fabricaTv = new FabricaPlasmaNegro();
    	tv = fabricaTv.createTv();
    	color = fabricaTv.createColor();
    	try {
    		color.colorear(tv);			
		} catch (Exception e) {
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e.getMessage(), ""));
	    	return;
		
		}
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));
    }
    
	
	/**
	 * @return the tv
	 */
	public Tv getTv() {
		return tv;
	}
	/**
	 * @param tv the tv to set
	 */
	public void setTv(Tv tv) {
		this.tv = tv;
	}
	/**
	 * @return the color
	 */
	public Color getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(Color color) {
		this.color = color;
	}
	
}
