package ar.edu.utn.d2s.me;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

public class RecomendacionTest {
	private Usuario usuarioValido = new Usuario();
	private Usuario usuarioValido2 = new Usuario();
	private Receta recetaValida = new Receta();
	private Receta recetaValida2 = new Receta();	
	private Receta recetaValida3 = new Receta();
	private Set<String> TiposDeComidaValidas = new HashSet<String>();
	private RepositorioRecetas repositorioRecetas = new RepositorioRecetas(); 
	private Grupo grupoValido = new Grupo();
	private Grupo grupoValido2 = new Grupo();
	private RepositorioGrupos repositorioGrupos = new RepositorioGrupos();
	private GeneradorRecomendaciones generadorRecomendaciones = new GeneradorRecomendaciones();
	private Calificacion calificacionValida = new Calificacion();
	private Calificacion calificacionValida2 = new Calificacion();
	private Calificacion calificacionValida3 = new Calificacion();

	
	@Before
	public void setUp() throws Exception{
		
		grupoValido.setNombre("nombreGrupoValido");
		repositorioGrupos.agregarGrupo(grupoValido);
		
		grupoValido2.setNombre("nombreGrupoValido2");
		repositorioGrupos.agregarGrupo(grupoValido2);
		
		TiposDeComidaValidas.add("ALMUERZO");
		TiposDeComidaValidas.add("CENA");
		
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");
		
		usuarioValido.setMail("usuarioValido@gmail.com");
		usuarioValido.setRepositorioRecetas(repositorioRecetas);
		usuarioValido.agregarPreferencia("acelga");
		recetaValida.setAutor(usuarioValido);
		recetaValida.setNombre("Tarta de acelga");
		recetaValida.agregarIngrediente("acelga");
		recetaValida.agregarIngrediente("huevo");
		recetaValida.agregarIngrediente("papa");
		recetaValida.setTiposDeComida(TiposDeComidaValidas);
		recetaValida.setProcedimiento("1-Preparar ...");
		recetaValida.setDificultad((byte) 1);		
		recetaValida.setTemporadas(temporadas);
		recetaValida.setCalorias(400);
		usuarioValido.agregarReceta(recetaValida);
		usuarioValido.agregarRestriccion(new Restriccion("vegetariano","carne"));
		usuarioValido.agregarRestriccion(new Restriccion("diabetico", "azucar"));

		grupoValido.agregarMiembro(usuarioValido);
		usuarioValido.compartirReceta(grupoValido, recetaValida);
		
		calificacionValida.setGrupo(grupoValido);
		calificacionValida.setUsuario(usuarioValido);
		calificacionValida.setValor(1);
		usuarioValido.calificar(recetaValida, calificacionValida);
		
		
		usuarioValido2.setMail("usuarioValido2@gmail.com");
		usuarioValido2.setRepositorioRecetas(repositorioRecetas);
		usuarioValido2.agregarPreferencia("huevo");
		recetaValida2.setAutor(usuarioValido2);
		recetaValida2.setNombre("Tarta de acelga heavy");
		recetaValida2.agregarIngrediente("acelga");
		recetaValida2.agregarIngrediente("huevo");
		recetaValida2.agregarIngrediente("palta");
		recetaValida2.setTiposDeComida(TiposDeComidaValidas);
		recetaValida2.setProcedimiento("1-Preparar ...");
		recetaValida2.setDificultad((byte) 1);		
		recetaValida2.setTemporadas(temporadas);
		recetaValida2.setCalorias(500);
		usuarioValido2.agregarReceta(recetaValida2);
		usuarioValido2.agregarRestriccion(new Restriccion("vegetariano", "carne"));
		usuarioValido2.agregarRestriccion(new Restriccion("diabetico", "azucar"));

		grupoValido2.agregarMiembro(usuarioValido2);
		grupoValido2.agregarMiembro(usuarioValido);
		usuarioValido2.compartirReceta(grupoValido2, recetaValida2);
		
		calificacionValida2.setGrupo(grupoValido2);
		calificacionValida2.setUsuario(usuarioValido2);
		calificacionValida2.setValor(3);
		usuarioValido2.calificar(recetaValida2, calificacionValida2);
		
		recetaValida3.setAutor(usuarioValido2);
		recetaValida3.setNombre("Tarta de acelga light");
		recetaValida3.agregarIngrediente("acelga");
		recetaValida3.agregarIngrediente("palta");
		recetaValida3.setTiposDeComida(TiposDeComidaValidas);
		recetaValida3.setProcedimiento("1-Preparar ...");
		recetaValida3.setDificultad((byte) 1);		
		recetaValida3.setTemporadas(temporadas);
		recetaValida3.setCalorias(500);
	
		usuarioValido2.agregarReceta(recetaValida3);

		usuarioValido2.compartirReceta(grupoValido2, recetaValida3);
		
		calificacionValida3.setGrupo(grupoValido2);
		calificacionValida3.setUsuario(usuarioValido2);
		calificacionValida3.setValor(0);
		usuarioValido2.calificar(recetaValida3, calificacionValida3);

	}	
	
	@Test
	public void testRecomendacion_N_RecetasMejorPuntaje(){
		CriterioRecomendacionStrategy criterioRecomendacion = new N_MayorPuntajeStrategy();
		((N_MayorPuntajeStrategy)criterioRecomendacion).setN(2);
		generadorRecomendaciones.setCriterioRecomendacion(criterioRecomendacion);
		List<Receta> subList = new ArrayList<Receta>();
		//Recetas mas puntuadas 
		subList.add(recetaValida2);
		subList.add(recetaValida);
		assertEquals(subList, generadorRecomendaciones.generarRecomendacion(usuarioValido));
	}
	
}
