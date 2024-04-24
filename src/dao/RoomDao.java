package dao;

import core.Database;
import entity.Pension;
import entity.Room;
import entity.User;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class RoomDao {
    private final Connection connection;

    // constructor for access to database
    public RoomDao() {
        this.connection = Database.getInstance();
    }

    // getter function of rooms with specifid id
    public Room getByID(int id) {
        Room obj = null;
        String query = "SELECT * FROM public.room WHERE id = ? ";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            // parameter assignment
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                // while query result has a next item, object is being returned through match function.
                obj = this.match(rs);
            }
            // if try block fails an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // matcher function for matching resultset and room obj
    //logic of match block is, the datas coming from result set, are being assigning to related objects
    public Room match(ResultSet rs) throws SQLException {
        Room obj = new Room();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_id(rs.getInt("pension_id"));
        obj.setSeason_id(rs.getInt("season_id"));
        obj.setType(rs.getString("type"));
        obj.setStock(rs.getInt("stock"));
        obj.setAdult_price(rs.getDouble("adult_price"));
        obj.setChild_price(rs.getDouble("child_price"));
        obj.setBed_capacity(rs.getInt("bed_capacity"));
        obj.setSquare_meter(rs.getInt("square_meter"));
        obj.setTelevision(rs.getBoolean("television"));
        obj.setMinibar(rs.getBoolean("minibar"));
        obj.setGame_console(rs.getBoolean("game_console"));
        obj.setProjection(rs.getBoolean("projection"));
        return obj;
    }


    // getter method for all rooms
    public ArrayList<Room> findAll() {
        ArrayList<Room> roomList = new ArrayList<>();
        String sql = "SELECT * FROM public.room";
        try {
            // while result set has next, values are being added to room list through match method and
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                roomList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // returns as roomlist list into array list
        return roomList;
    }

    // adder for room class
    public boolean save(Room room) {
        String query = "INSERT INTO public.room" +
                "(" +
                "hotel_id," +
                "pension_id," +
                "season_id," +
                "type," +
                "stock," +
                "adult_price," +
                "child_price," +
                "bed_capacity," +
                "square_meter," +
                "television," +
                "minibar," +
                "game_console," +
                "cash_box," +
                "projection" +
                ")" +
                "VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is added.
            return pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // getter method with query
    public ArrayList<Room> selectByQuery(String query){
        ArrayList<Room> roomList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(query);
            // while result set has next, room list will be added through match method
            while (rs.next()){
                roomList.add(this.match(rs));
            }
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomList;
    }

    // stock uptader method
    public boolean updateStock(Room room){
        String query = "UPDATE public.room SET stock = ? WHERE id = ? ";
        try {
            // parameter assignments
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, room.getStock());
            pr.setInt(2,room.getId());
            // execute updater
            pr.executeUpdate();
            // if try block fails an exception will be thrown
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }

    // room deleter method
    public boolean delete(int hotel_id) {
        try {
            String query = "DELETE FROM public.room WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            // parameter assignments
            pr.setInt(1, hotel_id);
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is deleted.
            return pr.executeUpdate() != -1;
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return true;
    }


    // updater method for room
    public boolean update(Room room) {
        try {
            String query = "UPDATE public.room SET " +
                    "hotel_id = ?," +
                    "pension_id = ?," +
                    "season_id= ?," +
                    "type= ?," +
                    "stock= ?," +
                    "adult_price = ?," +
                    "child_price = ?," +
                    "bed_capacity = ?," +
                    "square_meter = ?," +
                    "television = ?," +
                    "minibar = ?," +
                    "game_console = ?," +
                    "cash_box = ?," +
                    "projection = ? " +
                    "WHERE id = ? ";
            // parameter assignments
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1, room.getHotel_id());
            pr.setInt(2, room.getPension_id());
            pr.setInt(3, room.getSeason_id());
            pr.setString(4, room.getType());
            pr.setInt(5, room.getStock());
            pr.setDouble(6, room.getAdult_price());
            pr.setDouble(7, room.getChild_price());
            pr.setInt(8, room.getBed_capacity());
            pr.setInt(9, room.getSquare_meter());
            pr.setBoolean(10, room.isTelevision());
            pr.setBoolean(11, room.isMinibar());
            pr.setBoolean(12, room.isGame_console());
            pr.setBoolean(13, room.isCash_box());
            pr.setBoolean(14, room.isProjection());
            pr.setInt(15, room.getId());
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is updated.
            return pr.executeUpdate() != -1;

            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }



}

