package ar.edu.utn.abstractFactory;

public class Negro extends Color {

	public Negro() {
		// TODO Auto-generated constructor stub
		super.setNombre("Negro");
	}
	
	@Override
	public void colorear(Tv tv) {
		// TODO Auto-generated method stub
		super.validateTv(tv);
		tv.setColor(super.getNombre());
	}

}
