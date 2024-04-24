package entity;

import business.HotelManager;
import core.ComboItem;


 // variables
public class Hotel {
    private int id;
    private String name;
    private String address;
    private String mail;
    private String phone;
    private String star;
    private boolean wifi;
    private boolean car_park;
    private boolean pool;
    private boolean fitness;
    private boolean concierge;
    private boolean spa;
    private boolean room_service;
    private Pension pension;
    private HotelManager hotelManager;

    // default constructor
    public Hotel() {
    }

    // consturctor with class parameters
    public Hotel(int id, String name, String address, String mail, String phone, String star, boolean wifi, boolean car_park, boolean pool, boolean fitness, boolean concierge, boolean spa, boolean room_service) {
        HotelManager hotelManager = new HotelManager();
        Pension pension = new Pension();
        this.id = id;
        this.name = name;
        this.address = address;
        this.mail = mail;
        this.phone = phone;
        this.star = star;
        this.wifi = wifi;
        this.car_park = car_park;
        this.pool = pool;
        this.fitness = fitness;
        this.concierge = concierge;
        this.spa = spa;
        this.room_service = room_service;
    }

    // getter and setter methods

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getStar() {
        return star;
    }

    public void setStar(String star) {
        this.star = star;
    }

    public boolean isWifi() {
        return wifi;
    }

    public void setWifi(boolean wifi) {
        this.wifi = wifi;
    }

    public boolean isCar_park() {
        return car_park;
    }

    public void setCar_park(boolean car_park) {
        this.car_park = car_park;
    }

    public boolean isPool() {
        return pool;
    }

    public void setPool(boolean pool) {
        this.pool = pool;
    }

    public boolean isFitness() {
        return fitness;
    }

    public void setFitness(boolean fitness) {
        this.fitness = fitness;
    }

    public boolean isConcierge() {
        return concierge;
    }

    public void setConcierge(boolean concierge) {
        this.concierge = concierge;
    }

    public boolean isSpa() {
        return spa;
    }

    public void setSpa(boolean spa) {
        this.spa = spa;
    }

    public boolean isRoom_service() {
        return room_service;
    }

    public void setRoom_service(boolean room_service) {
        this.room_service = room_service;
    }

    public ComboItem getComboItem() {
        return new ComboItem(this.getId(), this.getName());
    }

    // stringify method
    // logic of this method is for us to see the contents of an object as a string with a meaning.
    // generally, .toString() method provides you an unclear prints such as memory sector.
    // with the logic above, we need to see the print with a meaning.
    // that is why, we need do exclude .toString() method with @Override and add this method for a meaningful output instead.
    @Override
    public String toString() {
        return "Hotel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", star='" + star + '\'' +
                ", wifi=" + wifi +
                ", car_park=" + car_park +
                ", pool=" + pool +
                ", fitness=" + fitness +
                ", concierge=" + concierge +
                ", spa=" + spa +
                ", room_service=" + room_service +
                '}';
    }

}
