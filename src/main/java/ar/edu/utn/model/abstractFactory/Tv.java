package ar.edu.utn.model.abstractFactory;

public abstract class Tv {
	private String tipo;
	private String color;
	private Integer pulgadas;
	
	public abstract void cambiarCanal(Integer canal); 
	
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public Integer getPulgadas() {
		return pulgadas;
	}
	public void setPulgadas(Integer pulgadas) {
		this.pulgadas = pulgadas;
	}
	
}
