package ar.edu.utn.d2s.me;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

@Entity
public class Comida {
	
	@Id
	@Column(name="ID_Comida")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY) 		
	private Receta receta;
	
	
	private String tipoComida;
	
//	@Column(name="Fecha planificada")
//	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDateTime")
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fecha;
	
	public Comida() {
		// TODO Auto-generated constructor stub
		receta = null;
		tipoComida = null;
		fecha = null;
	}
	
	public Receta getReceta() {
		return receta;
	}
	public void setReceta(Receta receta) {
		this.receta = receta;
	}
	public String getTipoComida() {
		return tipoComida;
	}
	public void setTipoComida(String tipoComida) {
		this.tipoComida = tipoComida;
	}
	public LocalDate getFecha() {
		return fecha;
	}
	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}
	
	
}
