package user_interface;

import user_interface.BookingInterface.DataHandler;

import java.time.LocalDate;
import java.time.Year;
import java.time.temporal.ChronoUnit;
import java.util.NavigableMap;
import java.util.TreeMap;

public class Room {
	public String roomID;
	public boolean airCon, bathtub, showerhead, iron, freeWaterBottles;
	public String roomType;
	public int guestCapacity;
	public double pricePerNight;
	public int duration;
	public double quantity;
	public LocalDate checkinDate;
	public LocalDate checkoutDate;
	public String numberOfRoom = "3";
	public NavigableMap<LocalDate, Boolean> isAvailable = new TreeMap<>();;


	public void updateIsAvailable() {
		int year = Year.now().getValue(); // Get the current year
		for (int month = 1; month <= 12; month++) {
			int daysInMonth = LocalDate.of(year, month, 1).lengthOfMonth();
			for (int day = 1; day <= daysInMonth; day++) {
				LocalDate date = LocalDate.of(year, month, day);
				this.isAvailable.put(date, true);
			}
		}
	}

	public Room(String roomID, double pricePerNight, String roomType) {
		updateIsAvailable();
		this.roomID = roomID;
		this.pricePerNight = pricePerNight;
		this.roomType = roomType;
		this.duration = 0;
		switch (roomType) {
			case "Single":
			case "Studio":
				this.guestCapacity = 1;
				break;
			case "Double":
			case "Twin":
			case "Queen":
			case "King":
				this.guestCapacity = 2;
				break;
			case "Triple":
				this.guestCapacity = 3;
				break;
			case "Quad":
				this.guestCapacity = 4;
				break;
			default:
				break;
		}
	}


	// Handle multi-rooms room such as a homestay apartment or a suite
	public Room(String roomID, double pricePerNight, String roomType, int guestCapacity) {
		this.roomID = roomID;
		this.pricePerNight = pricePerNight;
		this.roomType = roomType;
		this.guestCapacity = guestCapacity;
		this.duration = 0;
	}

	public boolean bookRoom(LocalDate checkinDate, LocalDate checkoutDate) {
		//LocalDate checkoutDate = checkinDate.plusDays(nights);
		NavigableMap<LocalDate, Boolean> subMap = isAvailable.headMap(checkoutDate, true);
		subMap = subMap.tailMap(checkinDate, true);
		if (subMap.values().stream().allMatch(Boolean::booleanValue)) {
			this.duration = (int)Math.abs(ChronoUnit.DAYS.between(checkinDate, checkoutDate));
			this.checkinDate = checkinDate;
			this.checkoutDate = checkoutDate;
			subMap.replaceAll((k, v) -> false);
			return true;
		}
		return false;
	}

	public double roomCost() {
		return this.pricePerNight * this.duration;
	}

	public double totalCost(double diningFees, double minibarFees, double laundryFees, double conpensationFees) {
		return this.roomCost() + diningFees + minibarFees + laundryFees + conpensationFees;
	}
}