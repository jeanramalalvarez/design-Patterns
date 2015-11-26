package ar.edu.utn.d2s.me;

import java.util.Comparator;

public class OrdenarRecetasPorMayorPuntuacion implements Comparator<Receta> {
	
	private Usuario usuario;
	
	public OrdenarRecetasPorMayorPuntuacion(Usuario usuario) {
		// TODO Auto-generated constructor stub
		this.usuario = usuario;
	}
	
	@Override
	public int compare(Receta r1, Receta r2) {
		// TODO Auto-generated method stub
		return r2.getPuntuacion(usuario) - r1.getPuntuacion(usuario);
	}
}
