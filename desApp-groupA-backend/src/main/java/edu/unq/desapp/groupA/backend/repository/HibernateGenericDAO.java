package edu.unq.desapp.groupA.backend.repository;

import java.io.Serializable;
import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate4.support.HibernateDaoSupport;

public abstract class HibernateGenericDAO<T> extends HibernateDaoSupport implements GenericRepository<T>, Serializable {

	private static final long serialVersionUID = 2150025908769301782L;
	
	protected Class<T> persistentClass = this.getDomainClass();

	@Autowired
	public void init(SessionFactory factory) {
	    setSessionFactory(factory);
	}

    @SuppressWarnings("unchecked")
    public int count() {
        List<Long> list = (List<Long>) this.getHibernateTemplate()
                .find("select count(*) from " + this.persistentClass.getName() + " o");

        // this.getHibernateTemplate().execute(new HibernateCallback<Car>() {
        //
        // @Override
        // public Car doInHibernate(final Session session) throws
        // HibernateException, SQLException {
        // throw new UnsupportedOperationException();
        // }
        // });
        Long count = list.get(0);
        return count.intValue();

    }

    public void delete(final T entity) {
        this.getHibernateTemplate().delete(entity);
    }

    public void deleteById(final Serializable id) {
        T obj = this.findById(id);
        this.getHibernateTemplate().delete(obj);
    }

    public List<T> findAll() {
        List<T> find = (List<T>) this.getHibernateTemplate().find("from " + this.persistentClass.getName() + " o");
        return find;

    }

    public List<T> findByExample(final T exampleObject) {
        return this.getHibernateTemplate().findByExample(exampleObject);

    }

    public T findById(final Serializable id) {
        return this.getHibernateTemplate().get(this.persistentClass, id);
    }

    protected abstract Class<T> getDomainClass();

    public void save(final T entity) {
        this.getHibernateTemplate().saveOrUpdate(entity);
    }

    public void update(final T entity) {
        this.getHibernateTemplate().update(entity);
    }

}