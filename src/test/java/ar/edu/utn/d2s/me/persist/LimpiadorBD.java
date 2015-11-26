package ar.edu.utn.d2s.me.persist;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

public class LimpiadorBD {
	public void limpiarBD(SessionFactory sessionFactory){
		Session session = sessionFactory.openSession();

		Connection connection = null;
        Statement stmt = null;
        try
        {
            Class.forName("com.mysql.jdbc.Driver");
            connection = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_ddss", "root", "1234");
             
            stmt = (Statement) connection.createStatement();
            stmt.execute("DELETE FROM preferencia");
            stmt.execute("DELETE FROM ingrediente");
            stmt.execute("DELETE FROM tipo_comida");
            stmt.execute("DELETE FROM temporada");

            

        }
        catch (Exception e) {
            e.printStackTrace();
        }finally {
            try {  
                stmt.close();
                connection.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        
        String hql = "delete from Restriccion";
	    session.createQuery(hql).executeUpdate();
	    
	    hql = "delete from Calificacion";
	    session.createQuery(hql).executeUpdate();

	    hql = "delete from Comida";
	    session.createQuery(hql).executeUpdate();

	    hql = "delete from Receta";
	    session.createQuery(hql).executeUpdate();

	    hql = "delete from Usuario";
	    session.createQuery(hql).executeUpdate();

	    hql = "delete from Grupo";
	    session.createQuery(hql).executeUpdate();
	    
	    session.close();
	    
	}
}
