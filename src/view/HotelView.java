package view;

import business.HotelManager;
import core.Helper;
import entity.Hotel;

import javax.swing.*;

// variables
public class HotelView extends Layout {
    private JPanel wrap;
    private JLabel lbl_otel_save;
    private JTextField tf_name;
    private JTextField tf_mail;
    private JTextField tf_phone;
    private JTextField tf_adress;
    private JPanel pnl_left;
    private JRadioButton rbut_carpark;
    private JRadioButton rbut_wifi;
    private JRadioButton rbut_swim;
    private JRadioButton rbut_gym;
    private JRadioButton rbut_concierge;
    private JRadioButton rbut_spa;
    private JRadioButton rbut_roomservices;
    private JButton btn_save_add_menu;
    private JComboBox tf_star;
    private Hotel hotel;
    private HotelManager hotelManager;


    // hotel add section // constructor
    public HotelView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.add(wrap);
        // frame initialization
        this.visualInitialize(500, 500);
        if(this.hotel.getId() != 0) {
            // if id is different from zero,
            // which means available in db,
            // information will be shown in visual interface
            // and lets user set it
            this.tf_name.setText(this.hotel.getName());
            this.tf_mail.setText(this.hotel.getMail());
            this.tf_phone.setText(this.hotel.getPhone());
            this.tf_adress.setText(this.hotel.getAddress());
            this.tf_star.setSelectedItem(this.hotel.getStar());
        }
        btn_save_add_menu.addActionListener(e -> {

            // a jtextfield is created to check if all necessary fields are filled
            JTextField[] checkFieldList = {this.tf_name, this.tf_mail, this.tf_phone, this.tf_adress};
            // if fields are empty, a message will be thrown
            if (Helper.isFieldListEmpty(checkFieldList)) {
                Helper.showMsg("fill");
            // checks if record is successful.
            } else {
                boolean result = true;
                this.hotel.setName(this.tf_name.getText());
                this.hotel.setMail(this.tf_mail.getText());
                this.hotel.setPhone(this.tf_phone.getText());
                this.hotel.setAddress(this.tf_adress.getText());
                this.hotel.setStar((String)this.tf_star.getSelectedItem());
                this.hotel.setCar_park(this.rbut_carpark.isSelected());
                this.hotel.setWifi(this.rbut_wifi.isSelected());
                this.hotel.setPool(this.rbut_swim.isSelected());
                this.hotel.setFitness(this.rbut_gym.isSelected());
                this.hotel.setConcierge(this.rbut_concierge.isSelected());
                this.hotel.setSpa(this.rbut_spa.isSelected());
                this.hotel.setRoom_service(this.rbut_roomservices.isSelected());
                result = this.hotelManager.save(hotel);

                // if proccess is successful, a done message is being thrown and
                // window disposed
                if (result) {
                    Helper.showMsg("done");

                    dispose();

                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }
}




