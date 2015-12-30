package ar.edu.utn.observer;

public class PantallaLed extends Suscriptor{

	public PantallaLed() {
		// TODO Auto-generated constructor stub
		super();
	}
	
	@Override
	public void mostrarNoticia(String noticia) {
		// TODO Auto-generated method stub
		super.incrementarCantidadNoticias();
	}


}
