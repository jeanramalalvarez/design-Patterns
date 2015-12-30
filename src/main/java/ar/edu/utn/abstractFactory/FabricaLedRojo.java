package ar.edu.utn.abstractFactory;

public class FabricaLedRojo extends FabricaTv {

	@Override
	public Tv createTv() {
		// TODO Auto-generated method stub
		return new Led();
	}

	@Override
	public Color createColor() {
		// TODO Auto-generated method stub
		return new Rojo();
	}

}
