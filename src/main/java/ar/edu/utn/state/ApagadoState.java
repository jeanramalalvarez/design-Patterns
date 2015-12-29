package ar.edu.utn.state;

public class ApagadoState implements TvState {
	@Override
	public void encender(Tv tv) throws Exception {
		// TODO Auto-generated method stub
		tv.setEstado(new EncendidoState());
	}
	
}
