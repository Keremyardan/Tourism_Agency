package entity;

// variables
public class User {
    private int id;
    private String username;
    private String password;
    private String role;


    // default constructor method
    public User() {

    }


    // constructor method with class parameters
    public User(int id, String username, String pass, String role) {
        this.id = id;
        this.username = username;
        this.password = pass;
        this.role = role;
    }


    // getter and setter methods
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String pass) {
        this.password = pass;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    // stringify method
    // logic of this method is for us to see the contents of an object as a string with a meaning.
    // generally, .toString() method provides you an unclear prints such as memory sector.
    // with the logic above, we need to see the print with a meaning.
    // that is why, we need do exclude .toString() method with @Override and add this method for a meaningful output instead.
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
