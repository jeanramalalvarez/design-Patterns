package ar.edu.utn;

import static org.junit.Assert.*;

import java.io.File;

import org.junit.Test;

import ar.edu.utn.strategy.CompresionStrategy;
import ar.edu.utn.strategy.MensajeCompresion;
import ar.edu.utn.strategy.RarStrategy;
import ar.edu.utn.strategy.ZipStrategy;

public class CompresionTest {
	private CompresionStrategy estrategiaCompresion;
	private File archivo;

	@Test
	public void testComprimirEnFormatoZip(){
		estrategiaCompresion = new ZipStrategy();
		assertEquals(MensajeCompresion.ZIPedado_CORRECTAMENTE, estrategiaCompresion.comprimir(archivo));
	}
	
	@Test
	public void testComprimirEnFormatoRar(){
		estrategiaCompresion = new RarStrategy();
		assertEquals(MensajeCompresion.RAReado_CORRECTAMENTE, estrategiaCompresion.comprimir(archivo));
	}
}
