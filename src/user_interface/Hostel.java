package user_interface;

public class Hostel extends Hotel {
	public boolean washingMachine;
	public boolean clothesDrier;
	public boolean kitchen;

	public Hostel(String name, String address, double userRating) {
		super(name, address, userRating);
	}

	public void utilitiesUpdate(boolean wifi, boolean parking, boolean restaurant, boolean swimmingpool, boolean spa,
			boolean gym, boolean laundry, boolean smoking, boolean pets, boolean washingMachine, boolean clothesDrier,
			boolean kitchen) {
		super.utilitiesUpdate(wifi, parking, restaurant, swimmingpool, spa, gym, laundry, smoking, pets);
		this.washingMachine = washingMachine;
		this.clothesDrier = clothesDrier;
		this.kitchen = kitchen;
	}

	public void addRooms(String roomID, double pricePerNight, int beds) {
		this.Rooms.put(roomID, new HostelRoom(roomID, pricePerNight, beds));
		if (pricePerNight < this.minimumPrice)
			this.minimumPrice = pricePerNight;
	}
}
