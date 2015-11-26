package ar.edu.utn.d2s.me;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import ar.edu.ut.d2s.exceptions.ComidaInvalidaInvaliException;
import ar.edu.ut.d2s.exceptions.FechaFueraFueraDeRangoException;
import ar.edu.ut.d2s.exceptions.GrupoInvalidoException;
import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioExistenteException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;

public class PlanificarComidaTest {
	private Usuario usuarioValido = new Usuario();
	private Receta recetaValida = new Receta();
	private Comida comidaValida = new Comida();
	private Planificador planificadorComidas = new Planificador();
	private Set<String> TiposDeComidaValidas = new HashSet<String>();
	private LocalDate fechaValida = new LocalDate();
	private RepositorioRecetas repositorioRecetas = new RepositorioRecetas(); 
	private Grupo grupoValido = new Grupo();
	private RepositorioGrupos repositorioGrupos = new RepositorioGrupos();

	@Before
	public void setUp() throws Exception{
		usuarioValido.setMail("usuarioValido@gmail.com");
		usuarioValido.setRepositorioRecetas(repositorioRecetas);
		recetaValida.setAutor(usuarioValido);
		recetaValida.setNombre("Tarta de acelga");
		recetaValida.agregarIngrediente("acelga");
		recetaValida.agregarIngrediente("huevo");
		recetaValida.agregarIngrediente("papa");
		TiposDeComidaValidas.add("ALMUERZO");
		TiposDeComidaValidas.add("CENA");
		recetaValida.setTiposDeComida(TiposDeComidaValidas);
		recetaValida.setProcedimiento("1-Preparar ...");
		recetaValida.setDificultad((byte) 1);
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaValida.setTemporadas(temporadas);
		recetaValida.setCalorias(400);
		usuarioValido.agregarReceta(recetaValida);
		usuarioValido.agregarRestriccion(new Restriccion("vegetariano", "carne"));
		usuarioValido.agregarRestriccion(new Restriccion("diabetico", "azucar"));
		comidaValida.setReceta(recetaValida);
		comidaValida.setFecha(fechaValida);
		comidaValida.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida, usuarioValido);
		repositorioGrupos.agregarGrupo(grupoValido);
		grupoValido.agregarMiembro(usuarioValido);
		
