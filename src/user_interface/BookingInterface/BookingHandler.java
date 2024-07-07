package user_interface.BookingInterface;

import user_interface.BookingInterface.ui_wireframe.BookingFrame;
import user_interface.Hotel;

import javax.swing.*;

import static user_interface.BookingInterface.PersonalDataReader.*;

public class BookingHandler {
    Hotel hotel;
    Customer customer;

    public static void main() {

            //Run the procedure
            BookingController.book();
            //PersonalDataReader.readPersonalInfo();
            //PaymentMethods.choosingPaymentMethod();
            //BookingResult.finalScreen();
    }
}
