package project;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.time.LocalDate;
import java.util.LinkedList;
import java.util.List;
import project.Gift.Type;

public class Shop {

	private List<Gift> gifts;
	private List<Purchase> purchases;
	private List<Customer> customers;
	private String pathToGiftsFile;
	private String pathToPurchasesFile;
	private String pathToCustomersFile;
  private String username;

	public Shop(String pathToGifts, String pathToPurchases, String pathToCustomers) {
		this.pathToGiftsFile = pathToGifts;
		this.pathToPurchasesFile = pathToPurchases;
		this.pathToCustomersFile = pathToCustomers;
		loadGifts(pathToGifts);
		loadCustomers(pathToCustomers);
		loadPurchases(pathToPurchases);
	}

    public boolean login(String username) {
      for (Customer c : customers) {
        if (c.getUsername().equals(username)) {
          this.setUsername(username);
          return true;
        }
      }
      return false;
    }

	public void makePurchase(String username, int giftId, LocalDate date) {
		try {
			String line = username + "," + giftId + "," + date.toString() + "\n";
			Files.write(Paths.get(pathToPurchasesFile), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		purchases.add(new Purchase(username, date, giftId));
	}

	public void addCustomer(String username, String fullName, String email) {
		try {
			String line = username + "," + fullName + "," + email + "\n";
			Files.write(Paths.get(pathToCustomersFile), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
		customers.add(new Customer(username, fullName, email));
	}

	public void addBungeeGift(int id, String name, double price, int validity, int height) {
		try {
			String line = id + "," + name + "," + price + "," + validity + "," + Type.BUNGEE_JUMPING.getValue() + "," + height + "\n";
			Files.write(Paths.get(pathToGiftsFile), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addDivingGift(int id, String name, double price, int validity, int duration, int depth) {
		try {
			String line = id + "," + name + "," + price + "," + validity + "," + Type.DIVING.getValue() + "," + duration + "," + depth + "\n";
			Files.write(Paths.get(pathToGiftsFile), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addDriftingGift(int id, String name, double price, int validity, int duration, String carBrand, int horsePower) {
		try {
			String line = id + "," + name + "," + price + "," + validity + "," + Type.DRIFTING_LESSON.getValue() + "," + duration + "," + carBrand + "," + horsePower + "\n";
			Files.write(Paths.get(pathToGiftsFile), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void addSkyDivingGift(int id, String name, double price, int validity, int height, int maxSpeed) {
		try {
			String line = id + "," + name + "," + price + "," + validity + "," + Type.SKYDIVING.getValue() + "," + height + "," + maxSpeed + "\n";
			Files.write(Paths.get(pathToGiftsFile), line.getBytes(), StandardOpenOption.APPEND);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public List<Gift> getGifts() {
		return gifts;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public List<Customer> getCustomers() {
		return customers;
	}

	private void loadGifts(String path) {
		gifts = new LinkedList<Gift>();
		try {
			int id;
			String name;
			double price;
			int validity;
			Type type;
			int height;
			int duration;
			int depth;
			String carBrand;
			int horsePower;
			int maxSpeed;
			for (String line : Files.readAllLines(Paths.get(path))) {
				String[] data = line.split(",");
				id = Integer.valueOf(data[0]);
				name = data[1];
				price = Double.valueOf(data[2]);
				validity = Integer.valueOf(data[3]);
				type = getTypeFromString(data[4]);
				switch (type) {
				case BUNGEE_JUMPING:
					height = Integer.valueOf(data[5]);
					gifts.add(new BungeeGift(id, name, price, validity, height));
					break;
				case DIVING:
					duration = Integer.valueOf(data[5]);
					depth = Integer.valueOf(data[6]);
					gifts.add(new DivingGift(id, name, price, validity, duration, depth));
					break;
				case DRIFTING_LESSON:
					duration = Integer.valueOf(data[5]);
					carBrand = data[6];
					horsePower = Integer.valueOf(data[7]);
					gifts.add(new DriftingGift(id, name, price, validity, duration, carBrand, horsePower));
					break;
				case SKYDIVING:
					height = Integer.valueOf(data[5]);
					maxSpeed = Integer.valueOf(data[6]);
					gifts.add(new SkyDivingGift(id, name, price, validity, height, maxSpeed));
					break;
				}
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadCustomers(String path) {
      customers = new LinkedList<Customer>();
		try {
			String username;
			String fullName;
			String email;
			for (String line : Files.readAllLines(Paths.get(path))) {
				String[] data = line.split(",");
				username = data[0];
				fullName = data[1];
				email = data[2];
				customers.add(new Customer(username, fullName, email));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private void loadPurchases(String path) {
      purchases = new LinkedList<Purchase>();
		try {
          for (String line : Files.readAllLines(Paths.get(path))) {
				String[] data = line.split(",");
				String username = data[0];
				int giftId = Integer.valueOf(data[1]);
				String date = data[2];
				purchases.add(new Purchase(username, date, giftId));
			}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Type getTypeFromString(String value) {
		for (Type t : Type.values()) {
			if (value.equals(t.getValue())) {
				return t;
			}
		}
		return null;
	}

    public String getUsername() {
      return username;
    }

    public void setUsername(String username) {
      this.username = username;
    }

}
