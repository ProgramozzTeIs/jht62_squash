package pti.sb_squash_mvc.dto;

public class LoginUserDto {
	
	private boolean firstLogin;
	private String role;
	
	
	public LoginUserDto(boolean firstLogin, String role) {
		super();
		this.firstLogin = firstLogin;
		this.role = role;
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
	
	
	

}
