package edu.unq.desapp.groupA.backend.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.hibernate.Session;

import edu.unq.desapp.groupA.backend.repository.HibernateUtil;


@Aspect
public class HibernateOperations {
	
    @Around("within(@(@org.springframework.stereotype.Repository *) *)")
    public Object executeInSession(final ProceedingJoinPoint pjp) throws Throwable {

    	System.out.println("Before");
    	
        Session session = null;
        Object entity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();

            entity = pjp.proceed();

            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
        	
        	System.out.println("After");
        	
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }

}
