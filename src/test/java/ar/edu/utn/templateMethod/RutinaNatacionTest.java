package ar.edu.utn.templateMethod;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.utn.templateMethod.Nadador;
import ar.edu.utn.templateMethod.NadoCrowl;
import ar.edu.utn.templateMethod.NadoMariposa;
import ar.edu.utn.templateMethod.NadoPecho;

public class RutinaNatacionTest {
	
	@Test
	public void ejecutarRutinaNadoMariposa(){
		Nadador nadador = new Nadador();
		nadador.setRutina(new NadoMariposa());
		nadador.ejecutarRutina();
		assertEquals(900, nadador.metrosNadadosUltimaRutina());
	}
	
	@Test
	public void ejecutarRutinaNadoCrowl(){
		Nadador nadador = new Nadador();
		nadador.setRutina(new NadoCrowl());
		nadador.ejecutarRutina();
		assertEquals(1300, nadador.metrosNadadosUltimaRutina());
	}
	
	@Test
	public void ejecutarRutinaNadoPecho(){
		Nadador nadador = new Nadador();
		nadador.setRutina(new NadoPecho());
		nadador.ejecutarRutina();
		assertEquals(1400, nadador.metrosNadadosUltimaRutina());
	}
}
