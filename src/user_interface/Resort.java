package user_interface;

public class Resort extends Hotel {
	public Resort (String name, String address, double userRating, int officialRating) {
		super(name, address, userRating);
		this.officialRating = officialRating;
	}

	public void addRooms(String roomID, double pricePerNight, String roomType, int guestCapacity) {
		this.Rooms.put(roomID, new Room(roomID, pricePerNight, roomType, guestCapacity));
		if (pricePerNight < this.minimumPrice)
			this.minimumPrice = pricePerNight;
	}
}