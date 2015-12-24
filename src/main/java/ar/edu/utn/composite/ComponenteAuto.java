package ar.edu.utn.composite;

public abstract class ComponenteAuto {
	private String nombre;
	private String descripcion;
	private Double precioBase;
	
	public ComponenteAuto(String nombre, String descripcion, double precioBase) {
		// TODO Auto-generated constructor stub
		setNombre(nombre);
		setDescripcion(descripcion);
		setPrecioBase(precioBase);
	}
	
	public abstract Double calcularPrecio();
	public void addComponente(ComponenteAuto c){
		//nothing to do in the case hojas
	}
	public void removeComponente(ComponenteAuto c){
		//nothing to do in the case hojas
	}
		
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public Double getPrecioBase() {
		return precioBase;
	}
	public void setPrecioBase(Double precioBase) {
		this.precioBase = precioBase;
	}

	
}
