package business;

import core.Helper;
import dao.HotelDao;
import entity.Hotel;

import java.util.ArrayList;


public class HotelManager {
    HotelDao hotelDao = new HotelDao();

    // function that brings a hotel by its id
    public Hotel getById(int id){
        return this.hotelDao.getByID(id);
    }

    // the method that brings all hotels
    public ArrayList<Hotel> findAll() {return this.hotelDao.findAll();}

    // the method that provides necessary information for table with generic data type as Object[] so, the array list may take
    // different type of objects
    public ArrayList<Object[]> getForTable(int size,ArrayList<Hotel> hotels){
        ArrayList<Object[]> hotelList = new ArrayList<>();
        for(Hotel obj : hotels){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getName();
            rowObject[i++] = obj.getAddress();
            rowObject[i++] = obj.getMail();
            rowObject[i++] = obj.getPhone();
            rowObject[i++] = obj.getStar();
            rowObject[i++] = obj.isCar_park();
            rowObject[i++] = obj.isWifi();
            rowObject[i++] = obj.isPool();
            rowObject[i++] = obj.isFitness();
            rowObject[i++] = obj.isConcierge();
            rowObject[i++] = obj.isSpa();
            rowObject[i++] = obj.isRoom_service();
            hotelList.add(rowObject);
        }
        return hotelList;
    }

    // add hotel records to database
    public boolean save(Hotel hotel){
        if(hotel.getId()!=0){
            Helper.showMsg("error");
        }
        return this.hotelDao.save(hotel);
    }

    // delete the hotel with specific id
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " id could not found");
            return false;
        }
        return this.hotelDao.delete(id);
    }

    // function that updates the hotel record
    public boolean update(Hotel hotel){
        if (this.getById(hotel.getId()) == null){
            Helper.showMsg(hotel.getId() + " id could not found");
            return false;
        }
        return this.hotelDao.update(hotel);
    }
}
