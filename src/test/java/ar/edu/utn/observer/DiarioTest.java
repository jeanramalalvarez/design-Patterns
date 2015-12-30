package ar.edu.utn.observer;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.observer.*;

public class DiarioTest {
	private Diario diario;

	@Before
	public void before(){
		diario = new Diario();
	}

	@Test
	public void testPublicar1Noticia1Suscriptor(){
		diario.agregarSuscriptor(new SitioWeb());
		diario.publicarNoticia("unaNoticiaMuySimpatica");
		assertEquals(1, diario.sumatoriaNoticasEnSuscriptores());
	}

	@Test
	public void testPublicar2Noticia1Suscriptor(){
		diario.agregarSuscriptor(new SitioWeb());
		diario.publicarNoticia("unaNoticiaMuySimpatica");
		diario.publicarNoticia("otraNoticiaMuySimpatica");
		assertEquals(2, diario.sumatoriaNoticasEnSuscriptores());
		
	}

	@Test
	public void testPublicar2Noticia2Suscriptores(){
		diario.agregarSuscriptor(new SitioWeb());
		diario.agregarSuscriptor(new PantallaLed());
		diario.publicarNoticia("unaNoticiaMuySimpatica");
		diario.publicarNoticia("otraNoticiaMuySimpatica");
		assertEquals(4, diario.sumatoriaNoticasEnSuscriptores());
		
	}
	
}
