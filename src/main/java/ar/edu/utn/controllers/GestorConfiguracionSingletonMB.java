package ar.edu.utn.controllers;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import ar.edu.utn.model.singleton.GestorArchivosConfiguracionSingleton;

@ManagedBean
@ViewScoped
public class GestorConfiguracionSingletonMB {
	private GestorArchivosConfiguracionSingleton gestorAC;
	
	public void make(){
		setGestorAC(GestorArchivosConfiguracionSingleton.getInstance());
	}

	/**
	 * @return the gestorAC
	 */
	public GestorArchivosConfiguracionSingleton getGestorAC() {
		return gestorAC;
	}

	/**
	 * @param gestorAC the gestorAC to set
	 */
	public void setGestorAC(GestorArchivosConfiguracionSingleton gestorAC) {
		this.gestorAC = gestorAC;
	}
}
