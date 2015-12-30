package ar.edu.utn.observer;

import java.util.ArrayList;
import java.util.List;

public class Diario {
	private List<Suscriptor> suscriptores;
	
	public Diario() {
		// TODO Auto-generated constructor stub
		suscriptores = new ArrayList<Suscriptor>();
	}
	
	public void publicarNoticia(String noticia){
		for (Suscriptor suscriptor : suscriptores) {
			suscriptor.mostrarNoticia(noticia);
		}
	}
	
	public void agregarSuscriptor(Suscriptor s){
		this.suscriptores.add(s);
	}

	public void removerSuscriptor(Suscriptor s){
		this.suscriptores.remove(s);
	}

	public List<Suscriptor> getSuscriptores() {
		return suscriptores;
	}

	public void setSuscriptores(List<Suscriptor> suscriptores) {
		this.suscriptores = suscriptores;
	}
	
	public Integer sumatoriaNoticasEnSuscriptores(){
		Integer sumatoria = 0;
		for (Suscriptor suscriptor : suscriptores) {
			sumatoria += suscriptor.getCantidadNoticias();
		}
		return sumatoria;
	}
	
}
