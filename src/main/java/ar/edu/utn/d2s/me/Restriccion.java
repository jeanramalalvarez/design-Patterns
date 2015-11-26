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
	
	
	
}
