package pti.sb_squash_mvc.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_squash_mvc.model.User;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database() {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		this.sessionFactory = cfg.buildSessionFactory();
	}
	
	public User getUserByName(String userName) {
		
		User user = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery <User> query = 
				session.createSelectionQuery("SELECT u FROM User u WHERE u.name = ?1", User.class);
		
		query.setParameter(1, userName);
		
		List <User> users = query.getResultList();
		
		user = users.get(0);
		
		tx.commit();
		session.close();
		
		return user;
	}
	
	

}
