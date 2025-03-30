package pti.sb_squash_mvc.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MatchDto {
	
	private String userOneName;
	private int userOnePoints; 
	private String userTwoName;
	private int userTwoPoints;
	
	private String placeName;
	private String placeAddress;
	private int placePrice;
	private double placePriceEur;
	
	private LocalDateTime gameDate;

	public MatchDto(String userOneName, int userOnePoints, String userTwoName, int userTwoPoints, String placeName,
			String placeAddress, int placePrice, double placePriceEur, LocalDateTime gameDate) {
		super();
		this.userOneName = userOneName;
		this.userOnePoints = userOnePoints;
		this.userTwoName = userTwoName;
		this.userTwoPoints = userTwoPoints;
		this.placeName = placeName;
		this.placeAddress = placeAddress;
		this.placePrice = placePrice;
		this.placePriceEur = placePriceEur;
		this.gameDate = gameDate;
	}

	public String getUserOneName() {
		return userOneName;
	}

	public void setUserOneName(String userOneName) {
		this.userOneName = userOneName;
	}

	public int getUserOnePoints() {
		return userOnePoints;
	}

	public void setUserOnePoints(int userOnePoints) {
		this.userOnePoints = userOnePoints;
	}

	public String getUserTwoName() {
		return userTwoName;
	}

	public void setUserTwoName(String userTwoName) {
		this.userTwoName = userTwoName;
	}

	public int getUserTwoPoints() {
		return userTwoPoints;
	}

	public void setUserTwoPoints(int userTwoPoints) {
		this.userTwoPoints = userTwoPoints;
	}

	public String getPlaceName() {
		return placeName;
	}

	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}

	public String getPlaceAddress() {
		return placeAddress;
	}

	public void setPlaceAddress(String placeAddress) {
		this.placeAddress = placeAddress;
	}

	public int getPlacePrice() {
		return placePrice;
	}

	public void setPlacePrice(int placePrice) {
		this.placePrice = placePrice;
	}

	public double getPlacePriceEur() {
		return placePriceEur;
	}

	public void setPlacePriceEur(double placePriceEur) {
		this.placePriceEur = placePriceEur;
	}

	public LocalDateTime getGameDate() {
		return gameDate;
	}

	public void setGameDate(LocalDateTime gameDate) {
		this.gameDate = gameDate;
	}


}
