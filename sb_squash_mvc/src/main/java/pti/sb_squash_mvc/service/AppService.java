package pti.sb_squash_mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.database.Database;
import pti.sb_squash_mvc.dto.StatusDto;

@Service
public class AppService {
	
	private Database db;

	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}

	public StatusDto logInUser(String userName, String password) {
		// TODO Auto-generated method stub
		return null;
	}

}
