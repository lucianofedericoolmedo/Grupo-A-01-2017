package edu.unq.desapp.groupA.backend.repository;

import org.springframework.stereotype.Repository;

import edu.unq.desapp.groupA.backend.model.PersistenceEntity;

@SuppressWarnings("unchecked")
@Repository
public abstract class HibernateGenericDAOTwo<T extends PersistenceEntity> {

	/*
    protected Class<T> persistentClass = this.getDomainClass();

    public SessionFactory getSessionFactory() {
    	return HibernateUtil.getSessionFactory();
    }
    
    public Session getSession() {
    	return getSessionFactory().getCurrentSession();
    }

    public int count() {
        Session session = null;
        List<Integer> find = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            find = (List<Integer>) session.createQuery("select count(*) from " + this.persistentClass.getName() + " o");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return find.get(0);

    }

    public void delete(final T entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public void delete(Long id) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            T entity = (T) session.get(getDomainClass(), id);
            session.delete(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public List<T> findAll() {
        Session session = null;
        List<T> find = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            find = (List<T>) session.createQuery("from " + this.persistentClass.getName() + " o");
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return find;
    }

    public T find(Long id) {

    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	T entity = (T) session.get(getDomainClass(), id);
    	return entity;
    	
    	/*
        Session session = null;
        T entity = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            entity =  (T) session.get(getDomainClass(), id);
            Hibernate.initialize(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }

    protected abstract Class<T> getDomainClass();

    public T save(final T entity) {
    	
    	Session session = HibernateUtil.getSessionFactory().getCurrentSession();
    	session.save(entity);
    	return entity;
    	
    	/*
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.save(entity);
            Hibernate.initialize(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return entity;
    }

    public void update(final T entity) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            session.update(entity);
            Hibernate.initialize(entity);
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    */

}
