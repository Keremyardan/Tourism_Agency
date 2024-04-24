package view;

import business.UserManager;
import core.Helper;
import entity.User;

import javax.swing.*;

public class LoginView extends Layout{
    private JPanel wrapper;
    private JTextField tf_user;
    private JPasswordField tf_pass;
    private JButton btn_login;
    private JLabel lbl_user;
    private JLabel lbl_pass;
    private JPanel w_top;
    private JPanel w_bot;
    private UserManager userManager;


    // login view constructor
    public LoginView(){
        this.add(wrapper);
        this.visualInitialize(450,450);
        this.userManager = new UserManager();


        // a listener assigned to login button
        btn_login.addActionListener(e -> {
            JTextField[] checkFieldList = {this.tf_user,this.tf_pass};
            // checks if necessary fields are filled
            if (Helper.isFieldListEmpty(checkFieldList)){
                Helper.showMsg("fill");

                // if credentials are not true ( if user could not be found), an error message will be thrown
            }else {
                User loginUser = this.userManager.findByLoging(this.tf_user.getText(),this.tf_pass.getText());
                if (loginUser == null){
                    Helper.showMsg("notFound");
                }else {
                    // if user credentials are corresponds with admin information, admin view will be shown
                    if (loginUser.getRole().equals("admin")){
                        AdminView adminView = new AdminView(loginUser);
                        dispose();
                        // if user credentials are corresponds with employee information, employee view will be shown
                    }else {
                        EmployeeView employeeView = new EmployeeView(loginUser);
                        dispose();
                    }
                }
            }
        });
    }
}