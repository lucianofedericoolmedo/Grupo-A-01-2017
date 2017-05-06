package edu.unq.desapp.groupA.backend.aspect;

import org.aspectj.lang.annotation.Aspect;


@Aspect
public class HibernateOperations {
	
	/*
//    @Around("within(@(@org.springframework.stereotype.Repository *) *)")
//	@Around("execution(* edu.unq.desapp.groupA.backend.repository.*.*(..))")
	@Around("@target(org.springframework.transaction.annotation.Transactional)")
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

*/
}
