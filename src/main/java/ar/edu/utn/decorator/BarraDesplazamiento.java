package ar.edu.utn.decorator;

public class BarraDesplazamiento extends DecoradorComponenteVisual {

	public BarraDesplazamiento(ComponenteVisual compVisual) {
		super(compVisual);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String dibujar() {
		// TODO Auto-generated method stub
		return super.getComponenteVisual().dibujar() + "BarraDesplazamiento";
	}

}