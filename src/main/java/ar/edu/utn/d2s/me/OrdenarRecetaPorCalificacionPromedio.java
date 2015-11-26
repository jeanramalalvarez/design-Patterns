package ar.edu.utn.d2s.me;

import java.util.Comparator;

public class OrdenarRecetaPorCalificacionPromedio implements Comparator<Receta> {
	
	private Grupo grupo;

	public OrdenarRecetaPorCalificacionPromedio(Grupo grupo) {
		// TODO Auto-generated constructor stub
		this.grupo = grupo;
	}

	@Override
	public int compare(Receta r1, Receta r2) {
		// TODO Auto-generated method stub
		return  r2.getCalificacionPromedio(grupo) - r1.getCalificacionPromedio(grupo) ;
	}

}
