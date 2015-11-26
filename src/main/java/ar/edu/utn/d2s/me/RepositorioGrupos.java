package ar.edu.utn.d2s.me;

import java.util.HashSet;
import java.util.Set;

import ar.edu.ut.d2s.exceptions.GrupoInvalidoException;

public class RepositorioGrupos {
	private Set<Grupo> grupos;

	public RepositorioGrupos() {
		this.grupos = new HashSet<Grupo>();
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}
	
	
	public void agregarGrupo(Grupo grupo) throws GrupoInvalidoException{
		if (!grupos.add(grupo)) {
			throw new GrupoInvalidoException("Error: ya existe un grupo con el nombre: " + grupo.getNombre());
		}
		;
	}
}