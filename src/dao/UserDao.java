package dao;

import core.Database;

import core.Helper;
import entity.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class UserDao {
    private final Connection con;

    // constructor for access to database
    public UserDao() {
        this.con = Database.getInstance();
    }

    // method that brings all users
    public ArrayList<User> findAll() {
        ArrayList<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM public.user";
        try {
            ResultSet rs = this.con.createStatement().executeQuery(sql);
            while (rs.next()) {
                // while query result has a next item, object is being returned through match function.
                userList.add(this.match(rs));
            }
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // method that finds user according to username and password
    public User findByLogin(String username, String password) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_name = ? AND user_pass = ?";
        try {
            // parameter assignments
            PreparedStatement pr = this.con.prepareStatement(query);
            pr.setString(1, username);
            pr.setString(2, password);
            // executequery method lets you run the sql query over database.
            // below, query has been made and results are added to a result set.
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

    // matcher function for matching resultset and user obj
    //logic of match block is, the datas coming from result set, are being assigning to related objects
    public User match(ResultSet rs) throws SQLException {
        User obj = new User();
        obj.setId(rs.getInt("user_id"));
        obj.setUsername(rs.getString("user_name"));
        obj.setPassword(rs.getString("user_pass"));
        obj.setRole(rs.getString("user_role"));
        return obj;
    }

    // user adder method
    public boolean save(User user) {
        String query = "INSERT INTO public.user (user_name , user_pass , user_role) VALUES (?,?, ?)";
        try {
            PreparedStatement pr = con.prepareStatement(query);
            // parameter assignments
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword());
            pr.setString(3, user.getRole());
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is added.
            return pr.executeUpdate() != -1;
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }



    // user deleter
    public boolean delete(int model_id) {
        try {
            String query = "DELETE FROM public.user WHERE user_id = ?";
            PreparedStatement pr = con.prepareStatement(query);
            // parameter assignment
            pr.setInt(1, model_id);
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is deleted.
            return pr.executeUpdate() != -1;
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // getter method with query
    public ArrayList<User> selectByQuery(String query) {
        ArrayList<User> userList = new ArrayList<>();
        try {
            // while query result has a next item, object is being returned through match function.
            ResultSet rs = this.con.createStatement().executeQuery(query);
            while (rs.next()) {
                userList.add(this.match(rs));

            }
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }

    // user updater method
    public boolean update(User user) {
        try {
            String query = "UPDATE public.user SET " +
                    "user_name = ?," +
                    "user_pass = ?," +
                    "user_role = ?" +
                    "WHERE user_id = ?";

            // parameter updated
            PreparedStatement pr = con.prepareStatement(query);
            pr.setString(1, user.getUsername());
            pr.setString(2, user.getPassword().toString());
            pr.setString(3, user.getRole());
            pr.setInt(4, user.getId());
            // checks if return is not equals to -1. Thus, if it is not equals to -1, that means there is a
            //row affected with this action and row is updated.
            return pr.executeUpdate() != -1;
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return true;
    }

    // getter function of user with specifid id
    public User getByID(int id) {
        User obj = null;
        String query = "SELECT * FROM public.user WHERE user_id = ?";
        try {
            // parameter assignments
            PreparedStatement pr = con.prepareStatement(query);
            pr.setInt(1, id);
            ResultSet rs = pr.executeQuery();
            // checks if rs has next item, a user obj is being created through match method
            if (rs.next()) obj = this.match(rs);
            // if try block fails, an exception will be thrown
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return obj;
    }
}


