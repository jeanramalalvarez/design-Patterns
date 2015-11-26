package ar.edu.utn.d2s.me;

import static org.junit.Assert.*;

import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.Test;

import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;

public class UsuarioTest {
	RepositorioUsuarios repositorioUsuarios = new RepositorioUsuarios();
	Usuario usuarioValido = new Usuario();
	
	@Before
	public void setUp() throws Exception{
		usuarioValido.setMail("mailValido@gmail.com");
		usuarioValido.setNombre("usuarioValido");
		usuarioValido.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioValido.agregarPreferencia("preferencia 1");
		repositorioUsuarios.agregarUsuario(usuarioValido);
	}
	
	//Que no haya 2 usuarios con el mismo e-mail (vamos a tomar que el nombre de usuario es el e-mail)
	@Test(expected = UsuarioInvalidoException.class)
	public void testAgregarUsuarioExistente() throws UsuarioInvalidoException{
		Usuario usuarioRepetido = new Usuario();
		usuarioRepetido.setMail("mailValido@gmail.com");// 2 usuarios son iguales cuando tienen el mismo mail
		repositorioUsuarios.agregarUsuario(usuarioRepetido);
	}
	
	//Nombre asumo que debe ser distinto de NULL
	@Test(expected = UsuarioInvalidoException.class)
	public void testAgregarUsuarioConNombreNull() throws UsuarioInvalidoException{
		Usuario usuarioConNombreInvalido = new Usuario();
		usuarioConNombreInvalido.setMail("usuarioConNombreInvalido");
		usuarioConNombreInvalido.setNombre(null);
		repositorioUsuarios.agregarUsuario(usuarioConNombreInvalido);
	}
	
	//Edad (>18)
	@Test(expected = UsuarioInvalidoException.class)
	public void testAgregarUsuarioMenorDeEdad() throws UsuarioInvalidoException{
		Usuario usuarioMenorDeEdad = new Usuario();
		usuarioMenorDeEdad.setMail("usuarioMenorDeEdad");
		usuarioMenorDeEdad.setNombre("usuarioMenorDeEdad");
		usuarioMenorDeEdad.setFechaNacimiento(new LocalDate(2000, 7, 5));
		repositorioUsuarios.agregarUsuario(usuarioMenorDeEdad);
	}
	
	//Si se agrega una preferencia que ya estaba no debe tener efecto
	@Test
	public void testAgregarPreferenciaDeUsuario(){
		assertFalse (usuarioValido.agregarPreferencia("preferencia 1"));
	}
	
	
	
	
}
