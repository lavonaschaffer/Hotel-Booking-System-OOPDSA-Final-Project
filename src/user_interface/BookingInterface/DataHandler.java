package user_interface.BookingInterface;

import com.toedter.calendar.JCalendar;
import user_interface.Hotel;
import user_interface.Room;
import user_interface.TraditionalHotel;

import java.time.LocalDate;
import java.util.List;

public class DataHandler {
    protected static Customer customer;
    protected static List<Room> rooms;
    public static double totalPrice;
    public static LocalDate start, end;
    public static Hotel selectedHotel;
}