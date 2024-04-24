package business;

import core.Helper;
import dao.UserDao;
import entity.User;

import java.util.ArrayList;


public class UserManager {

    // new object for access to userdao (db)
    private final UserDao userDao;

    //constructor
    public UserManager() {
        this.userDao = new UserDao();
    }

    // function that lets us find a user by username and pass
    public User findByLoging(String username, String password) {
        return this.userDao.findByLogin(username, password);

    }

    // the method that provides necessary information for table with generic data type as Object[] so, the array list may take
    // different type of objects
    public ArrayList<Object[]> getForTable(int size, ArrayList<User> modelList) {
        ArrayList<Object[]> modelObjList = new ArrayList<>();
        for (User obj : modelList) {
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getUsername();
            rowObject[i++] = obj.getPassword();
            rowObject[i++] = obj.getRole();
            modelObjList.add(rowObject);
        }
        return modelObjList;

    }

    // function gets all users
    public ArrayList<User> findAll() {
        return this.userDao.findAll();
    }

    //function for adding user records to db
    public boolean save(User user) {
        if (this.getById(user.getId()) != null) {
            Helper.showMsg("error");
            return false;
        }
        return this.userDao.save(user);
    }

    //function for update user records on db
    public boolean update(User user) {
        if (this.getById(user.getId()) == null) {
            Helper.showMsg(user.getId() + "ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.update(user);
    }

    // function that gets the user with specific id
    public User getById(int id) {
        return this.userDao.getByID(id);
    }


    // deletion function for user
    public boolean delete(int id) {
        if (this.getById(id) == null) {
            Helper.showMsg(id + " ID kayıtlı model bulunamadı");
            return false;
        }
        return this.userDao.delete(id);
    }

    // function for user search according to role
    public ArrayList<User> searchForTable(String role) {
        // assuming there are going to be multiple users, the list will be held in an arraylist
        String select = "SELECT * FROM public.user";
        // since the role is a string, generic type is being assigned as strimg
        ArrayList<String> whereList = new ArrayList<>();
        if (role != null) {
            // if user role is not null, sql stynthax is being added to query string like user_role='admin' by encapsulating
            // the query string
            whereList.add("user_role='" + role + "'");
        }

        // creating a single string named wherestr by joining the elements of the wherelist
        String whereStr = String.join(" AND ", whereList);
        String query = select;
        // if there is no empty strinng, then WHERE clause is being appended to query
        if (whereStr.length() > 0) {
            query += " WHERE " + whereStr;
        }

        //then the query is passed to the selectbyquery method of userdao and relevant user data from db and returns
        // an arraylist.
        return this.userDao.selectByQuery(query);
    }
}