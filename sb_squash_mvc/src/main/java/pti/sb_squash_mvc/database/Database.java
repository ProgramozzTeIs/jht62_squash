package pti.sb_squash_mvc.database;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

@Repository
public class Database {
	
	private SessionFactory sessionFactory;
	
	public Database() {
		
		Configuration cfg = new Configuration();
		cfg.configure();
		
		this.sessionFactory = cfg.buildSessionFactory();
	}
	
	

}
