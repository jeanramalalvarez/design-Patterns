package ar.edu.utn.d2s.hibernate;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
 
public class HibernateUtil {
 
    private static SessionFactory sessionFactory = null;
    
 
    public static SessionFactory getSessionFactory() {
        if(sessionFactory == null) {
        	Configuration cfg = new Configuration().addResource("hibernate.cfg.xml").configure();
        	StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(cfg.getProperties());
        	sessionFactory = cfg.buildSessionFactory(ssrb.build());
        }
        return sessionFactory;
    }
 
    public static void setSessionFactory(SessionFactory sessionFactory) {
        HibernateUtil.sessionFactory = sessionFactory;
    }

    
    
}