package ar.edu.utn.builder;

public class NotebookDirector {

	private NotebookBuilder notebookBuilder;
	
	public NotebookDirector(NotebookBuilder notebookBuilder) {
		// TODO Auto-generated constructor stub
		this.notebookBuilder = notebookBuilder;
	}
	
	public void construir(){
		//comunicación con el builder
		this.notebookBuilder.definirNotebook();
		this.notebookBuilder.construirPlacaMadre();
		this.notebookBuilder.construirMemoria();
		this.notebookBuilder.construirProcesador();
		this.notebookBuilder.construirPantalla();
		this.notebookBuilder.construirDisco();
	}
	
	public NotebookBuilder getNotebookBuilder() {
		return notebookBuilder;
	}
	public void setNotebookBuilder(NotebookBuilder notebookBuilder) {
		this.notebookBuilder = notebookBuilder;
	}
	
	

}
