package user_interface.BookingInterface;

import user_interface.Room;

import java.time.LocalDateTime;
import java.util.List;

class Customer {
        private String name, phone, email, address;
        private String id_numbers;
        private boolean sex;
        private List<Room> rooms;
        private LocalDateTime checkin, checkout;

        public Customer(String name, String phone, String email, String address, Boolean sex, String id_numbers) {
            this.name = name;
            this.phone = phone;
            this.email = email;
            this.address = address;
            this.sex = sex;
            this.id_numbers = id_numbers;
        }

        public Customer(LocalDateTime checkin, LocalDateTime checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }

        public String getName() {
            return name;
        }

        public String getPhone() {
            return phone;
        }

        public String getEmail() {
            return email;
        }

        public String getAddress() {
            return address;
        }

        public String getId_numbers() {
            return id_numbers;
        }

        public String getSex() {
            return (sex) ? "Male" : "Female";
        }

        public LocalDateTime getCheckin() {
            return checkin;
        }

        public LocalDateTime getCheckout() {
            return checkout;
        }

        public List<Room> getRooms() {
            return rooms;
        }

        public void addRoom(Room room) {
            rooms.add(room);
        }
    }