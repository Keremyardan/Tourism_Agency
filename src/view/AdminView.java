package view;

import business.UserManager;
import core.ComboItem;
import core.Helper;
import entity.User;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.ArrayList;

// variables for admin view
public class AdminView extends Layout {

    private JPanel wrapper;
    private JTabbedPane tabbedPane1;
    private JLabel lbl_welcome;
    private JButton LOGOUTButton;
    private JTable tbl_user;
    private JTextField tf_username;
    private JTextField tf_pass;
    private JComboBox<ComboItem> cmb_user_role;
    private JButton btn_add;
    private JButton DELETEButton;
    private JComboBox cmb_users;
    private JButton btn_search_user;
    private JScrollPane scrl_user;
    private JButton btn_new_user;
    private JButton btn_clear_user;
    private JPanel w_top;
    private JPanel w_bot;
    private User user;
    private UserManager userManager;
    private DefaultTableModel tmdl_user = new DefaultTableModel();
    private Object[] col_user ;
    private JPopupMenu user_menu;


    // constructor method
    public AdminView(User user) {
        // creating a new obj for popup menu
        this.user_menu = new JPopupMenu();
        this.col_user = col_user;
        this.userManager = new UserManager();
        this.add(wrapper);
        // visual interface loder with pixel values
        this.visualInitialize(1000, 500);
        this.user = user;

        // disposal upon user has no values. closes the window
        if (user == null) {
            dispose();

        }

        // welcome label with username
        this.lbl_welcome.setText("Welcome : " + this.user.getUsername());

        //user table loading and row selection added
        loadUserTable(null);
        tableRowSelect(tbl_user);

        // listener for log out button and window disposal
        LOGOUTButton.addActionListener(e -> {
            LoginView loginView = new LoginView();
            dispose();
        });

        // listener for add button
        btn_add.addActionListener(e -> {
            // checks if field is empty.
            if (Helper.isFieldListEmpty(new JTextField[]{tf_username, tf_pass})) {
                // if it is, a fill message will be shown
                Helper.showMsg("fill");
            } else {
                // if, if block fails and which means if the field is not empty,
                //  a new user obj will be created.
                boolean result;
                User userr = new User();
                // below we assign the value of userupdate's return to userr
                if (getUserUpdated() != null){
                    userr = getUserUpdated();
                }

                // username and user password are being taken as string and  user role value is being taken by combo box
                userr.setUsername(tf_username.getText());
                userr.setPassword(tf_pass.getText());
                userr.setRole((String) cmb_user_role.getSelectedItem());

                // if block for update
                if (btn_add.getText().equals("UPDATE")){
                    result = userManager.update(userr);
                // else block for user save as it is
                }else{
                    result = userManager.save(userr);
                }

                // if user recorded, a done message will be shown
                // and user table will be cleared
                if (result) {
                    Helper.showMsg("done");
                    loadUserTable(null);
                    // if else block is being executed, an error message will be shown
                } else {
                    Helper.showMsg("error");

                }
            }
        });

        // action listener for delete process
        DELETEButton.addActionListener(e -> {
            // a confirmation message will be shown to ask client whether he is sure or not.
            if(Helper.confirm("sure")){
                // selected row will be assigned as selecteduserid
                int selectUserId = getTableSelectedRow(tbl_user,0);
                // selected id will be deletedand table will be cleared after shown of done message
                if (userManager.delete(selectUserId)){
                    Helper.showMsg("done");
                    loadUserTable(null);
                }else{
                    // if else block runs, an error message will be shown
                    Helper.showMsg("error");
                }
            }
        });

        // listener for search button
        btn_search_user.addActionListener(e -> {
            // taking the values of clients choice
            String selectedUser= (String) this.cmb_users.getSelectedItem();
            // user's choices are being recorded in an arraylist
            ArrayList<User> userListBySearch=this.userManager.searchForTable(selectedUser);
            ArrayList<Object[]> userRowListBySearch=this.userManager.getForTable(col_user.length,userListBySearch);
            loadUserTable(userRowListBySearch);

        });
        // calling tablerowselect in user table for a row selection
        tableRowSelect(tbl_user);

        // add listener to new user button
        this.btn_new_user.addActionListener(e -> {
            this.tf_username.setEnabled(true);
            this.tf_pass.setEnabled(true);
            this.cmb_user_role.setEnabled(true);
            this.btn_add.setEnabled(true);
            this.tf_username.setText(null);
            this.tf_pass.setText(null);
            this.cmb_user_role.setSelectedItem("ADMIN");
            this.btn_add.setText("ADD");
            // ıser clarification
            setUserUpdated(null);

        });

        // update method for user update action
        this.user_menu.add("Update").addActionListener(e -> {
            this.tf_username.setEnabled(true);
            this.tf_pass.setEnabled(true);
            this.cmb_user_role.setEnabled(true);
            this.btn_add.setEnabled(true);
            int selectUserId = this.getTableSelectedRow(tbl_user,0);
            User userUpdate = this.userManager.getById(selectUserId);
            this.tf_username.setText(userUpdate.getUsername());
            this.tf_pass.setText(userUpdate.getPassword());
            this.cmb_user_role.setSelectedItem(userUpdate.getRole());
            this.btn_add.setText("UPDATE");
            setUserUpdated(userUpdate);
        });
        // popup addition
        tbl_user.setComponentPopupMenu(user_menu);



        // action listeners for clear button
        btn_clear_user.addActionListener(e -> {
           cmb_users.setSelectedItem(null);
           loadUserTable(null);
        });
    }

    // table initialization for user
    public void loadUserTable(ArrayList<Object[]> userList) {

        this.col_user = new Object[] {"ID", "Username", "Password", "Role"};
        if(userList==null){
            userList=this.userManager.getForTable(this.col_user.length,this.userManager.findAll());
        }
        createTable(this.tmdl_user,this.tbl_user,col_user,userList);
    }

    // function enables row selection
    public void tableRowSelect(JTable table){
        table.addMouseListener(new MouseAdapter() {


            @Override
            public void mouseReleased(MouseEvent e) {
                int selected_row = table.rowAtPoint(e.getPoint());
                table.setRowSelectionInterval(selected_row,selected_row);
            }
        });
    }


    private void createUIComponents() {
    }
    private User getUserUpdated (){
        return user;

    }
    private void setUserUpdated (User user) {
        this.user = user;
    }

}

