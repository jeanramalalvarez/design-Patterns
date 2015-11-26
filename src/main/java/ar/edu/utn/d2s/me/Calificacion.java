package ar.edu.utn.d2s.me;

import javax.persistence.*;

@Entity
public class Calificacion {
	
	@Id
	@Column(name="ID_Calificacion")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY) 	
	private Grupo grupo;
	
	@OneToOne(cascade=CascadeType.ALL,fetch=FetchType.LAZY) 		
	private Usuario usuario;
	
	private int valor;
	
	public Calificacion() {
		// TODO Auto-generated constructor stub
		grupo = null;
		usuario = null;
		valor = 0;
	}
	
	@Override
	public String toString() {
		return "Calificacion [grupo=" + grupo + ", usuario=" + usuario
				+ ", valor=" + valor + "]";
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grupo == null) ? 0 : grupo.hashCode());
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Calificacion other = (Calificacion) obj;
		if (grupo == null) {
			if (other.grupo != null)
				return false;
		} else if (!grupo.equals(other.grupo))
			return false;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		return true;
	}
	public Grupo getGrupo() {
		return grupo;
	}
	public void setGrupo(Grupo grupo) {
		this.grupo = grupo;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	public int getValor() {
		return valor;
	}
	public void setValor(int valor) {
		this.valor = valor;
	}
}
