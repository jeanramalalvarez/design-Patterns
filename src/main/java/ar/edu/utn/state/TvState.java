package ar.edu.utn.state;

public interface TvState {
	public final String MSG_ERROR = "Error: accion no admitida para el estado actual";
	
	//A default method in the interface created using "default" keyword
	default public void encender( Tv tv) throws Exception{
		throw new Exception(MSG_ERROR);
	}
	
	default public void apagar(Tv tv) throws Exception{
		throw new Exception(MSG_ERROR);
	}
	
	default public void cambiarCanal(Integer c, Tv tv) throws Exception{
		throw new Exception(MSG_ERROR);
	}
	
	default public void cambiarVolumen(Integer v,  Tv tv) throws Exception{
		throw new Exception(MSG_ERROR);
	}
}
