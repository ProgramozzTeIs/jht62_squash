package pti.sb_squash_mvc.dto;

public class LoginUserDto {
	
	private boolean firstLogin;
	private String role;
	private int userId;
	
	
	public LoginUserDto(boolean firstLogin, String role, int userId) {
		super();
		this.firstLogin = firstLogin;
		this.role = role;
		this.userId = userId;
	}
	
	
	public boolean isFirstLogin() {
		return firstLogin;
	}
	public void setFirstLogin(boolean firstLogin) {
		this.firstLogin = firstLogin;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}


	public int getUserId() {
		return userId;
	}


	public void setUserId(int userId) {
		this.userId = userId;
	}
	
	
	
	
	

}
