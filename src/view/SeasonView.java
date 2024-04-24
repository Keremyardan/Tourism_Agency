 package view;

import business.HotelManager;
import business.SeasonManager;
import core.ComboItem;
import core.Helper;
import entity.Hotel;
import entity.Season;

import javax.swing.*;
import javax.swing.text.MaskFormatter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SeasonView extends Layout {
    private JPanel wrap;
    private JPanel wrap_season;
    private JButton btn_save_season;
    private JLabel lbl_hotel_id;
    private JFormattedTextField tf_season_start;
    private JFormattedTextField tf_season_finish;
    private JComboBox cmb_hotel_season;
    private HotelManager hotelManager;
    private Hotel hotel;
    private SeasonManager seasonManager;
    private Season season;

    // constructor
    public SeasonView() {
        this.hotelManager = new HotelManager();
        this.hotel = new Hotel();
        this.seasonManager = new SeasonManager();
        this.season = new Season();
        this.cmb_hotel_season.getSelectedItem();
        this.add(wrap);
        this.visualInitialize(375, 300);

        // add all hotel names to combobox
        for (Hotel hotel : this.hotelManager.findAll()) {
            this.cmb_hotel_season.addItem(hotel.getComboItem());
        }

        // button save listener. season start date, end date, and hotel id
        // will be added to db
        btn_save_season.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                boolean result = false;
                ComboItem selectSeason = (ComboItem) cmb_hotel_season.getSelectedItem();
                season.setHotel_id(selectSeason.getKey());
                season.setSeason_type(cmb_hotel_season.getSelectedItem().toString());
                JFormattedTextField[] checkDateList = {tf_season_start, tf_season_finish};
                if (Helper.isFieldListEmpty(checkDateList)) {
                    Helper.showMsg("fill");
                } else {
                    try {

                        season.setStart_date(LocalDate.parse(tf_season_start.getText(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));
                        season.setFinish_date(LocalDate.parse(tf_season_finish.getText(), DateTimeFormatter.ofPattern("MM/dd/yyyy")));

                        result = seasonManager.save(season);
                    // an exception will be thrown if date is not in a valid format
                    } catch (DateTimeException ex) {
                        Helper.showMsg("Invalid date format");
                        return;
                    }
                }
                if (result) {
                    Helper.showMsg("done");

                    dispose();
                } else {
                    Helper.showMsg("error");
                }
            }
        });
    }

    // this method is for accept date input in specified format
    private void createUIComponents() throws ParseException {
        this.tf_season_start = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_season_start.setText("11/11/2024");
        this.tf_season_finish = new JFormattedTextField(new MaskFormatter("##/##/####"));
        this.tf_season_finish.setText("11/11/2024");
    }

}

