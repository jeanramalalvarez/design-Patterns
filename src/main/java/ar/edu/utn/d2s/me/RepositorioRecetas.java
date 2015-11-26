package ar.edu.utn.d2s.me;

import java.util.HashSet;
import java.util.Set;

public class RepositorioRecetas {
	private Set<Receta> recetasPorDefecto;

	public RepositorioRecetas() {
		// TODO Auto-generated constructor stub
		recetasPorDefecto = new HashSet<Receta>();
	}
	
	public Set<Receta> getRecetas() {
		return recetasPorDefecto;
	}

	public void setRecetas(Set<Receta> recetas) {
		this.recetasPorDefecto = recetas;
	}

	@Override
	public String toString() {
		return "RepositorioRecetas [recetas=" + recetasPorDefecto + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((recetasPorDefecto == null) ? 0 : recetasPorDefecto.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RepositorioRecetas other = (RepositorioRecetas) obj;
		if (recetasPorDefecto == null) {
			if (other.recetasPorDefecto != null)
				return false;
		} else if (!recetasPorDefecto.equals(other.recetasPorDefecto))
			return false;
		return true;
	}

	public Set<Receta> getRecetasPermitidas(Set<Restriccion> restricciones) {
		// TODO Auto-generated method stub
		Set<Receta> recetasPermitidas = new HashSet<Receta>();
		for (Receta receta : recetasPorDefecto) {
			if (receta.cumpleConRestricciones(restricciones)) {
				recetasPermitidas.add(receta);
			}
		}
		return recetasPermitidas;
	}
	
	public void agregarReceta(Receta receta){
		recetasPorDefecto.add(receta);
	}
}
