package ar.edu.utn.d2s.me;

import java.util.HashSet;
import java.util.Set;

import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;

public class RepositorioUsuarios {
	
	private Set<Usuario> usuarios;

	public RepositorioUsuarios() {
		// TODO Auto-generated constructor stub
		usuarios = new HashSet<Usuario>();
	}
	
	
	public Set<Usuario> getUsuarios() {
		return usuarios;
	}
	public void setUsuarios(Set<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	
	
	public void agregarUsuario(Usuario usuario) throws UsuarioInvalidoException{
		if (!this.usuarios.add(usuario)) {
			throw new UsuarioInvalidoException("Error: Ya existe usuario con mail " + usuario.getMail());
		}
		
		if (usuario.getNombre() == null) {
			throw new UsuarioInvalidoException("Error: El nombre de usuario no puede ser null");
		}
		
		if (usuario.getEdad() < 18) {
			throw new UsuarioInvalidoException("Error: El usuario debe tener mas de 18 años, no " + usuario.getEdad() );
		}
	}
}
