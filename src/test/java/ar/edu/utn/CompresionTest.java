package ar.edu.utn;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.utn.strategy.CompresionStrategy;
import ar.edu.utn.strategy.MensajeCompresion;
import ar.edu.utn.strategy.RarStrategy;
import ar.edu.utn.strategy.ZipStrategy;

public class CompresionTest {
	private CompresionStrategy estrategiaCompresion;

	@Test
	public void testComprimirEnFormatoZip(){
		estrategiaCompresion = new ZipStrategy();
		assertEquals(MensajeCompresion.ZIPedado_CORRECTAMENTE, estrategiaCompresion.comprimir());
	}
	
	@Test
	public void testComprimirEnFormatoRar(){
		estrategiaCompresion = new RarStrategy();
		assertEquals(MensajeCompresion.RAReado_CORRECTAMENTE, estrategiaCompresion.comprimir());
	}
}
