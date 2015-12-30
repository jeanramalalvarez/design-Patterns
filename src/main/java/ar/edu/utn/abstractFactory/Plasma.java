package ar.edu.utn.abstractFactory;

public class Plasma extends Tv {

	public Plasma() {
		// TODO Auto-generated constructor stub
		super.setColor(null);
		super.setPulgadas(50);
		super.setTipo("Plasma");
	}
	
	@Override
	public void cambiarCanal(Integer canal) {
		// TODO Auto-generated method stub

	}

}
