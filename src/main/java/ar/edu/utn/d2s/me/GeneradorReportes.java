package ar.edu.utn.d2s.me;

import java.util.Set;

public class GeneradorReportes {
	private CriterioReportesStrategy criterioReportes;

	public CriterioReportesStrategy getCriterioReportes() {
		return criterioReportes;
	}

	public void setCriterioReportes(CriterioReportesStrategy criterioReportes) {
		this.criterioReportes = criterioReportes;
	}
	
	public Set<Comida> generarReporte(Usuario usuario){
		criterioReportes.setUsuario(usuario);
		return this.criterioReportes.generarReporte();
	}
	
	
}
