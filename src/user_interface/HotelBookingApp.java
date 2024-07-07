package user_interface;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Date;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.xml.crypto.Data;

import com.toedter.calendar.JCalendar;
import user_interface.BookingInterface.BookingHandler;
import user_interface.BookingInterface.DataHandler;
import user_interface.BookingInterface.PersonalDataReader;
import user_interface.BookingInterface.ui_wireframe.BookingFrame;

public class HotelBookingApp extends JFrame {
	public BookingSystem bookingSystem;
	private JTextField countryField;
	private JTextField cityField;
	private JTextField numberOfPeopleField;
	private JCheckBox traditionalHotelCheckBox;
	private JCheckBox resortCheckBox;
	private JCheckBox hostelCheckBox;
	private JCheckBox homestayCheckBox;
	private JCheckBox motelCheckBox;
	private JCheckBox wifiCheckBox;
	private JCheckBox parkingCheckBox;
	private JCheckBox restaurantCheckBox;
	private JCheckBox swimmingpoolCheckBox;
	private JCheckBox spaCheckBox;
	private JCheckBox gymCheckBox;
	private JCheckBox laundryCheckBox;
	private JCheckBox smokingCheckBox;
	private JCheckBox petsCheckBox;
	private JCheckBox breakfastCheckBox;
	private JCheckBox lunchCheckBox;
	private JCheckBox dinnerCheckBox;
	private JCheckBox reservationPolicyCheckBox;
	private JTextField minPriceField;
	private JTextField maxPriceField;
	private JTextField minUserRatingField;
	private JTextField minOfficialRatingField;
	private JComboBox<String> sortByComboBox;
	private JButton searchButton;
	private JButton bookButton;
	private JTable hotelTable;
	private DefaultTableModel tableModel;
	private JCalendar startDateCalendar;
	private JCalendar endDateCalendar;

	public HotelBookingApp(BookingSystem bookingSystem) {
		this.bookingSystem = bookingSystem;
	}

	public void initUI() {
		setLayout(new BorderLayout());

		// Search form
		JPanel searchPanel = new JPanel();
		searchPanel.setLayout(new GridBagLayout());
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);

