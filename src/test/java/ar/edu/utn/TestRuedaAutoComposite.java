package ar.edu.utn;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.composite.ComponenteAuto;
import ar.edu.utn.composite.ElementoCompuestoAuto;
import ar.edu.utn.composite.ElementoSimpleAuto;

public class TestRuedaAutoComposite {
	private ComponenteAuto tornillo1;
	private ComponenteAuto tornillo2;
	private ComponenteAuto tornillo3;
	private ComponenteAuto tornillo4;
	private ComponenteAuto llanta;
	private ComponenteAuto neumatico;
	private ComponenteAuto valvula;
	private ComponenteAuto rueda;
	
	@Before
	public void before(){
		//construimos las partes simples
		tornillo1 = new ElementoSimpleAuto("Tornillo llanta", "Tornillo llanta marca ACME", 0.21);
		tornillo2 = new ElementoSimpleAuto("Tornillo llanta", "Tornillo llanta marca ACME", 0.21);
		tornillo3 = new ElementoSimpleAuto("Tornillo llanta", "Tornillo llanta marca ACME", 0.21);
		tornillo4 = new ElementoSimpleAuto("Tornillo llanta", "Tornillo llanta marca ACME", 0.21);
		
		llanta = new ElementoCompuestoAuto("Llanta ACME 15'", "Llanta ACME de 15'", 42.22);
		llanta.addComponente(tornillo1);
		llanta.addComponente(tornillo2);
		llanta.addComponente(tornillo3);
		llanta.addComponente(tornillo4);
		
		neumatico = new ElementoSimpleAuto("Neumático 15'", "Neumático Michelin de 15'", 13.42);
		valvula = new ElementoSimpleAuto("Válvula", "Válvula de neumático genérica", 0.49);
		
		rueda = new ElementoCompuestoAuto("Rueda 15'", "Rueda de 15' con llanta ACME y neumático Michelin", 0);
		rueda.addComponente(neumatico);
		rueda.addComponente(valvula);
		rueda.addComponente(llanta);	
	}
	
	@Test
	public void testPrecioRuedaAuto15ACMEMichelin(){
			assertEquals(56.97, rueda.calcularPrecio());
		
	}
	
	@Test
	public void testPrecioRuedaAuto15ACMEMichelinConAumentoPrcioTornillos(){
		tornillo1.setPrecioBase(0.34);
		tornillo2.setPrecioBase(0.34);
		tornillo3.setPrecioBase(0.34);
		tornillo4.setPrecioBase(0.34);	
		assertEquals(57.49, rueda.calcularPrecio(),0.01);
	}
	
	@Test
	public void testPrecioRuedaAuto15ACMEMichelinConAumentoPrcioValvula(){
		valvula.setPrecioBase(1.49);
		assertEquals(57.97, rueda.calcularPrecio(),0.01);
	}
}
