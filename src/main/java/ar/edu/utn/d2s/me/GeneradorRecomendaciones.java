package ar.edu.utn.d2s.me;

import java.util.List;

public class GeneradorRecomendaciones {
	
	private CriterioRecomendacionStrategy criterioRecomendacion;

	public CriterioRecomendacionStrategy getCriterioRecomendacion() {
		return criterioRecomendacion;
	}

	public void setCriterioRecomendacion(
			CriterioRecomendacionStrategy criterioRecomendacion) {
		this.criterioRecomendacion = criterioRecomendacion;
	}
	
	public List<Receta> generarRecomendacion(Usuario usuario){
		this.criterioRecomendacion.setUsuario(usuario);
		return criterioRecomendacion.generarRecomendacion();
	}
	

}
