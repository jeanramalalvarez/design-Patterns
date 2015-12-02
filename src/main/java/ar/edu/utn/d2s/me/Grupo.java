package ar.edu.utn.d2s.me;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.GenerationType;

import ar.edu.ut.d2s.exceptions.UsuarioExistenteException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;

import javax.persistence.*;
@Entity
public class Grupo {
	
	@Id
	@Column(name = "ID_Grupo")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	
	@ManyToMany(mappedBy = "grupos",cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Usuario> miembros;
	
	private String nombre;
	
	
	@ManyToMany(mappedBy = "grupos", cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Receta> recetasCompartidas;	
	
	
	public Grupo() {
		this.miembros = new HashSet<Usuario>();
		this.nombre = null;
		recetasCompartidas = new HashSet<Receta>();
	}

	public Set<Usuario> getUsuarios() {
		return miembros;
	}

	public void setUsuarios(Set<Usuario> usuarios) {
		this.miembros = usuarios;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Receta> getRecetasCompartidas() {
		return recetasCompartidas;
	}

	public void setRecetasCompartidas(Set<Receta> recetasCompartidas) {
		this.recetasCompartidas = recetasCompartidas;
	}

	@Override
	public String toString() {
		return "Grupo [usuarios=" + miembros + ", nombre=" + nombre + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((nombre == null) ? 0 : nombre.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Grupo other = (Grupo) obj;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	public void agregarMiembro(Usuario usuario) throws UsuarioExistenteException {
		if (!miembros.add(usuario)) {
			throw new UsuarioExistenteException("Error: El grupo ya contiene al miembro: " + usuario.getMail());
		}
		usuario.agregarGrupo(this);
		
	}
	
	public void removerMiembro(Usuario usuario) throws UsuarioInvalidoException{
		if (!miembros.remove(usuario)) {
			throw new UsuarioInvalidoException("Error: no se puede remover a un usuario que no es miembro: ");
		}
		removerRecetasCompartidas(usuario);
	}

	
	private void removerRecetasCompartidas(Usuario usuario) {
		// TODO Auto-generated method stub
		for (Receta recetaUsuario : recetasCompartidas) {
			if (recetaUsuario.getAutor() == usuario) {
				recetasCompartidas.remove(recetaUsuario);				
			}
		}
	}

	public void agregarReceta(Receta recetaUsuario) throws UsuarioInvalidoException{
		if (!miembros.contains(recetaUsuario.getAutor())) {
			throw new UsuarioInvalidoException("Error: el usuario no pertenece al grupo, no puede compartir su receta");
		}
		recetasCompartidas.add(recetaUsuario);
		recetaUsuario.agregarGrupo(this);
	}
	
	
	public int getCantidadMiembros(){
		return this.miembros.size();
	}

	public Set<Receta> getRecetasPermitidas(Set<Restriccion> restricciones) {
		// TODO Auto-generated method stub
		Set<Receta> recetasPermitidas = new HashSet<Receta>();
		for (Receta receta : recetasCompartidas) {
			if (receta.cumpleConRestricciones(restricciones)) {
				recetasPermitidas.add(receta);
			}
		}
		return recetasPermitidas;
	}

	public List<Receta> getRankingRecetas() {
		// TODO Auto-generated method stub
		List sortedList = new ArrayList(recetasCompartidas);
		Collections.sort(sortedList, new OrdenarRecetaPorCalificacionPromedio(this));
		
		return sortedList;
	}
	
	public void removerRecetaCompartida(Receta receta){
		recetasCompartidas.remove(receta);
	}

	public Set<Receta> recetasCompartidas(Usuario usuario) {
		// TODO Auto-generated method stub
		Set<Receta> reporteRecetasCompartidas = new HashSet<Receta>();
		for (Receta receta : recetasCompartidas) {
			if (receta.getAutor().equals(usuario)) {
				reporteRecetasCompartidas.add(receta);
			}
		}
		return reporteRecetasCompartidas;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Set<Usuario> getMiembros() {
		return miembros;
	}

	public void setMiembros(Set<Usuario> miembros) {
		this.miembros = miembros;
	}
	
	

}
