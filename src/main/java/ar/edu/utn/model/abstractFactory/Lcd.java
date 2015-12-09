package ar.edu.utn.model.abstractFactory;

public class Lcd extends Tv {

	public Lcd() {
		// TODO Auto-generated constructor stub
		super.setColor(null);
		super.setPulgadas(42);
		super.setTipo("Lcd");
	}
	@Override
	public void cambiarCanal(Integer canal) {
		// TODO Auto-generated method stub
	}

}
