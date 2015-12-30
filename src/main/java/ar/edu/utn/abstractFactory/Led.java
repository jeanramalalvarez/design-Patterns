package ar.edu.utn.abstractFactory;

public class Led extends Tv {

	public Led() {
		// TODO Auto-generated constructor stub
		super.setColor(null);
		super.setPulgadas(60);
		super.setTipo("Led");
	}
	@Override
	public void cambiarCanal(Integer canal) {
		// TODO Auto-generated method stub

	}

}
