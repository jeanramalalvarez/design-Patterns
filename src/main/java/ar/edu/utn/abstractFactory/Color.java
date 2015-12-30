package ar.edu.utn.abstractFactory;

public abstract class Color {
	private String nombre;
	
	public abstract void colorear(Tv tv);

	/**
	 * @return the nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * @param nombre the nombre to set
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public void validateTv(Tv tv){
		if (tv == null) {
			throw new NullPointerException("Error: la tv no puede ser nula");
		}
	}
}
