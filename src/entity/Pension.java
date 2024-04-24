package entity;

import core.ComboItem;

// variables
public class Pension {
    private int id;
    private String pension_type;
    private int hotel_id;

    // default constructor method
    public Pension() {
    }

    // constructor method with class variables
    public Pension(int id, String pension_type, int hotel_id) {
        this.id = id;
        this.pension_type = pension_type;
        this.hotel_id = hotel_id;
    }

    // getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPension_type() {
        return pension_type;
    }

    public void setPension_type(String pension_type) {
        this.pension_type = pension_type;
    }

    public int getHotel_id() {
        return hotel_id;
    }

    public void setHotel_id(int hotel_id) {
        this.hotel_id = hotel_id;
    }

    public ComboItem getComboItem(){
        return new ComboItem(this.getId(), "Pension ID : "+ this.getId() + " Hotel ID : " + this.getHotel_id() + " Pension Type : " + this.getPension_type());}

    // stringify method
    // logic of this method is for us to see the contents of an object as a string with a meaning.
    // generally, .toString() method provides you an unclear prints such as memory sector.
    // with the logic above, we need to see the print with a meaning.
    // that is why, we need do exclude .toString() method with @Override and add this method for a meaningful output instead.
    @Override
    public String toString() {
        return "Pension{" +
                "id=" + id +
                ", pension_type='" + pension_type + '\'' +
                ", hotel_id=" + hotel_id +
                '}';
    }
}
