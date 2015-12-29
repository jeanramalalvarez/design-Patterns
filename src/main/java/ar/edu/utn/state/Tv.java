package ar.edu.utn.state;

public class Tv {
	private Integer canal;
	private Integer volumen;
	private TvState estado;
	
	public void encender() throws Exception{
		this.estado.encender(this);
	}
	
	public void apagar() throws Exception{
		this.estado.apagar(this);
	}
	
	public void cambiarCanal(Integer nuevoCanal) throws Exception{
		this.estado.cambiarCanal(nuevoCanal, this);
	}
	
	public void cambiarVolumen(Integer nuevoVolumen) throws Exception{
		this.estado.cambiarVolumen(nuevoVolumen, this);
	}
	
	public Tv(TvState estado) {
		// TODO Auto-generated constructor stub
		this.estado = estado;
	}
	public Integer getCanal() {
		return canal;
	}
	public void setCanal(Integer canal) {
		this.canal = canal;
	}
	public Integer getVolumen() {
		return volumen;
	}
	public void setVolumen(Integer volumen) {
		this.volumen = volumen;
	}
	public TvState getEstado() {
		return estado;
	}
	public void setEstado(TvState estado) {
		this.estado = estado;
	}
	
	
}