		gbc.gridx = 0;
		gbc.gridy = 0;
		searchPanel.add(new JLabel("Start Date:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 0;
		startDateCalendar = new JCalendar();
		searchPanel.add(startDateCalendar, gbc);

		gbc.gridx = 0;
		gbc.gridy = 1;
		searchPanel.add(new JLabel("End Date:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 1;
		endDateCalendar = new JCalendar();
		searchPanel.add(endDateCalendar, gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		searchPanel.add(new JLabel("Country:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 2;
		countryField = new JTextField(20);
		searchPanel.add(countryField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 3;
		searchPanel.add(new JLabel("City:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		cityField = new JTextField(20);
		searchPanel.add(cityField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 4;
		searchPanel.add(new JLabel("Number of People:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 4;
		numberOfPeopleField = new JTextField(20);
		searchPanel.add(numberOfPeopleField, gbc);

		// Filtering fields
		JPanel filteringPanel = new JPanel();
		filteringPanel.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();
		gbc.anchor = GridBagConstraints.LINE_START;
		gbc.insets = new Insets(5, 0, 5, 5);

		gbc.gridx = 2;
		gbc.gridy = 0;
		gbc.gridwidth = 4;
		filteringPanel.add(new JLabel("Filtering Options:"), gbc);

		gbc.gridx = 0;
		gbc.gridy = 2;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Hotel Type:"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 3;
		traditionalHotelCheckBox = new JCheckBox("Traditional Hotel");
		filteringPanel.add(traditionalHotelCheckBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 3;
		resortCheckBox = new JCheckBox("Resort");
		filteringPanel.add(resortCheckBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 3;
		hostelCheckBox = new JCheckBox("Hostel");
		filteringPanel.add(hostelCheckBox, gbc);
		gbc.gridx = 3;
		gbc.gridy = 3;
		homestayCheckBox = new JCheckBox("Homestay");
		filteringPanel.add(homestayCheckBox, gbc);
		gbc.gridx = 4;
		gbc.gridy = 3;
		motelCheckBox = new JCheckBox("Motel");
		filteringPanel.add(motelCheckBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 5;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Utilities:"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 6;
		wifiCheckBox = new JCheckBox("Wi-Fi");
		filteringPanel.add(wifiCheckBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 6;
		parkingCheckBox = new JCheckBox("Parking");
		filteringPanel.add(parkingCheckBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 6;
		restaurantCheckBox = new JCheckBox("Restaurant");
		filteringPanel.add(restaurantCheckBox, gbc);
		gbc.gridx = 3;
		gbc.gridy = 6;
		swimmingpoolCheckBox = new JCheckBox("Swimming Pool");
		filteringPanel.add(swimmingpoolCheckBox, gbc);
		gbc.gridx = 0;
		gbc.gridy = 7;
		spaCheckBox = new JCheckBox("Spa");
		filteringPanel.add(spaCheckBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 7;
		gymCheckBox = new JCheckBox("Gym");
		filteringPanel.add(gymCheckBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 7;
		laundryCheckBox = new JCheckBox("Laundry");
		filteringPanel.add(laundryCheckBox, gbc);
		gbc.gridx = 3;
		gbc.gridy = 7;
		smokingCheckBox = new JCheckBox("Smoking ");
		filteringPanel.add(smokingCheckBox, gbc);
		gbc.gridx = 4;
		gbc.gridy = 7;
		petsCheckBox = new JCheckBox("Pets");
		filteringPanel.add(petsCheckBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 9;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Meals:"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 10;
		breakfastCheckBox = new JCheckBox("Breakfast");
		filteringPanel.add(breakfastCheckBox, gbc);
		gbc.gridx = 1;
		gbc.gridy = 10;
		lunchCheckBox = new JCheckBox("Lunch");
		filteringPanel.add(lunchCheckBox, gbc);
		gbc.gridx = 2;
		gbc.gridy = 10;
		dinnerCheckBox = new JCheckBox("Dinner");
		filteringPanel.add(dinnerCheckBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 12;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Has Reservation Policy: "), gbc);
		gbc.gridx = 1;
		gbc.gridy = 12;
		reservationPolicyCheckBox = new JCheckBox();
		filteringPanel.add(reservationPolicyCheckBox, gbc);

		gbc.gridx = 0;
		gbc.gridy = 14;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Prices:"), gbc);
		gbc.gridx = 0;
		gbc.gridy = 15;
		filteringPanel.add(new JLabel("Minimum:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 15;
		minPriceField = new JTextField(5);
		filteringPanel.add(minPriceField, gbc);
		gbc.gridx = 2;
		gbc.gridy = 15;
		filteringPanel.add(new JLabel("Maximum:"), gbc);
		gbc.gridx = 3;
		gbc.gridy = 15;
		maxPriceField = new JTextField(5);
		filteringPanel.add(maxPriceField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 17;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Minimum User Rating:"), gbc);
		gbc.gridx = 1;
		gbc.gridy = 17;
		minUserRatingField = new JTextField(5);
		filteringPanel.add(minUserRatingField, gbc);

		gbc.gridx = 2;
		gbc.gridy = 17;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Minimum Official Rating:"), gbc);
		gbc.gridx = 3;
		gbc.gridy = 17;
		minOfficialRatingField = new JTextField(5);
		filteringPanel.add(minOfficialRatingField, gbc);

		gbc.gridx = 0;
		gbc.gridy = 19;
		gbc.gridwidth = 1;
		filteringPanel.add(new JLabel("Sorting by: "), gbc);
		sortByComboBox = new JComboBox<String>(new String[] { "By Price", "By User Rating", "By Official Rating" });
		gbc.gridx = 1;
		gbc.gridy = 19;
		filteringPanel.add(sortByComboBox, gbc);

		// Book button
		bookButton = new JButton("Book");
		bookButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//TODO
			}
		});
		// Search button
		searchButton = new JButton("Search");
		searchButton.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				search();
			}
		});

		// Make the Table non edit
		tableModel = new DefaultTableModel() {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		};

		tableModel.addColumn("Name");
		tableModel.addColumn("Type");
		tableModel.addColumn("Address");
		tableModel.addColumn("Minimum Price");
		tableModel.addColumn("User Rating");
		tableModel.addColumn("Official Rating");
		hotelTable = new JTable(tableModel);

		// Add components to frame
		add(filteringPanel, BorderLayout.EAST);
		add(searchPanel, BorderLayout.WEST);
		add(new JScrollPane(hotelTable), BorderLayout.CENTER);
		add(searchButton, BorderLayout.SOUTH);
		//add(bookButton);

		// Set frame properties
		setTitle("Hotel Booking App");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		// Set up booking view
		BookingFrame.setNewView("Setting up");
	}

	private void search() {
		// Parse input
		LocalDate startDate = startDateCalendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate endDate = endDateCalendar.getDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		LocalDate localStartDate = LocalDate.of(startDate.getYear(), startDate.getMonthValue(),
				startDate.getDayOfMonth());
		LocalDate localEndDate = LocalDate.of(endDate.getYear(), endDate.getMonthValue(), endDate.getDayOfMonth());
		String country = countryField.getText();
		String city = cityField.getText();
		int numberOfPeople;
		if (numberOfPeopleField.getText().equals(""))
			numberOfPeople = 1;
		else
			numberOfPeople = Integer.parseInt(numberOfPeopleField.getText());
		String[] hotelTypes = {};
		if (traditionalHotelCheckBox.isSelected()) {
			hotelTypes = Arrays.copyOf(hotelTypes, hotelTypes.length + 1);
			hotelTypes[hotelTypes.length - 1] = "TraditionalHotel";
		}
		if (resortCheckBox.isSelected()) {
			hotelTypes = Arrays.copyOf(hotelTypes, hotelTypes.length + 1);
			hotelTypes[hotelTypes.length - 1] = "Resort";
		}
		if (hostelCheckBox.isSelected()) {
			hotelTypes = Arrays.copyOf(hotelTypes, hotelTypes.length + 1);
			hotelTypes[hotelTypes.length - 1] = "Hostel";
		}
		if (homestayCheckBox.isSelected()) {
			hotelTypes = Arrays.copyOf(hotelTypes, hotelTypes.length + 1);
			hotelTypes[hotelTypes.length - 1] = "Homestay";
		}
		if (motelCheckBox.isSelected()) {
			hotelTypes = Arrays.copyOf(hotelTypes, hotelTypes.length + 1);
			hotelTypes[hotelTypes.length - 1] = "Motel";
		}
		boolean[] utilities = { wifiCheckBox.isSelected(), parkingCheckBox.isSelected(),
				restaurantCheckBox.isSelected(), swimmingpoolCheckBox.isSelected(), spaCheckBox.isSelected(),
				gymCheckBox.isSelected(), laundryCheckBox.isSelected(), smokingCheckBox.isSelected(),
				petsCheckBox.isSelected() };
		boolean[] meals = { breakfastCheckBox.isSelected(), lunchCheckBox.isSelected(), dinnerCheckBox.isSelected() };
		boolean reservationPolicy = reservationPolicyCheckBox.isSelected();
		double minPrice;
		if (minPriceField.getText().equals(""))
			minPrice = 0.0;
		else
			minPrice = Double.parseDouble(minPriceField.getText());
		double maxPrice;
		if (maxPriceField.getText().equals(""))
			maxPrice = Double.MAX_VALUE;
		else
			maxPrice = Double.parseDouble(maxPriceField.getText());
		double minUserRating;
		if (minUserRatingField.getText().equals(""))
			minUserRating = 0.0;
		else
			minUserRating = Double.parseDouble(minUserRatingField.getText());
		int minOfficialRating;
		if (minOfficialRatingField.getText().equals(""))
			minOfficialRating = 0;
		else
			minOfficialRating = Integer.parseInt(minOfficialRatingField.getText());

		// Search and filter hotels
		List<Hotel> availableHotels = bookingSystem.searchHotels(localStartDate, localEndDate, country, city,
				numberOfPeople);
		availableHotels = bookingSystem.filterType(hotelTypes);
		availableHotels = bookingSystem.filterUtilities(utilities);
		availableHotels = bookingSystem.filterMeals(meals);
		availableHotels = bookingSystem.filterReservation(reservationPolicy);
		availableHotels = bookingSystem.filterPrices(minPrice, maxPrice);
		availableHotels = bookingSystem.filterUserRating((int) minUserRating);
		availableHotels = bookingSystem.filterOfficialRating(minOfficialRating);

		// Sorting
		String sortBy = (String) sortByComboBox.getSelectedItem();
		if (sortBy.equals("By Price")) {
			availableHotels = bookingSystem.sortByPrice();
		} else if (sortBy.equals("By User Rating")) {
			availableHotels = bookingSystem.sortByUserRating();
		} else if (sortBy.equals("By Official Rating")) {
			availableHotels = bookingSystem.sortByOfficialRating();
		}

		// Select the whole row
		hotelTable.setCellSelectionEnabled(false);
		hotelTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		hotelTable.setRowSelectionAllowed(true);
		
		// Update table
		tableModel.setRowCount(0);
		for (Hotel hotel : availableHotels) {
			tableModel.addRow(new Object[] { hotel.name, hotel.getClass().getSimpleName(), hotel.address,
					hotel.minimumPrice, hotel.userRating, hotel.officialRating });
		}
		
		// Get selected thing
				List<Hotel> finalAvailableHotels = availableHotels;
				hotelTable.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent me) {
						//System.out.println(me.getClickCount());
						if (me.getClickCount() == 2) {
							JTable target = (JTable) me.getSource();
							int row = target.getSelectedRow(); // select a row
							int column = target.getSelectedColumn(); // select a column
							DataHandler.selectedHotel = finalAvailableHotels.get(hotelTable.getSelectedRow());
							DataHandler.start = LocalDate.ofInstant(startDateCalendar.getDate().toInstant(), ZoneId.systemDefault());
							DataHandler.end = LocalDate.ofInstant(endDateCalendar.getDate().toInstant(), ZoneId.systemDefault());


							BookingHandler.main();
						}
					}
				});
	}

	public static String getRandomAddress() {
		String[] streetNames = { "Willow", "Birch", "Elm", "Cedar", "Oak", "Maple", "Pine", "Walnut", "Chestnut",
				"Laurel", "Magnolia", "Cypress", "Poplar", "Sycamore", "Aspen", "Juniper", "Redwood", "Spruce", "Alder",
				"Hawthorn" };
		String[] streetTypes = { "St", "Ave", "Rd", "Ln", "Blvd", "Dr" };
		Random random = new Random();
		String streetName = streetNames[random.nextInt(streetNames.length)];
		String streetType = streetTypes[random.nextInt(streetTypes.length)];
		int streetNumber = random.nextInt(200) + 1;
		return streetNumber + " " + streetName + " " + streetType;
	}

	public static String getRandomChar() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		Random random = new Random();
		int randomIndex = random.nextInt(characters.length());
		char randomChar = characters.charAt(randomIndex);
		return String.valueOf(randomChar);
	}

	public static String getRandomCountry() {
		String[] countries = { "France", "Spain", "United States", "Italy", "United Kingdom", "Germany", "China",
				"Japan", "Thailand", "Vietnam" };
		Random random = new Random();
		int randomIndex = random.nextInt(countries.length);
		return countries[randomIndex];
	}

	public static void main(String[] args) {
		BookingSystem bookingSystem = new BookingSystem();

		// Make up some hotels
		String[] types = { "Single", "Studio", "Double", "Twin", "King", "Queen", "Triple", "Quad" };
		for (int i = 0; i < 50; i++) {
			Random random = new Random();
			int floors = random.nextInt(10) + 1;
			TraditionalHotel newHotel = new TraditionalHotel(
					"Hotel" + getRandomChar() + getRandomChar() + getRandomChar(),
					getRandomAddress() + ", " + getRandomChar() + ", " + getRandomCountry(),
					Math.round(random.nextDouble() * 5.0 * 100.0) / 100.0, random.nextInt(5) + 1, floors);
			for (int j = 1; j <= floors; j++)
				newHotel.addRooms(j, 20, 100.0 + random.nextInt(30), types[random.nextInt(types.length)]);
			newHotel.utilitiesUpdate(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean());
			Boolean bf = random.nextBoolean();
			if (bf)
				newHotel.mealsUpdate(bf, random.nextBoolean(), random.nextBoolean());
			else
				newHotel.mealsUpdate(false, false, false);
			newHotel.reservationPolicyUpdate(random.nextBoolean(), (int) (10.0 + random.nextInt(5)));
			bookingSystem.addHotel(newHotel);

			Resort newResort = new Resort("Hotel" + getRandomChar() + getRandomChar() + getRandomChar(),
					getRandomAddress() + ", " + getRandomChar() + ", " + getRandomCountry(),
					Math.round(random.nextDouble() * 5.0 * 100.0) / 100.0, random.nextInt(5) + 1);
			for (int j = 1; j <= 20; j++)
				newResort.addRooms(Integer.toString(j), 400.0 + random.nextInt(100), types[random.nextInt(4) + 3],
						random.nextInt(4) + 1);
			newResort.utilitiesUpdate(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean());
			bf = random.nextBoolean();
			if (bf)
				newResort.mealsUpdate(bf, random.nextBoolean(), random.nextBoolean());
			else
				newResort.mealsUpdate(false, false, false);
			newResort.reservationPolicyUpdate(random.nextBoolean(), (int) (50.0 + random.nextInt(10)));
			bookingSystem.addHotel(newResort);

			Hostel newHostel = new Hostel("Hotel" + getRandomChar() + getRandomChar() + getRandomChar(),
					getRandomAddress() + ", " + getRandomChar() + ", " + getRandomCountry(),
					Math.round(random.nextDouble() * 5.0 * 100.0) / 100.0);
			for (int j = 1; j <= 5; j++)
				newHostel.addRooms(Integer.toString(j), 10.0 + random.nextInt(5), random.nextInt(5) + 4);
			newHostel.utilitiesUpdate(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean());
			newHostel.mealsUpdate(false, false, false);
			newHostel.reservationPolicyUpdate(random.nextBoolean(), 0);
			bookingSystem.addHotel(newHostel);

			Homestay newHomestay = new Homestay("Hotel" + getRandomChar() + getRandomChar() + getRandomChar(),
					getRandomAddress() + ", " + getRandomChar() + ", " + getRandomCountry(),
					Math.round(random.nextDouble() * 5.0 * 100.0) / 100.0);
			newHomestay.addRooms("A", 20.0 + random.nextInt(10), types[random.nextInt(4)], 2);
			newHomestay.addRooms("B", 20.0 + random.nextInt(10), types[random.nextInt(4)], 2);
			newHomestay.utilitiesUpdate(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean());
			newHomestay.mealsUpdate(false, false, false);
			newHomestay.reservationPolicyUpdate(random.nextBoolean(), (int) (10.0 + random.nextInt(5)));
			bookingSystem.addHotel(newHomestay);

			Motel newMotel = new Motel("Hotel" + getRandomChar() + getRandomChar() + getRandomChar(),
					getRandomAddress() + ", " + getRandomChar() + ", " + getRandomCountry(),
					Math.round(random.nextDouble() * 5.0 * 100.0) / 100.0);
			newMotel.addRooms(floors, 20, 4.0 + random.nextInt(3));
			newMotel.utilitiesUpdate(random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean(), random.nextBoolean(), random.nextBoolean(),
					random.nextBoolean(), random.nextBoolean());
			newMotel.mealsUpdate(random.nextBoolean(), false, false);
			newMotel.reservationPolicyUpdate(false, 0);
			bookingSystem.addHotel(newMotel);
		}

		HotelBookingApp app = new HotelBookingApp(bookingSystem);
		app.initUI();
		app.setVisible(true);
	}
}