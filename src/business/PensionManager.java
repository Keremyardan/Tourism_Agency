package business;

import core.Helper;
import dao.PensionDao;
import entity.Hotel;
import entity.Pension;
import entity.Season;
import entity.User;

import java.util.ArrayList;



public class PensionManager {
    PensionDao pensionDao = new PensionDao();

    // method that brings pension with specific id
    public Pension getById(int id){
        return this.pensionDao.getByID(id);
    }

    // method that brings all pensions
    public ArrayList<Pension> findAll() {return this.pensionDao.findAll();}

    // method that brings pensions which have specific hotel id
    public ArrayList<Pension> getPensionByOtelId(int id){return this.pensionDao.getPensionByOtelId(id);}

    // the method that provides necessary information for table with generic data type as Object[] so, the array list may take
    // different type of objects
    public ArrayList<Object[]> getForTable(int size,ArrayList<Pension> pensions){
        ArrayList<Object[]> pensionList = new ArrayList<>();
        for (Pension obj : pensions){
            int i = 0;
            Object[] rowObject = new Object[size];
            rowObject[i++] = obj.getId();
            rowObject[i++] = obj.getHotel_id();
            rowObject[i++] = obj.getPension_type();
            pensionList.add(rowObject);
        }
        return pensionList;
    }


    // send the pension record to database
    public boolean save(Pension pension){
        if(pension.getId()!=0){
            Helper.showMsg("error");
        }
        return this.pensionDao.save(pension);
    }

    // updates pension record
    public boolean update(Pension pension) {
        if (this.getById(pension.getId()) == null) {
            Helper.showMsg(pension.getId() + " id could not found");
            return false;
        }
        return this.pensionDao.update(pension);
    }

    // deletes pension record
    public boolean delete(int id){
        if(this.getById(id) == null){
            Helper.showMsg(id + " id could not found");
            return false;
        }
        return this.pensionDao.delete(id);
    }
}
