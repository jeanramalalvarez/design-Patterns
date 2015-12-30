package ar.edu.utn.observer;

public abstract class Suscriptor {
	private Integer cantidadNoticias;
	
	
	public Suscriptor() {
		// TODO Auto-generated constructor stub
		cantidadNoticias = 0;
	}
	
	public abstract void mostrarNoticia(String noticia);
	
	public Integer getCantidadNoticias() {
		return cantidadNoticias;
	}

	/**
	 * @param cantidadNoticias the cantidadNoticias to set
	 */
	public void setCantidadNoticias(Integer cantidadNoticias) {
		this.cantidadNoticias = cantidadNoticias;
	}

	public void incrementarCantidadNoticias() {
		// TODO Auto-generated method stub
		this.cantidadNoticias++;
	}

}
