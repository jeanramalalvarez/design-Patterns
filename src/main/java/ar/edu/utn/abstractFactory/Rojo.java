package ar.edu.utn.abstractFactory;

public class Rojo extends Color {

	public Rojo() {
		// TODO Auto-generated constructor stub
		super.setNombre("Rojo");
	}

	@Override
	public void colorear(Tv tv) {
		// TODO Auto-generated method stub
		tv.setColor(super.getNombre());
	}

}
