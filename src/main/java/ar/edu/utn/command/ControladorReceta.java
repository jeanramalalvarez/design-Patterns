package ar.edu.utn.command;

public  class ControladorReceta {
	private String autorReceta;
	private String nombreReceta;
	private RepositorioRecetas repositorio;
	
	public void crearReceta() {
		// TODO Auto-generated method stub
		Receta receta = new Receta(getAutorReceta(), getNombreReceta());
		getRepositorio().agregarReceta(receta);
	}

	public void eliminarReceta() {
		// TODO Auto-generated method stub
		getRepositorio().eliminarReceta(nombreReceta);
	}
	public String getAutorReceta() {
		return autorReceta;
	}
	public void setAutorReceta(String autorReceta) {
		this.autorReceta = autorReceta;
	}
	public String getNombreReceta() {
		return nombreReceta;
	}
	public void setNombreReceta(String nombreReceta) {
		this.nombreReceta = nombreReceta;
	}
	public RepositorioRecetas getRepositorio() {
		return repositorio;
	}
	public void setRepositorio(RepositorioRecetas repositorio) {
		this.repositorio = repositorio;
	}
	public ControladorReceta(String autorReceta, String nombreReceta, RepositorioRecetas repositorio) {
		super();
		this.autorReceta = autorReceta;
		this.nombreReceta = nombreReceta;
		this.repositorio = repositorio;
	}

	
	
}
