package edu.unq.desapp.groupA.backend.repository;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import edu.unq.desapp.groupA.backend.model.Brand;

public class HibernateUtil {

	 private static SessionFactory sessionFactory;
	    private static ServiceRegistry serviceRegistry;

	    public static SessionFactory buildSessionFactory() {
	        try {
	            // Create the SessionFactory from hibernate.cfg.xml
	            Configuration configuration = new Configuration();
	            configuration.configure();
	            serviceRegistry = new ServiceRegistryBuilder().applySettings(configuration.getProperties()).buildServiceRegistry();     
	            return new Configuration().configure().buildSessionFactory(serviceRegistry);
	        } catch (Throwable ex) {
	            // Make sure you log the exception, as it might be swallowed
	            System.err.println("Initial SessionFactory creation failed." + ex);
	            throw new ExceptionInInitializerError(ex);
	        }
	    }

	    public static SessionFactory getSessionFactory() {
	        sessionFactory = buildSessionFactory();
	        return sessionFactory;
	    }
}
