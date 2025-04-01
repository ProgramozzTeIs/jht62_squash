package pti.sb_squash_mvc.dto;

import java.util.List;

public class AdminDto {
	
	private int userId;
	private List<UserDto> allUsers;
	private List<PlaceDto> allPlaces;
	
	public AdminDto(int userId, List<UserDto> allUsers, List<PlaceDto> allPlaces) {
		super();
		this.userId = userId;
		this.allUsers = allUsers;
		this.allPlaces = allPlaces;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
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
