package entity;

import java.time.LocalDate;

// variables
public class Reservation {
    public int id;
    public int room_id;
    public LocalDate check_in_date;
    public LocalDate check_out_date;
    public double total_price;
    public int guest_count;
    public String guest_name;
    public String guess_citizen_id;
    public String guess_phone;
    public String guess_mail;
    private int adult_count;
    private int child_count;


    // default constructor method
    public Reservation() {
    }


    // constructor method with class variables
    public Reservation(int id, int room_id, LocalDate check_in_date, LocalDate check_out_date, double total_price, int guest_count, String guest_name, String guess_citizen_id, String guess_mail,String guess_phone) {
        this.id = id;
        this.room_id = room_id;
        this.check_in_date = check_in_date;
        this.check_out_date = check_out_date;
        this.total_price = total_price;
        this.guest_count = guest_count;
        this.guest_name = guest_name;
        this.guess_citizen_id = guess_citizen_id;
        this.guess_mail = guess_mail;
        this.guess_phone = guess_phone;
    }


    // getter and setter methods


    public String getGuess_citizen_id() {
        return guess_citizen_id;
    }

    public String getGuess_phone() {
        return guess_phone;
    }

    public String getGuess_mail() {
        return guess_mail;
    }

    public int getAdult_count() {
        return adult_count;
    }

    public void setAdult_count(int adult_count) {
        this.adult_count = adult_count;
    }

    public int getChild_count() {
        return child_count;
    }

    public void setChild_count(int child_count) {
        this.child_count = child_count;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRoom_id() {
        return room_id;
    }

    public void setRoom_id(int room_id) {
        this.room_id = room_id;
    }

    public LocalDate getCheck_in_date() {
        return check_in_date;
    }

    public void setCheck_in_date(LocalDate check_in_date) {
        this.check_in_date = check_in_date;
    }

    public LocalDate getCheck_out_date() {
        return check_out_date;
    }

    public void setCheck_out_date(LocalDate check_out_date) {
        this.check_out_date = check_out_date;
    }

    public double getTotal_price() {
        return total_price;
    }

    public void setTotal_price(double total_price) {
        this.total_price = total_price;
    }

    public int getGuest_count() {
        return guest_count;
    }

    public void setGuest_count(int guest_count) {
        this.guest_count = guest_count;
    }

    public String getGuest_name() {
        return guest_name;
    }

    public void setGuest_name(String guest_name) {
        this.guest_name = guest_name;
    }

    public String getGuest_citizen_id() {
        return guess_citizen_id;
    }

    public void setGuess_citizen_id(String guess_citizen_id) {
        this.guess_citizen_id = guess_citizen_id;
    }

    public String getGuest_phone() {
        return guess_phone;
    }

    public void setGuess_phone(String guess_phone) {
        this.guess_phone = guess_phone;
    }

    public String getGuest_mail() {
        return guess_mail;
    }

    public void setGuess_mail(String guess_mail) {
        this.guess_mail = guess_mail;
    }

    // stringify method
    // logic of this method is for us to see the contents of an object as a string with a meaning.
    // generally, .toString() method provides you an unclear prints such as memory sector.
    // with the logic above, we need to see the print with a meaning.
    // that is why, we need do exclude .toString() method with @Override and add this method for a meaningful output instead.
    @Override
    public String toString() {
        return "Reservation{" +
                "id=" + id +
                ", room_id=" + room_id +
                ", check_in_date=" + check_in_date +
                ", check_out_date=" + check_out_date +
                ", total_price=" + total_price +
                ", guest_count=" + guest_count +
                ", guest_name='" + guest_name + '\'' +
                ", guess_citizen_id='" + guess_citizen_id + '\'' +
                ", guess_phone='" + guess_phone + '\'' +
                ", guess_mail='" + guess_mail + '\'' +
                '}';
    }
}
