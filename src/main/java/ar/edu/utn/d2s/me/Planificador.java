package ar.edu.utn.d2s.me;

import java.util.Date;
import java.util.Set;

import org.joda.time.LocalDate;

import ar.edu.ut.d2s.exceptions.ComidaInvalidaInvaliException;
import ar.edu.ut.d2s.exceptions.FechaFueraFueraDeRangoException;

public class Planificador {

	public void planificar(Comida comida, Usuario usuario) throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		if (comida.getReceta() == null) {
			throw new ComidaInvalidaInvaliException("Error: debe seleccionar una receta");
		}
		
		if (!esValidaFechaDeComida(comida.getFecha())) {
			if (!esValidaFechaDeComida(comida.getFecha())) {
				throw new FechaFueraFueraDeRangoException("Error: no se puede planificar mas alla de una semana, hacia adenlante o atrás");
				
			}
		}
		
		if (!esValidoHorarioDeRecetaParaComida(comida.getTipoComida(), comida.getReceta().getTiposDeComida())) {
			throw new ComidaInvalidaInvaliException("Error: el horario de la comida no coincide con los horarios de la la receta");
			
		}
		
		if ( usuario.existeComida(comida.getTipoComida(), comida.getFecha())) {
			throw new ComidaInvalidaInvaliException("Error: ya hay una receta planificada, para la fecha y horarios seleccionados");

		}else {			
			usuario.agregarComida(comida);
		}
		
		
		
	}
	
	
	private boolean esValidoHorarioDeRecetaParaComida(String tipoComida, Set<String> tiposDeComidaReceta) {
		// TODO Auto-generated method stub
		if (tiposDeComidaReceta.contains(tipoComida)) {
			return true;
		}
		return false;
	}


	public boolean esValidaFechaDeComida(LocalDate fecha) {
		// TODO Auto-generated method stub
		long SEMANA_MILISEGUNDOS = 7 * 24 * 60 * 60 * 1000;
		Date fechaSistema = (new LocalDate()).toDate();
		Date fechaComida = fecha.toDate();
		
		if (fechaComida.getTime() < fechaSistema.getTime() - SEMANA_MILISEGUNDOS
				|| fechaComida.getTime() > fechaSistema.getTime() + SEMANA_MILISEGUNDOS) {
			return false;
		}
		return true;
	}


	public void removerPlanicacion(Comida comida, Usuario usuario){
		usuario.removerComida(comida.getTipoComida(), comida.getFecha());
	}


	public Receta consultarPlanificacion(String tipoComida, LocalDate fecha, Usuario usuario){
		Comida comida =  usuario.getComida(tipoComida, fecha);
		return  (comida != null) ?comida.getReceta() : null;
	}
	
	public String replanificar(Comida comidaPlanificada, Receta recetaNueva, Usuario usuario) throws ComidaInvalidaInvaliException{
		
		if (!esValidoHorarioDeRecetaParaComida(comidaPlanificada.getTipoComida(), comidaPlanificada.getReceta().getTiposDeComida())) {
			throw new ComidaInvalidaInvaliException("Error: el horario de la comida no coincide con los horarios de la la receta");
			
		}
		
		if (recetaNueva != null) {//se cambia la receta plafinicada
			
			comidaPlanificada.setReceta(recetaNueva);
			return "actualizada";
		}
		else{//receta == null se remueve la comida planificada
			usuario.getComidasPlanificadas().remove(comidaPlanificada);
			return "removida";
		}
	}
}
