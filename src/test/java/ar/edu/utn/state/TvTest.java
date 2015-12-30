package ar.edu.utn.state;

import static org.junit.Assert.*;

import org.junit.Test;

import ar.edu.utn.state.ApagadoState;
import ar.edu.utn.state.EncendidoState;
import ar.edu.utn.state.Tv;
import ar.edu.utn.state.TvState;

public class TvTest {
	private Tv tv;
	@Test
	public void testEncenderTvApagada() throws Exception{
		tv = new Tv(new ApagadoState());
		tv.encender();
		assertTrue(tv.getEstado() instanceof EncendidoState);

	}
	
	@Test (expected = Exception.class)
	public void testEncederTvEncendida() throws Exception{
		tv = new Tv(new EncendidoState());
		tv.encender();
	}
	
	@Test 
	public void testCambiarCanalTvEncendida() throws Exception{
		tv = new Tv(new EncendidoState());
		tv.cambiarCanal(11);
		assertEquals(11, tv.getCanal());
	}
	
	@Test 
	public void testCambiarVolumenTvEncendida() throws Exception{
		tv = new Tv(new EncendidoState());
		tv.cambiarVolumen(10);
		assertEquals(10, tv.getVolumen());
	}
	
	@Test 
	public void testApagarTvEncendida() throws Exception{
		tv = new Tv(new EncendidoState());
		tv.apagar();
		assertTrue(tv.getEstado() instanceof ApagadoState);

	}
	
	
}
