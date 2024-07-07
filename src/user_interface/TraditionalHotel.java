package user_interface;

public class TraditionalHotel extends Hotel {
	public int floorNum;

	public TraditionalHotel(String name, String address, double userRating, int officialRating, int floorNum) {
		super(name, address, userRating);
		this.officialRating = officialRating;
		this.floorNum = floorNum;
	}

	public void addRooms(String roomID, double pricePerNight, String roomType) {
		this.Rooms.put(roomID, new Room(roomID, pricePerNight, roomType));
		if (pricePerNight < this.minimumPrice)
			this.minimumPrice = pricePerNight;
	}

	// If the entire floor has the same room type
	public void addRooms(int floor, int roomsPerFloor, double pricePerNight, String roomType) {
		for (int i = 1; i <= roomsPerFloor; i++) {
			String roomID = String.valueOf(i);
			while (roomID.length() < String.valueOf(roomsPerFloor).length() - 1) {
				roomID = "0" + roomID;
			}
			roomID = String.valueOf(floor) + roomID;
			this.Rooms.put(roomID, new Room(roomID, pricePerNight, roomType));
		}
		if (pricePerNight < this.minimumPrice)
			this.minimumPrice = pricePerNight;
	}
}
