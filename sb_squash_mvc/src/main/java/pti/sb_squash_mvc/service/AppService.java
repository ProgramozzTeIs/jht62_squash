package pti.sb_squash_mvc.service;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.database.Database;
import pti.sb_squash_mvc.dto.GameDto;
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
	
	public GameDto showResults() {
			
		GameDto gameDto = null;
		MatchDto matchDto = null;
		List<MatchDto> matches = new ArrayList<>();
		List<UserDto> users = new ArrayList<>();
		List<PlaceDto> placeDtos = new ArrayList<>();
			
		List<Game> games = db.getAllMatches();
		if(games != null) {
			for(int index = 0; index < games.size(); index++) {
				Game currentGame = games.get(index);
					
				User firstUser = db.getUserById(currentGame.getUserId1());
				if(firstUser != null && !users.contains(firstUser.getId())) {
						
					UserDto userDto = new UserDto(firstUser.getId(), firstUser.getName());
					users.add(userDto);
						
				}
				User secondUser = db.getUserById(currentGame.getUserId2());
				if(secondUser != null && !users.contains(secondUser.getId())) {
					UserDto userDto = new UserDto(secondUser.getId(), secondUser.getName());
					users.add(userDto);
				}
					
				Place currentPlace = db.getPlace(currentGame.getPlaceId());
				if(currentPlace != null && !placeDtos.contains(currentPlace)) {
					PlaceDto placeDto = new PlaceDto(currentPlace.getId(), currentPlace.getName());
					placeDtos.add(placeDto);
				}
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String formattedDate= currentGame.getDate().format(formatter);
				
				matchDto = new MatchDto(
						firstUser.getName(),
						currentGame.getUser1Points(),
						secondUser.getName(),
						currentGame.getUser2Points(),
						currentPlace.getName(),
						currentPlace.getAddress(),
						currentPlace.getPrice(),
						0,
						formattedDate);
				matches.add(matchDto);
				
			}
			
			gameDto = new GameDto(1, matches, users, placeDtos);
		}

		return gameDto;
	}

	public GameDto getAllGamesByIds(int userId, int filterNameId) {
		
		GameDto gameDto = null;
		MatchDto matchDto = null;
		
		List <MatchDto> matches = new ArrayList<>();
		List <UserDto> users = new ArrayList<>();
		List <PlaceDto> placeDtos = new ArrayList<>();
		
		User user = db.getUserById(userId);
		
		if (user != null && user.isLoggedIn() == true) {
			
			List <Game> userGames = db.getAllMatchesByNameId(filterNameId);		
			
			for (int index = 0; index < userGames.size(); index ++) {
				
				Game filteredGame = userGames.get(index);			
				User userOne = db.getUserById(filteredGame.getUserId1());
				if (userOne != null) {
					
					UserDto filteredUserOne = new UserDto(userOne.getId(), userOne.getName());
					users.add(filteredUserOne);
				}
				
				User userTwo = db.getUserById(filteredGame.getUserId2());
				if(userTwo != null) {
					
					UserDto filteredUserTwo = new UserDto(userTwo.getId(), userTwo.getName());
					users.add(filteredUserTwo);
				}
				
				Place filteredPlace = db.getPlace(filteredGame.getPlaceId());
				if (filteredPlace != null) {
					
					PlaceDto filteredPlaceDto = new PlaceDto(filteredPlace.getId(), filteredPlace.getName());
					placeDtos.add(filteredPlaceDto);
				}
				
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
				String formattedDate = filteredGame.getDate().format(formatter);
				
				matchDto = new MatchDto(
						userOne.getName(),
						filteredGame.getUser1Points(),
						userTwo.getName(),
						filteredGame.getUser2Points(),
						filteredPlace.getName(),
						filteredPlace.getAddress(),
						filteredPlace.getPrice(),
						0,
						formattedDate		
						);
				
				matches.add(matchDto);
			}
			
			gameDto = new GameDto(filterNameId, matches, users, placeDtos);
		}
		
		return gameDto;
	}

}
