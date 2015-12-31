package ar.edu.utn.command;

public abstract class ComandoReceta {

	private ControladorReceta controlador;
	
	public ComandoReceta(ControladorReceta controlador) {
		// TODO Auto-generated constructor stub
		this.controlador=controlador;
	}

	public abstract void ejecutar();

	/**
	 * @return the controlador
	 */
	public ControladorReceta getControlador() {
		return controlador;
	}

	/**
	 * @param controlador the controlador to set
	 */
	public void setControlador(ControladorReceta controlador) {
		this.controlador = controlador;
	}
}
