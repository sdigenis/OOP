package project;

import java.time.LocalDate;

public class Purchase{

	private String username;
	private LocalDate date;
	private int giftId;
	
	public Purchase(String username, LocalDate date, int giftId) {
		this.username = username;
		this.date = date;
		this.giftId = giftId;
	}
	
	public Purchase(String username, String date, int giftId) {
		this.username = username;
		this.date = LocalDate.parse(date);
		this.giftId = giftId;
	}
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public int getGiftId() {
		return giftId;
	}
	public void setGiftId(int giftId) {
		this.giftId = giftId;
	}
	
	
	
}
