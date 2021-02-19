package project;

public class DivingGift extends Gift {

	private int duration;
	
	private int depth;
	
	public DivingGift(int id, String name, double price, int validity, int duration, int depth) {
		super(id, name, price, validity);
		this.duration = duration;
		this.depth = depth;
		this.type = Type.DIVING;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public int getDepth() {
		return depth;
	}

	public void setDepth(int depth) {
		this.depth = depth;
	}
	
}
