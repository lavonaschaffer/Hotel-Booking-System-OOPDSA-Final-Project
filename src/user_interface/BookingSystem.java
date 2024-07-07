package user_interface;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookingSystem {
	public List<Hotel> Hotels = new ArrayList<Hotel>();
	public List<Hotel> searchedHotels;
	public List<Hotel> availableHotels;

	public void addHotel(Hotel thisHotel) {
		Hotels.add(thisHotel);
	}

	public List<Hotel> sortByPrice() {
		Collections.sort(availableHotels);
		return availableHotels;
	}

	public List<Hotel> sortByUserRating() {
		Collections.sort(availableHotels, Comparator.comparingDouble(Hotel::getUserRating).reversed());
		return availableHotels;
	}

	public List<Hotel> sortByOfficialRating() {
		Collections.sort(availableHotels, Comparator.comparingInt(Hotel::getOfficialRating).reversed());
		return availableHotels;
	}

	public List<Hotel> searchHotels(LocalDate startDate, LocalDate endDate, String country, String city, int people) {
		this.searchedHotels = new ArrayList<>();
		for (Hotel hotel : Hotels) {
			boolean notempty = hotel.notEmpty(people, startDate, endDate);
			if (notempty && (hotel.country.equals(country) || country.equals(""))
					&& (hotel.city.equals(city) || city.equals(""))) {
				searchedHotels.add(hotel);
			}
		}
		return searchedHotels;
	}

	// Parameter is a list of hotel types needed
	public List<Hotel> filterType(String[] hotelTypes) {
		availableHotels = new ArrayList<Hotel>(searchedHotels);
		if (hotelTypes.length == 0)
			return availableHotels;
		for (int i = 0; i < availableHotels.size(); i++) {
			Hotel hotel = availableHotels.get(i);
			boolean flag = false;
			for (String type : hotelTypes) {
				if (hotel.getClass().getSimpleName().equals(type)) {
					flag = true;
					break;
				}
			}
			if (!flag) {
				availableHotels.remove(hotel);
				i--;
			}
		}
		return availableHotels;
	}

	// Parameter is a boolean[9] of utilities available in this order:
	// Wifi, parking, restaurant, swimmingpool, spa, gym, laundry, smoking, pets
	public List<Hotel> filterUtilities(boolean[] utilities) {
		for (int i = 0; i < availableHotels.size(); i++) {
			Hotel hotel = availableHotels.get(i);
			boolean flag;
			if (utilities[0] && !hotel.wifi) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[1] && !hotel.parking) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[2] && !hotel.restaurant) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[3] && !hotel.swimmingpool) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[4] && !hotel.spa) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[5] && !hotel.gym) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[6] && !hotel.laundry) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[7] && !hotel.smoking) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (utilities[8] && !hotel.pets) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
		}
		return availableHotels;
	}

	// Parameter is a boolean[3] of free meals
	public List<Hotel> filterMeals(boolean[] meals) {
		for (int i = 0; i < availableHotels.size(); i++) {
			Hotel hotel = availableHotels.get(i);
			boolean flag;
			if (meals[0] && !hotel.breakfast) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (meals[1] && !hotel.lunch) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
			if (meals[2] && !hotel.dinner) {
				flag = availableHotels.remove(hotel);
				if(flag) i--;
			}
		}
		return availableHotels;
	}

	// Parameter is a single boolean of if the hotel has reservation policy or not
	public List<Hotel> filterReservation(boolean reservationPolicy) {
		for (int i = 0; i < availableHotels.size(); i++) {
			Hotel hotel = availableHotels.get(i);
			if (reservationPolicy && !hotel.reservation) {
				availableHotels.remove(hotel);
				i--;
			}
		}
		return availableHotels;
	}

	// Parameters are two double for the price range
	public List<Hotel> filterPrices(double minPrice, double maxPrice) {
		availableHotels = this.sortByPrice();
		// Binary search for startIndex
		int left = 0;
		int right = availableHotels.size() - 1;
		int startIndex = availableHotels.size() - 1;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			double midPrice = availableHotels.get(mid).minimumPrice;
			if (midPrice == minPrice) {
				startIndex = mid;
				break;
			} else if (midPrice < minPrice) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (startIndex == availableHotels.size() - 1) {
			startIndex = left + 1;
		}

		// Binary search for endIndex
		left = 0;
		right = availableHotels.size() - 1;
		int endIndex = 0;
		while (left <= right) {
			int mid = left + (right - left) / 2;
			double midPrice = availableHotels.get(mid).minimumPrice;
			if (midPrice == maxPrice) {
				endIndex = mid;
				break;
			} else if (midPrice < maxPrice) {
				left = mid + 1;
			} else {
				right = mid - 1;
			}
		}
		if (endIndex == 0) {
			endIndex = right + 1;
		}
		if (startIndex < endIndex)
			availableHotels = availableHotels.subList(startIndex, endIndex);
		return availableHotels;
	}

	// Parameter is the minimum user rating
	public List<Hotel> filterUserRating(double minUserRating) {
		availableHotels = this.sortByUserRating();
		int endIndex = availableHotels.size();
		for (int i = 0; i < availableHotels.size(); i++) {
			if (availableHotels.get(i).userRating < minUserRating) {
				endIndex = i;
				break;
			}
		}
		availableHotels = availableHotels.subList(0, endIndex);
		return availableHotels;
	}

	// Parameter is the minimum official rating - which is the number of stars
	public List<Hotel> filterOfficialRating(int minStar) {
		availableHotels = this.sortByOfficialRating();
		int endIndex = availableHotels.size();
		for (int i = 0; i < availableHotels.size(); i++) {
			if (availableHotels.get(i).officialRating < minStar) {
				endIndex = i;
				break;
			}
		}
		availableHotels = availableHotels.subList(0, endIndex);
		return availableHotels;
	}
}