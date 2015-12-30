package ar.edu.utn.controllers;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import ar.edu.utn.abstractFactory.Color;
import ar.edu.utn.abstractFactory.FabricaLcdAzul;
import ar.edu.utn.abstractFactory.FabricaLcdNegro;
import ar.edu.utn.abstractFactory.FabricaLedRojo;
import ar.edu.utn.abstractFactory.FabricaPlasmaNegro;
import ar.edu.utn.abstractFactory.FabricaTv;
import ar.edu.utn.abstractFactory.Tv;


@ManagedBean
@ViewScoped
public class TvAbstractFactoryMB {
	private Tv tv;
	private Color color;
	private FabricaTv fabricaTv;
	private String fabricaTvSelected;

    @PostConstruct
    public void init() {
    }
	
    public void make(){
    	try {
			fabricaTv =  this.obtenerFabricaSeleccionada();
		} catch (Exception e1) {
			// TODO Auto-generated catch block
			FacesContext facesContext = FacesContext.getCurrentInstance();
	    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,e1.getMessage(), ""));
	    	return;
		}

    	tv = fabricaTv.createTv();
    	color = fabricaTv.createColor();
   		color.colorear(tv);			
    	//Message will be show in next page. The next view will show message in first p:message with property "for=null" 
		FacesContext facesContext = FacesContext.getCurrentInstance();
    	facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO,"Success", ""));    	
    }
    

    
    
    
	
	private FabricaTv obtenerFabricaSeleccionada() throws Exception {
		// TODO Auto-generated method stub
		switch (fabricaTvSelected) {
		case "LCD_AZUL":
			return new FabricaLcdAzul();
		case "PLASMA_NEGRO":
			return new FabricaPlasmaNegro();
			
		case "LCD_NEGRO":
			return new FabricaLcdNegro();
		case "LED_ROJO":
			return new FabricaLedRojo();
		default:
			throw new  Exception("Error: factory not exist");
		}
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


	/**
	 * @return the fabricaTvSelected
	 */
	public String getFabricaTvSelected() {
		return fabricaTvSelected;
	}

	/**
	 * @param fabricaTvSelected the fabricaTvSelected to set
	 */
	public void setFabricaTvSelected(String fabricaTvSelected) {
		this.fabricaTvSelected = fabricaTvSelected;
	}
	
}
