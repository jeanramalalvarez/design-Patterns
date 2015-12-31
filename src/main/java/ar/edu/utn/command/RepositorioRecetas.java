package ar.edu.utn.command;

import java.util.ArrayList;
import java.util.List;

public class RepositorioRecetas {
	private List<Receta> recetas;
	
	
	
	public RepositorioRecetas() {
		recetas = new ArrayList<>();
	}

	public int cantidadRecetaDefaul(){
		int contador = 0;
		for (Receta receta : recetas) {
			if (receta.getAutor() == null ) {
				contador++;
			}
		}
		return contador;
	}
	
	public int cantidadRecetaPropias(){
		int contador = 0;
		for (Receta receta : recetas) {
			if (receta.getAutor() != null ) {
				contador++;
			}
		}
		return contador;
	}

	public void agregarReceta(Receta receta) {
		// TODO Auto-generated method stub
		this.recetas.add(receta);
	}

	public void eliminarReceta(String nombreReceta) {
		// TODO Auto-generated method stub
		for (Receta receta : recetas) {
			if (receta.getNombre().equals(nombreReceta)) {
				this.recetas.remove(receta);
				return;
			}
		}
	}
	
}
