package ar.edu.utn.d2s.me;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class N_MayorPuntajeStrategy extends CriterioRecomendacionStrategy {

	private int N;
	
	@Override
	public List<Receta> generarRecomendacion() {
		
		//Obtengo todas las recetas de todos los grupos del usuario
		Set<Receta> recetasDeGrupos  = getRecetasDeGrupos(super.getUsuario());
		
		//Ordeno
		List sortedList = new ArrayList(recetasDeGrupos);
		Collections.sort(sortedList, new OrdenarRecetasPorMayorPuntuacion(super.getUsuario()));
		
		//Retorno las N
		if (N > sortedList.size()) {
			return (List<Receta>) sortedList.subList(0, sortedList.size());			
		}
		return (List<Receta>) sortedList.subList(0, N);
	}


	public Set<Receta> getRecetasDeGrupos(Usuario usuario) {
		Set<Receta> recetasDeGrupos = new HashSet<Receta>();
		for (Grupo grupo : usuario.getGrupos()) {
			recetasDeGrupos.addAll(grupo.getRecetasCompartidas());
		}
		
		return recetasDeGrupos;
	}

	
	public int getN() {
		return N;
	}

	public void setN(int n) {
		N = n;
	}


	
}
