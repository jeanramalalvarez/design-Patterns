package ar.edu.utn.builder;

public abstract class NotebookBuilder {
	private Notebook notebook;
	
	public abstract void definirNotebook();
	public abstract void construirPantalla();
	public abstract void construirDisco();
	public abstract void construirProcesador();
	public abstract void construirMemoria();
	public abstract void construirPlacaMadre();
	/**
	 * @return the notebook
	 */
	public Notebook getNotebook() {
		return notebook;
	}
	/**
	 * @param notebook the notebook to set
	 */
	public void setNotebook(Notebook notebook) {
		this.notebook = notebook;
	}
}
