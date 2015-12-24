package ar.edu.utn.composite;

public class ElementoSimpleAuto extends ComponenteAuto {

	public ElementoSimpleAuto(String nombre, String descripcion, double precioBase) {
		// TODO Auto-generated constructor stub
		
		super(nombre, descripcion, precioBase);

	}

	@Override
	public Double calcularPrecio() {
		// TODO Auto-generated method stub
		return super.getPrecioBase();
	}

}
