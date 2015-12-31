package ar.edu.utn.command;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComandosTests {
	@Test
	public void testCrear1RecetaPropia(){
		
		RepositorioRecetas repositorio = new RepositorioRecetas();
		String autorReceta = "daniel";
		String nombreReceta = "lechon";
		
		ControladorReceta controlador = new ControladorReceta(autorReceta, nombreReceta, repositorio);
		ComandoReceta comando = new CreacionReceta(controlador);
		comando.ejecutar();
		assertEquals(1, repositorio.cantidadRecetaPropias());
	}
	
	@Test
	public void testCrear1RecetaDefault(){
		
		RepositorioRecetas repositorio = new RepositorioRecetas();
		String autorReceta = null;
		String nombreReceta = "lechon";
		
		ControladorReceta controlador = new ControladorReceta(autorReceta, nombreReceta, repositorio);
		ComandoReceta comando = new CreacionReceta(controlador);
		comando.ejecutar();
		assertEquals(1, repositorio.cantidadRecetaDefaul());
	}
	
	@Test
	public void testEliminarRecetaDefault(){
		
		RepositorioRecetas repositorio = new RepositorioRecetas();
		String autorReceta = null;
		String nombreReceta = "lechon";
		
		ControladorReceta controlador = new ControladorReceta(autorReceta, nombreReceta, repositorio);
		ComandoReceta comando = new CreacionReceta(controlador);
		comando.ejecutar();

		comando = new EliminacionReceta(controlador);
		comando.ejecutar();
		assertEquals(0, repositorio.cantidadRecetaDefaul());
		
	}
	
}
