package ar.edu.utn.d2s.me.persist;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.d2s.me.Comida;
import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Planificador;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioGrupos;
import ar.edu.utn.d2s.me.RepositorioRecetas;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;


public class UsuarioTestPersist {
	
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

		Usuario usuarioValido = new Usuario();
		usuarioValido.setMail("mailValido@gmail.com");
		usuarioValido.setNombre("usuarioValido");
		usuarioValido.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioValido.agregarPreferencia("preferencia 1");
		
		//Guardo al Usuario
		Transaction tx = this.session.beginTransaction();
		// this.session.save(living);
		this.session.save(usuarioValido);
		tx.commit();

		this.session.close();
		this.session = sessionFactory.openSession();

		//Consulto
		Criteria usuarioCriteria = this.session.createCriteria(Usuario.class);
		usuarioCriteria.add(Restrictions.eq("mail", "mailValido@gmail.com"));
		List<Usuario> usuarios = usuarioCriteria.list();
		assertEquals(1, usuarios.size());
		this.session.close();
		
	}
	
}
