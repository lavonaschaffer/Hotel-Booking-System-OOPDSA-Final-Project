package user_interface.BookingInterface;

import user_interface.BookingInterface.ui_wireframe.BookingFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PaymentMethods {

    private static JLabel paymentMethodLabel;
    private static JLabel totalPriceLabel;
    private static JRadioButton payByCardButton, bookReservationButton;
    private static JButton proceedButton;
    private static CardLayout cardLayout;
    private static JPanel cardPanel;

    protected static void choosingPaymentMethod() {
        //Create new view
        BookingFrame.setNewView("Choosing Payment Method");

        paymentMethodLabel = new JLabel("Select Payment Method:");
        payByCardButton = new JRadioButton("Pay by Card");
        bookReservationButton = new JRadioButton("Book Reservation");
        ButtonGroup paymentMethodGroup = new ButtonGroup();
        totalPriceLabel = new JLabel("Total Price: $" + DataHandler.totalPrice);
        paymentMethodGroup.add(payByCardButton);
        paymentMethodGroup.add(bookReservationButton);

        payByCardButton.setSelected(true);
        payByCardButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (payByCardButton.isSelected()) {
                    cardLayout.show(cardPanel, "CardPayment");
                }
            }
        });

        bookReservationButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (bookReservationButton.isSelected()) {
                    cardLayout.show(cardPanel, "Reservation");
                }
            }
        });

        JPanel paymentMethodPanel = new JPanel();
        paymentMethodPanel.add(paymentMethodLabel);
        paymentMethodPanel.add(payByCardButton);
        paymentMethodPanel.add(bookReservationButton);

//        proceedButton = new JButton("Proceed");
//        proceedButton.addActionListener(new ActionListener() {
//            @Override
//            public void actionPerformed(ActionEvent e) {
//                if (payByCardButton.isSelected()) {
//                    cardLayout.show(cardPanel, "CardPayment");
//                } else if (bookReservationButton.isSelected()) {
//                    cardLayout.show(cardPanel, "Reservation");
//                }
//            }
//        });

        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);

        cardPanel.add(new CardPaymentPanel(), "CardPayment");
        cardPanel.add(new ReservationPanel(), "Reservation");

        BookingFrame.frame.add(paymentMethodPanel, BorderLayout.NORTH);
        //BookingFrame.frame.add(proceedButton, BorderLayout.CENTER);
        BookingFrame.frame.add(cardPanel, BorderLayout.SOUTH);

        BookingFrame.frame.setLocationRelativeTo(null);

        BookingFrame.frame.setVisible(true);
    }
}

class CardPaymentPanel extends JPanel {

    private JLabel cardNumberLabel, expiryDateLabel, cvvLabel;
    private JTextField cardNumberField, expiryDateField, cvvField;
    private JButton submitButton;

    public CardPaymentPanel() {
        setLayout(new GridLayout(4, 2, 10, 10));

        cardNumberLabel = new JLabel("Card Number:");
        expiryDateLabel = new JLabel("Expiry Date (MM/YY):");
        cvvLabel = new JLabel("CVV:");

        cardNumberField = new JTextField();
        expiryDateField = new JTextField();
        cvvField = new JTextField();

        submitButton = new JButton("Submit");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle card payment submission
                String cardNumber = cardNumberField.getText();
                String expiryDate = expiryDateField.getText();
                String cvv = cvvField.getText();

                // Process payment with the provided card details
                SwingUtilities.invokeLater(() ->
                        {
                            JOptionPane.showMessageDialog(BookingFrame.frame, "Booked");
                        }
                );

                BookingResult.finalScreen();
            }
        });

        add(cardNumberLabel);
        add(cardNumberField);
        add(expiryDateLabel);
        add(expiryDateField);
        add(cvvLabel);
        add(cvvField);
        add(new JLabel(""));
        add(submitButton);
    }
}

class ReservationPanel extends JPanel {

    private JLabel reservationInfoLabel;
    private JButton confirmReservationButton;

    public ReservationPanel() {
        setLayout(new GridLayout(2, 1, 10, 10));

        reservationInfoLabel = new JLabel("Booking reservation will be confirmed.");
        confirmReservationButton = new JButton("Confirm Reservation");
        confirmReservationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle reservation confirmation
                JOptionPane.showMessageDialog(ReservationPanel.this, "Reservation confirmed successfully!");

                BookingResult.finalScreen();
            }
        });

        add(reservationInfoLabel);
        add(confirmReservationButton);
    }

}
