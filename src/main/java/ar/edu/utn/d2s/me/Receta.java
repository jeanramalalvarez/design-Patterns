package ar.edu.utn.d2s.me;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;

import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;

@Entity
public class Receta {
	
	@Id
	@Column(name = "ID_Receta")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

	@ManyToOne(  fetch=FetchType.EAGER)
	@JoinColumn(name="ID_Usuario") 
	private Usuario usuario;
	
	private String nombre;

	@ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="Tipo_comida", joinColumns=@JoinColumn(name="ID_Receta"))
    @Column(name="tipo_comida")
	private Set<String> tiposDeComida;
	
	@ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="Ingrediente", joinColumns=@JoinColumn(name="ID_Receta"))
    @Column(name="ingrediente")
	private Set<String> ingredientes;
	
    
    private String procedimiento;
    private byte  dificultad;
    
    @ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="Temporada", joinColumns=@JoinColumn(name="ID_Receta"))
    @Column(name="temporada")
    private Set<String> temporadas;
    
    private int calorias;
    
    
    @OneToMany(  fetch=FetchType.EAGER)
    @JoinColumn(name="ID_Receta") 
    private Set<Calificacion> calificaciones;
    
	@ManyToMany(  fetch=FetchType.EAGER)
	@JoinTable(
			name = "RecetaGrupo",		
			joinColumns = {@JoinColumn(name = "ID_Receta")},
			inverseJoinColumns = {@JoinColumn(name =  "ID_Grupo")}
			)
    private Set<Grupo> grupos;
    private Boolean precargada;
	
	public Receta() {
		// TODO Auto-generated constructor stub
		usuario = null;
		nombre = null;
		tiposDeComida = new HashSet<String>();
		ingredientes = new HashSet<String>();
		setProcedimiento(null);
		dificultad = 0;
		setTemporadas(new HashSet<String>());
		setCalorias(0);
		setCalificaciones(new HashSet<Calificacion>());
		grupos = new HashSet<Grupo>();
		setPrecargada(false);
	}
	
	public Usuario getAutor() {
		return usuario;
	}
	public void setAutor(Usuario autor) {
		this.usuario = autor;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public Set<String> getTiposDeComida() {
		return tiposDeComida;
	}
	public void setTiposDeComida(Set<String> tiposDeComida) {
		this.tiposDeComida = tiposDeComida;
	}
	public Set<String> getIngredientes() {
		return ingredientes;
	}
	public void setIngredientes(Set<String> ingredientes) {
		this.ingredientes = ingredientes;
	}
	public String getProcedimiento() {
		return procedimiento;
	}

	public void setProcedimiento(String procedimiento) {
		this.procedimiento = procedimiento;
	}

	public Set<String> getTemporadas() {
		return temporadas;
	}

	public void setTemporadas(Set<String> temporadas) {
		this.temporadas = temporadas;
	}

	public Set<Calificacion> getCalificaciones() {
		return calificaciones;
	}

	public void setCalificaciones(Set<Calificacion> calificaciones) {
		this.calificaciones = calificaciones;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((usuario == null) ? 0 : usuario.hashCode());
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
		Receta other = (Receta) obj;
		if (usuario == null) {
			if (other.usuario != null)
				return false;
		} else if (!usuario.equals(other.usuario))
			return false;
		if (nombre == null) {
			if (other.nombre != null)
				return false;
		} else if (!nombre.equals(other.nombre))
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "RecetaUsuario [autor=" + usuario + ", nombre=" + nombre + "]";
	}
	public boolean cumpleConRestricciones(Set<Restriccion> restricciones) {
		// TODO Auto-generated method stub
		for (Restriccion restriccion : restricciones) {
			if (ingredientes.contains(restriccion.getIngredienteProhibido())) {
				return false;//la receta contiene un ingrediente no permitido
			}
		}
		return  true;
	}
	
	public void agregarIngrediente(String ingrediente){
		ingredientes.add(ingrediente);
	}

	public int getCantidadIngredientes() {
		// TODO Auto-generated method stub
		return ingredientes.size();
	}

	public byte getDificultad() {
		return dificultad;
	}

	public void setDificultad(byte dificultad) {
		this.dificultad = dificultad;
	}

	public boolean esDificultadValida() {
		// TODO Auto-generated method stub
		return (dificultad >= 1 && dificultad <= 5) ?true :false;
	}

	public byte getCantidadTemporadas() {
		// TODO Auto-generated method stub
		return (byte)temporadas.size();
	}

	public int getCalorias() {
		return calorias;
	}

	public void setCalorias(int calorias) {
		this.calorias = calorias;
	}
	
	public void agregarCalificacion(Calificacion calificacion){
		calificaciones.add(calificacion);
	}
	
	public Set<Calificacion> getCalifcaciones(Grupo grupo){
		Set<Calificacion> calificacionesTmp = new HashSet<Calificacion>();
		for (Calificacion calificacion : calificaciones) {
			if (calificacion.getGrupo().equals(grupo)) {
				calificacionesTmp.add(calificacion);
			}
		}
		return calificacionesTmp;
	}
	
	public void modificarCalificacion(Usuario usuario, Grupo grupo, int valor){
		for (Calificacion calificacion : calificaciones) {
			if (calificacion.getGrupo().equals(grupo) && calificacion.getUsuario().equals(usuario)) {
				calificacion.setValor(valor);
				break;
			}
		}
	}
	
	public int getCalificacion(Usuario usuario, Grupo grupo) throws RecetaInvalidaException{
		for (Calificacion calificacion : calificaciones) {
			if (calificacion.getGrupo().equals(grupo) && calificacion.getUsuario().equals(usuario)) {
				return calificacion.getValor();
			}
		}
		throw new RecetaInvalidaException("Error: la receta no tiene aun calificaciones");
	}

	public boolean fuisteCalificada(Usuario usuario, Grupo grupo) {
		// TODO Auto-generated method stub
		for (Calificacion calificacion : calificaciones) {
			if (calificacion.getGrupo().equals(grupo) && calificacion.getUsuario().equals(usuario)) {
				return true;
			}
		}
		return false;
	}

	public int getCalificacionPromedio(Grupo grupo) {
		// TODO Auto-generated method stub
		int acumulador = 0;
		int contador = 0;
		
		for (Calificacion calificacion : calificaciones) {
			if (calificacion.getGrupo().equals(grupo)) {
				acumulador += calificacion.getValor();
				contador++;
			}
		}
		
		return (int)acumulador/contador;		
	}
	
	public Receta clone() {
		
		Receta recetaClonada =  new Receta();
		recetaClonada.setAutor(this.usuario);
		recetaClonada.setCalificaciones(new HashSet<Calificacion>());
		recetaClonada.setCalorias(this.calorias);
		recetaClonada.setDificultad(this.dificultad);
		
		recetaClonada.setIngredientes(new HashSet<String>());
		for (String ingrediente : ingredientes) {
			recetaClonada.agregarIngrediente(ingrediente);
		}
		
		recetaClonada.setNombre(nombre);
		recetaClonada.setProcedimiento(procedimiento);
		
		recetaClonada.setTemporadas(new HashSet<String>());
	    for (String temporada : temporadas) {
			recetaClonada.agregarTemporada(temporada);
		}
	    
	    recetaClonada.setTiposDeComida(new HashSet<String>());
	    for (String tipoComida : tiposDeComida) {
			recetaClonada.agregarTipoComida(tipoComida);
		}
	    
		return recetaClonada;

	}
	
	private void agregarTipoComida(String tipoComida) {
		// TODO Auto-generated method stub
		tiposDeComida.add(tipoComida);
		
	}

	public void agregarTemporada(String temporada){
		temporadas.add(temporada);
	}
	
	public int getPuntuacion(Usuario usuario){
		int puntuacion  = 0;
		//1 por cada vez que una preferencia coincida con un ingrediente
		for (String preferencia : usuario.getPreferencias()) {
			for (String ingrediente : ingredientes) {
				if (ingrediente.equals(preferencia)) {
					puntuacion++;
				}
			}			
		}
		
		//Promedio del número de estrellas que tiene las recetas en cada grupo al que pertenece.
		int acumulador = 0;
		int contador = 0;
		
		for (Grupo grupo : grupos) {
			acumulador += getCalificacionPromedio(grupo);
			contador ++;
		}
		
		return puntuacion + acumulador / contador;
	}

	public void agregarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		grupos.add(grupo);
	}

	public Boolean getPrecargada() {
		return precargada;
	}

	public void setPrecargada(Boolean precargada) {
		this.precargada = precargada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

}
