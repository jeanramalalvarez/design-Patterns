package ar.edu.utn.templateMethod;

public class Nadador {
	private RutinaNatacion rutina;
	
	public void ejecutarRutina(){
		rutina.ejecutarRutina();
	}

	public RutinaNatacion getRutina() {
		return rutina;
	}

	public void setRutina(RutinaNatacion rutina) {
		this.rutina = rutina;
	}

	public Integer metrosNadadosUltimaRutina() {
		// TODO Auto-generated method stub
		return this.rutina.getMetrosNadados();
	}
}
