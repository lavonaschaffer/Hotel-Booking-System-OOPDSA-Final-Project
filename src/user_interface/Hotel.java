package user_interface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

abstract public class Hotel implements Comparable<Hotel> {
	protected Map<String, Room> Rooms;
	public String name;
	public String address, street, city, country, postalCode;
	public boolean wifi, parking, restaurant, swimmingpool, spa, gym, laundry, smoking, pets;
	public boolean breakfast, lunch, dinner;
	public boolean reservation;
	public double cancellationFeesPercentage;
	public double userRating;
	public int officialRating = 0;
	public double minimumPrice;

	public static boolean isNumeric(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	Hotel(String name, String address, double userRating) {
		this.name = name;
		this.address = address;
		String[] info = address.split(", ");
		int i = info.length - 1;
		if (isNumeric(info[i])) {
			postalCode = info[i--];
		}
		this.country = info[i--];
		this.city = info[i--];
		this.userRating = userRating;
		this.minimumPrice = Double.MAX_VALUE;
		this.Rooms = new HashMap<String, Room>();
	}

	public void utilitiesUpdate(boolean wifi, boolean parking, boolean restaurant, boolean swimmingpool, boolean spa,
								boolean gym, boolean laundry, boolean smoking, boolean pets) {
		this.wifi = wifi;
		this.parking = parking;
		this.restaurant = restaurant;
		this.swimmingpool = swimmingpool;
		this.spa = spa;
		this.gym = gym;
		this.laundry = laundry;
		this.smoking = smoking;
		this.pets = pets;
	}

	public void mealsUpdate(boolean breakfast, boolean lunch, boolean dinner) {
		this.breakfast = breakfast;
		this.lunch = lunch;
		this.dinner = dinner;
	}

	public void reservationPolicyUpdate(boolean reservation, double cancellationFeesPercentage) {
		this.reservation = reservation;
		this.cancellationFeesPercentage = cancellationFeesPercentage;
	}

	public boolean notEmpty(int customers, LocalDate checkinDate, LocalDate checkoutDate) {
		for (Room room : Rooms.values()) {
			if (room.isAvailable.subMap(checkinDate, checkoutDate).values().stream().allMatch(Boolean::booleanValue) &&
					room.guestCapacity >= customers)
				return true;
		}
		return false;
	}

	public List<Room> availableRooms(int customers) {
		List<Room> available = new ArrayList<>();
		for (Room room : Rooms.values()) {
			if ((room.checkinDate == null && room.checkoutDate == null) ||
					room.isAvailable.subMap(room.checkinDate, room.checkoutDate).values().stream().noneMatch(Boolean::booleanValue) &&
							room.guestCapacity >= customers)
				available.add(room);
		}
		return available;
	}

	@Override
	public int compareTo(Hotel other) {
		return Double.compare(this.minimumPrice, other.minimumPrice);
	}

	public double getUserRating() {
		return userRating;
	}

	public int getOfficialRating() {
		return officialRating;
	}

	public List<Room> getAvailableRoom() {
		return this.Rooms.values().stream().toList();
	}

	public String[] getAvailableAmenities() {
		ArrayList<String> amenities = new ArrayList<>();
		if (this.wifi) amenities.add("Wifi");
		if (this.parking) amenities.add("Parking");
		if (this.restaurant) amenities.add("Restaurant");
		if (this.swimmingpool) amenities.add("Swimmingpool");
		if (this.spa) amenities.add("Spa");
		if (this.gym) amenities.add("Gym");
		if (this.laundry) amenities.add("Laundry");
		if (this.smoking) amenities.add("Smoking");
		if (this.pets) amenities.add("Pets");

		return amenities.toArray(new String[amenities.size()]);
	}
}
