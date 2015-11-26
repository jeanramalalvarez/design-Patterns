package ar.edu.utn.d2s.me;

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
import static org.junit.Assert.*;

public class RecetaTest {
	
	private Usuario usuarioValido = new Usuario();
	private Receta recetaValida = new Receta();
	private Set<String> TiposDeComidaValidas = new HashSet<String>();
	private Set<String> temporadas = new HashSet<String>();
	private RepositorioGrupos repositorioGrupos = new RepositorioGrupos();
	private Grupo grupoValido = new Grupo();
	private Grupo grupoValido2 = new Grupo();
	Calificacion calificacionValida = new Calificacion();
	Calificacion calificacionValida2 = new Calificacion();
	private Comida comidaValida = new Comida();
	private Planificador planificadorComidas = new Planificador();
	private LocalDate fechaValida = new LocalDate();
	
	@Before
	public void setUp() throws Exception{
		
		grupoValido.setNombre("nombreGrupoValido");
		repositorioGrupos.agregarGrupo(grupoValido);
		
		grupoValido2.setNombre("nombreGrupoValido2");
		repositorioGrupos.agregarGrupo(grupoValido2);
		
		usuarioValido.setMail("mailValido@gmail.com");
		usuarioValido.setNombre("usuarioValido");
		usuarioValido.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioValido.agregarPreferencia("preferencia 1");
		grupoValido.agregarMiembro(usuarioValido);
		grupoValido2.agregarMiembro(usuarioValido);
		
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
		recetaValida.setCalorias(400);
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaValida.setTemporadas(temporadas);
		usuarioValido.agregarReceta(recetaValida);
		usuarioValido.compartirReceta(grupoValido, recetaValida);
		
		calificacionValida.setGrupo(grupoValido);
		calificacionValida.setUsuario(usuarioValido);
		calificacionValida.setValor(1);
		usuarioValido.calificar(recetaValida, calificacionValida);
		
		calificacionValida2.setGrupo(grupoValido2);
		calificacionValida2.setUsuario(usuarioValido);
		calificacionValida2.setValor(4);
		usuarioValido.calificar(recetaValida, calificacionValida2);
		
		comidaValida.setReceta(recetaValida);
		comidaValida.setFecha(fechaValida);
		comidaValida.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida, usuarioValido);
		
	}
	
	//No debe permitir que existan dos recetas iguales (2 recetas son iguales cuando tiene el mismo
	//nombre y la misma persona)
	@Test (expected = RecetaInvalidaException.class)
	public void testAgregarRecetaRepetida() throws RecetaInvalidaException{
		Receta recetaRepetida = new Receta();
		recetaRepetida.setAutor(usuarioValido);
		recetaRepetida.setNombre("Tarta de acelga");
		recetaRepetida.agregarIngrediente("acelga");
		recetaRepetida.agregarIngrediente("huevo");
		recetaRepetida.agregarIngrediente("papa");
		recetaRepetida.setTiposDeComida(TiposDeComidaValidas);
		recetaRepetida.setProcedimiento("1-Preparar ...");
		usuarioValido.agregarReceta(recetaRepetida);
	}
	
	/*
	 * Validación de datos obligatorios:
		o Nombre de receta
		o Ingredientes (lista de valores) (deben ser 2 o más)
		o Procedimiento (Explicación de la receta)
		o Dificultad de la receta (lista de valores: 1 a 5 estrellas)
		o Temporada recetario
		o Calorías total de la receta
	 * */
	
	//o Nombre de receta
	@Test(expected = RecetaInvalidaException.class)
	public void testAgregarRecetaConNombreNull() throws RecetaInvalidaException{
		Receta recetaNombreNull = new Receta();
		recetaNombreNull.setAutor(usuarioValido);
		recetaNombreNull.setNombre(null);
		recetaNombreNull.agregarIngrediente("acelga");
		recetaNombreNull.agregarIngrediente("huevo");
		recetaNombreNull.agregarIngrediente("papa");
		recetaNombreNull.setTiposDeComida(TiposDeComidaValidas);
		recetaNombreNull.setProcedimiento("1-Preparar ...");
		
		usuarioValido.agregarReceta(recetaNombreNull);
	}
	
	//o Ingredientes (lista de valores) (deben ser 2 o más)
	@Test(expected = RecetaInvalidaException.class) 
	public void testAgregarRecetaConMenosDe2Ingredientes() throws RecetaInvalidaException{
		Receta recetaCon1Ingrediente = new Receta();
		recetaCon1Ingrediente.setAutor(usuarioValido);
		recetaCon1Ingrediente.setNombre("tarta de acelga poor");
		recetaCon1Ingrediente.agregarIngrediente("acelga");
		recetaCon1Ingrediente.setTiposDeComida(TiposDeComidaValidas);
		recetaCon1Ingrediente.setProcedimiento("1-Preparar ...");
		usuarioValido.agregarReceta(recetaCon1Ingrediente);
	}
	
	//o Procedimiento (Explicación de la receta)
	@Test(expected = RecetaInvalidaException.class) 
	public void testAgregarRecetaSinProcedimiento() throws RecetaInvalidaException{
		Receta recetaSinProcedimiento = new Receta();
		recetaSinProcedimiento.setAutor(usuarioValido);
		recetaSinProcedimiento.setNombre("tarta de acelga poor");
		recetaSinProcedimiento.agregarIngrediente("acelga");
		recetaSinProcedimiento.setTiposDeComida(TiposDeComidaValidas);
		recetaSinProcedimiento.setProcedimiento(null);
		
		usuarioValido.agregarReceta(recetaSinProcedimiento);
	}
	
	//o Dificultad de la receta (lista de valores: 1 a 5 estrellas)
	@Test(expected = RecetaInvalidaException.class)
	public void testAgregarRecetaSinDificultad() throws RecetaInvalidaException{
		Receta recetaSinProcedimiento = new Receta();
		recetaSinProcedimiento.setAutor(usuarioValido);
		recetaSinProcedimiento.setNombre("tarta de acelga poor");
		recetaSinProcedimiento.agregarIngrediente("acelga");
		recetaSinProcedimiento.setTiposDeComida(TiposDeComidaValidas);
		recetaSinProcedimiento.setProcedimiento(null);
		recetaSinProcedimiento.setDificultad((byte) 0);
		
		usuarioValido.agregarReceta(recetaSinProcedimiento);
	}
	
	//o Temporada recetario
	@Test(expected = RecetaInvalidaException.class)
	public void testAgregarRecetaSinTemporada() throws RecetaInvalidaException{
		Receta recetaSinTemporada = new Receta();
		recetaSinTemporada.setAutor(usuarioValido);
		recetaSinTemporada.setNombre("tarta de acelga poor");
		recetaSinTemporada.agregarIngrediente("acelga");
		recetaSinTemporada.setTiposDeComida(TiposDeComidaValidas);
		recetaSinTemporada.setProcedimiento(null);
		recetaSinTemporada.setDificultad((byte) 0);
		
		usuarioValido.agregarReceta(recetaSinTemporada);
	}
	
	//o Calorías total de la receta
	@Test (expected = RecetaInvalidaException.class	)
	public void testAgregarRecetaSinCalorias() throws RecetaInvalidaException{
		Receta recetaSinCalorias = new Receta();
		recetaSinCalorias.setAutor(usuarioValido);
		recetaSinCalorias.setNombre("tarta de acelga poor");
		recetaSinCalorias.agregarIngrediente("acelga");
		recetaSinCalorias.agregarIngrediente("huevo");
		recetaSinCalorias.setTiposDeComida(TiposDeComidaValidas);
		recetaSinCalorias.setProcedimiento("1-preparar ...");
		recetaSinCalorias.setDificultad((byte) 1);
		recetaSinCalorias.setCalorias(0);
		recetaSinCalorias.setTemporadas(temporadas);
		usuarioValido.agregarReceta(recetaSinCalorias);
	}
	
	/*
	 * Calificar Receta:
		 No se debe permitir calificar una receta de un grupo al que no se pertenece
		 La calificación de la receta es visible solo para los miembros del grupo (listarCalificacionesReceta)
		 Se puede modificar la calificación de la receta en cualquier momento pero no se debe poder calificar
		más de una vez la misma receta
		 Se debe poder pedir un ranking de recetas por grupo (por promedio)
		 Si un usuario comparte una receta en más de un grupo, las calificaciones no deben mezclarse.
	 * */

	// No se debe permitir calificar una receta de un grupo al que no se pertenece
	@Test(expected = UsuarioInvalidoException.class)
	public void testCalificarRecetaDeUnGrupoAlQueNoSePertenece() throws UsuarioInvalidoException, RecetaInvalidaException{
		Usuario usuarioNoMiembro = new Usuario();
		usuarioNoMiembro.setMail("mailNoMiembro@gmail.com");
		usuarioNoMiembro.setNombre("usuarioNoMiembro");
		usuarioNoMiembro.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioNoMiembro.agregarPreferencia("preferencia 1");
		
		Calificacion calificacion = new Calificacion();
		calificacion.setGrupo(grupoValido);
		calificacion.setUsuario(usuarioNoMiembro);
		calificacion.setValor(1);
		
		usuarioNoMiembro.calificar(recetaValida, calificacion);
	}
	
	@Test
	public void testCalificarRecetaDeUnGrupoAlQueSePertenece() throws UsuarioInvalidoException, UsuarioExistenteException, RecetaInvalidaException{
		Usuario usuarioMiembro = new Usuario();
		usuarioMiembro.setMail("mailMiembro@gmail.com");
		usuarioMiembro.setNombre("usuarioMiembro");
		usuarioMiembro.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioMiembro.agregarPreferencia("preferencia 1");
		
		grupoValido.agregarMiembro(usuarioMiembro);
		
		Calificacion calificacion = new Calificacion();
		calificacion.setGrupo(grupoValido);
		calificacion.setUsuario(usuarioMiembro);
		calificacion.setValor(1);
		
		usuarioMiembro.calificar(recetaValida, calificacion);
		assertEquals(1, recetaValida.getCalificacion(usuarioMiembro, grupoValido));
	}

	// La calificación de la receta es visible solo para los miembros del grupo (listarCalificacionesReceta)
	@Test(expected = UsuarioInvalidoException.class)
	public void testListarCalificacionesUsuarioNoMiembro() throws UsuarioInvalidoException{
		Usuario usuarioMiembro = new Usuario();
		usuarioMiembro.setMail("mailMiembro@gmail.com");
		usuarioMiembro.setNombre("usuarioMiembro");
		usuarioMiembro.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioMiembro.agregarPreferencia("preferencia 1");
		usuarioMiembro.listarCalificaciones(grupoValido, recetaValida);
	}
	
	public void testListarCalificacionesUsuarioMiembro() throws UsuarioInvalidoException{
		assertEquals(1, usuarioValido.listarCalificaciones(grupoValido, recetaValida).size());
	}
	
	//Se puede modificar la calificación de la receta en cualquier momento pero no se debe poder calificar
	//más de una vez la misma receta
	@Test
	public void testModificarCalificacionReceta() throws UsuarioInvalidoException, RecetaInvalidaException{
		usuarioValido.modificarCalificacion(recetaValida, grupoValido, 2);
		assertEquals(2, recetaValida.getCalificacion(usuarioValido, grupoValido));
	}
	
	@Test(expected = RecetaInvalidaException.class)
	public void testVolverACalificarUnaRecetaYaCalificada() throws UsuarioInvalidoException, RecetaInvalidaException{
		Calificacion calificacion = new Calificacion();
		calificacion.setGrupo(grupoValido);
		calificacion.setUsuario(usuarioValido);
		calificacion.setValor(1);
		
		usuarioValido.calificar(recetaValida, calificacion);
	}
	
	//Se debe poder pedir un ranking de recetas por grupo (por promedio)
	@Test
	public void testObtenerRankingDeRecetasPorGrupo() throws UsuarioExistenteException, UsuarioInvalidoException, RecetaInvalidaException, GrupoInvalidoException{
		Usuario usuarioMiembro = new Usuario();
		usuarioMiembro.setMail("mailMiembro@gmail.com");
		usuarioMiembro.setNombre("usuarioMiembro");
		usuarioMiembro.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioMiembro.agregarPreferencia("preferencia 1");
		
		grupoValido.agregarMiembro(usuarioMiembro);
		
		Calificacion calificacion = new Calificacion();
		calificacion.setGrupo(grupoValido);
		calificacion.setUsuario(usuarioMiembro);
		calificacion.setValor(1);
		
		usuarioMiembro.calificar(recetaValida, calificacion);
		
		
		Usuario usuarioMiembro2 = new Usuario();
		usuarioMiembro2.setMail("mailMiembro2@gmail.com");
		usuarioMiembro2.setNombre("usuarioMiembro2");
		usuarioMiembro2.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioMiembro2.agregarPreferencia("preferencia 1");
				
		Receta recetaValida2 = new Receta();
		recetaValida2.setAutor(usuarioMiembro2);
		recetaValida2.setNombre("Tarta de lechuga");
		recetaValida2.agregarIngrediente("lechuga");
		recetaValida2.agregarIngrediente("huevo");
		recetaValida2.agregarIngrediente("papa");
		recetaValida2.setTiposDeComida(TiposDeComidaValidas);
		recetaValida2.setProcedimiento("1-Preparar ...");
		recetaValida2.setDificultad((byte) 1);
		recetaValida2.setCalorias(400);
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaValida2.setTemporadas(temporadas);
		usuarioMiembro2.agregarReceta(recetaValida2);
		
		grupoValido.agregarMiembro(usuarioMiembro2);
		usuarioMiembro2.compartirReceta(grupoValido, recetaValida2);
		
		Calificacion calificacion2 = new Calificacion();
		calificacion2.setGrupo(grupoValido);
		calificacion2.setUsuario(usuarioMiembro2);
		calificacion2.setValor(4);
				
		usuarioMiembro2.calificar(recetaValida2, calificacion2);

		
		assertEquals(recetaValida2, usuarioValido.getRankingRecetas(grupoValido).iterator().next());
		
	}
	
	//Si un usuario comparte una receta en más de un grupo, las calificaciones no deben mezclarse.
	@Test 
	public void testGetCalificacionRecetaValidaEnGrupo1() throws RecetaInvalidaException{
		assertEquals(1, recetaValida.getCalificacion(usuarioValido, grupoValido));
	}
	
	@Test 
	public void testGetCalificacionRecetaValidaEnGrupo2() throws RecetaInvalidaException{
		assertEquals(4, recetaValida.getCalificacion(usuarioValido, grupoValido2));
	}
	
	//Modificar o Eliminar Receta
	//Eliminar una receta no debe influir en los historiales de los usuarios
	@Test
	public void testEliminarRecetaNoAfectaAlHistorial(){
		usuarioValido.eliminarRecetaPropia(recetaValida);
		assertEquals(recetaValida, planificadorComidas.consultarPlanificacion("ALMUERZO", fechaValida, usuarioValido));
		
	}
	
	//Si un usuario se borra de un grupo, no debe influir en el historial de los usuarios
	@Test
	public void testRemoverMiembroNoInfluyeAlHistorialDeOtrosMiembros() throws UsuarioExistenteException, FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException, UsuarioInvalidoException{
		Usuario usuarioMiembro = new Usuario();
		usuarioMiembro.setMail("mailMiembro@gmail.com");
		usuarioMiembro.setNombre("usuarioMiembro");
		usuarioMiembro.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioMiembro.agregarPreferencia("preferencia 1");
		
		grupoValido.agregarMiembro(usuarioMiembro);
		
		planificadorComidas.planificar(comidaValida, usuarioMiembro);
		
		grupoValido.removerMiembro(usuarioValido);
		
		assertEquals(recetaValida, planificadorComidas.consultarPlanificacion("ALMUERZO", fechaValida, usuarioMiembro));
		
	}
	
	//Al modificar una receta no se deben presentar cambios en el historial (Ej: Si se cambian los
	//ingredientes y/o las calorías)
	@Test
	public void testModificarProcedimientoNoAfectaPlanificacionOtrosMiembros() throws UsuarioExistenteException, FechaFueraFueraDeRangoException, ComidaInvalidaInvaliException, RecetaInvalidaException{
		Usuario usuarioMiembro = new Usuario();
		usuarioMiembro.setMail("mailMiembro@gmail.com");
		usuarioMiembro.setNombre("usuarioMiembro");
		usuarioMiembro.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioMiembro.agregarPreferencia("preferencia 1");
		
		grupoValido.agregarMiembro(usuarioMiembro);
		
		planificadorComidas.planificar(comidaValida, usuarioMiembro);
		
		usuarioValido.modificarProcedimientoReceta(recetaValida, "tarta de acelga cool", "1.Preparacion super cool...");
		
		assertEquals(recetaValida, planificadorComidas.consultarPlanificacion("ALMUERZO", fechaValida, usuarioMiembro));
		
	}
	
}
