package ar.edu.utn.builder;

public  class Notebook {
	private Pantalla pantalla;
	private DiscoRigido disco;
	private PlacaMadre placaMadre;
	private String marca;
	private String modelo;
	
	public Notebook() {
		// TODO Auto-generated constructor stub
	}

	public Pantalla getPantalla() {
		return pantalla;
	}

	public void setPantalla(Pantalla pantalla) {
		this.pantalla = pantalla;
	}

	public DiscoRigido getDisco() {
		return disco;
	}

	public void setDisco(DiscoRigido disco) {
		this.disco = disco;
	}

	public PlacaMadre getPlacaMadre() {
		return placaMadre;
	}

	public void setPlacaMadre(PlacaMadre placaMadre) {
		this.placaMadre = placaMadre;
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

}
