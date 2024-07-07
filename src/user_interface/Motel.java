package user_interface;

public class Motel extends Hotel {
	public Motel(String name, String address, double userRating) {
		super(name, address, userRating);
	}

	// Motels tend to have a single room type of Single for the entire building
	public void addRooms(int numberOfFloors, int roomsPerFloor, double pricePerNight) {
		for (int floor = 1; floor <= numberOfFloors; floor++) {
			for (int i = 1; i <= roomsPerFloor; i++) {
				String roomID = String.valueOf(i);
				while (roomID.length() < String.valueOf(roomsPerFloor).length() - 1) {
					roomID = "0" + roomID;
				}
				roomID = String.valueOf(floor) + roomID;
				this.Rooms.put(roomID, new Room(roomID, pricePerNight, "Single"));
			}
		}
		if (pricePerNight < this.minimumPrice)
			this.minimumPrice = pricePerNight;
	}
}
