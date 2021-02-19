package project;

public abstract class Gift {

	private int id;
	private String name;
	protected Type type;
	private double price;
	private int validity;

	public Gift(int id, String name, double price, int validity) {
		this.name = name;
		this.id = id;
		this.price = price;
		this.validity = validity;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getValidity() {
		return validity;
	}

	public void setValidity(int validity) {
		this.validity = validity;
	}

	public Type getType() {
		return this.type;
	}

	public enum Type {
      BUNGEE_JUMPING("Bungee jumping"), DRIFTING_LESSON("Drifting"), DIVING("Diving"), SKYDIVING(
          "Skydiving");
		private String value;

		Type(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}

	}

	@Override
	public String toString() {
		return "Gift [name= " + name + ", id= " + id + ", price= " + price + ", validity= " + validity + "]";
	}

}
