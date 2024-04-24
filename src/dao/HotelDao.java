package dao;

import core.Database;
import entity.Hotel;
import entity.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class HotelDao {
    private final Connection connection;

    // constructor for access to database
    public HotelDao() {

        this.connection = Database.getInstance();
    }


    // brings the hotel with specific id
    public Hotel getByID(int id) {
        Hotel obj = null;
        String query = "SELECT * FROM public.hotel WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }


    // match for hotel obj and resultset
    //logic of match block is, the datas coming from result set, are being assigning to related objects
    public Hotel match(ResultSet rs) throws SQLException {
        Hotel obj = new Hotel();
        obj.setId(rs.getInt("id"));
        obj.setName(rs.getString("name"));
        obj.setAddress(rs.getString("address"));
        obj.setMail(rs.getString("mail"));
        obj.setPhone(rs.getString("phone"));
        obj.setStar(rs.getString("star"));
        obj.setCar_park(rs.getBoolean("car_park"));
        obj.setWifi(rs.getBoolean("wifi"));
        obj.setPool(rs.getBoolean("pool"));
        obj.setFitness(rs.getBoolean("fitness"));
        obj.setConcierge(rs.getBoolean("concierge"));
        obj.setSpa(rs.getBoolean("spa"));
        obj.setRoom_service(rs.getBoolean("room_service"));

        return obj;
    }

    // the function that brings all hotels
    public ArrayList<Hotel> findAll() {
        ArrayList<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                hotelList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return hotelList;
    }


    // hotel save function. sends/inserts records to db
    public boolean save(Hotel hotel) {
        String query = "INSERT INTO public.hotel " +
                "(" +
                "name," +
                "mail," +
                "phone," +
                "address," +
                "star,"+
                "car_park," +
                "wifi," +
                "pool," +
                "fitness," +
                "concierge," +
                "spa," +
                "room_service " +
                ")" +
                " VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            // according to the query above, assignments for indexes and hotel parameters
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getMail());
            pr.setString(3, hotel.getPhone());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isCar_park());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoom_service());
            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is saved.
            return pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // function for hotel deletion
    public boolean delete(int model_id){
        try{
            String query = "DELETE FROM public.hotel WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,model_id);

            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is deleted.
            return pr.executeUpdate() != -1;
        }catch (SQLException e){

            e.printStackTrace();
        }
        return true;
    }

    // function for hotel update
    public boolean update(Hotel hotel) {
        try {
            String query = "UPDATE public.hotel SET " +
                    "name," +
                    "mail," +
                    "phone," +
                    "address," +
                    "star,"+
                    "car_park," +
                    "wifi," +
                    "pool," +
                    "fitness," +
                    "concierge," +
                    "spa," +
                    "room_service " +
                    "WHERE hotel = ?";
            // according to the query above, assignments for indexes and hotel parameters
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setString(1, hotel.getName());
            pr.setString(2, hotel.getMail());
            pr.setString(3, hotel.getPhone());
            pr.setString(4, hotel.getAddress());
            pr.setString(5, hotel.getStar());
            pr.setBoolean(6, hotel.isCar_park());
            pr.setBoolean(7, hotel.isWifi());
            pr.setBoolean(8, hotel.isPool());
            pr.setBoolean(9, hotel.isFitness());
            pr.setBoolean(10, hotel.isConcierge());
            pr.setBoolean(11, hotel.isSpa());
            pr.setBoolean(12, hotel.isRoom_service());

            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is updated.
            return pr.executeUpdate() != -1;

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
