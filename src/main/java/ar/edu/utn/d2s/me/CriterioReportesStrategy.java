package ar.edu.utn.d2s.me;

import java.util.Set;

public abstract class CriterioReportesStrategy {
	private Usuario usuario;
	
	public abstract Set<Comida> generarReporte();

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
}
