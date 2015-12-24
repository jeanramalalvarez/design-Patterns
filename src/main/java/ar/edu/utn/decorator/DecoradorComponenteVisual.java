package ar.edu.utn.decorator;

public abstract class DecoradorComponenteVisual extends ComponenteVisual {
	private ComponenteVisual componenteVisual;
	
	/**
	 * @return the componenteVisual
	 */
	public ComponenteVisual getComponenteVisual() {
		return componenteVisual;
	}

	/**
	 * @param componenteVisual the componenteVisual to set
	 */
	public void setComponenteVisual(ComponenteVisual componenteVisual) {
		this.componenteVisual = componenteVisual;
	}
	
	public DecoradorComponenteVisual(ComponenteVisual compVisual) {
		// TODO Auto-generated constructor stub
		this.componenteVisual = compVisual;
	}
	
}
