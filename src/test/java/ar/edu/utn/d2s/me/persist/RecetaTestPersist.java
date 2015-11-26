package ar.edu.utn.d2s.me.persist;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import java.util.HashSet;
import java.util.List;



import java.util.Set;

import javax.persistence.EntityManager;

import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.junit.*;


import ar.edu.utn.d2s.me.Receta;
import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;


public class RecetaTestPersist {
	
	//Variables de conexión a la BD
	private static SessionFactory sessionFactory;
	private Session session = null;
	
	@BeforeClass
	public static void setUpClass() {
		
		//Cargar configuracion y e inicializar el objeto conector a la BD (SESSION FACTORY) 
		Configuration cfg = new Configuration().addResource("hibernate.cfg.xml").configure();
		StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
		sessionFactory = cfg.buildSessionFactory(ssrb.build());

	}
	
	//Establece conexion a través del objeto sessionFactory
	@Before
	public void setUp() {
		LimpiadorBD cleanner = new LimpiadorBD();
		cleanner.limpiarBD(sessionFactory);
		session = sessionFactory.openSession();
		

	}
	
	@Test
	public void testRecetaValida() throws Exception{
		//Creo 

		Usuario usuarioValido = new Usuario();
		usuarioValido.setNombre("usarioValido");
		usuarioValido.setMail("usarioValido@gmail.com");
		usuarioValido.setFechaNacimiento(new LocalDate(1989,5,26));
		
		Receta recetaValida = new Receta();
		recetaValida.setNombre("Tarta de acelga");
		recetaValida.agregarIngrediente("acelga");
		recetaValida.agregarIngrediente("huevo");
		recetaValida.agregarIngrediente("papa");
		Set<String> TiposDeComidaValidas = new HashSet<String>();
		TiposDeComidaValidas.add("ALMUERZO");
		TiposDeComidaValidas.add("CENA");
		recetaValida.setTiposDeComida(TiposDeComidaValidas);
		recetaValida.setProcedimiento("1-Preparar ...");
		recetaValida.setDificultad((byte) 1);
		recetaValida.setCalorias(400);
		Set<String> temporadas = new HashSet<String>();
		temporadas.add("primavera");
		temporadas.add("verano");
		temporadas.add("invierno");
		temporadas.add("otoño");		
		recetaValida.setTemporadas(temporadas);
		recetaValida.setAutor(usuarioValido);
		
		//Guardo
		Transaction tx = this.session.beginTransaction();
		this.session.save(recetaValida);//Se indica a hibernate la intencion de persistir al objeto
		tx.commit();//Hibernate ejecuta la persistencia de todos los objetos save-ados
		
		// Cierro y vuelvo a abrir la sesion, asi me aseguro que no tengo la
		// habitacion en la cache de la sesion
		this.session.close();
		this.session = sessionFactory.openSession();
		
		//Consulto
		Criteria recetaCriteria = this.session.createCriteria(Receta.class);
		recetaCriteria.add(Restrictions.eq("nombre", "Tarta de acelga"));
		List<Receta> recetas = recetaCriteria.list();
		assertEquals(1, recetas.size());
		this.session.close();
		
	}
	
}

