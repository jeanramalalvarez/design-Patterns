package ar.edu.utn.model.abstractFactory;

public class FabricaPlasmaNegro extends FabricaTv {

	@Override
	public Tv createTv() {
		// TODO Auto-generated method stub
		return new Plasma();
	}

	@Override
	public Color createColor() {
		// TODO Auto-generated method stub
		return new Negro();
	}

}
