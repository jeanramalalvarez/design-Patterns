package ar.edu.utn.composite;

import java.util.ArrayList;
import java.util.List;

public class ElementoCompuestoAuto extends ComponenteAuto {

	private List<ComponenteAuto> componentesAuto;

	public ElementoCompuestoAuto(String nombre, String descripcion, double precioBase) {
		// TODO Auto-generated constructor stub
		super(nombre, descripcion, precioBase);
		componentesAuto = new ArrayList<>();
	}

	@Override
	public Double calcularPrecio() {
		// TODO Auto-generated method stub
		Double precio = super.getPrecioBase();
		for (ComponenteAuto componente : componentesAuto) {
			precio += componente.calcularPrecio();
		}
		return precio;
	}

	/**
	 * @return the componentesAuto
	 */
	public List<ComponenteAuto> getComponentesAuto() {
		return componentesAuto;
	}

	/**
	 * @param componentesAuto the componentesAuto to set
	 */
	public void setComponentesAuto(List<ComponenteAuto> componentesAuto) {
		this.componentesAuto = componentesAuto;
	}
	
	@Override
	public void addComponente(ComponenteAuto c) {
		// TODO Auto-generated method stub
		this.componentesAuto.add(c);
	}
	
	@Override
	public void removeComponente(ComponenteAuto c) {
		// TODO Auto-generated method stub
		this.componentesAuto.remove(c);
	}

}