		Receta recetaDefaulVegetariana = new Receta();
		recetaDefaulVegetariana.setAutor(null);
		recetaDefaulVegetariana.setNombre("ensalada lechuga y tomate");
		recetaDefaulVegetariana.agregarIngrediente("lechuga");
		recetaDefaulVegetariana.agregarIngrediente("tomate");
		recetaDefaulVegetariana.setTiposDeComida(TiposDeComidaValidas);
		repositorioRecetas.agregarReceta(recetaDefaulVegetariana);
	}
	
	//Consultar una planificación realizada (Ej: que voy a desayunar el 15/05?: nada / receta X)
	@Test
	public void testConsultarPlanificarComidaValida(){
		assertEquals(recetaValida,planificadorComidas.consultarPlanificacion("ALMUERZO", fechaValida, usuarioValido));
	}
	
	@Test
	public void testConsultarPlanificarComidaInvalida(){
		assertNull(planificadorComidas.consultarPlanificacion("MERIENDA", new LocalDate(), new Usuario()));
	}
	
	//Verificar el efecto de una replanificación (por otra receta o si no se come nada)
	@Test
	public void testReplanificarComidaConReceta() throws RecetaInvalidaException, FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		Receta recetaReplanificada = new Receta();
		recetaReplanificada.setAutor(usuarioValido);
		recetaReplanificada.setNombre("Ensalada de lechuga y tomate");
		recetaReplanificada.setTiposDeComida(TiposDeComidaValidas);
		recetaReplanificada.setProcedimiento("1-Preparar ...");
		recetaReplanificada.setDificultad((byte) 1);
		recetaReplanificada.agregarIngrediente("lechunga");
		recetaReplanificada.agregarIngrediente("tomate");
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaReplanificada.setTemporadas(temporadas);
		recetaReplanificada.setCalorias(390);
		usuarioValido.agregarReceta(recetaReplanificada);

		Comida comidaReplanificada = new Comida();
		comidaReplanificada.setReceta(recetaReplanificada);
		comidaReplanificada.setFecha(fechaValida);
		comidaReplanificada.setTipoComida("ALMUERZO");
		
		planificadorComidas.planificar(comidaReplanificada, usuarioValido);
		assertEquals(recetaReplanificada, planificadorComidas.consultarPlanificacion("ALMUERZO", fechaValida, usuarioValido));
	}
	
	@Test
	public void testEliminarPlanificacion() throws RecetaInvalidaException{
		planificadorComidas.removerPlanicacion(comidaValida, usuarioValido);
		assertNull(planificadorComidas.consultarPlanificacion("ALMUERZO", fechaValida, usuarioValido));
	}
	
	//No se puede planificar/replanificar más allá de una semana (hacia adelante o atrás)
	@Test(expected = FechaFueraFueraDeRangoException.class)
	public void testNoSePuedePlanificarMasAllaDeUnaSemanaAdelante() throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		Comida comidaConFechaInvalida = new Comida();
		comidaConFechaInvalida.setReceta(recetaValida);
		comidaConFechaInvalida.setFecha(new LocalDate(2016/10/1));
		comidaConFechaInvalida.setTipoComida("ALMUERZO");
		
		planificadorComidas.planificar(comidaConFechaInvalida, usuarioValido);
	}
	
	@Test(expected = FechaFueraFueraDeRangoException.class)
	public void testNoSePuedePlanificarMasAllaDeUnaSemanaAtras() throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		Comida comidaConFechaInvalida = new Comida();
		comidaConFechaInvalida.setReceta(recetaValida);
		comidaConFechaInvalida.setFecha(new LocalDate(2014/9/15));
		comidaConFechaInvalida.setTipoComida("ALMUERZO");
		
		planificadorComidas.planificar(comidaConFechaInvalida, usuarioValido);
	}
	
	@Test
	public void testPlanificarDentroDeUnaSemana() throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		Comida comidaConFechaInvalida = new Comida();
		comidaConFechaInvalida.setReceta(recetaValida);
		comidaConFechaInvalida.setFecha(new LocalDate());
		comidaConFechaInvalida.setTipoComida("ALMUERZO");
		
		planificadorComidas.planificar(comidaConFechaInvalida, usuarioValido);
	}
	
	//No se puede planificar una receta en un horario que no corresponde (desayuno, merienda, almuerzo, cena)
	@Test(expected = ComidaInvalidaInvaliException.class)
	public void testPlanificarRecetaEnHorarioInvalidoDeComida() throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		Comida comidaConFechaInvalida = new Comida();
		comidaConFechaInvalida.setReceta(recetaValida);
		comidaConFechaInvalida.setFecha(new LocalDate());
		comidaConFechaInvalida.setTipoComida("DESAYUNO");
		
		planificadorComidas.planificar(comidaConFechaInvalida, usuarioValido);
	}
	
	/*Las recetas disponibles para un usuario son:
	- Las que creo + las x defecto + las que están en otro grupo
	- Antes que nada deben respectarse las restricciones:
		-Los vegetarianos no se le debe listarse ninguna receta que tenga como ingrediente “carne”
		- Los diabéticos no se le debe listar ninguna receta que tenga como ingrediente “azúcar”
	*/
	@Test
	public void testListarRecetasDisponibles() throws RecetaInvalidaException, UsuarioExistenteException, GrupoInvalidoException, UsuarioInvalidoException{
		Usuario usuarioCarnivoro = new Usuario();
		usuarioCarnivoro.setMail("usuarioOmnivoro@gmail.com");
		usuarioCarnivoro.setRepositorioRecetas(repositorioRecetas);
		Receta recetaCarnivora = new Receta();
		recetaCarnivora.setAutor(usuarioCarnivoro);
		recetaCarnivora.setNombre("Milanesas con papas fritas");
		recetaCarnivora.agregarIngrediente("carne");
		recetaCarnivora.agregarIngrediente("papas");
		recetaCarnivora.agregarIngrediente("huevo");
		recetaCarnivora.setTiposDeComida(TiposDeComidaValidas);
		recetaCarnivora.setProcedimiento("1-Preparar ...");
		recetaCarnivora.setDificultad((byte) 1);
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaCarnivora.setTemporadas(temporadas);
		recetaCarnivora.setCalorias(1400);
		usuarioCarnivoro.agregarReceta(recetaCarnivora);
		usuarioCarnivoro.agregarRestriccion(new Restriccion("diabetico", "azucar"));
		grupoValido.agregarMiembro(usuarioCarnivoro);
		usuarioCarnivoro.compartirReceta(grupoValido, recetaCarnivora);
		
		assertEquals(2,usuarioValido.listarRecetasDisponibles().size());
	}
	
	
	
}
