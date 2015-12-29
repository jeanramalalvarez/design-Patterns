package ar.edu.utn.state;

public class EncendidoState implements TvState {
	@Override
	public void apagar(Tv tv) throws Exception {
		// TODO Auto-generated method stub
		tv.setEstado(new ApagadoState());
	}
	
	@Override
	public void cambiarCanal(Integer c, Tv tv) throws Exception {
		// TODO Auto-generated method stub
		tv.setCanal(c);;
	}
	
	@Override
	public void cambiarVolumen(Integer v, Tv tv) throws Exception {
		// TODO Auto-generated method stub
		tv.setVolumen(v);
	}
	
}
