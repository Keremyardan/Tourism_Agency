package dao;

import core.Database;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class PensionDao {

    private final Connection connection;

    // constructor for access to database
    public PensionDao() {
        this.connection = Database.getInstance();
    }

    // function for bringing the pension information by a hotel id
    public ArrayList<Pension> getPensionByOtelId(int id) {
        ArrayList<Pension> pensions = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_pension WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            // after query search, parameters are being assigned
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            // while rs has next, match method will be executed for pension object and pensions arraylist will be
            // added to pension
            while (rs.next()) {
                Pension pension = match(rs);
                pensions.add(pension);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pensions;
    }

    // getter function for pension which has a specific id
    public Pension getByID(int id) {
        Pension obj = null;
        // query
        String query = "SELECT * FROM public.hotel_pension WHERE id = ? ";
        try {
            // parameter assignment
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            // while rs has next objects, match method will be executed
            if (rs.next()) {
                obj = this.match(rs);
            }
            // if there is a problem with try block, then an exception will be thrown.
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // matcher function for matching resultset and pension obj
    //logic of match block is, the datas coming from result set, are being assigning to related objects
    public Pension match(ResultSet rs) throws SQLException {

        Pension obj = new Pension();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setPension_type(rs.getString("pension_type"));


        return obj;
    }

    // function for pension update
    public boolean update(Pension pension) {
        try {
            String query = "UPDATE public.hotel_pension SET " +
                    "hotel_id = ?," +
                    "pension_type = ?" +
                    "WHERE user_id = ?";

            PreparedStatement pr = connection.prepareStatement(query);
            // parameter assignments
            pr.setInt(1,pension.getHotel_id());
            pr.setString(2,pension.getPension_type());
            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is updatedd.
            return pr.executeUpdate() != -1;

            // if a problem occurs, sql exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // function to bring all pensions
    public ArrayList<Pension> findAll() {
        ArrayList<Pension> pensionList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_pension";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            // once sql query returns as a result set,
            // whlile rs has next item, is being added to pension list through match method and returns to array list
            // through pensionlist return.
            while (rs.next()) {

                pensionList.add(this.match(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pensionList;
    }

    // function to add pensions
    public boolean save(Pension pension){
        String query = "INSERT INTO public.hotel_pension"+
                "("+
                "hotel_id,"+
                "pension_type"+
                ")"+
                "VALUES (?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            // parameters assignment
            pr.setInt(1,pension.getHotel_id());
            pr.setString(2,pension.getPension_type());
            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is saved.
            return  pr.executeUpdate() != -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // function for pension deletion by id
    public boolean delete(int hotel_id){
        try{
            String query = "DELETE FROM public.hotel_pension WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            pr.setInt(1,hotel_id);
            // checks if return is not equasl to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is deleted.
            return pr.executeUpdate() != -1;
        }catch (SQLException throwables){
            throwables.printStackTrace();
        }
        return true;
    }
}