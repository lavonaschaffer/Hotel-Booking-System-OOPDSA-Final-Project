package user_interface.BookingInterface;

import user_interface.BookingInterface.ui_wireframe.BookingFrame;
import user_interface.BookingInterface.DataHandler;
import user_interface.Room;

import javax.swing.*;
import javax.swing.text.Document;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

public class BookingResult {
    protected static void finalScreen() {
        //Renote the information
        JLabel nameLabel, hotelLabel, checkInLabel, checkOutLabel;
        JButton generateInvoiceButton;

        //CreateView
        BookingFrame.setNewView("Booking Confirmation");

        //AdditionalForThisView
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(6, 2, 10, 10));

        nameLabel = new JLabel("Customer: " + DataHandler.customer.getName());
        hotelLabel = new JLabel("Hotel: " + DataHandler.selectedHotel.name);
        //roomLabel = new JLabel("Room: " + DataHandler.room.roomType);
        //numberOfRoom = new JLabel("Number of rooms: " + DataHandler.rooms);
        //roomID = new JLabel("Room ID: ");
        checkInLabel = new JLabel("Check-In: " + DataHandler.start);
        checkOutLabel = new JLabel("Check-Out: " + DataHandler.end);

        JPanel scrollPane = new JPanel();

        int count = 0;
        for (Room room : DataHandler.rooms) {
            if (room.quantity > 0) {
                count++;
                JPanel roomCardPanel = new JPanel();
                roomCardPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                roomCardPanel.setLayout(new GridLayout(0, 2));

                roomCardPanel.add(new JLabel("Room Type: " + room.roomType));
                roomCardPanel.add(new JLabel("Quantity: " + room.quantity));
                roomCardPanel.add(new JLabel("Price per Room: $" + room.pricePerNight));
                double roomTotal = room.quantity * room.pricePerNight;
                roomCardPanel.add(new JLabel("Room Total: $" + roomTotal));

                panel.add(roomCardPanel);
            }
        }

//        scrollPane.setLayout(new GridLayout(8 + count, 2, 10, 10));
//        scrollPane.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        // scrollPane.setVisible(true);

        JLabel totalPriceLabel = new JLabel("Total Price: $" + DataHandler.totalPrice);

        generateInvoiceButton = new JButton("Generate Invoice");
        generateInvoiceButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                invoiceGenerator();
            }
        });

        panel.add(nameLabel);
        panel.add(hotelLabel);
        panel.add(checkInLabel);
        panel.add(checkOutLabel);
        panel.add(scrollPane);
        panel.add(totalPriceLabel, BorderLayout.BEFORE_FIRST_LINE);
        panel.add(generateInvoiceButton, BorderLayout.AFTER_LAST_LINE);

        BookingFrame.frame.add(panel);
        BookingFrame.frame.setVisible(true);
    }

    protected static void invoiceGenerator() {
//        Document document = new Document();
        //            PdfWriter.getInstance(document, new FileOutputStream("Invoice.pdf"));
//            document.open();
//            document.add(new Paragraph("Hotel Booking Invoice"));
//            document.add(new Paragraph("Customer: " + customerName));
//            document.add(new Paragraph("Hotel: " + hotelName));
//            document.add(new Paragraph("Room: " + roomType));
//            document.add(new Paragraph("Check-In Date: " + checkInDate));
//            document.add(new Paragraph("Check-Out Date: " + checkOutDate));
//            document.close();
        JOptionPane.showMessageDialog(BookingFrame.frame,
                "Printed");
    }
}
