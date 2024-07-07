package user_interface;

public class Homestay extends Hotel {
	public boolean washingMachine, clothesDrier, kitchen;

	public Homestay(String name, String address, double userRating) {
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

	public void addRooms(String roomID, double pricePerNight, String roomType, int guestCapacity) {
		this.Rooms.put(roomID, new Room(roomID, pricePerNight, roomType, guestCapacity));
		if (pricePerNight < this.minimumPrice)
			this.minimumPrice = pricePerNight;
	}
}
