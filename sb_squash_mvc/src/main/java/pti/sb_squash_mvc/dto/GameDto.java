package pti.sb_squash_mvc.dto;

import java.time.LocalDateTime;
import java.util.List;

public class GameDto {
	
	private int userId;
	private List<MatchDto> matches;
	private List<UserDto> allUsers;
	private List<PlaceDto> allPlaces;
	
	
	public GameDto(int userId, List<MatchDto> matches, List<UserDto> allUsers, List<PlaceDto> allPlaces) {
		super();
		this.userId = userId;
		this.matches = matches;
		this.allUsers = allUsers;
		this.allPlaces = allPlaces;
	}


	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
		
	public List<MatchDto> getMatches() {
		return matches;
	}
	
	public void setMatches(List<MatchDto> matches) {
		this.matches = matches;
	}
	
	public List<UserDto> getAllUsers() {
		return allUsers;
	}
	
	public void setAllUsers(List<UserDto> allUsers) {
		this.allUsers = allUsers;
	}
	
	public List<PlaceDto> getAllPlaces() {
		return allPlaces;
	}
	
	public void setAllPlaces(List<PlaceDto> allPlaces) {
		this.allPlaces = allPlaces;
	}
	
	public void sort() {
		
		for(int currentIndex = 0; currentIndex < matches.size(); currentIndex++) {
			MatchDto currentMatch = matches.get(currentIndex);
			LocalDateTime currentMatchDate = currentMatch.getGameDate();
			for(int nextIndex = currentIndex + 1; nextIndex < matches.size(); nextIndex++) {
				MatchDto nextMatch = matches.get(nextIndex);
				LocalDateTime nextMatchDate = nextMatch.getGameDate();
				
				if(nextMatchDate.isAfter(currentMatchDate)) {
					matches.set(nextIndex, currentMatch);
					matches.set(currentIndex, nextMatch);
					currentIndex--;
					break;
				}
			}
		}
	}
}