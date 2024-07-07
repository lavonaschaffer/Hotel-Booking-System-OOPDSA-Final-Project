package user_interface;

import java.time.LocalDate;

public class HostelRoom extends Room {
	private int availableBeds;
	public int customers;

	HostelRoom(String roomID, double pricePerNight, int beds) {
		super(roomID, pricePerNight, "Single");
		this.guestCapacity = beds;
		this.availableBeds = beds;
	}

	public boolean bookRoom(int nights, LocalDate checkinDate, int customers) {
		if (availableBeds >= customers) {
			this.duration = nights;
			this.checkinDate = checkinDate;
			this.checkoutDate = checkinDate.plusDays(duration);
			this.customers = customers;
			this.availableBeds = this.availableBeds - customers;
			for (LocalDate date = checkinDate; date.isBefore(checkoutDate); date = date.plusDays(1)) {
				this.isAvailable.put(date, false);
			}

			return true;
		}
		return false;
	}

	@Override
	public double roomCost() {
		return this.pricePerNight * this.duration * this.customers;
	}
}
