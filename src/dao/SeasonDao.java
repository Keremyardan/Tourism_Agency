package dao;

import core.Database;
import entity.Season;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;


public class SeasonDao {
    private final Connection connection;

    // constructor for access to database
    public SeasonDao() {
        this.connection = Database.getInstance();
    }

    // function for having hotel seasons by hotel id
    public ArrayList<Season> getSeasonsByOtelId(int otelId) {
        ArrayList<Season> seasons = new ArrayList<>();
        String query = "SELECT * FROM public.hotel_season WHERE hotel_id = ?";

        try (PreparedStatement pr = connection.prepareStatement(query)) {
            // parameter assignment
            pr.setInt(1, otelId);
            ResultSet rs = pr.executeQuery();
            // while query result has a next item, object is being returned through match function.
            while (rs.next()) {
                Season season = match(rs);
                seasons.add(season);
            }
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasons;
    }

    // getter method for a season with spesific id
    public Season getSeasonByID(int id) {
        Season obj = null;
        String query = "SELECT * FROM public.hotel_season WHERE id = ? ";
        try {
            // parameter assignments
            PreparedStatement pr = this.connection.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            if (rs.next()) {
                // while query result has a next item, object is being returned through match function.
                obj = this.match(rs);
            }
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }

    // matcher function for matching resultset and season obj
    //logic of match block is, the datas coming from result set, are being assigning to related objects
    public Season match(ResultSet rs) throws SQLException {
        Season obj = new Season();
        obj.setId(rs.getInt("id"));
        obj.setHotel_id(rs.getInt("hotel_id"));
        obj.setStart_date(LocalDate.parse(rs.getString("start_date")));
        obj.setFinish_date(LocalDate.parse(rs.getString("finish_date")));


        return obj;
    }

    // getter method for all seasons
    public ArrayList<Season> findAll() {
        ArrayList<Season> seasonList = new ArrayList<>();
        String sql = "SELECT * FROM public.hotel_season";
        try {
            ResultSet rs = this.connection.createStatement().executeQuery(sql);
            // while result set has next, values are being added to season list through match method and
            // returns as season list into array list
            while (rs.next()) {

                seasonList.add(this.match(rs));
            }
            // if try block fails an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return seasonList;
    }

    // season adder method
    public boolean save(Season season){
        String query = "INSERT INTO public.hotel_season"+
                "("+
                "hotel_id,"+
                "start_date," +
                "finish_date"+
                ")"+
                "VALUES (?,?,?)";
        try {
            PreparedStatement pr = connection.prepareStatement(query);
            // parameter assignment
            pr.setInt(1,season.getHotel_id());
            pr.setDate(2, Date.valueOf(season.getStart_date()));
            pr.setDate(3, Date.valueOf(season.getFinish_date()));
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is added.
            return  pr.executeUpdate() != -1;

            // if try bloc fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // season deleter method
    public boolean delete(int hotel_id){
        try{
            String query = "DELETE FROM public.hotel_season WHERE id = ?";
            PreparedStatement pr = connection.prepareStatement(query);
            // parameter assignment
            pr.setInt(1,hotel_id);
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is deleted.
            return pr.executeUpdate() != -1;
            // if try block fails, an exception will be thrown
        }catch (SQLException e){
            e.printStackTrace();
        }
        return true;
    }
}


