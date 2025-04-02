package pti.sb_squash_mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.database.Database;
import pti.sb_squash_mvc.dto.AdminDto;
import pti.sb_squash_mvc.dto.GameDto;
import pti.sb_squash_mvc.dto.LoginUserDto;
import pti.sb_squash_mvc.dto.MatchDto;
import pti.sb_squash_mvc.dto.PlaceDto;
import pti.sb_squash_mvc.dto.StatusDto;
import pti.sb_squash_mvc.dto.UserDto;
import pti.sb_squash_mvc.model.Game;
import pti.sb_squash_mvc.model.Place;
import pti.sb_squash_mvc.model.User;



@Service
public class AppService {
	
	private Database db;

	@Autowired
	public AppService(Database db) {
		super();
		this.db = db;
	}

	public LoginUserDto logInUser(String userName, String password) {
		
		LoginUserDto lud = null;
		
		User user = db.getUserByName(userName, password); 
		
		if (user != null) {

			lud = new LoginUserDto(user.isFirstLogin(), user.getRole());
			
			user.setLoggedIn(true);
			db.updateUserLogInStatus(user);
		}
		
		
		return lud;
	}
	
	public GameDto showResults(int userId) {
			
		GameDto gameDto = null;
		MatchDto matchDto = null;
		List<MatchDto> matches = new ArrayList<>();
		List<UserDto> users = new ArrayList<>();
		List<PlaceDto> placeDtos = new ArrayList<>();
		
		// TODO Login status check
		
		List<Game> games = db.getAllMatches();
		if(games != null) {
			for(int index = 0; index < games.size(); index++) {
				Game currentGame = games.get(index);
					
				User firstUser = db.getUserById(currentGame.getUserId1());
				if(firstUser != null && !users.contains(firstUser)) { // TODO Create own contains() method, users contains the current user?
						
					UserDto userDto = new UserDto(firstUser.getId(), firstUser.getName());
					users.add(userDto);
						
				}
				User secondUser = db.getUserById(currentGame.getUserId2());
				if(secondUser != null && !users.contains(secondUser)) { // // TODO Create own contains() method, users contains the current user?
					UserDto userDto = new UserDto(secondUser.getId(), secondUser.getName());
					users.add(userDto);
				}
					
				Place currentPlace = db.getPlace(currentGame.getPlaceId());
				if(currentPlace != null && !placeDtos.contains(currentPlace)) { // TODO Create own contains() method, placeDtos contains the current place?
					PlaceDto placeDto = new PlaceDto(currentPlace.getId(), currentPlace.getName());
					placeDtos.add(placeDto);
				}
				
				matchDto = new MatchDto(
						firstUser.getName(),
						currentGame.getUser1Points(),
						secondUser.getName(),
						currentGame.getUser2Points(),
						currentPlace.getName(),
						currentPlace.getAddress(),
						currentPlace.getPrice(),
						0,
						currentGame.getDate());
				matches.add(matchDto);
				
			}
			
			gameDto = new GameDto(userId, matches, users, placeDtos); 
			gameDto.sort(); 
		}

		return gameDto;
	}

	public GameDto getAllGamesByIds(int userId, int filterNameId, int filterPlaceId) {
		
		GameDto gameDto = null;
		MatchDto matchDto = null;
		
		List <MatchDto> matches = new ArrayList<>();
		List <UserDto> users = new ArrayList<>();
		List <PlaceDto> placeDtos = new ArrayList<>();
		
		User user = db.getUserById(userId);
		
		if (user != null && user.isLoggedIn() == true) {
			
			//if(filterPlaceId != 0) {
				
				//userGames = db.getAllMatchesByPlaceId(filterPlaceId);
				
			//} else {
				
			//	userGames = db.getAllMatchesByNameId(filterNameId);
			//}
			
			List <Game> userGames = db.getAllMatches();
			
			for (int index = 0; index < userGames.size(); index ++) {
				
				Game currentGame = userGames.get(index);
				
				User userOne = db.getUserById(currentGame.getUserId1());
				
				if (userOne != null) { 
					
					UserDto userOneDto = new UserDto(userOne.getId(), userOne.getName());
					
					boolean isUserInTheList = false;
					
					for (int usersIndex = 0; usersIndex < users.size(); usersIndex ++) {
						
						UserDto userDto = users.get(usersIndex);
						
						if (userOneDto.getUserId() == userDto.getUserId()) {
							
							isUserInTheList = true;		
						}
					}
					
					if (isUserInTheList != true) {
						
						users.add(userOneDto);
					}
				}
				
				User userTwo = db.getUserById(currentGame.getUserId2());
				
				if (userTwo != null) { 
					
					UserDto userTwoDto = new UserDto(userTwo.getId(), userTwo.getName());
					
					boolean isUserInTheList = false;
					
					for (int usersIndex = 0; usersIndex < users.size(); usersIndex ++) {
						
						UserDto userDto = users.get(usersIndex);
						
						if (userTwoDto.getUserId() == userDto.getUserId()) {
							
							isUserInTheList = true;		
						}
					}
					
					if (isUserInTheList != true) {
						
						users.add(userTwoDto);
					}
				}
				
				Place filteredPlace = db.getPlace(currentGame.getPlaceId());
				if (filteredPlace != null) { // TODO Create own contains() method, placeDtos contains the current place?
						
					PlaceDto filteredPlaceDto = new PlaceDto(filteredPlace.getId(), filteredPlace.getName());
					placeDtos.add(filteredPlaceDto);
				}
			

			// TODO Calculate <users> and <placeDtos> from all games
				
				
			matchDto = new MatchDto(
					userOne.getName(),
					currentGame.getUser1Points(),
					userTwo.getName(),
					currentGame.getUser2Points(),
					filteredPlace.getName(),
					filteredPlace.getAddress(),
					filteredPlace.getPrice(),
					0,
					currentGame.getDate()	
					);
				
			matches.add(matchDto);
			}	
		}
			
			gameDto = new GameDto(filterNameId, matches, users, placeDtos); // TODO Use userId for instantiation
		
		return gameDto;
	}

	public AdminDto registerPlace(int userId, String newPlaceName, int newPlacePrice, String newPlaceAddress) {
		// TODO Auto-generated method stub
		return null;
	}

}
