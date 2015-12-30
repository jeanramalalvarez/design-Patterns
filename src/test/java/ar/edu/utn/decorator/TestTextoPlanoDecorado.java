package ar.edu.utn.decorator;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import ar.edu.utn.decorator.BarraDesplazamiento;
import ar.edu.utn.decorator.Borde;
import ar.edu.utn.decorator.ComponenteVisual;
import ar.edu.utn.decorator.TextoPlano;

public class TestTextoPlanoDecorado {
	private ComponenteVisual textoPlano;//componente simple
	
	@Before
	public void before(){
		textoPlano = new TextoPlano();	
	}
	@Test
	public void textoPlanoDecoradoConBordeBarraDesplazamiento(){
		ComponenteVisual textoPlanoBorde = new Borde(textoPlano);//componente que decora al simple
		ComponenteVisual textoPlanoBordeBarra= new BarraDesplazamiento(textoPlanoBorde);//otro componente que decora
		assertEquals("TextoPlanoBordeBarraDesplazamiento", textoPlanoBordeBarra.dibujar());
		
	}
	
	@Test
	public void textoPlanoDecoradoConBarraDesplazamiento(){
				
		ComponenteVisual textoPlanoBarra= new BarraDesplazamiento(textoPlano);
		assertEquals("TextoPlanoBarraDesplazamiento", textoPlanoBarra.dibujar());
		
	}
	
	@Test
	public void textoPlanoDecoradoConBorde(){
		ComponenteVisual textoPlanoBorde = new Borde(textoPlano);			
		assertEquals("TextoPlanoBorde", textoPlanoBorde.dibujar());
		
	}
}
