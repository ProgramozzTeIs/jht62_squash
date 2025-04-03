package pti.sb_squash_mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pti.sb_squash_mvc.database.Database;
import pti.sb_squash_mvc.dto.AdminDto;
import pti.sb_squash_mvc.dto.GameDto;
import pti.sb_squash_mvc.dto.LoginUserDto;
import pti.sb_squash_mvc.dto.MatchDto;
import pti.sb_squash_mvc.dto.PlaceDto;
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

			lud = new LoginUserDto(user.isFirstLogin(), user.getRole(), user.getId());
			
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
		
		User user = db.getUserById(userId);
		if(user.isLoggedIn()) {
		
			List<Game> games = db.getAllMatches();
			if(games != null) {
				for(int index = 0; index < games.size(); index++) {
					Game currentGame = games.get(index);
						
					User firstUser = db.getUserById(currentGame.getUserId1());
					addUserToList(firstUser, users);	
					
					User secondUser = db.getUserById(currentGame.getUserId2());
					addUserToList(secondUser,users);
					
						
					Place currentPlace = db.getPlace(currentGame.getPlaceId());
					
					if(currentPlace != null) { 
						boolean placeExist = false;
						for(int containIndex = 0; containIndex < placeDtos.size(); containIndex++) {
							PlaceDto currentPlaceDto = placeDtos.get(containIndex);
							if(currentPlace.getId() == currentPlaceDto.getPlaceId()) {
								placeExist = true;
								break;
							}
						
						
						}
						if(placeExist == false){
							PlaceDto placeDto = new PlaceDto(currentPlace.getId(), currentPlace.getName());
							placeDtos.add(placeDto);
						}
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
			
			List <Game> userGames = db.getAllMatches();

			if (userGames != null) {

				if(filterPlaceId != 0) {
					
					userGames = db.getAllMatchesByPlaceId(filterPlaceId);
					
				} else if (filterNameId != 0) {
					
					userGames = db.getAllMatchesByNameId(filterNameId);
				} 
				
				for (int index = 0; index < userGames.size(); index++) {
					
					Game currentGame = userGames.get(index);
					
					User firstUser = db.getUserById(currentGame.getUserId1());
					addUserToList(firstUser, users);	
					
					User secondUser = db.getUserById(currentGame.getUserId2());
					addUserToList(secondUser,users);
					
					Place filteredPlace = db.getPlace(currentGame.getPlaceId());
					addPlaceToList(filteredPlace, placeDtos);
					
					matchDto = new MatchDto(
							firstUser.getName(),
							currentGame.getUser1Points(),
							secondUser.getName(),
							currentGame.getUser2Points(),
							filteredPlace.getName(),
							filteredPlace.getAddress(),
							filteredPlace.getPrice(),
							0,
							currentGame.getDate());
					
					matches.add(matchDto);

				}	
				
				gameDto = new GameDto(userId, matches, users, placeDtos);
			}

		}

		
		return gameDto;
	}


	public AdminDto registerPlace(int userId, String newPlaceName, int newPlacePrice, String newPlaceAddress) {
		
		AdminDto adminDto = null;
		
		List<UserDto> userDtos = new ArrayList<>();
		List<PlaceDto> placeDtos = new ArrayList<>();
		
		User user = db.getUserById(userId);
		
		if(user != null && user.getRole().equals("admin") && user.isLoggedIn()) {
			
			Place newPlace = new Place();
			
			newPlace.setName(newPlaceName);
			newPlace.setPrice(newPlacePrice);
			newPlace.setAddress(newPlaceAddress);
			
			db.saveNewPlace(newPlace);
			
			List<User> allUsers = db.getAllUsers();
			List<Place> allPlaces = db.getAllPlaces();
			
			for(int usersIndex = 0; usersIndex < allUsers.size(); usersIndex++) {
				
				User currentUser = allUsers.get(usersIndex);
				UserDto userDto = new UserDto(
							currentUser.getId(),
							currentUser.getName()
						);
				userDtos.add(userDto);
			}
			for(int placesIndex = 0; placesIndex < allPlaces.size(); placesIndex++) {
				
				Place currentPlace = allPlaces.get(placesIndex);
				PlaceDto placeDto = new PlaceDto(
							currentPlace.getId(),
							currentPlace.getName(),
							currentPlace.getAddress(),
							currentPlace.getPrice()
						
						);
				
				placeDtos.add(placeDto);
			}
			adminDto = new AdminDto(
						userId, 
						userDtos, 
						placeDtos
					);
		}
		
		return adminDto;
	}
	
	public AdminDto registerUser(int userId, String userName, String userRole) {
		
		AdminDto adminDto = null;
		
		List <UserDto> userDtos = new ArrayList<>();
		List <PlaceDto> placeDtos = new ArrayList<>();
		
		User user = db.getUserById(userId);
		
		if (user != null && user.getRole().equals("admin") && user.isLoggedIn()) {
			
			if(userRole.equals("admin") || userRole.equals("user")) {
				
				Random random = new Random();
				
				int password = 1000 + random.nextInt(9000);
				
				String generatedPassword = String.valueOf(password);
				
				User registerPlayer = new User();
				
				registerPlayer.setName(userName);
				registerPlayer.setPassword(generatedPassword);
				registerPlayer.setRole(userRole);
				registerPlayer.setFirstLogin(true);
				registerPlayer.setLoggedIn(false);
				
				db.saveNewUser(registerPlayer);
			}

			List <User> allUsers = db.getAllUsers();
			List <Place> allPlaces = db.getAllPlaces();
			
			for(int usersIndex = 0; usersIndex < allUsers.size(); usersIndex ++) {
				
				User currentUser = allUsers.get(usersIndex);
				
				UserDto userDto = new UserDto(currentUser.getId(), currentUser.getName());
				
				userDtos.add(userDto);
			}
			
			for(int placesIndex = 0; placesIndex < allPlaces.size(); placesIndex ++) {
				
				Place currentPlace = allPlaces.get(placesIndex);
				
				PlaceDto placeDto = new PlaceDto(
							currentPlace.getId(),
							currentPlace.getName(),
							currentPlace.getAddress(),
							currentPlace.getPrice()
						);
				
				placeDtos.add(placeDto);
			}
			
			adminDto = new AdminDto(
					userId,
					userDtos,
					placeDtos
					);
		}

		return adminDto;
	}
	
	private void addUserToList(User user, List<UserDto> users) {
		
		if(user != null) {
			boolean userExist = false;
			for(int containIndex = 0; containIndex < users.size(); containIndex++) {
				UserDto userDto = users.get(containIndex);
				if(user.getId() == userDto.getUserId()) {
					userExist = true;
					break;
				}
				
				
			}
			
			if(userExist == false) {
				UserDto newUser = new UserDto(user.getId(), user.getName());
				users.add(newUser);
			}
		
		}
	}

	private void addPlaceToList(Place filteredPlace, List<PlaceDto> placeDtos) {
		
		if(filteredPlace != null) {
			boolean placeExist = false;
			for(int containIndex = 0; containIndex < placeDtos.size(); containIndex++) {
				PlaceDto placeDto = placeDtos.get(containIndex);
				if(filteredPlace.getId() == placeDto.getPlaceId()) {
					placeExist = true;
					break;
				}
			}
			
			if(placeExist == false) {
				PlaceDto newPlace = new PlaceDto(filteredPlace.getId(), filteredPlace.getName());
				placeDtos.add(newPlace);
			}
		}
	}

}
