package ar.edu.utn.d2s.me.persist;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.d2s.me.Calificacion;
import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;

public class CalificacionTestPersist {
	private static SessionFactory sessionFactory;
	private Session session = null;
	
	@BeforeClass
	public static void setUpClass() {

		Configuration cfg = new Configuration().addResource("hibernate.cfg.xml").configure();
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
		sessionFactory = cfg.buildSessionFactory(ssrb.build());

	}
	
	@SuppressWarnings("rawtypes")
	public static <T> List<T> listAndCast(Criteria q) {
		@SuppressWarnings("unchecked")
		List list = q.list();
		return list;
	}
	
	@Before
	public void setUp() {
		LimpiadorBD cleanner = new LimpiadorBD();
		cleanner.limpiarBD(sessionFactory);
		session = sessionFactory.openSession();
		
	}
	
	@Test
	public void test() throws Exception{

		//Guardo 
		Calificacion calificacionValida = new Calificacion();
		
		Grupo grupoValido = new Grupo();
		grupoValido.setNombre("nombreGrupoValido");
		calificacionValida.setGrupo(grupoValido);
		
		Usuario usuarioValido = new Usuario();
		usuarioValido.setMail("mailValido@gmail.com");
		usuarioValido.setNombre("usuarioValido");
		usuarioValido.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioValido.agregarPreferencia("preferencia 1");
		
		Receta recetaValida = new Receta();
		recetaValida.setAutor(usuarioValido);
		recetaValida.setNombre("Tarta de acelga");
		
		calificacionValida.setUsuario(usuarioValido);
		calificacionValida.setValor(1);
		grupoValido.agregarMiembro(usuarioValido);
		usuarioValido.compartirReceta(grupoValido, recetaValida);
		usuarioValido.calificar(recetaValida, calificacionValida);
		
		Transaction tx = this.session.beginTransaction();
		// this.session.save(living);
		this.session.save(recetaValida);
		tx.commit();

		this.session.close();
		this.session = sessionFactory.openSession();

		//Consulto
		Criteria calificacionCriteria = this.session.createCriteria(Calificacion.class);
		calificacionCriteria.add(Restrictions.eq("valor", 1));
		List<Calificacion> calificaciones = calificacionCriteria.list();
		assertEquals(1, calificaciones.size());
		this.session.close();
	}
}
