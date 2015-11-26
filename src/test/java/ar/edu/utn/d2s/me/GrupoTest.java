package ar.edu.utn.d2s.me;

import java.util.HashSet;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

import ar.edu.ut.d2s.exceptions.GrupoInvalidoException;
import ar.edu.ut.d2s.exceptions.UsuarioExistenteException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;
import static org.junit.Assert.*;

public class GrupoTest {
	
	private RepositorioGrupos repositorioGrupos = new RepositorioGrupos();
	private Grupo grupoValido = new Grupo();
	private Usuario usuarioValido = new Usuario();
	private Receta recetaValida = new Receta();
		
	@Before
	public void setUp() throws Exception{
		grupoValido.setNombre("nombreGrupoValido");
		repositorioGrupos.agregarGrupo(grupoValido);
		usuarioValido.setMail("usuarioValido@gmail.com");
		grupoValido.agregarMiembro(usuarioValido);
		
		recetaValida.setAutor(usuarioValido);
		recetaValida.setNombre("Milanesas con papas fritas");
		recetaValida.setProcedimiento("1-Preparar ...");
		recetaValida.setDificultad((byte) 1);
		recetaValida.agregarIngrediente("carne");
		recetaValida.agregarIngrediente("papa");
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaValida.setTemporadas(temporadas);
		recetaValida.setCalorias(1400);
		usuarioValido.agregarReceta(recetaValida);
		usuarioValido.compartirReceta(grupoValido, recetaValida);
	}
	
	@Test (expected = GrupoInvalidoException.class)
	//No puede haber 2 grupos con el mismo nombre
	public void testAgregarGrupoConElMismoNombre() throws GrupoInvalidoException{
		Grupo grupoInvalido = new Grupo();
		grupoInvalido.setNombre("nombreGrupoValido");
		repositorioGrupos.agregarGrupo(grupoInvalido);
	}
	
	@Test
	//Al agregar un usuario: El mismo es miembro del grupo
	public void testAgregarUsuarioAGrupo(){
		assertEquals(1, grupoValido.getCantidadMiembros());
	}
	
	@Test
	//Al agregar un usuario: El grupo es uno de los grupos del usuario
	public void testElGrupoEsUnoDeLosGruposDelUsuario(){
		assertEquals(1, usuarioValido.getCantidadGrupos());
	}
	
	@Test (expected = UsuarioExistenteException.class)
	//Si se agrega 2 veces el mismo usuario debe generarse un error
	public void testAgregarUsuarioExistenteAGrupo() throws UsuarioExistenteException{
		grupoValido.agregarMiembro(usuarioValido);
	}
	
	// El usuario puede compartir recetas al grupo si es miembro
	@Test
	public void testCompartirRecetaUsuarioMiembro() throws GrupoInvalidoException, UsuarioInvalidoException{
		usuarioValido.compartirReceta(grupoValido, recetaValida);
	}
	
	@Test (expected = UsuarioInvalidoException.class)
	public void testCompartirRecetaUsuarioNoMiembro() throws GrupoInvalidoException, UsuarioInvalidoException{
		grupoValido.removerMiembro(usuarioValido);
		usuarioValido.compartirReceta(grupoValido, recetaValida);
	}
	
	//Listar recetas de grupo
	@Test
	public void testListarRecetasDeGrupo() throws GrupoInvalidoException, UsuarioInvalidoException{
		assertEquals(1, grupoValido.getRecetasCompartidas().size());
	}
	
	//Al quitar un miembro: Si se trata de quitar un usuario que no es miembro debe generarse un error
	@Test (expected = UsuarioInvalidoException.class)
	public void testRemoverUsuarioNoMiembroDeGrupo() throws UsuarioInvalidoException{
		Usuario usuarioNoMiembro = new Usuario();
		grupoValido.removerMiembro(usuarioNoMiembro);
	}
	
	//Al quitar un miembro: Las recetas del usuario que compartió ya no deben estar disponibles para el grupo
	public void testAlRemoverMiembroRecetasYaNoDisponiblesParaGrupo() throws UsuarioInvalidoException{
		grupoValido.removerMiembro(usuarioValido);		
		assertEquals(0, grupoValido.getRecetasCompartidas().size());
	}
	
}