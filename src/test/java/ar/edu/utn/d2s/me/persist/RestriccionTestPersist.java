package ar.edu.utn.d2s.me.persist;

import static org.junit.Assert.assertEquals;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.List;






import org.hibernate.*;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Restrictions;
import org.joda.time.LocalDate;
import org.junit.*;

import ar.edu.utn.d2s.me.Restriccion;
import ar.edu.utn.d2s.me.Usuario;


public class RestriccionTestPersist {
	
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
	public void testRestriccionValida() {
		//Creo 
		Restriccion restriccionValida = new Restriccion("diabetico", "azucar");
		Usuario usuarioValido = new Usuario();
		usuarioValido.setMail("mailValido@gmail.com");
		usuarioValido.setNombre("usuarioValido");
		usuarioValido.setFechaNacimiento(new LocalDate(1989, 5, 26));
		usuarioValido.agregarPreferencia("preferencia 1");
		usuarioValido.agregarRestriccion(restriccionValida);
		
		//Guardo
		Transaction tx = this.session.beginTransaction();
		this.session.save(usuarioValido);//Se indica a hibernate la intencion de persistir al objeto
		tx.commit();//Hibernate ejecuta la persistencia de todos los objetos save-ados
		
		// Cierro y vuelvo a abrir la sesion, asi me aseguro que no tengo la
		// habitacion en la cache de la sesion
		this.session.close();
		this.session = sessionFactory.openSession();
		
		//Consulto
		Criteria restriccionCriteria = this.session.createCriteria(Restriccion.class);
		restriccionCriteria.add(Restrictions.eq("restriccion", "diabetico"));
		List<Restriccion> restricciones = restriccionCriteria.list();
		assertEquals(1, restricciones.size());
		this.session.close();
		
	}
	
}
