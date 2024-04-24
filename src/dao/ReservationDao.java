package dao;

import core.Database;
import entity.Reservation;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class ReservationDao {

    private final Connection connection;

    // constructor for access to database
    public ReservationDao() {
        this.connection = Database.getInstance();
    }

    // getter function of reservations by hotel id
    public ArrayList<Reservation> getReservationByOtelId(int otelId) {
        ArrayList<Reservation> reservations = new ArrayList<>();
        String query = "SELECT * FROM public.reservation WHERE room_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            // parameter assignment
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();
            // once sql query returns as a result set
            // and while rs has next item, is being added to reservation list through match method and returns to array list
            // through reservations return.
            while (rs.next()) {
                Reservation reservation = match(rs);
                reservations.add(reservation);
            }
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // reservations return
        return reservations;
    }

    // getter method by id
    public Reservation getByID(int id) {
        Reservation obj = null;
        String query = "SELECT * FROM public.reservation WHERE id = ?";
        try {
            PreparedStatement pr = this.connection.prepareStatement(query);
            // parameter assignment
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            // while query result has a next item, object is being returned through match function.
            if (rs.next()) {
                obj = this.match(rs);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // obj return phase
        return obj;
    }


    // matcher function for matching resultset and reservation obj
    //logic of match block is, the datas coming from result set, are being assigning to related objects
    public Reservation match(ResultSet rs) throws SQLException {
        //logic of match block is, the datas coming from result set, are being assigning to related objects
        Reservation obj = new Reservation();
        obj.setId(rs.getInt("id"));
        obj.setRoom_id(rs.getInt("room_id"));
        obj.setCheck_in_date(LocalDate.parse(rs.getString("check_in_date")));
        obj.setTotal_price(rs.getDouble("total_price"));
        obj.setGuest_count(rs.getInt("guest_count"));
        obj.setGuest_name(rs.getString("guest_name"));
        obj.setGuess_citizen_id(rs.getString("guest_citizen_id"));
        obj.setGuess_mail(rs.getString("guest_mail"));
        obj.setGuess_phone(rs.getString("guest_phone"));
        obj.setCheck_out_date(LocalDate.parse(rs.getString("check_out_date")));
        return obj;
    }

    // getter method for all reservations
    public ArrayList<Reservation> findAll() {
        ArrayList<Reservation> reservationList = new ArrayList<>();
        String sql = "SELECT * FROM public.reservation";
        try {
            // while resultset has next, values are being added to reservation listh through match method and
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            while (rs.next()) {

                reservationList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        // returns as reservation list into array list
        return reservationList;
    }

    // adder method for reservations
    public boolean save(Reservation reservation){
        String query = "INSERT INTO public.reservation"+
                "("+
                "room_id,"+
                "check_in_date," +
                "total_price,"+
                "guest_count,"+
                "guest_name,"+
                "guest_citizen_id,"+
                "guest_mail,"+
                "guest_phone,"+
                "check_out_date"+
                ")"+
                "VALUES (?,?,?,?,?,?,?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            // parameters assignments
            pr.setInt(1,reservation.getRoom_id());
            pr.setDate(2,Date.valueOf(reservation.getCheck_in_date()));
            pr.setDouble(3,reservation.getTotal_price());
            pr.setInt(4,reservation.getGuest_count());
            pr.setString(5,reservation.getGuest_name());
            pr.setString(6,reservation.getGuest_citizen_id());
            pr.setString(7,reservation.getGuest_mail());
            pr.setString(8,reservation.getGuest_phone());
            pr.setDate(9,Date.valueOf(reservation.getCheck_out_date()));
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is saved.
            return  pr.executeUpdate() != -1;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return true;
    }

    // reservation deletion method
    public boolean delete(int hotel_id){
        try{
            String query = "DELETE FROM public.reservation WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            // parameter assignment
            pr.setInt(1,hotel_id);
            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is deleted.
            return pr.executeUpdate() != -1;
        }catch (SQLException e){
            // if try block fails, an exception will be thrown
            e.printStackTrace();
        }
        return true;
    }

    // getter for a specific reservation id
    public ArrayList<Reservation> getByReservationId(int id){
        return this.selectByQuery("SELECT * FROM public.reservation WHERE id="+id);
    }

    // reservation getter by query
    public ArrayList<Reservation> selectByQuery(String query){
        ArrayList<Reservation> resList=new ArrayList<>();
        try {
            // while result set has next, reservation list will be added through match method
            ResultSet rs=this.connection.createStatement().executeQuery(query);
            while (rs.next()){
                resList.add(this.match(rs));

            }
            // if try block fails, exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resList;
    }


    // reservation updater function
    public boolean update (Reservation reservation){
        try{
            String query = "UPDATE public.reservation SET " +
                    "room_id = ?,"+
                    "check_in_date = ?," +
                    "total_price = ?,"+
                    "guest_count = ?,"+
                    "guest_name = ?,"+
                    "guest_citizen_id = ?,"+
                    "guest_mail = ?,"+
                    "guest_phone = ?,"+
                    "check_out_date = ? "+
                    "WHERE id = ?";
            // parameter assignments
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,reservation.getRoom_id());
            pr.setDate(2,Date.valueOf(reservation.getCheck_in_date()));
            pr.setDouble(3,reservation.getTotal_price());
            pr.setInt(4,reservation.getGuest_count());
            pr.setString(5,reservation.getGuest_name());
            pr.setString(6,reservation.getGuest_citizen_id());
            pr.setString(7,reservation.getGuest_mail());
            pr.setString(8,reservation.getGuest_phone());
            pr.setDate(9,Date.valueOf(reservation.getCheck_out_date()));
            pr.setInt(10,reservation.getId());
            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is updated.
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }

}

