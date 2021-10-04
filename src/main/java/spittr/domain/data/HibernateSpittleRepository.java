package spittr.domain.data;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.SessionFactory;

import spittr.domain.Spittle;

public class HibernateSpittleRepository {

	private SessionFactory sessionFactory;

	public HibernateSpittleRepository(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	private Session currentSession() {
		return sessionFactory.getCurrentSession();
	}
	
	public long count() {
		return findAll().size();
	}

	public List<Order> findAll() {
		return currentSession().getCriteriaBuilder().createQuery(Spittle.class).getOrderList();
	}
	
	public Spittle findByUsername(String username) {
		return (Spittle) currentSession().getCriteriaBuilder().createQuery(Spittle.class).getOrderList();
	}
	
	
	
}
