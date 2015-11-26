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
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;

public class GrupoTestPersist {
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
		Grupo grupoValido = new Grupo();
		grupoValido.setNombre("nombreGrupoValido");
		Transaction tx = this.session.beginTransaction();
		// this.session.save(living);
		this.session.save(grupoValido);
		tx.commit();

		this.session.close();
		this.session = sessionFactory.openSession();

		//Consulto
		Criteria grupoCriteria = this.session.createCriteria(Grupo.class);
		grupoCriteria.add(Restrictions.eq("nombre", "nombreGrupoValido"));
		List<Grupo> grupos = grupoCriteria.list();
		assertEquals(1, grupos.size());
		this.session.close();
	}
}
