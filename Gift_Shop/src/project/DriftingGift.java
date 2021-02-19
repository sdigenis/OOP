package project;

public class DriftingGift extends Gift {

	private int duration;
	
	private String carBrand;
	
	private int horsePower;
	
	public DriftingGift(int id, String name, double price, int validity, int duration, String carBrand, int horsePower) {
		super(id, name, price, validity);
		this.duration = duration;
		this.carBrand = carBrand;
		this.horsePower = horsePower;
		this.type = Type.DRIFTING_LESSON;
	}

	public int getDuration() {
		return duration;
	}

	public void setDuration(int duration) {
		this.duration = duration;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public int getHorsePower() {
		return horsePower;
	}

	public void setHorsePower(int horsePower) {
		this.horsePower = horsePower;
	}
	
	
}
