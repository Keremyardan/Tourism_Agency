package business;

import core.Helper;
import dao.ReservationDao;
import entity.Reservation;

import java.util.ArrayList;


public class ReservationManager {
    ReservationDao reservationDao = new ReservationDao();

    // function that brings reservations with specific id
    public Reservation getById(int id){return this.reservationDao.getByID(id);}

    //function that brings reservations with specific hotel id
    public ArrayList<Reservation> getReservationByOtelId(int id){return this.reservationDao.getReservationByOtelId(id);}

    // function that brings all reservations
    public ArrayList<Reservation> findAll() {return this.reservationDao.findAll();}

    // the method that provides necessary information for table with generic data type as Object[] so, the array list may take
    // different type of objects
    public ArrayList<Object[]> getForTable(int size,ArrayList<Reservation> reservations){
        ArrayList<Object[]> resList = new ArrayList<>();
        for (Reservation obj : reservations){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getRoom_id();
            rowObject[i++] = obj.getCheck_in_date();
            rowObject[i++] = obj.getCheck_out_date();
            rowObject[i++] = obj.getTotal_price();
            rowObject[i++] = obj.getGuest_count();
            rowObject[i++] = obj.getGuest_name();
            rowObject[i++] = obj.getGuest_citizen_id();
            rowObject[i++] = obj.getGuest_mail();
            rowObject[i++] = obj.getGuest_phone();

            resList.add(rowObject);
        }
        return resList;
    }

    // function that adds reservation record to db
    public boolean save(Reservation reservation){
        if(reservation.getId()!=0){
            Helper.showMsg("error");
        }
        return this.reservationDao.save(reservation);
    }


    // function that deletes reservation record to db
    public boolean delete(int id){
        if (this.getById(id)==null){
            Helper.showMsg(" reservation could not found");
            return false;
        }
        for (Reservation reservation:this.reservationDao.getByReservationId(id)){
            this.reservationDao.delete(reservation.getId());
        }
        return this.reservationDao.delete(id);
    }

    // function that updates reservation record on db

    public boolean update(Reservation reservation){
        if(this.getById(reservation.getId()) == null){
            Helper.showMsg("reservation could not found");
            return false;
        }
        return this.reservationDao.update(reservation);
    }

}


