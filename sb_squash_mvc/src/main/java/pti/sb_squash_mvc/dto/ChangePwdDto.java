package pti.sb_squash_mvc.dto;

public class ChangePwdDto {
	
	private int userId;
	private String oldPassword;
	
	public ChangePwdDto(int userId, String oldPassword) {
		super();
		this.userId = userId;
		this.oldPassword = oldPassword;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}
	
	

}
