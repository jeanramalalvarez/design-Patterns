package ar.edu.utn.d2s.me;

import javax.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.annotations.Type;
import org.joda.time.LocalDate;
import org.joda.time.Years;

import ar.edu.ut.d2s.exceptions.GrupoInvalidoException;
import ar.edu.ut.d2s.exceptions.RecetaInvalidaException;
import ar.edu.ut.d2s.exceptions.UsuarioInvalidoException;

@Entity
public class Usuario {
	
	
	@Id
	@Column(name="ID_Usuario")
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@Type(type="org.jadira.usertype.dateandtime.joda.PersistentLocalDate")
	private LocalDate fechaNacimiento;
	
	private String nombre;
	private String mail;
	
	@ElementCollection(fetch=FetchType.EAGER)
    @CollectionTable(name="Preferencia", joinColumns=@JoinColumn(name="ID_Usuario"))
    @Column(name="preferencia")
	private Set<String> preferencias;		
	
	
	@ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinTable(
			name = "UsuarioGrupo",		
			joinColumns = {@JoinColumn(name = "ID_Usuario")},
			inverseJoinColumns = {@JoinColumn(name =  "ID_Grupo")}
			)
	private Set<Grupo>	 grupos;
	
	@OneToMany (mappedBy = "usuario" , cascade = CascadeType.ALL, fetch=FetchType.EAGER)
	private Set<Receta> recetasPropias;
	
	
    @OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="ID_Usuario")  
	private Set<Comida> comidasPlanificadas;
	
	@OneToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
	@JoinColumn(name="ID_Usuario") 
	private Set<Restriccion> restricciones;
	
	@Transient
	private RepositorioRecetas repositorioRecetas;

	private String password;
	
	public Usuario() {
		// TODO Auto-generated constructor stub
		fechaNacimiento = null;
		nombre = null;
		mail = null;
		preferencias = new HashSet<String>();
		grupos = new HashSet<Grupo>();
		setRecetasPropias(new HashSet<Receta>());
		comidasPlanificadas = new HashSet<Comida>();
		restricciones = new HashSet<Restriccion>();
		repositorioRecetas = null;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public Set<String> getPreferencias() {
		return preferencias;
	}

	public void setPreferencias(Set<String> preferencias) {
		this.preferencias = preferencias;
	}

	public LocalDate getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(LocalDate fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	

	
	
	public Set<Receta> getRecetasPropias() {
		return recetasPropias;
	}

	public void setRecetasPropias(Set<Receta> recetasPropias) {
		this.recetasPropias = recetasPropias;
	}

	public Set<Grupo> getGrupos() {
		return grupos;
	}

	public void setGrupos(Set<Grupo> grupos) {
		this.grupos = grupos;
	}

	public Set<Comida> getComidasPlanificadas() {
		return comidasPlanificadas;
	}

	public void setComidasPlanificadas(Set<Comida> comidasPlanificadas) {
		this.comidasPlanificadas = comidasPlanificadas;
	}

	public RepositorioRecetas getRepositorioRecetas() {
		return repositorioRecetas;
	}

	public void setRepositorioRecetas(RepositorioRecetas repositorioRecetas) {
		this.repositorioRecetas = repositorioRecetas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((mail == null) ? 0 : mail.hashCode());
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
		Usuario other = (Usuario) obj;
		if (mail == null) {
			if (other.mail != null)
				return false;
		} else if (!mail.equals(other.mail))
			return false;
		return true;
	}

	
	
	
	public int getEdad(){
		Years years =  Years.yearsBetween(this.getFechaNacimiento(), new LocalDate());
		return years.getYears();
	}

	public boolean agregarPreferencia(String preferencia) {
		// TODO Auto-generated method stub
		return this.preferencias.add(preferencia);
	}
	
	public int getCantidadGrupos(){
		return grupos.size();
	}

	public void agregarGrupo(Grupo grupo) {
		// TODO Auto-generated method stub
		grupos.add(grupo);
		
	}
	
	public void agregarReceta(Receta recetaUsuario) throws RecetaInvalidaException{
		if (recetaUsuario.getCalorias() == 0) {
			throw new RecetaInvalidaException("Error: la receta debe tener calorias");																
		}
		
		if (recetaUsuario.getCantidadTemporadas() == 0) {
			throw new RecetaInvalidaException("Error: la receta debe tener al menos una temporada");													
		}
		
		if (!recetaUsuario.esDificultadValida()) {
			throw new RecetaInvalidaException("Error: la dificultad debe estar en el rango de 1 a 5");										
		}
		
		if (recetaUsuario.getProcedimiento() == null) {
			throw new RecetaInvalidaException("Error: una receta debe tener procedimiento");									
		}
		
		if (recetaUsuario.getCantidadIngredientes() < 2) {
			throw new RecetaInvalidaException("Error: una receta debe tener 2 o mas ingredientes");						
		}
		
		if (recetaUsuario.getNombre() == null) {
			throw new RecetaInvalidaException("Error: una receta no puede tener nombre NULL");			
		}
		
		if (recetasPropias.contains(recetaUsuario)) {
			 throw new RecetaInvalidaException("Error: receta duplicada");			
		}
		
		if (!(recetaUsuario.getAutor() == this)) {
			throw new RecetaInvalidaException("Error: la receta no pertenece al usuario " + this.getMail() + "sino a" + recetaUsuario.getAutor().getMail());
		}
		
		recetasPropias.add(recetaUsuario);			
	}
	
	public void compartirReceta(Grupo grupo, Receta recetaPropia) throws GrupoInvalidoException, UsuarioInvalidoException{
		if (!grupos.contains(grupo)) {
			throw new GrupoInvalidoException("Error: no se puede compartir una receta en un grupo al que no es miembro");
		}
		grupo.agregarReceta(recetaPropia);
	}

	public void agregarComida(Comida comida) {
		// TODO Auto-generated method stub
		comidasPlanificadas.add(comida);
	}
	
	public boolean removerComida(String tipoComida, LocalDate fecha) {
		// TODO Auto-generated method stub
		for (Comida comida : comidasPlanificadas) {
			if (comida.getTipoComida().equals(tipoComida) && comida.getFecha().isEqual(fecha)) {
			comidasPlanificadas.remove(comida);
				return true;
			}
		}
		return false;
	}
	
	public boolean existeComida(String tipoComida, LocalDate fecha){
		for (Comida comida : comidasPlanificadas) {
			if (comida.getTipoComida().equals(tipoComida) && comida.getFecha().isEqual(fecha)) {
				return true;
			}
		}
		return false;
	}
	
	public Comida getComida(String tipoComida, LocalDate fecha){
		for (Comida comida : comidasPlanificadas) {
			if (comida.getTipoComida().equals(tipoComida) && comida.getFecha().isEqual(fecha)) {
				return comida;
			}
		}
		return null;
	}

	public Set<Restriccion> getRestricciones() {
		return restricciones;
	}

	public void setRestricciones(Set<Restriccion> restricciones) {
		this.restricciones = restricciones;
	}
	
	public void agregarRestriccion(Restriccion restriccion){
		restricciones.add(restriccion);
	}
	
	public Set<Receta> listarRecetasDisponibles(){
		Set<Receta> recetasDisponibles = new HashSet<Receta>();
		recetasDisponibles.addAll(recetasPropias);
		for (Grupo grupo : grupos) {
			recetasDisponibles.addAll(grupo.getRecetasPermitidas(restricciones));
		}
		recetasDisponibles.addAll(repositorioRecetas.getRecetasPermitidas(restricciones));

		return recetasDisponibles;
	}
	
	public void calificar(Receta receta, Calificacion calificacion) throws UsuarioInvalidoException, RecetaInvalidaException{
		if (!esUnoDeMisGrupos(calificacion.getGrupo())) {
			throw new UsuarioInvalidoException("Error: el usuario no puede calificar una receta de un grupo al que no pertenece");
		}
		if (receta.fuisteCalificada(calificacion.getUsuario(), calificacion.getGrupo())) {
			throw new RecetaInvalidaException("Error: la receta ya fue calificada por este usuario");
		}
		
		receta.agregarCalificacion(calificacion);
	}

	public boolean esUnoDeMisGrupos(Grupo grupo) {
		// TODO Auto-generated method stub
		return grupos.contains(grupo);
	}
	
	public Set<Calificacion> listarCalificaciones(Grupo grupo, Receta receta) throws UsuarioInvalidoException{
		if (!esUnoDeMisGrupos(grupo)) {
			throw new UsuarioInvalidoException("Error: el usuario no es miembro del grupo, no se pueden listar las calificaciones de la receta");
		}
		return receta.getCalificaciones();
	}
	
	
	public void modificarCalificacion(Receta receta, Grupo grupo, int valor) throws UsuarioInvalidoException{
		if (!esUnoDeMisGrupos(grupo)) {
			throw new UsuarioInvalidoException("Error: el usuario no es miembro del grupo, no se pueden listar las calificaciones de la receta");
		}
		receta.modificarCalificacion(this, grupo, valor);
	}

	public List<Receta> getRankingRecetas(Grupo grupoValido) {
		// TODO Auto-generated method stub
		return grupoValido.getRankingRecetas();
	}
	
	public void eliminarRecetaPropia(Receta receta){
		for (Grupo grupo : grupos) {
			grupo.removerRecetaCompartida(receta);
		}
		
		recetasPropias.remove(receta);
	}
	
	public void modificarProcedimientoReceta(Receta receta, String nuevoNombreReceta, String nuevoProcedimiento) throws RecetaInvalidaException{
		Receta recetaClonada = receta.clone();
		recetaClonada.setProcedimiento(nuevoProcedimiento);
		recetaClonada.setNombre(nuevoNombreReceta);
		agregarReceta(recetaClonada);
	}
	
	public Receta getReceta(String nombreReceta, Usuario autor){
		for (Receta receta : recetasPropias) {
			if (receta.getAutor().equals(autor) && receta.getNombre().equals(nombreReceta)) {
				return receta;
			}
		}
		
		return null;
	}
	
	public Set<Receta> recetasCompartidasEnGrupos(){
		Set<Receta> recetasCompartidas = new HashSet<Receta>();
		for (Grupo grupo : grupos) {
			recetasCompartidas.addAll(grupo.recetasCompartidas(this));
		}
		return recetasCompartidas;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public Set<Comida> getComidasReplanificables(){
		//todas las comidas 1 semana atras y una semana adelante
		Set<Comida> comidasReplanificables = new HashSet<Comida>();
		Planificador planificador = new Planificador();
		
		for (Comida comida : comidasPlanificadas) {
			if (planificador.esValidaFechaDeComida(comida.getFecha())) {
				comidasReplanificables.add(comida);
			}
		}
		return comidasReplanificables;
	}
}
