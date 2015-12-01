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
	
	@OneToOne(cascade=CascadeType.PERSIST, fetch=FetchType.EAGER) 		
	private Receta receta;
	
	
	private String tipoComida;

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

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fecha == null) ? 0 : fecha.hashCode());
		result = prime * result + ((tipoComida == null) ? 0 : tipoComida.hashCode());
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
		Comida other = (Comida) obj;
		if (fecha == null) {
			if (other.fecha != null)
				return false;
		} else if (!fecha.equals(other.fecha))
			return false;
		if (tipoComida == null) {
			if (other.tipoComida != null)
				return false;
		} else if (!tipoComida.equals(other.tipoComida))
			return false;
		return true;
	}
	
	
}
