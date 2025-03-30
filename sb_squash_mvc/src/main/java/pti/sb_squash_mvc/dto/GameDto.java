package pti.sb_squash_mvc.dto;

import java.util.List;

public class GameDto {
	
	private int userId;
	private List<MatchDto> maches;
	private List<UserDto> allUsers;
	private List<PlaceDto> allPlaces;
	
	
	public GameDto(int userId, List<MatchDto> maches, List<UserDto> allUsers, List<PlaceDto> allPlaces) {
		super();
		this.userId = userId;
		this.maches = maches;
		this.allUsers = allUsers;
		this.allPlaces = allPlaces;
	}


	public int getUserId() {
		return userId;
	}
	
	public void setUserId(int userId) {
		this.userId = userId;
	}
		
	public List<MatchDto> getMaches() {
		return maches;
	}
	
	public void setMaches(List<MatchDto> maches) {
		this.maches = maches;
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
}