package pti.sb_squash_mvc.database;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.SelectionQuery;
import org.springframework.stereotype.Repository;

import pti.sb_squash_mvc.model.Game;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.User;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database() {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		this.sessionFactory = cfg.buildSessionFactory();
	}
	
	public User getUserByName(String userName, String password) {
		
		User user = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery <User> query = 
				session.createSelectionQuery("SELECT u FROM User u WHERE u.name = ?1 AND u.password = ?2", User.class); 
		
		query.setParameter(1, userName);
		query.setParameter(2, password);
		
		List <User> users = query.getResultList();
		
		if (users.size() != 0) {
			
			user = users.get(0);
		}
		
		tx.commit();
		session.close();
		
		return user;
	}
	
	public List<Game> getAllMatches() {
		
		List<Game> games = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Game> sq = session.createSelectionQuery(
				"SELECT g FROM Game g", Game.class);
		
		games = sq.getResultList();
		
		tx.commit();
		session.close();
		
		return games;
	}

	public User getUserById(int userId) {
		User user = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		user= session.get(User.class, userId);
		
		tx.commit();
		session.close();
		
		return user;
	}

	public Place getPlace(int placeId) {
		
		Place place = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		place = session.get(Place.class, placeId);
		
		tx.commit();
		session.close();
		
		return place;
	}

	public List <Game> getAllMatchesByNameId(int filterNameId) {
		
		List <Game> games = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery <Game> query = session.createSelectionQuery(
				"SELECT f FROM Game f WHERE f.userId1 = ?1 OR f.userId2 = ?1", Game.class); 
		
		query.setParameter(1, filterNameId);
		
		games = query.getResultList();
		
		tx.commit();
		session.close();
		
		return games;
	}
	
	public List<Game> getAllMatchesByPlaceId(int placeId) {
		
		List<Game> games = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Game> query = session.createSelectionQuery(
				"SELECT p FROM Game p WHERE p.placeId = ?1", Game.class);
		
		query.setParameter(1, placeId);
		
		games = query.getResultList();
		
		tx.commit();
		session.close();
		
		return games;
	}

	public void updateUserLogInStatus(User user) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.merge(user);
		
		tx.commit();
		session.close();
		
	}

	public void saveNewUser(User registerPlayer) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(registerPlayer);
		
		tx.commit();
		session.close();
		
	}

	public List<User> getAllUsers() {
		
		List <User> users = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<User> query = session.createSelectionQuery(
				"SELECT u FROM User u", User.class);
		
		users = query.getResultList();
		
		tx.commit();
		session.close();
		
		return users;
	}

	public List<Place> getAllPlaces() {
		
		List <Place> places = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		SelectionQuery<Place> query = session.createSelectionQuery(
				"SELECT p FROM Place p", Place.class);
		
		places = query.getResultList();
		
		tx.commit();
		session.close();
		
		return places;
	}

	public void saveNewPlace(Place newPlace) {
		
		Session session = sessionFactory.openSession();
		Transaction tx = session.beginTransaction();
		
		session.persist(newPlace);
		
		
		tx.commit();
		session.close();
	}
	
	

}
