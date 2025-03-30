package pti.sb_squash_mvc.dto;

public class StatusDto {
	
	private boolean error;

	public StatusDto(boolean error) {
		super();
		this.error = error;
	}

	public boolean isError() {
		return error;
	}

	public void setError(boolean error) {
		this.error = error;
	}

	public boolean getError() {
		return false;
	}

	

}
