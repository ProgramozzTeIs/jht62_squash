package pti.sb_squash_mvc.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "games")
public class Game {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "user_id1")
	private int userId1;
	
	@Column(name = "user1_points")
	private int user1Points;
	
	@Column(name = "user_id2")
	private int userId2;
	
	@Column(name = "user2_points")
	private int user2Points;
	
	@Column(name = "place_id")
	private int placeId;
	
	@Column(name = "date")
	private LocalDateTime date;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getUserId1() {
		return userId1;
	}
	
	public void setUserId1(int userId1) {
		this.userId1 = userId1;
	}
	
	public int getUser1Points() {
		return user1Points;
	}
	
	public void setUser1Points(int user1Points) {
		this.user1Points = user1Points;
	}
	
	public int getUserId2() {
		return userId2;
	}
	
	public void setUserId2(int userId2) {
		this.userId2 = userId2;
	}
	
	public int getUser2Points() {
		return user2Points;
	}
	
	public void setUser2Points(int user2Points) {
		this.user2Points = user2Points;
	}
	
	public int getPlaceId() {
		return placeId;
	}
	
	public void setPlaceId(int placeId) {
		this.placeId = placeId;
	}
	
	public LocalDateTime getDate() {
		return date;
	}
	
	public void setDate(LocalDateTime date) {
		this.date = date;
	}
}