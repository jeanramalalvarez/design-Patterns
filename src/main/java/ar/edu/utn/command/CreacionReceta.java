package ar.edu.utn.command;

public class CreacionReceta extends ComandoReceta {

	public CreacionReceta(ControladorReceta controlador) {
		// TODO Auto-generated constructor stub
		super(controlador);
	}

	@Override
	public void ejecutar() {
		// TODO Auto-generated method stub
		super.getControlador().crearReceta();
	}

}
