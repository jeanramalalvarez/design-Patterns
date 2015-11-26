package ar.edu.utn.d2s.me.persist;

import static org.junit.Assert.assertEquals;

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
import org.joda.time.LocalDate;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import ar.edu.utn.d2s.me.Calificacion;
import ar.edu.utn.d2s.me.Comida;
import ar.edu.utn.d2s.me.Grupo;
import ar.edu.utn.d2s.me.Planificador;
import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.RepositorioGrupos;
import ar.edu.utn.d2s.me.RepositorioRecetas;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;

public class ComidaTestPersist {
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
		Receta recetaValida = new Receta();
		Comida comidaValida = new Comida();
		Planificador planificadorComidas = new Planificador();
		Set<String> TiposDeComidaValidas = new HashSet<String>();
		LocalDate fechaValida = new LocalDate();
	
		usuarioValido.setMail("usuarioValido@gmail.com");
		recetaValida.setAutor(usuarioValido);
		recetaValida.setNombre("Tarta de acelga");
		recetaValida.agregarIngrediente("acelga");
		recetaValida.agregarIngrediente("huevo");
		recetaValida.agregarIngrediente("papa");
		TiposDeComidaValidas.add("ALMUERZO");
		TiposDeComidaValidas.add("CENA");
		recetaValida.setTiposDeComida(TiposDeComidaValidas);
		recetaValida.setProcedimiento("1-Preparar ...");
		recetaValida.setDificultad((byte) 1);
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaValida.setTemporadas(temporadas);
		recetaValida.setCalorias(400);
		usuarioValido.agregarReceta(recetaValida);
		usuarioValido.agregarRestriccion(new Restriccion("vegetariano", "carne"));
		usuarioValido.agregarRestriccion(new Restriccion("diabetico", "azucar"));
		comidaValida.setReceta(recetaValida);
		comidaValida.setFecha(fechaValida);
		comidaValida.setTipoComida("ALMUERZO");
		planificadorComidas.planificar(comidaValida, usuarioValido);
		
		//Guardo
		Transaction tx = this.session.beginTransaction();
		// this.session.save(living);
		this.session.save(usuarioValido);
		tx.commit();

		this.session.close();
		this.session = sessionFactory.openSession();
		
		//Consulto
		Criteria comidaCriteria = this.session.createCriteria(Comida.class);
		comidaCriteria.add(Restrictions.eq("tipoComida", "ALMUERZO"));
		List<Comida> comidas = comidaCriteria.list();
		assertEquals(1, comidas.size());
		this.session.close();
	}
}
