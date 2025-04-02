package pti.sb_squash_mvc.dto;

public class PlaceDto {
	
	private int placeId;
	private String name;
	private String address;
	private int price;
	
	public PlaceDto(int placeId, String name) {
		super();
		this.placeId = placeId;
		this.name = name;
	}

	public PlaceDto(int placeId, String name, String address, int price) {
		super();
		this.placeId = placeId;
		this.name = name;
		this.address = address;
		this.price = price;
	}


	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
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
