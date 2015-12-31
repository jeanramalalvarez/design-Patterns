package ar.edu.utn.command;

public class Receta {
	private String nombre;
	private String autor;
	
	public Receta(String autorReceta, String nombreReceta) {
		// TODO Auto-generated constructor stub
		this.nombre = nombreReceta;
		this.autor = autorReceta;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getAutor() {
		return autor;
	}

	public void setAutor(String autor) {
		this.autor = autor;
	}
	
}
