package project;

public class BungeeGift extends Gift {
	
	private int height;

	public BungeeGift(int id, String name, double price, int validity, int height) {
		super(id, name, price, validity);
		this.height = height;
		this.type = Type.BUNGEE_JUMPING;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}
	
	

}
