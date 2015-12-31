package ar.edu.utn.command;

public class EliminacionReceta extends ComandoReceta {

	public EliminacionReceta(ControladorReceta controlador) {
		super(controlador);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		this.getControlador().eliminarReceta();
	}

}
