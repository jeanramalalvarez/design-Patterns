package ar.edu.utn.d2s.me;

import java.util.List;


public abstract class CriterioRecomendacionStrategy {
	private Usuario usuario;

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
	public abstract List<Receta> generarRecomendacion();	
}
