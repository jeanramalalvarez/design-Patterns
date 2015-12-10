package ar.edu.utn.builder;

public class Procesador {
	private Double velocidad;
	private String marca;
	private String modelo;
	private String cacheL1;
	private String cacheL2;
	
	public void ejecutrarIntruccion(String instruccion){	
	}
	
	public Procesador() {
		// TODO Auto-generated constructor stub
	}

	public Double getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(Double velocidad) {
		this.velocidad = velocidad;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public void setCacheL2(String cacheL2) {
		this.cacheL2 = cacheL2;
	}

	public String getCacheL1() {
		return cacheL1;
	}

	public void setCacheL1(String cacheL1) {
		this.cacheL1 = cacheL1;
	}

	public String getCacheL2() {
		return cacheL2;
	}

}
