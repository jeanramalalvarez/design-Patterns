package ar.edu.utn.d2s.me;

import java.util.HashSet;
import java.util.Set;

public class CriterioIgredienteStrategy extends CriterioReportesStrategy {

	private String ingrediente;
	
	@Override
	public Set<Comida> generarReporte() {
		Set<Comida> comidasPlanificadasUsuario = super.getUsuario().getComidasPlanificadas();
		Set<Comida> reporteComidasFiltradas = new HashSet<Comida>();
		
		for (Comida comida : comidasPlanificadasUsuario) {
			if (comida.getReceta().getIngredientes().contains(ingrediente)) {
				reporteComidasFiltradas.add(comida);
			}
		}
		return reporteComidasFiltradas;
	}

	public String getIngrediente() {
		return ingrediente;
	}

	public void setIngrediente(String ingrediente) {
		this.ingrediente = ingrediente;
	}

}
