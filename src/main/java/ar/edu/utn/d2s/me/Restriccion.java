package ar.edu.utn.d2s.me;

import javax.persistence.*;

@Entity
public class Restriccion {
	

	@Id
	@Column(name="ID_Restriccion")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id_Restriccion;

	private String restriccion;
	private String ingredienteProhibido;
	
	public Restriccion() {
		// TODO Auto-generated constructor stub
	}
	
	public Restriccion(String restriccion, String ingredienteProhibido) {
		// TODO Auto-generated constructor stub
		this.restriccion = restriccion;
		this.ingredienteProhibido = ingredienteProhibido;
	}
	
	public int getId_Restriccion() {
		return id_Restriccion;
	}
	public void setId_Restriccion(int id_Restriccion) {
		this.id_Restriccion = id_Restriccion;
	}
	public String getRestriccion() {
		return restriccion;
	}
	public void setRestriccion(String restriccion) {
		this.restriccion = restriccion;
	}
	public String getIngredienteProhibido() {
		return ingredienteProhibido;
	}
	public void setIngredienteProhibido(String ingredienteProhibido) {
		this.ingredienteProhibido = ingredienteProhibido;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((restriccion == null) ? 0 : restriccion.hashCode());
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
		Restriccion other = (Restriccion) obj;
		if (restriccion == null) {
			if (other.restriccion != null)
				return false;
		} else if (!restriccion.equals(other.restriccion))
			return false;
		return true;
	}
	
}
