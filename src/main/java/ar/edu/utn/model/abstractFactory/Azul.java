package ar.edu.utn.model.abstractFactory;

public class Azul extends Color {
	
	public Azul() {
		// TODO Auto-generated constructor stub
		super.setNombre("Azul");
	}
	
	@Override
	public void colorear(Tv tv) {
		// TODO Auto-generated method stub
		super.validateTv(tv);
		tv.setColor(super.getNombre());
	}

}
