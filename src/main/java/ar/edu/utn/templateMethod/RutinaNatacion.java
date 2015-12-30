package ar.edu.utn.templateMethod;

public abstract class RutinaNatacion {
	
	private Integer metrosNadados;
	
	public RutinaNatacion() {
		// TODO Auto-generated constructor stub
		metrosNadados = 0;
	}
	
	public void ejecutarRutina(){
		this.precalentar();
		this.nadar();
		this.finalizar();
	}
	
	public void precalentar(){
		this.nadar(200);
	}
	
	public abstract void nadar();
	
	public void finalizar(){
		this.nadar(200);
	}

	public void nadar(int m) {
		// TODO Auto-generated method stub
		this.metrosNadados +=m;
	}

	/**
	 * @return the metrosNadados
	 */
	public Integer getMetrosNadados() {
		return metrosNadados;
	}

	/**
	 * @param metrosNadados the metrosNadados to set
	 */
	public void setMetrosNadados(Integer metrosNadados) {
		this.metrosNadados = metrosNadados;
	}
}
