package ar.edu.utn.d2s.me;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import ar.edu.ut.d2s.exceptions.ComidaInvalidaInvaliException;
import ar.edu.ut.d2s.exceptions.FechaFueraFueraDeRangoException;

public class ReportesTest {
	private Usuario usuarioValido = new Usuario();
	private Receta recetaValida = new Receta();
	private Receta recetaValida2 = new Receta();	
	private Comida comidaValida = new Comida();
	private Planificador planificadorComidas = new Planificador();
	private Set<String> TiposDeComidaValidas = new HashSet<String>();
	private LocalDate fechaValida = new LocalDate();
	private RepositorioRecetas repositorioRecetas = new RepositorioRecetas(); 
	private Grupo grupoValido = new Grupo();
	private Grupo grupoValido2 = new Grupo();
	private RepositorioGrupos repositorioGrupos = new RepositorioGrupos();
	private GeneradorReportes generadorReportes = new GeneradorReportes();
	
	
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
		
		recetaValida2.setAutor(usuarioValido);
		recetaValida2.setNombre("Tarta de acelga heavy");
		recetaValida2.agregarIngrediente("acelga");
		recetaValida2.agregarIngrediente("huevo");
		recetaValida2.agregarIngrediente("papa");
		recetaValida2.agregarIngrediente("aceite");
		recetaValida2.setTiposDeComida(TiposDeComidaValidas);
		recetaValida2.setProcedimiento("1-Preparar ...");
		recetaValida2.setDificultad((byte) 1);
		recetaValida2.setTemporadas(temporadas);
		recetaValida2.setCalorias(600);
		usuarioValido.agregarReceta(recetaValida2);
		usuarioValido.agregarRestriccion(new Restriccion("vegetariano", "carne"));
		usuarioValido.agregarRestriccion(new Restriccion("diabetico", "azucar"));
		
		grupoValido.setNombre("gurpoValido");
		grupoValido2.setNombre("gurpoValido2");
		repositorioGrupos.agregarGrupo(grupoValido);
		repositorioGrupos.agregarGrupo(grupoValido2);		
		grupoValido.agregarMiembro(usuarioValido);
		grupoValido2.agregarMiembro(usuarioValido);		
		usuarioValido.compartirReceta(grupoValido, recetaValida);
		usuarioValido.compartirReceta(grupoValido2, recetaValida2);
		
		
		
		
	}
	
	//Comidas planificadas segun rango de fechas
	@Test
	public void testGenerarReporteComidasPlanificadasSegunRangoFechas() throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		//Se planifican varias comidas
		Comida comidaValida2 = new Comida();		
		comidaValida2.setReceta(recetaValida);
		comidaValida2.setFecha(fechaValida.plusDays(2));
		comidaValida2.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida2, usuarioValido);
		
		Comida comidaValida3 = new Comida();		
		comidaValida3.setReceta(recetaValida);
		comidaValida3.setFecha(fechaValida.minusDays(2));
		comidaValida3.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida3, usuarioValido);
		
		CriterioReportesStrategy criterioReportes = new CriterioRangoFechasStrategy();
		((CriterioRangoFechasStrategy)criterioReportes).setFechaInicio(fechaValida.minusDays(3));
		((CriterioRangoFechasStrategy)criterioReportes).setFechaFin(fechaValida.plusDays(3));
		
		generadorReportes.setCriterioReportes(criterioReportes);
		assertEquals(3, generadorReportes.generarReporte(usuarioValido).size());
		
	}
	
	//Comidas planificadas segun nombre ingrediente
	@Test
	public void testGenerarReporteComidasPlanificadasSegunRangoSiContieneIngrediente() throws FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException{
		//Se planifican varias comidas
		Comida comidaValida2 = new Comida();		
		comidaValida2.setReceta(recetaValida);
		comidaValida2.setFecha(fechaValida.plusDays(2));
		comidaValida2.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida2, usuarioValido);
		
		Comida comidaValida3 = new Comida();		
		comidaValida3.setReceta(recetaValida);
		comidaValida3.setFecha(fechaValida.minusDays(2));
		comidaValida3.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida3, usuarioValido);
		
		CriterioReportesStrategy criterioReportes = new CriterioIgredienteStrategy();
		((CriterioIgredienteStrategy)criterioReportes).setIngrediente("acelga");
		generadorReportes.setCriterioReportes(criterioReportes);
		assertEquals(3, generadorReportes.generarReporte(usuarioValido).size());
		
	}
	
	//Recetas nuevas propuestas por los usuarios.
	//Se genera un reporte con todas las recetas compartidas por un usuario en todos sus grupos
	@Test
	public void testGenerarReporteRecetasCompartidasEnGrupos(){
		assertEquals(2, usuarioValido.recetasCompartidasEnGrupos().size());
	}
	

	
}
