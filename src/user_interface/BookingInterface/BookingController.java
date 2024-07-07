package user_interface.BookingInterface;

import com.toedter.calendar.JCalendar;
import user_interface.BookingInterface.ui_wireframe.BookingFrame;
import user_interface.Room;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class BookingController {

        private static JLabel totalPriceLabel;
        private static JComboBox<Integer>[] roomComboBoxes;

        protected static void book() {

                //Empty the screen for new things
                BookingFrame.setNewView("Overview");

                // Left Panel for Hotel Info
                JPanel leftPanel = new JPanel();
                leftPanel.setLayout(new BoxLayout(leftPanel, BoxLayout.Y_AXIS));

                // Hotel Name
                JLabel hotelNameLabel = new JLabel("Hotel Name: " + DataHandler.selectedHotel.name);
                leftPanel.add(hotelNameLabel);

                // Hotel Address
                JLabel hotelAddressLabel = new JLabel("Hotel Address: " + DataHandler.selectedHotel.address);
                leftPanel.add(hotelAddressLabel);

                // Hotel Image
                JLabel hotelImageLabel = new JLabel(new ImageIcon("path/to/your/image.jpg"));
                leftPanel.add(hotelImageLabel);

                // Hotel Description
                JTextArea hotelDescriptionArea = new JTextArea(5, 20);
                hotelDescriptionArea.setText("Description of the hotel...");
                hotelDescriptionArea.setWrapStyleWord(true);
                hotelDescriptionArea.setLineWrap(true);
                hotelDescriptionArea.setEditable(false);
                JScrollPane scrollPane = new JScrollPane(hotelDescriptionArea);
                leftPanel.add(scrollPane);

                // Hotel Amenities
                JPanel amenitiesPanel = new JPanel();
                amenitiesPanel.setLayout(new BoxLayout(amenitiesPanel, BoxLayout.Y_AXIS));
                String[] amenities = DataHandler.selectedHotel.getAvailableAmenities();


                for (int i = 0; i < amenities.length; i++) {
                        amenitiesPanel.add(new JLabel(amenities[i]));
                }
                leftPanel.add(amenitiesPanel);

                // Right Panel for Booking
                JPanel rightPanel = new JPanel();
                rightPanel.setLayout(new BoxLayout(rightPanel, BoxLayout.Y_AXIS));

                DataHandler.rooms = DataHandler.selectedHotel.getAvailableRoom();
                rightPanel.setLayout(new BorderLayout());

                // Panel to hold all room cards
                JPanel cardPanel = new JPanel();
                cardPanel.setLayout(new BoxLayout(cardPanel, BoxLayout.Y_AXIS));

                // Create JComboBoxes for each room
                roomComboBoxes = new JComboBox[DataHandler.rooms.size()];

                // Adding each room card to the cardPanel
                for (int i = 0; i < DataHandler.rooms.size(); i++) {
                        Room room = DataHandler.rooms.get(i);
                        JPanel roomCard = createRoomCard(room, i, DataHandler.start, DataHandler.end);
                        cardPanel.add(roomCard);
                }

                // Adding cardPanel to a scroll pane
                JScrollPane rightScrollPane = new JScrollPane(cardPanel);
                rightPanel.add(rightScrollPane, BorderLayout.CENTER);

                // Footer to display total price
                JPanel footerPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
                totalPriceLabel = new JLabel("Total Price: $0.00");
                footerPanel.add(totalPriceLabel);
                rightPanel.add(footerPanel, BorderLayout.SOUTH);

                // Calendar selection
//                JCalendar startDate = new JCalendar();
//                JCalendar endDate = new JCalendar();
//                rightScrollPane.add(startDate);
//                rightScrollPane.add(endDate);

                // Book Button
                JButton bookButton = new JButton("Book");
                bookButton.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                //JOptionPane.showMessageDialog(BookingFrame.frame, "Booking Successful!");
                                // Get text from the input

                                for (Room room : DataHandler.rooms) {
                                        room.bookRoom(DataHandler.start, DataHandler.end);
                                }

                                PersonalDataReader.readPersonalInfo();
                        }
                });
                leftPanel.add(bookButton);


                // Split Pane to hold both panels
                JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, leftPanel, rightPanel);
                splitPane.setDividerLocation(400);

                BookingFrame.frame.add(splitPane, BorderLayout.CENTER);
                BookingFrame.frame.setVisible(true);
        }

        private static JPanel createRoomCard(Room room, int index, LocalDate startDate, LocalDate endDate) {
                JPanel card = new JPanel();
                card.setLayout(new GridLayout(0, 2));
                card.setBorder(BorderFactory.createLineBorder(Color.BLACK));

                card.add(new JLabel("Room ID:"));
                card.add(new JLabel(room.roomID));
                card.add(new JLabel("Room Type:"));
                card.add(new JLabel(room.roomType));
                card.add(new JLabel("Guest Capacity:"));
                card.add(new JLabel(String.valueOf(room.guestCapacity)));
                card.add(new JLabel("Price per Night:"));
                card.add(new JLabel(String.valueOf(room.pricePerNight)));
//                card.add(new JLabel("Is Available:"));
//                card.add(new JLabel(room.isAvailable ? "Yes" : "No"));
                card.add(new JLabel("Number of Rooms:"));
                card.add(new JLabel(String.valueOf(room.numberOfRoom)));

                // Drop down list for the customer to choose the number of rooms to book
                card.add(new JLabel("Rooms to Book:"));
                Integer[] roomNumbers = new Integer[Integer.parseInt(room.numberOfRoom) + 1];
                for (int i = 0; i <= Integer.parseInt(room.numberOfRoom); i++) {
                        roomNumbers[i] = i;
                }
                JComboBox<Integer> roomNumberComboBox = new JComboBox<>(roomNumbers);
                roomNumberComboBox.setEnabled(room.isAvailable.subMap(startDate, endDate).values().stream().allMatch(Boolean::booleanValue));
                roomNumberComboBox.addActionListener(new ActionListener() {
                        @Override
                        public void actionPerformed(ActionEvent e) {
                                updateTotalPrice();
                        }
                });
                roomComboBoxes[index] = roomNumberComboBox;
                card.add(roomNumberComboBox);

                return card;
        }

        private static void updateTotalPrice() {
                double totalPrice = 0;
                for (int i = 0; i < DataHandler.rooms.size(); i++) {
                        Room room = DataHandler.rooms.get(i);
                        JComboBox<Integer> comboBox = roomComboBoxes[i];
                        int selectedRooms = (int) comboBox.getSelectedItem();

                        // Calculate the price (subjective to change according to the bookRoom method)
                        totalPrice += selectedRooms * room.pricePerNight * Math.abs(ChronoUnit.DAYS.between(DataHandler.start, DataHandler.end));
                        room.quantity = selectedRooms;
                }
                totalPriceLabel.setText(String.format("Total Price (estimated): $%.2f", totalPrice));
                DataHandler.totalPrice = totalPrice;
        }
}

