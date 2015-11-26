package ar.edu.utn.d2s.me;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;

public class CriterioRangoFechasStrategy extends CriterioReportesStrategy {
	
	private LocalDate fechaInicio;
	private LocalDate fechaFin;
	
	@Override
	public Set<Comida> generarReporte() {
		// TODO Auto-generated method stub
		Set<Comida> comidasPlanificadasUsuario = super.getUsuario().getComidasPlanificadas();
		Set<Comida> reporteComidasFiltradas = new HashSet<Comida>();
		
		for (Comida comida : comidasPlanificadasUsuario) {
			if (comida.getFecha().isAfter(fechaInicio) && comida.getFecha().isBefore(fechaFin)) {
				reporteComidasFiltradas.add(comida);
			}
		}
		return reporteComidasFiltradas;
	}

	public LocalDate getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(LocalDate fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public LocalDate getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(LocalDate fechaFin) {
		this.fechaFin = fechaFin;
	}

}
