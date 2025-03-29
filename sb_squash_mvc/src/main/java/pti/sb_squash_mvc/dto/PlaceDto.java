package pti.sb_squash_mvc.dto;

public class PlaceDto {
	
	private int placeId;
	private String name;
	
	public PlaceDto(int placeId, String name) {
		super();
		this.placeId = placeId;
		this.name = name;
	}

	public int getPlaceId() {
		return placeId;
	}

	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	

}
