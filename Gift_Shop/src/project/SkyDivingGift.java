package project;

public class SkyDivingGift extends Gift {
	
	private int height;
	
	private int maxSpeed;

	public SkyDivingGift(int id, String name, double price, int validity, int height, int maxSpeed) {
		super(id, name, price, validity);
		this.height = height;
		this.maxSpeed = maxSpeed;
		this.type = Type.SKYDIVING;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	public int getMaxSpeed() {
		return this.maxSpeed;
	}
	
	public void setMaxSpeed(int maxSpeed) {
		this.maxSpeed = maxSpeed;
	}
	
}
