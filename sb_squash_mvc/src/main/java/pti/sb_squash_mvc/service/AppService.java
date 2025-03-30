package pti.sb_squash_mvc.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.database.Database;
import pti.sb_squash_mvc.dto.StatusDto;
import pti.sb_squash_mvc.model.User;

@Service
public class AppService {
	
	private Database db;

	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}

	public StatusDto logInUser(String userName, String password) {
		
		StatusDto statusDto = null;
		
		User user = db.getUserByName(userName);
		
		boolean isError = true;
		
		if (user != null && user.getPassword().equals(password)) {

			isError = false;	

		}
		
		statusDto = new StatusDto(isError);
		
		return statusDto;
	}

}
